package org.balazsbela.bugreportr.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Project implements Serializable {
	private List<Bug> bugs;
	private List<Developer> developers;	
	private String name;
	private int projectId;
	
	public Project() {
		bugs = new ArrayList<Bug>();
		developers = new ArrayList<Developer>();
	}
	
	public List<Bug> getBugs() {
		return bugs;
	}
	
	public void setBugs(List<Bug> bugs) {
		this.bugs = bugs;
	}
	
	public List<Developer> getDevelopers() {
		return developers;
	}
	
	public void setDevelopers(List<Developer> developers) {
		this.developers = developers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bugs == null) ? 0 : bugs.hashCode());
		result = prime * result + ((developers == null) ? 0 : developers.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (bugs == null) {
			if (other.bugs != null)
				return false;
		} else if (!bugs.equals(other.bugs))
			return false;
		if (developers == null) {
			if (other.developers != null)
				return false;
		} else if (!developers.equals(other.developers))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	public String toString() {
		return name;
	}
	
}
