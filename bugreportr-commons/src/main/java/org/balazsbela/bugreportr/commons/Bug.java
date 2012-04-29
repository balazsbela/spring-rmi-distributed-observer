package org.balazsbela.bugreportr.commons;

import java.io.Serializable;

public class Bug implements Serializable {
	String name;
	String desription;
	Developer assignee;
	Tester reporter;
	Boolean fixed=false;

	public Bug() {
		
	}
	
	public Bug(String name,String description) {
		this.name = name;
		this.desription = description;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesription() {
		return desription;
	}
	public void setDesription(String desription) {
		this.desription = desription;
	}
	public Developer getAssignee() {
		return assignee;
	}
	public void setAssignee(Developer assignee) {
		this.assignee = assignee;
	}
	public Tester getReporter() {
		return reporter;
	}
	public void setReporter(Tester reporter) {
		this.reporter = reporter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assignee == null) ? 0 : assignee.hashCode());
		result = prime * result + ((desription == null) ? 0 : desription.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((reporter == null) ? 0 : reporter.hashCode());
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
		Bug other = (Bug) obj;
		if (assignee == null) {
			if (other.assignee != null)
				return false;
		} else if (!assignee.equals(other.assignee))
			return false;
		if (desription == null) {
			if (other.desription != null)
				return false;
		} else if (!desription.equals(other.desription))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (reporter == null) {
			if (other.reporter != null)
				return false;
		} else if (!reporter.equals(other.reporter))
			return false;
		return true;
	}

	public Boolean getFixed() {
		return fixed;
	}

	public void setFixed(Boolean fixed) {
		this.fixed = fixed;
	}
	
	public String toString() {
		return name;
	}
}
