package com.lucky5;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;

import static com.lucky5.utils.Util.*;

/**
 * Class containing details of Application URL
 * 
 * @author Yashpal_Rawat
 *
 */
public class AppURL {
	private String url;
	private String userKey;
	private String passwordKey;
	private String userValue;
	private String passwordValue;
	private AppURLStatus status;
	private boolean bSecured;
	private final Map<String, String> customProperties = new HashMap<String, String>();
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getPasswordKey() {
		return passwordKey;
	}
	public void setPasswordKey(String passwordKey) {
		this.passwordKey = passwordKey;
	}
	public String getUserValue() {
		return userValue;
	}
	public void setUserValue(String userValue) {
		this.userValue = userValue;
	}
	
	@JsonIgnore
	public String getPasswordValue() {
		return passwordValue;
	}
	public void setPasswordValue(String passwordValue) {
		this.passwordValue = passwordValue;
	}
	
	public AppURLStatus getStatus() {
		return status;
	}
	public void setStatus(AppURLStatus status) {
		this.status = status;
	}
	public boolean isbSecured() {
		return bSecured;
	}
	public void setbSecured(boolean bSecured) {
		this.bSecured = bSecured;
	}
	/*
	 * Method for adding custom properties, this may vary application to  application
	 */
	public void addCustomProperty(String property,String value, boolean overwrite){
		if(customProperties.get(property) != null && 
				customProperties.get(property).trim().length() > 0 &&
				overwrite) {
			customProperties.put(property,value);
		}
	}
	
	public boolean isValidURL() {
		return !(bSecured ? (isBlank(getUrl()) && isBlank(getUserKey())&& isBlank(getPasswordKey())) : 
			(isBlank(getUrl())));
	}
	public Map<String, String> getCustomProperties() {
		return customProperties;
	}
	@Override
	public String toString() {
		return "AppURL [url=" + url + ", userKey=" + userKey + ", passwordKey="
				+ passwordKey + ", userValue=" + userValue + ", status="
				+ status + ", bSecured=" + bSecured + ", customProperties="
				+ customProperties + "]";
	}
}


