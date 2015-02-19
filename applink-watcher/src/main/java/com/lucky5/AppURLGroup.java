package com.lucky5;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.lucky5.utils.Util;

/**
 * URLGroup containing list of grouped URL's need to monitored.
 * It will be responsible for running background checks in configured time 
 * 
 * @author Yashpal_Rawat
 *
 */
public class AppURLGroup {
	private String name;
	private final List<AppURL> lstURL = new ArrayList<AppURL>();
	private int interval = 10;
	private Timer timer = new Timer();
	private static final Logger _LOG = Logger.getLogger(AppURLGroup.class);
	private static final Watcher watcher = new GenericWatcher();
	private boolean startFlag = false;
	
	public AppURLGroup(String name, int interval) {
		super();
		this.name = name;
		this.interval = interval;
	}
	
	public void addLink(AppURL link){
		if(!link.isValidURL()) {
			throw new IllegalArgumentException("incorrect parameters, please provide url and authentication if required");
		}
		_LOG.debug("link valid adding to active list");
		lstURL.add(link);
	}
	
	public synchronized void start() {
		if(lstURL.size() > 0) {
			_LOG.debug("starting timer");
			timer.schedule(new TimerTask(){
				@Override
				public void run() {
					_LOG.debug("task running");
					for(AppURL link : lstURL){
						watcher.watch(link);
					}
				}			
			}, interval*1000);
			startFlag = true;
		}
		else
			_LOG.debug("Not starting group as their are no configured links");
	}
	
	public synchronized void stop() {
		startFlag = false;
		timer.cancel();
	}
	
	public boolean isStarted(){
		return startFlag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AppURL> getLstURL() {
		return lstURL;
	}

	public int getInterval() {
		return interval;
	}
	
	public void setInterval(int interval) {
		this.interval = interval;
	}

	public Timer getTimer() {
		return timer;
	}

	public boolean isStartFlag() {
		return startFlag;
	}

}
