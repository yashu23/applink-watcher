package com.lucky5;

import java.util.ArrayList;
import java.util.List;

/*
 * ActiveQueue of all groups that needs to executed in regular interval
 */
public class ActiveQueue {
	private final List<AppURLGroup> lstAppURLGroup = new ArrayList<AppURLGroup>();
	private static ActiveQueue instance = null;
	private ActiveQueue(){}
	
	public static ActiveQueue getInstance(){
		if(instance == null){
			instance = new ActiveQueue();
		}
		return instance;
	}
	
	public synchronized void add(AppURLGroup group){
		lstAppURLGroup.add(group);
	}
	
	public synchronized void update(AppURLGroup group){
		
	}
	
	public void start() {
		for(AppURLGroup group : lstAppURLGroup) {
			group.start();
		}
	}
}
