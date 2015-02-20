package com.lucky5;

import java.util.ArrayList;
import java.util.Collections;
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
	
	/*
	 * single instance for active q should be created
	 */
	public static ActiveQueue getInstance(){
		if(instance == null){
			instance = new ActiveQueue();
			_LOG.debug("creating new active queue instance");
		}
		return instance;
	}
	
	
	/*
	 * add app url  group which needs to be monitored.
	 * 
	 * if app url is already being monitored throw exception, else
	 * add app url group to active queue and start the monitoring
	 */
	public synchronized int add(AppURLGroup group){
		_LOG.debug("adding new app group for monitoring [" + group.getName() + " , interval = " + 
	group.getInterval() + "]");
		if(!lstAppURLGroup.contains(group)) {
			lstAppURLGroup.add(group);
			group.start();
			return lstAppURLGroup.size() - 1;
		}
		else
			throw new IllegalStateException("app url group already being monitored");
	}
	
	/*
	 * update the group and restart it	
	 */
	public synchronized void update(int index,String name,List<AppURL> urls,int interval){
		AppURLGroup group = lstAppURLGroup.get(index);
		if(group != null) {			
			group.stop();
			_LOG.debug("original timer stopped");
			group.setName(name);
			group.getLstURL().clear();
			group.getLstURL().addAll(urls);
			group.setInterval(interval);
			group.start();
			_LOG.debug("new timer started");
		}
	}
	
	public List<AppURLGroup> getAllGroup() {
		return Collections.unmodifiableList(lstAppURLGroup);
	}
	
	public AppURLGroup getGroupByIndex(int index){
		return lstAppURLGroup.get(index);
	}
}
