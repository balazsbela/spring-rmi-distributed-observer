package org.balazsbela.bugreportr.xml;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.balazsbela.bugreportr.commons.Bug;
import org.balazsbela.bugreportr.commons.Developer;
import org.balazsbela.bugreportr.commons.Project;
import org.balazsbela.bugreportr.commons.Tester;

public class GenerateProjects {
	public static void main(String[] args) throws FileNotFoundException {		
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("developers.xml")));
		List<Developer> devs = (List<Developer>) decoder.readObject();			
		decoder.close();
		
		decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("testers.xml")));
		List<Tester> testers = (List<Tester>) decoder.readObject();			
		decoder.close();
		
		List<Project> projects = new ArrayList<Project>();
		
		Project proj = new Project();
		proj.setName("Nuclear Misile Control");
		
		List<Developer> devList1 = new ArrayList<Developer>();
		for(int i=0;i<devs.size()-2;i++) {
			devList1.add(devs.get(i));
		}
		
		proj.setDevelopers(devList1);
		proj.setBugs(new ArrayList<Bug>());
		
		projects.add(proj);
		
		proj = new Project();
		proj.setName("My Little Pony MMORPG");
		
		List<Developer> devList2 = new ArrayList<Developer>();
		for(int i=3;i<devs.size();i++) {
			devList2.add(devs.get(i));
		}
		
		proj.setDevelopers(devList2);
		proj.setBugs(new ArrayList<Bug>());
		projects.add(proj);
		
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("projects.xml")));
		encoder.writeObject(projects);
		encoder.close();
		
	}
}
