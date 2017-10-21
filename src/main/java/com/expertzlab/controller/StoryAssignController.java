package com.expertzlab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.expertzlab.service.ProjectService;
import com.expertzlab.service.UserService;
import com.expertzlab.service.StoryService;
import com.expertzlab.service.SprintService;


@Controller
public class StoryAssignController {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private StoryService storyService;
	
	@Autowired
	private SprintService sprintService;
	
	
	/*
	@RequestMapping (value = "/storyuser", method = RequestMethod.GET)
	public ModelAndView assignStoryUser(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("storyuser");
		return modelAndView;
	}*/
	
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	public StoryService getStoryService() {
		return storyService;
	}

	public void setStoryService(StoryService storyService) {
		this.storyService = storyService;
	}


	public SprintService getSprintService() {
		return sprintService;
	}

	public void setSprintService(SprintService sprintService) {
		this.sprintService = sprintService;
	}
}




