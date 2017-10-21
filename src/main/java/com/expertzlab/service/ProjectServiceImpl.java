package com.expertzlab.service;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import com.expertzlab.model.Project;
import com.expertzlab.model.Story;
import com.expertzlab.model.Role;
import com.expertzlab.model.User;
import com.expertzlab.repository.ProjectRepository;
import com.expertzlab.repository.RoleRepository;
import com.expertzlab.repository.UserRepository;
import com.expertzlab.repository.StoryRepository;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private ProjectRepository projectRepository;
	
	
	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void saveProject(Project project,User user) {
		project.getUsers().add(user);
		projectRepository.save(project);
		
		user.getProjects().add(project);
		userRepository.save(user);
	}
	
	@Override
	public void saveProject(Project project) {
		
		projectRepository.save(project);
	
	}
	

	@Override
	public List<Project> findAll() {
		
		 return projectRepository.findAll();
	}


	@Override
	public Project findById(int id) {
		
		return projectRepository.findById(id);
	}

	
	@Override
	public void deleteProject (Project project){
		
		 projectRepository.delete(project);
	}

	
}