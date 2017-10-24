package com.expertzlab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



import com.expertzlab.model.Project;

import com.expertzlab.service.ProjectService;
import com.expertzlab.service.UserService;
import com.expertzlab.util.Util;
import com.expertzlab.service.SprintService;
import com.expertzlab.service.StoryService;


@Controller
public class BacklogController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private Util util;
	
	@RequestMapping(value={ "/project/productbacklog/{id}"}, method = RequestMethod.GET)
	public ModelAndView productbacklog(@PathVariable("id") int id){
		 Project project = projectService.findById(id);
	// Project story = storyService.findAll();
	   ModelAndView modelAndView = new ModelAndView();
	  modelAndView.addObject("storys", project.getStories());
	   modelAndView.addObject("project", project);
		modelAndView.setViewName("productbacklog");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}
	
	
	@RequestMapping(value={ "/project/sprintbacklog/{id}"}, method = RequestMethod.GET)
	public ModelAndView sprintbacklog(@PathVariable("id") int id){
		 Project project = projectService.findById(id);
		 ModelAndView modelAndView = new ModelAndView();
	  modelAndView.addObject("sprints", project.getSprints());
	  modelAndView.addObject("project", project);
		modelAndView.setViewName("sprintbacklog");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}
}

	

