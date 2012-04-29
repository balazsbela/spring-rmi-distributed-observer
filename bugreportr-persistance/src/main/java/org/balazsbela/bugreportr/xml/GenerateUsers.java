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

import org.balazsbela.bugreportr.commons.Developer;
import org.balazsbela.bugreportr.commons.Tester;

public class GenerateUsers {
	public static void main(String[] args) {		
		try {
			List<Developer> devList = new ArrayList<Developer>();
			Developer d = new Developer(1,"johny","password");		
			devList.add(d);

			d = new Developer(2,"jonathan","12345");
			devList.add(d);
			
			d = new Developer(5,"tom","12345");
			devList.add(d);			

			d = new Developer(6,"ivan","12345");
			devList.add(d);			

			d = new Developer(7,"sergey","12345");
			devList.add(d);
			
			List<Tester> testerList = new ArrayList<Tester>();
			Tester t = new Tester(3,"alice","qwert");			
			testerList.add(t);

			t = new Tester(4,"conan","qwert");
			testerList.add(t);

			XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("developers.xml")));
			encoder.writeObject(devList);
			encoder.close();
			
			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("testers.xml")));
			encoder.writeObject(testerList);
			encoder.close();

			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("developers.xml")));
			List<Developer> devDeserialized = (List<Developer>) decoder.readObject();			
			decoder.close();
			
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("testers.xml")));
			List<Tester> testers = (List<Tester>) decoder.readObject();			
			decoder.close();
			
			for(Developer dev:devDeserialized) {
				System.out.println(dev.getUserId()+" "+dev.getUsername()+" "+dev.getPassword());
			}
			
			for(Tester tt:testers) {
				System.out.println(tt.getUserId()+" "+tt.getUsername()+" "+tt.getPassword());
			}
		} catch (FileNotFoundException e) {
		}
	}
}
