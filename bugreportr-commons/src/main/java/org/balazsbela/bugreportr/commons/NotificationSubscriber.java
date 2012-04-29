package org.balazsbela.bugreportr.commons;

import java.io.Serializable;

public interface NotificationSubscriber extends Serializable {
	String getCallbackUrl();
	void setCallbackUrl(String callbackUrl);
	void update(Event e);
}
