package com.lucky5;

import static org.junit.Assert.*;

import org.junit.Test;

public class ActiveQueueTest {
	public static void main(String[] args) {
		AppURL url = new AppURL();
		url.setbSecured(false);
		url.setUrl("http://ndtv.com/");
		AppURLGroup group = new AppURLGroup("Dev", 1);
		group.addLink(url);
		
		
		AppURL url2 = new AppURL();
		url2.setbSecured(false);
		url2.setUrl("http://ndtv.com/");
		AppURLGroup group2 = new AppURLGroup("Dev2", 2);
		group2.addLink(url2);
		
		ActiveQueue queue = ActiveQueue.getInstance();
		queue.add(group);
		queue.add(group2);
		queue.start();
		
	}

}
