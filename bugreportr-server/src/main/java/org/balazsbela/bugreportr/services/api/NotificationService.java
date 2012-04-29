package org.balazsbela.bugreportr.services.api;

import org.balazsbela.bugreportr.commons.Event;
import org.balazsbela.bugreportr.commons.NotificationSubscriber;

public interface NotificationService {
	void publishEvent(Event e);
	void subscribe(NotificationSubscriber ns);
	void unsubscribe(NotificationSubscriber ns);
}
