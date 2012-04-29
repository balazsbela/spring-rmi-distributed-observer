package org.balazsbela.bugreportr.repositories.file.impl;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.xml.transform.stream.StreamResult;

import org.balazsbela.bugreportr.commons.Developer;
import org.balazsbela.bugreportr.commons.Project;
import org.balazsbela.bugreportr.repositories.api.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class ProjectRepositoryImpl implements ProjectRepository {

	private List<Project> projects;
	private static final String FILENAME = "projects.xml";
	
	public ProjectRepositoryImpl() throws FileNotFoundException {
		loadProjects();
	}
	
	private void loadProjects() throws FileNotFoundException {
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(FILENAME)));
		projects = (List<Project>) decoder.readObject();			
		decoder.close();
	}
	
	@Override
	public List<Project> getProjects() {
		try {
			loadProjects();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return projects;
	}

	@Override
	public void saveProjects() {	
		XMLEncoder encoder;
		try {
			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(FILENAME)));
			encoder.writeObject(projects);
			encoder.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public Project getProject(String name) {	
		try {
			loadProjects();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Project p:projects) {
			if(p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}

	@Override
	public void saveProject(String name) {
		saveProjects();
	}

}
