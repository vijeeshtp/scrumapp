package com.expertzlab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.expertzlab.service.ProjectService;
import com.expertzlab.service.UserService;
import com.expertzlab.util.Util;

@Controller
public class ProjectAssignController {
	@Autowired
	private Util util;
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping (value = "/projectuser", method = RequestMethod.GET)
	public ModelAndView assignProjectUser(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("projectuser");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}
	
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
	
}
