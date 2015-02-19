package com.lucky5;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/*
 * ActiveQueue of all groups that needs to executed in regular interval
 */
public class ActiveQueue {
	private final List<AppURLGroup> lstAppURLGroup = new ArrayList<AppURLGroup>();
	private static ActiveQueue instance = null;
	private static final Logger _LOG = Logger.getLogger(ActiveQueue.class);
	private ActiveQueue(){}
	
	public static ActiveQueue getInstance(){
		if(instance == null){
			instance = new ActiveQueue();
			_LOG.debug("creating new active queue instance");
		}
		return instance;
	}
	
	public synchronized int add(AppURLGroup group){
		_LOG.debug("adding new app group for monitoring");
		if(!lstAppURLGroup.contains(group)) {
			lstAppURLGroup.add(group);
			group.start();
			return lstAppURLGroup.size() - 1;
		}
		return -1;
	}
	
	public synchronized void update(int index,List<AppURL> urls,int interval){
		AppURLGroup group = lstAppURLGroup.get(index);
		if(group != null) {
			group.stop();
			group.getLstURL().clear();
			group.getLstURL().addAll(urls);
			group.setInterval(interval);
			group.start();
		}
	}
	
	public void start() {
		_LOG.debug("starting all app groups");
		for(AppURLGroup group : lstAppURLGroup) {
			group.start();
		}
	}
}
