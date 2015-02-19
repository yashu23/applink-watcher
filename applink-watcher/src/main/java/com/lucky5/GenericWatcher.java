package com.lucky5;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class GenericWatcher implements Watcher {
	private static final Logger _LOG = Logger.getLogger(GenericWatcher.class);
	public boolean watch(AppURL url) {
		_LOG.debug("entering watch method");
		if(url.isValidURL()) {
			try {
				_LOG.debug("checking if link is enabled");
				if(url.getStatus() == AppURLStatus.ENABLED) {
					final WebClient webClient = new WebClient();
					
					//String strTitle = null;
					_LOG.debug("checking if link is secured");
					
					/*if(url.isbSecured()) {
						webClient.getOptions().setUseInsecureSSL(true);
						final HtmlPage page = webClient.getPage(url.getUrl());
					    final HtmlForm form = page.getFormByName(url.getCustomProperties().get("formName"));
					    final HtmlTextInput userField = form.getInputByName(url.getUserKey());
					    final HtmlPasswordInput passwordField = form.getInputByName(url.getPasswordKey());

					    // Change the value of the text field
					    userField.setValueAttribute(url.getUserValue());
					    passwordField.setValueAttribute(url.getPasswordValue());

					    // Now submit the form by clicking the button and get back the second page.
					    final HtmlPage page2 = form.click();
						strTitle = page2.getTitleText();
					}
					else {
						final HtmlPage page = webClient.getPage(url.getUrl());
						strTitle = page.getTitleText();
					}
					webClient.closeAllWindows();
					_LOG.debug("title of next page is " + strTitle);
					return strTitle.equalsIgnoreCase(url.getCustomProperties().get("expectedTitle"));*/
					TimeUnit.SECONDS.sleep(5);
					return true;
				}
			} catch (FailingHttpStatusCodeException e) {
				e.printStackTrace();
			} /*catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (ElementNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} */catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
