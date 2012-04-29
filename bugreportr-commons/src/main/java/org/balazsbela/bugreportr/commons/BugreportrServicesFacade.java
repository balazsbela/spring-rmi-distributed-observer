package org.balazsbela.bugreportr.commons;

import java.util.List;

public interface BugreportrServicesFacade {
	User login(User u);
	void publishEvent(Event e);
	void subscribe(NotificationSubscriber ns);
	void unsubscribe(NotificationSubscriber ns);
    void reportBug(Project p,Bug b,Tester t);
    void fixBug(Project p, Bug b,Developer dev);
    List<Project> getProjects(User u);
    List<Developer> getDevelopers(Project p);
    List<Bug> getBugs(Project p);
	List<Project> getProjects(Developer d);
	List<Bug> getBugs(Tester t);
}
