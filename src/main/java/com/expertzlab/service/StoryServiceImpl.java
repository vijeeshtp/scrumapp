package com.expertzlab.service;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.expertzlab.model.Project;
import com.expertzlab.model.Role;
import com.expertzlab.model.Sprint;
import com.expertzlab.model.User;
import com.expertzlab.model.Story;
import com.expertzlab.repository.ProjectRepository;
import com.expertzlab.repository.RoleRepository;
import com.expertzlab.repository.UserRepository;
import com.expertzlab.repository.StoryRepository;
import com.expertzlab.repository.SprintRepository;



@Service("storyService")
public class StoryServiceImpl implements StoryService {

	@Autowired
	StoryRepository storyRepository;
	
	@Autowired
	SprintRepository sprintRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Override
	public void saveStory(Story story,User user,Project project,Sprint sprint){
		
		story.setProject(project);
		story.setUser(user);
		story.setSprint(sprint);
		storyRepository.save(story);
		
		user.getStories().add(story);
		userRepository.save(user);
		
		project.getStories().add(story);
		projectRepository.save(project);
		
		sprint.getStories().add(story);
		sprintRepository.save(sprint);
		
		
	}


	@Override
	public void saveUser(Story story) {
		storyRepository.save(story);
	}


	@Override
	public void updateStory(Story story) {
		
		// User user=userRepository.findOne(Long.parseLong(story.getAssigned()));
		// Sprint sprint= sprintRepository.findOne(  story.getSprint().getId()); 
	
		
		 Story rStory=	storyRepository.findOne(story.getId());
		 rStory.setDescription(story.getDescription());
		 rStory.setDuration(story.getDuration());
		// rStory.setActual(story.getActual());
		 rStory.setAssigned(story.getAssigned());
		 rStory.setType(story.getType());
		 rStory.setEst_hrs(story.getEst_hrs());
		 rStory.setName(story.getName());
		 rStory.setPriority(story.getPriority());
		 rStory.setStatus(story.getStatus());
		 rStory.setProject(story.getProject());
		 rStory.setRemaining(story.getRemaining());
		 rStory.setActual(story.getActual());
		 

			if	(story.getStatus().equals("closed") ) {
				rStory.setResolvedDate(new Date());
			}
			
		if	(!story.getAssigned().equals(rStory.getUser().getId())  ) {
			User user=userRepository.findOne(Long.parseLong(story.getAssigned()));
			rStory.setUser(user);
			user.getStories().add(story);
			userRepository.save(user);		
		}
		
		
		if( story.getSprint() !=null &&  ! story.getSprint().equals(rStory.getSprint().getId())) {
			 Sprint sprint= sprintRepository.findOne(  story.getSprint().getId()); 
			 rStory.setSprint(sprint);
			 sprint.getStories().add(story);
			 sprintRepository.save(sprint);			
			 
		}
	 
		 storyRepository.save(rStory);
	
	}

	

	@Override
	public void saveSprint(Sprint sprint) {
		sprintRepository.save(sprint);
	}

	
	

	@Override
	public List<Story> findAll() {
		 return storyRepository.findAll();
	}
	
	@Override
	public Story findById(int id) {
		
		return storyRepository.findById(id);
	}

	
	@Override
	public void deleteStory(Story story){
		
		storyRepository.delete(story);
	}

	
}


