package com.lucky5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

/*
 * class for conversion between pojo and json string
 * 
 */
public class AppLinkBusiness {
	private static final Logger _LOG = Logger.getLogger(AppLinkBusiness.class);
	private static final ActiveQueue queue = ActiveQueue.getInstance();
	private static final ObjectMapper mapper = new ObjectMapper();
	
	/*
	 * this method assumes proper json string
	 */
	public String addAppGroup(final String jsonString) {
		try {
			JsonNode rootNode = mapper.readTree(jsonString);
			JsonNode nameNode = rootNode.get("name");
			JsonNode intervalNode = rootNode.get("interval");
			
			AppURLGroup group = new AppURLGroup(nameNode.asText(),intervalNode.asInt());
			
			JsonNode appurlarrayNode = rootNode.get("appurlarray");
			Iterator<JsonNode> iterator = appurlarrayNode.getElements();
			
			while(iterator.hasNext()){
				JsonNode appURLNode = iterator.next();
				
				AppURL appURL = new AppURL();
				appURL.setbSecured(appURLNode.get("secured").asBoolean());
				appURL.setPasswordKey(appURLNode.get("passkey").asText());
				appURL.setPasswordValue(appURLNode.get("password").asText());
				appURL.setUserKey(appURLNode.get("userkey").asText());
				appURL.setUserValue(appURLNode.get("uservalue").asText());
				appURL.setUrl(appURLNode.get("url").asText());
				
				if(appURLNode.get("enabled").asBoolean()) {
					appURL.setStatus(AppURLStatus.ENABLED);
				}
				else {
					appURL.setStatus(AppURLStatus.DISABLED);
				}
				group.getLstURL().add(appURL);
				
			}		
			queue.add(group);
		} catch (Exception ex) {
			_LOG.error("exception raised while adding group",ex);
			throw new RuntimeException(ex);
		}
		return getActiveQueueAsJson();
	}
	
	
	/*
	 * return active queue as json string
	 */
	public String getActiveQueueAsJson() {
		try {
			return mapper.writeValueAsString(queue);
		} catch (Exception ex) {
			_LOG.error("exception raised while adding group",ex);
			throw new RuntimeException(ex);
		}
	}
	
	/*
	 * return json string for app group
	 */
	public String getAppGroup(int index){
		try {
			return mapper.writeValueAsString(queue.getGroupByIndex(index));
		} catch (Exception ex) {
			_LOG.error("exception raised while adding group",ex);
			throw new RuntimeException(ex);
		}
	}
	
	
	/*
	 *  update app group, restart it 
	 */
	public String updateAppGroup(String jsonString,int index) {
		try {
			JsonNode rootNode = mapper.readTree(jsonString);
			JsonNode nameNode = rootNode.get("name");
			JsonNode intervalNode = rootNode.get("interval");
			
			List<AppURL> lstURL = new ArrayList<AppURL>();
			JsonNode appurlarrayNode = rootNode.get("appurlarray");
			Iterator<JsonNode> iterator = appurlarrayNode.getElements();
			
			while(iterator.hasNext()){
				JsonNode appURLNode = iterator.next();				
				AppURL appURL = new AppURL();
				appURL.setbSecured(appURLNode.get("secured").asBoolean());
				appURL.setPasswordKey(appURLNode.get("passkey").asText());
				appURL.setPasswordValue(appURLNode.get("passvalue").asText());
				appURL.setUserKey(appURLNode.get("userkey").asText());
				appURL.setUserValue(appURLNode.get("uservalue").asText());
				appURL.setUrl(appURLNode.get("url").asText());
				
				if(appURLNode.get("enabled").asBoolean()) {
					appURL.setStatus(AppURLStatus.ENABLED);
				}
				else {
					appURL.setStatus(AppURLStatus.DISABLED);
				}
				lstURL.add(appURL);
			}		
			queue.update(index, nameNode.asText(), lstURL, intervalNode.asInt());
		} catch (Exception ex) {
			_LOG.error("exception raised while adding group",ex);
			throw new RuntimeException(ex);
		}
		return getActiveQueueAsJson();
	}
}
