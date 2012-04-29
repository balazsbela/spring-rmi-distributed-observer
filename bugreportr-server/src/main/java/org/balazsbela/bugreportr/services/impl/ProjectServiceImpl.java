package org.balazsbela.bugreportr.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.balazsbela.bugreportr.commons.Bug;
import org.balazsbela.bugreportr.commons.BugFixEvent;
import org.balazsbela.bugreportr.commons.BugreportEvent;
import org.balazsbela.bugreportr.commons.Developer;
import org.balazsbela.bugreportr.commons.Project;
import org.balazsbela.bugreportr.commons.Tester;
import org.balazsbela.bugreportr.commons.User;
import org.balazsbela.bugreportr.repositories.api.ProjectRepository;
import org.balazsbela.bugreportr.services.api.NotificationService;
import org.balazsbela.bugreportr.services.api.ProjectService;

public class ProjectServiceImpl implements ProjectService {
	ProjectRepository projectRepository;
	NotificationService notificationService;

	public void reportBug(Project p, Bug b,Tester tester) {
		Project proj = projectRepository.getProject(p.getName());
		if (!proj.getBugs().contains(b)) {
			proj.getBugs().add(b);
		}
		projectRepository.saveProject(p.getName());
		
		notificationService.publishEvent(new BugreportEvent());
	}

	public void fixBug(Project p, Bug b,Developer dev) {
		Project proj = projectRepository.getProject(p.getName());

		for (Bug bug : proj.getBugs()) {
			if (bug.getName().equals(b.getName())) {
				bug.setAssignee(dev);
				bug.setFixed(true);
			}
		}
		
		projectRepository.saveProject(p.getName());
		notificationService.publishEvent(new BugFixEvent());
	}

	public List<Project> getProjects(User u) {
		if(u.getClass().equals(Tester.class)) {
			return projectRepository.getProjects();
		}
		else {
			List<Project> projects = new ArrayList<Project>();
			for(Project p:projectRepository.getProjects()) {
				Developer d = (Developer) u;
				if(p.getDevelopers().contains(d)) {
					projects.add(p);
				}
			}
			return projects;
		}
	}

	public List<Developer> getDevelopers(Project p) {
		Project proj = projectRepository.getProject(p.getName());
		return proj.getDevelopers();
	}

	public List<Bug> getBugs(Project p) {
		Project proj = projectRepository.getProject(p.getName());
		return proj.getBugs();
	}

	public void setProjectRepository(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

}
