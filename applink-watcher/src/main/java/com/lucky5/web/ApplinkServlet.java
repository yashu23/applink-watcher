package com.lucky5.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import com.lucky5.ActiveQueue;
import com.lucky5.AppLinkBusiness;
import com.lucky5.AppURL;
import com.lucky5.AppURLGroup;
import com.lucky5.AppURLStatus;

@WebServlet(urlPatterns = { "/app" })
public class ApplinkServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger _LOG = Logger.getLogger(ApplinkServlet.class);
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
			_LOG.debug("inside add group method");
			String value = req.getParameter("jsonvalue");
			String result = facade.addAppGroup(value);
			resp.getWriter().println(result);
		}
		else if("updategroup".equalsIgnoreCase(method)) {
			
		}
		else if("viewupdate".equalsIgnoreCase(method)) {
			
		}
		else {
			String result = facade.getActiveQueueAsJson();
			resp.getWriter().println(result);
		}
	}
}
