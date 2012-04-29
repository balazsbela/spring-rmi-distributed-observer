package org.balazsbela.bugreportr.controller;

import java.rmi.RemoteException;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.balazsbela.bugreportr.commons.Bug;
import org.balazsbela.bugreportr.commons.BugFixEvent;
import org.balazsbela.bugreportr.commons.BugreportEvent;
import org.balazsbela.bugreportr.commons.BugreportrServicesFacade;
import org.balazsbela.bugreportr.commons.Developer;
import org.balazsbela.bugreportr.commons.Event;
import org.balazsbela.bugreportr.commons.NotificationSubscriber;
import org.balazsbela.bugreportr.commons.Project;
import org.balazsbela.bugreportr.commons.Tester;
import org.balazsbela.bugreportr.commons.User;
import org.balazsbela.bugreportr.gui.BugBrowser;
import org.balazsbela.bugreportr.gui.BugBrowserImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.remoting.rmi.RmiServiceExporter;

public class MainController {
	RmiServiceExporter serviceExporter;
	BugreportrServicesFacade services;
	NotificationSubscriber notificationSubscriber;
	User currentUser;
	BugBrowser bugBrowser;

	private static MainController instance;

	/*
	 * Again, RMI is stateless, and if spring manages it's lifecycle, it will be
	 * instantiated again and again and again, without retaining state.
	 */
	public static MainController getInstance() {
		if (instance == null) {
			instance = new MainController();
		}
		return instance;
	}

	private MainController() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		notificationSubscriber = (NotificationSubscriber) ctx.getBean("notificationSubscriberService");
		services = (BugreportrServicesFacade) ctx.getBean("bugreportrServices");
	}

	// Constants:
	private static final String registryHost = "localhost";
	private static final int registryPort = 1099;

	Log log = LogFactory.getLog(getClass());

	public void login(String username, String password) throws RemoteException {
		User u = new User(0, username, password);
		log.debug("Controller" + u.getUsername() + " " + u.getPassword());
		User returnedUser = services.login(u);
		if (returnedUser != null) {
			log.debug("Login successful!");
			log.debug("Returned user" + u.getUserId() + " " + u.getUsername());
			startRMIService(username);
			MainController.getInstance().currentUser = returnedUser;
			services.subscribe(notificationSubscriber);
		} else {
			throw new IllegalArgumentException("Invalid credentials!");
		}
	}

	public void startRMIService(String username) throws RemoteException {
		serviceExporter = new RmiServiceExporter();
		serviceExporter.setServiceName("NotificationService-" + username);
		notificationSubscriber.setCallbackUrl("rmi://" + registryHost + ":" + registryPort + "/"
				+ "NotificationService-" + username);
		serviceExporter.setServiceInterface(NotificationSubscriber.class);
		// serviceExporter.setRegistryHost(registryHost);
		serviceExporter.setRegistryPort(registryPort);
		serviceExporter.setService(notificationSubscriber);
		serviceExporter.prepare();

		log.info("Published RMI Notification service at:" + "rmi://" + registryHost + ":" + registryPort + "/"
				+ "NotificationService-" + username);
	}

	public void setServices(BugreportrServicesFacade services) {
		this.services = services;
	}

	public void setNotificationSubscriber(NotificationSubscriber notificationSubscriber) {
		this.notificationSubscriber = notificationSubscriber;
	}

	public List<Project> getProjects() {
		return services.getProjects(this.currentUser);
	}

	public String getLabel() {
		if (currentUser.getClass().equals(Developer.class)) {
			return "Browse Bugs";
		} else {
			return "Report bug!";
		}
	}

	public void reportBug(String bugName, String bugDescription, String projectName) {
		Bug b = new Bug(bugName, bugDescription);
		Project p = new Project();
		p.setName(projectName);

		services.reportBug(p, b, ((Tester) currentUser));
	}

	public void handleEvent(Event e) {
		log.info("Handling event:" + e.toString());
		if (currentUser.getClass().equals(Tester.class)) {
			// We don't need to update
		} else {
			if (e.getClass().equals(BugFixEvent.class)) {
				if (bugBrowser != null) {
					bugBrowser.loadBugs();
				}
			} else {
				if (e.getClass().equals(BugreportEvent.class)) {
					if (bugBrowser != null) {
						bugBrowser.loadBugs();
					}
				}
			}
		}

	}

	public List<Bug> getBugs(String projectName) {
		Project p = new Project();
		p.setName(projectName);
		return services.getBugs(p);
	}

	public void fixBug(Bug b, String projectName) {
		Project p = new Project();
		p.setName(projectName);
		services.fixBug(p, b, ((Developer) currentUser));
	}

	public BugBrowser getBugBrowser() {
		return bugBrowser;
	}

	public void setBugBrowser(BugBrowser bugBrowser) {
		this.bugBrowser = bugBrowser;
	}

	public String getUsername() {
		return currentUser.getUsername();
	}

	public void unsubscribe() {
		services.unsubscribe(notificationSubscriber);		
	}
	
	public void notifyUI() {
		
	}
}
