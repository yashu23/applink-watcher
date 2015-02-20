package com.lucky5.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.lucky5.ActiveQueue;
import com.lucky5.AppURL;
import com.lucky5.AppURLGroup;
import com.lucky5.AppURLStatus;

@WebServlet(urlPatterns = { "/app" })
public class ApplinkServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final ActiveQueue queue = ActiveQueue.getInstance();
	private static final Logger _LOG = Logger.getLogger(ApplinkServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		String strURLList = req.getParameter("str");
		String strName = req.getParameter("name");
		int interval = Integer.parseInt(req.getParameter("interval"));
		
		if("addgroup".equalsIgnoreCase(method)) {
			_LOG.debug("inside add group method");
			
			AppURLGroup group = new AppURLGroup(strName, interval);
			for(String str : strURLList.split("#")) {
				
				String []strAppURL = str.split(",");
				String url = strAppURL[0];
				String secured = strAppURL[1];
				String enabled = strAppURL[2];
				String userkey = strAppURL[3];
				String uservalue = strAppURL[4];
				String passkey = strAppURL[5];
				String passvalue = strAppURL[6];
				
				AppURL appURL = new AppURL();
				appURL.setbSecured(Boolean.getBoolean(secured));
				appURL.setPasswordKey(passkey);
				appURL.setPasswordValue(passvalue);
				appURL.setUserKey(userkey);
				appURL.setUserValue(uservalue);
				appURL.setUrl(url);
				if("true".equalsIgnoreCase(enabled)) {
					appURL.setStatus(AppURLStatus.ENABLED);
				}
				else {
					appURL.setStatus(AppURLStatus.DISABLED);
				}
				group.getLstURL().add(appURL);				
			}
			
			
			StringBuffer strb = new StringBuffer();
			for(AppURLGroup group2 : queue.getAllGroup()) {				
				strb.append(group2.getName());
				strb.append("@#");
				strb.append(group2.getInterval());
				strb.append("@#");
				for(AppURL url : group2.getAllURL()) {
					strb.append(url.getUrl());
					strb.append(",");
					strb.append(url.isbSecured());
					strb.append(",");
					strb.append(url.getStatus().ordinal());
					strb.append(",");
					strb.append(url.getUserKey());
					strb.append(",");
					strb.append(url.getUserValue());
					strb.append(",");
					strb.append(url.getPasswordKey());
				}
				strb.append("--");
			}
			resp.getWriter().println(strb.toString());
		}
		else if("updategroup".equalsIgnoreCase(method)) {
			
		}
		else if("viewupdate".equalsIgnoreCase(method)) {
			
		}
		else {
			StringBuffer strb = new StringBuffer();
			for(AppURLGroup group : queue.getAllGroup()) {				
				strb.append(group.getName());
				strb.append("@#");
				strb.append(group.getInterval());
				strb.append("@#");
				for(AppURL url : group.getAllURL()) {
					strb.append(url.getUrl());
					strb.append(",");
					strb.append(url.isbSecured());
					strb.append(",");
					strb.append(url.getStatus().ordinal());
					strb.append(",");
					strb.append(url.getUserKey());
					strb.append(",");
					strb.append(url.getUserValue());
					strb.append(",");
					strb.append(url.getPasswordKey());
				}
				strb.append("--");
			}
		}
	}
}
