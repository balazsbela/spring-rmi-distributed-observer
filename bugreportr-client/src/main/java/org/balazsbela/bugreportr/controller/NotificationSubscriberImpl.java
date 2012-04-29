package org.balazsbela.bugreportr.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.balazsbela.bugreportr.commons.Event;
import org.balazsbela.bugreportr.commons.NotificationSubscriber;

public class NotificationSubscriberImpl implements NotificationSubscriber {
	private long port = 2000;
	private String host = "localhost";	
	private Log log = LogFactory.getLog(getClass());
	private String callbackUrl="";
	
	@Override
	public void update(Event e) {
		log.info("Update called!");
		log.info(e.getClass().toString()+" event received!");
		
		MainController.getInstance().handleEvent(e);
	}

	@Override
	public String getCallbackUrl() {
		//"rmi://localhost:1099/HelloService		
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((callbackUrl == null) ? 0 : callbackUrl.hashCode());
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime * result + ((log == null) ? 0 : log.hashCode());
		result = prime * result + (int) (port ^ (port >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotificationSubscriberImpl other = (NotificationSubscriberImpl) obj;
		if (callbackUrl == null) {
			if (other.callbackUrl != null)
				return false;
		} else if (!callbackUrl.equals(other.callbackUrl))
			return false;
		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		if (log == null) {
			if (other.log != null)
				return false;
		} else if (!log.equals(other.log))
			return false;
		if (port != other.port)
			return false;
		return true;
	}

	
}
