package com.lucky5.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

//import org.apache.log4j.Logger;

import com.lucky5.AppLinkBusiness;

public class AppLinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger _LOG = Logger.getLogger(AppLinkServlet.class);
	private static final AppLinkBusiness facade = new AppLinkBusiness();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		
		if("addgroup".equalsIgnoreCase(method)) {
			//_LOG.debug("inside add group method");
			String value = req.getParameter("jsonvalue");
			String result = facade.addAppGroup(value);
			_LOG.debug("return json :: " + result);
			resp.getWriter().println(result);
		}
		else if("updategroup".equalsIgnoreCase(method)) {
			int index = Integer.parseInt(req.getParameter("index"));
			String value = req.getParameter("jsonvalue");
			resp.getWriter().println(facade.updateAppGroup(value, index));
		}
		else if("viewupdate".equalsIgnoreCase(method)) {
			int index = Integer.parseInt(req.getParameter("index"));
			resp.getWriter().println(facade.getAppGroup(index));
		}
		else {
			String result = facade.getActiveQueueAsJson();
			resp.getWriter().println(result);
		}
	}
}
