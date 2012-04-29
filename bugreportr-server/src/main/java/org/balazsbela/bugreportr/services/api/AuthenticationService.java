package org.balazsbela.bugreportr.services.api;

import org.balazsbela.bugreportr.commons.User;

public interface AuthenticationService {
	User login(User u);
}
