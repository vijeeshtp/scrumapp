package com.expertzlab.service;

import java.util.List;
import java.util.Set;

import com.expertzlab.model.Story;
import com.expertzlab.model.User;
import com.expertzlab.model.Project;
import com.expertzlab.model.Sprint;

public interface StoryService {
	
	public List<Story> findAll();
	
	public void saveStory(Story story,User user,Project project, Sprint sprint );

	public void saveSprint(Sprint sprint);
	public void saveUser(Story user);
	public Story findById(int Id);
	public void deleteStory (Story story);
	public void updateStory(Story story) ;

}
