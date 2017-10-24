package com.expertzlab.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.expertzlab.model.Project;
import com.expertzlab.service.ProjectService;

import com.expertzlab.model.User;
import com.expertzlab.service.UserService;
import com.expertzlab.util.Util;
import com.expertzlab.model.Story;
import com.expertzlab.service.StoryService;

import com.expertzlab.model.Sprint;
import com.expertzlab.service.SprintService;



@Controller

public class StoryController {

	@Autowired
	private Util util;
	

	@Autowired
	private StoryService storyService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SprintService sprintService;
	
	
	/*@RequestMapping (value = "/storyuser", method = RequestMethod.GET)
	public ModelAndView assignStoryUser(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("storyuser");
		return modelAndView;
	}
		*/
		
	@RequestMapping(value="/story", method = RequestMethod.GET)
	
	public ModelAndView story(){
		ModelAndView modelAndView = new ModelAndView();
		Story story =new Story();
		modelAndView.addObject("story", story);
		modelAndView.setViewName("story");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}
	
	

	@RequestMapping("/story/delete/{id}")
	
    public ModelAndView deleteStory(@PathVariable("id") int id) {
        Story story = storyService.findById(id);
        int   projectId= story.getProject().getId();
        storyService.deleteStory(story);
        ModelAndView modelAndView = new ModelAndView();
        
        Story s =new Story();
        Project project= projectService.findById(projectId);
        s.setProject(project);
        
		modelAndView.addObject("story", s);
		modelAndView.addObject("storys", project.getStories());
		modelAndView.setViewName("story");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
    }
	
	
	
	
	@RequestMapping("/project/createstory/{id}")
	
    public ModelAndView createStory(@PathVariable("id") int id) {
        Story story =new Story();
        Project project= projectService.findById(id);
        story.setProject(project);
        ModelAndView modelAndView = new ModelAndView();
      //  modelAndView.addObject("successMessage", "story has been created successfully");
		modelAndView.addObject("story", story);
		modelAndView.setViewName("story");
		modelAndView.addObject("storys", project.getStories());
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
    }
	
	
	
	@RequestMapping("/story/create")
	
    public ModelAndView createStorysave(  Story story) {
      int projectid= story.getProject().getId();
     Project project= projectService.findById(projectid);
    		  User user=userService.findById(Long.parseLong(story.getAssigned()));
    		  int sprintid= story.getSprint().getId(); 
    		Sprint sprint=sprintService.findById(sprintid);
    			storyService.saveStory(story, user, project,sprint);	  
    			ModelAndView modelAndView = new ModelAndView();
    			modelAndView.addObject("successMessage", "story has been created successfully");
    			modelAndView.addObject("storys", project.getStories());
		modelAndView.addObject("story", story);
		modelAndView.setViewName("story");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
    }
	
	
	@RequestMapping(value = "/story/update", method = RequestMethod.POST)
	public ModelAndView updateStory(@Valid Story story, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		   int projectid= story.getProject().getId();
		     Project project= projectService.findById(projectid);
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("story-edit");
			modelAndView.addObject("storys", storyService.findAll());
		
		} else {
			storyService.updateStory( story);
			modelAndView.addObject("successMessage", "Story has been updated successfully");
			modelAndView.setViewName("story-edit");
			modelAndView.addObject("storys", project.getStories());
		}
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}
	
	
		@RequestMapping("/story/edit/{id}")
	    public ModelAndView editView(@PathVariable("id") int id) {
	      Story story = storyService.findById(id);
	      
	        ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("story", story);
			modelAndView.addObject("storys", story.getProject().getStories());
			
			modelAndView.addObject("users", userService.findAll());
			modelAndView.addObject("projects", projectService.findAll());
			modelAndView.setViewName("story-edit");
			modelAndView.addObject("role", util.getRole());
			modelAndView.addObject("enabled",  util.getRole().equals("SMASTER"));
			return modelAndView;
	    }
		
	
}
	