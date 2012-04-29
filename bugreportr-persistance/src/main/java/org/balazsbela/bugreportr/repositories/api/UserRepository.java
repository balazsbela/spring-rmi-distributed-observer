package org.balazsbela.bugreportr.repositories.api;

import java.util.List;

import org.balazsbela.bugreportr.commons.User;

public interface UserRepository {
	void saveUsers();
	List<User> getUsers();
	void saveUser(long userId);
	User getUser(long userId);
}	
