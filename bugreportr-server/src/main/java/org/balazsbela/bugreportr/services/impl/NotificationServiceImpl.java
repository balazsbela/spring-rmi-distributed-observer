package org.balazsbela.bugreportr.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.balazsbela.bugreportr.commons.Event;
import org.balazsbela.bugreportr.commons.NotificationSubscriber;
import org.balazsbela.bugreportr.services.api.NotificationService;
import org.balazsbela.bugreportr.subscribers.SubscriberManager;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.remoting.RemoteLookupFailureException;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;


public class NotificationServiceImpl implements NotificationService {
	private Log log = LogFactory.getLog(getClass());
	
	//One SubscriberManager to rule them all.
	SubscriberManager subscriberManager = SubscriberManager.getInstance();;
	
	public NotificationServiceImpl() {
		log.debug("Instantiated NotifactionService");
	}
	
	public void publishEvent(Event e) {				
		try {
			for(NotificationSubscriber service:subscriberManager.getSubscribers()) {
				service.update(e);
				log.debug("Sending update command!");
			}
		} catch (RemoteLookupFailureException exc) {
			log.debug("Can't access!");
		}

	}

	public void unsubscribe(NotificationSubscriber ns) {		
		subscriberManager.removeSubscriber(ns);
	}
	
	public void subscribe(NotificationSubscriber ns) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		RmiProxyFactoryBean rmiProxy = new RmiProxyFactoryBean();
		rmiProxy.setServiceInterface(NotificationSubscriber.class);
		rmiProxy.setServiceUrl(ns.getCallbackUrl());		
		rmiProxy.setLookupStubOnStartup(false);
		NotificationSubscriber service = (NotificationSubscriber) ProxyFactory.getProxy(rmiProxy.getServiceInterface(),rmiProxy);
		rmiProxy.prepare();
		subscriberManager.addSubscriber(service);
		
		log.info("Added new subscriber with:"+ns.getCallbackUrl());
		log.info("Current list of subscribers:");
		for(NotificationSubscriber subs : subscriberManager.getSubscribers()) {
			log.info(subs.getCallbackUrl());
		}
				
	}

	public void setSubscriberManager(SubscriberManager subscriberManager) {
		this.subscriberManager = subscriberManager;
	}

}
