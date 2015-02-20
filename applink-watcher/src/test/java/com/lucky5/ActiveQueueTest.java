package com.lucky5;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ActiveQueueTest {
	public static void main(String[] args) {
		
		AppURL url = new AppURL();		
		url.setbSecured(false);
		url.setUrl("http://ndtv.com/");
		url.setStatus(AppURLStatus.ENABLED);
		
		AppURLGroup group = new AppURLGroup("Dev", 1);
		group.addLink(url);
		
		
		AppURL url2 = new AppURL();
		url2.setbSecured(false);
		url2.setUrl("http://ndtv.com/");
		url2.setStatus(AppURLStatus.ENABLED);
		
		AppURLGroup group2 = new AppURLGroup("Dev2", 2);
		group2.addLink(url2);
		
		ActiveQueue queue = ActiveQueue.getInstance();
		int grp1 = queue.add(group);
		int grp2 = queue.add(group2);
		
		
		try {
			TimeUnit.SECONDS.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<AppURL> urls = new ArrayList<AppURL>();
		AppURL newurl = new AppURL();
		newurl.setStatus(AppURLStatus.ENABLED);
		newurl.setUrl("www.test.com");
		urls.add(newurl);
		
		queue.update(grp2, "Test2", urls, 1);
		
		//queue.start();
		
	}

}
