package org.balazsbela.bugreportr.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.balazsbela.bugreportr.commons.User;
import org.balazsbela.bugreportr.repositories.api.UserRepository;
import org.balazsbela.bugreportr.services.api.AuthenticationService;

public class AuthenticationServiceImpl implements AuthenticationService {	

	UserRepository userRepository;
	private Log log = LogFactory.getLog(getClass());

	public User login(User u) {
		List<User> users = userRepository.getUsers();
		for (User user : users) {
			//log.debug("User:"+user.getUsername()+" "+user.getPassword());
			if(user.getUsername().equals(u.getUsername())) {
				if(user.getPassword().equals(u.getPassword())) {
					return user;
				}
			}
		}
		return null;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

}
