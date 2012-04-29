package org.balazsbela.bugreportr.repositories.file.impl;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.balazsbela.bugreportr.commons.Developer;
import org.balazsbela.bugreportr.commons.Tester;
import org.balazsbela.bugreportr.commons.User;
import org.balazsbela.bugreportr.repositories.api.UserRepository;

public class UserRepositoryImpl implements UserRepository {
	private Log log = LogFactory.getLog(getClass());
	
	List<User> users = new ArrayList<User>();
	
	@Override
	public void saveUsers() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getUsers() {		
		try {
			users.clear();
//			InputStream is = ClassLoader.getSystemResourceAsStream("users.in");
//			BufferedReader br = new BufferedReader(new InputStreamReader(is));
//			String line;
//			 while ((line = br.readLine()) != null)   {							
//				String[] sList = line.split(";");
//				int userId = Integer.parseInt(sList[0]);
//				if(sList[1].equals("developer")) {
//					Developer dev = new Developer();
//					dev.setUserId(Integer.parseInt(sList[0]));
//					dev.setUsername(sList[2]);
//					dev.setPassword(sList[3]);
//					users.add(dev);
//				}
//				else {
//					Tester tester = new Tester();
//					tester.setUserId(Integer.parseInt(sList[0]));
//					tester.setUsername(sList[2]);
//					tester.setPassword(sList[3]);
//					users.add(tester);
//				}
//				
//			}
//			br.close();
//			is.close();

			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("developers.xml")));
			List<Developer> devs = (List<Developer>) decoder.readObject();			
			decoder.close();
			
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("testers.xml")));
			List<Tester> testers = (List<Tester>) decoder.readObject();			
			decoder.close();
			
			users.addAll(devs);
			users.addAll(testers);
			
			for(User u:users) {
				log.debug("Read user:"+u.getUserId()+" "+u.getUsername()+" "+u.getPassword());
			}
		} catch (IOException e) {
			log.debug("Problem reading files" + e);
		}

		return users;
	}

	@Override
	public void saveUser(long userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getUser(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
