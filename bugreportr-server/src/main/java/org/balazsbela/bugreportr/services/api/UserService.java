package org.balazsbela.bugreportr.services.api;

import java.util.List;

import org.balazsbela.bugreportr.commons.Bug;
import org.balazsbela.bugreportr.commons.Developer;
import org.balazsbela.bugreportr.commons.Project;
import org.balazsbela.bugreportr.commons.Tester;

public interface UserService {
	List<Project> getProjects(Developer d);
	List<Bug> getBugs(Tester t);
}
