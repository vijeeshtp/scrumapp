package com.expertzlab.service;
import java.util.List;
import java.util.Set;

import com.expertzlab.model.Project;
import com.expertzlab.model.Story;
import com.expertzlab.model.User;

public interface ProjectService {
	public List<Project> findAll();
	public void saveProject(Project project,User user);
	public void saveProject(Project project);
	public Project findById(int Id); 
	public void deleteProject (Project project);
}