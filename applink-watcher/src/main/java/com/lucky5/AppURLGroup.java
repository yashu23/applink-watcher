package com.lucky5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
	private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(100);
	private ScheduledFuture<?> handle;
	private static final Logger _LOG = Logger.getLogger(AppURLGroup.class);
	private static final Watcher watcher = new GenericWatcher();
	
	public AppURLGroup(String name, int interval) {
		super();
		this.name = name;
		this.interval = interval;
	}
	
	public void addLink(AppURL link){
		if(!link.isValidURL()) {
			throw new IllegalArgumentException("incorrect parameters, "
					+ "please provide url and authentication if required");
		}
		_LOG.debug("link valid adding to active list");
		lstURL.add(link);
	}
	
	public synchronized void start() {
		if(lstURL.size() > 0) {
			_LOG.debug("starting timer with interval " + interval);
			if(handle == null) {
				handle = executor.scheduleAtFixedRate(new Runnable(){
	
					public void run() {
						_LOG.debug("task running");
						for(AppURL link : lstURL){
							watcher.watch(link);
						}					
					}
					
				}, 0, interval, TimeUnit.MINUTES);
			}			
		}
		else
			_LOG.debug("Not starting group as their are no configured links");
	}
	
	public synchronized void stop() {
		handle.cancel(false);
		handle = null;		
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
	
	public List<AppURL> getAllURL() {
		return Collections.unmodifiableList(lstURL);
	}
}
