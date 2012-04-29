package org.balazsbela.bugreportr.subscribers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.balazsbela.bugreportr.commons.NotificationSubscriber;

public class SubscriberManager {
	private List<NotificationSubscriber> subscribers = new ArrayList<NotificationSubscriber>();
	private Log log = LogFactory.getLog(getClass());
	
	private static SubscriberManager instance;
	
	/*
	 * I need this to be singleton, otherwise NotificationService 
	 * keeps instantiating it.
	 */
	public static SubscriberManager getInstance() {
		if(instance==null) {
			instance = new SubscriberManager();
		}
		return instance;
	}
	
	private SubscriberManager() {
		log.debug("Instantiating SubscriberManager!");
	}
	
	public void addSubscriber(NotificationSubscriber service) {
		subscribers.add(service);		
	}

	public List<NotificationSubscriber> getSubscribers() {
		return subscribers;
	}
	
	public void removeSubscriber(NotificationSubscriber ns) {
		List<NotificationSubscriber> subscribers2 = new ArrayList<NotificationSubscriber>();

		subscribers.remove(ns);
		log.debug("Removing subscriber:"+ns.getCallbackUrl());
		log.debug("Remaining:");
		for(NotificationSubscriber subs:subscribers) {
			if(!subs.getCallbackUrl().equals(ns.getCallbackUrl())) {
				subscribers2.add(subs);
			}
		}
		
		subscribers.clear();
		subscribers.addAll(subscribers2);
		log.debug("Remaining:");
		for(NotificationSubscriber subs : subscribers) {
			log.debug(subs.getCallbackUrl());
		}
	}
}
