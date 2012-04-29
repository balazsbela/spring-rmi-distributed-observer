package org.balazsbela.bugreportr.services.impl;

import java.util.List;

import org.balazsbela.bugreportr.commons.Bug;
import org.balazsbela.bugreportr.commons.BugreportrServicesFacade;
import org.balazsbela.bugreportr.commons.Developer;
import org.balazsbela.bugreportr.commons.Event;
import org.balazsbela.bugreportr.commons.NotificationSubscriber;
import org.balazsbela.bugreportr.commons.Project;
import org.balazsbela.bugreportr.commons.Tester;
import org.balazsbela.bugreportr.commons.User;
import org.balazsbela.bugreportr.services.api.AuthenticationService;
import org.balazsbela.bugreportr.services.api.NotificationService;
import org.balazsbela.bugreportr.services.api.ProjectService;
import org.balazsbela.bugreportr.services.api.UserService;

public class BugreportrServicesFacadeImpl implements BugreportrServicesFacade {
	private AuthenticationService authenticationService;
	private ProjectService projectService;
	private UserService userService;
	private NotificationService notificationService;
	
	public User login(User u) {
		return authenticationService.login(u);
	}

	public void publishEvent(Event e) {
		notificationService.publishEvent(e);
	}

	public void subscribe(NotificationSubscriber ns) {
		notificationService.subscribe(ns);
	}

	public void reportBug(Project p,Bug b,Tester t) {
		projectService.reportBug(p,b,t);
	}

	public void fixBug(Project p, Bug b,Developer dev) {
		projectService.fixBug(p,b,dev);
	}

	public List<Project> getProjects(User u) {
		return projectService.getProjects(u);
	}

	public List<Developer> getDevelopers(Project p) {
		return projectService.getDevelopers(p);
	}

	public List<Bug> getBugs(Project p) {
		return projectService.getBugs(p);
	}

	public List<Project> getProjects(Developer d) {
		return userService.getProjects(d);
	}

	public List<Bug> getBugs(Tester t) {
		return userService.getBugs(t);
	}

	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public NotificationService getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public void unsubscribe(NotificationSubscriber ns) {
		notificationService.unsubscribe(ns);		
	}

}
