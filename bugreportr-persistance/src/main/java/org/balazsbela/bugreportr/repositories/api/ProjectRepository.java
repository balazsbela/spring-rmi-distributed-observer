package org.balazsbela.bugreportr.repositories.api;

import java.util.List;

import org.balazsbela.bugreportr.commons.*;

public interface ProjectRepository {
	List<Project> getProjects();
	void saveProjects();
	
	Project getProject(String name);
	void saveProject(String name);
}
