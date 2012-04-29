package org.balazsbela.bugreportr.services.api;

import java.util.List;

import org.balazsbela.bugreportr.commons.Bug;
import org.balazsbela.bugreportr.commons.Developer;
import org.balazsbela.bugreportr.commons.Project;
import org.balazsbela.bugreportr.commons.Tester;
import org.balazsbela.bugreportr.commons.User;


public interface ProjectService {

    void reportBug(Project p,Bug b,Tester t);
    void fixBug(Project p, Bug b,Developer dev);
    List<Project> getProjects(User u);
    List<Developer> getDevelopers(Project p);
    List<Bug> getBugs(Project p);

}
