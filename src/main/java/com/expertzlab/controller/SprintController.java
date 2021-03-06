package com.expertzlab.controller;

import java.util.Date;
import java.util.Set;

import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.expertzlab.model.Project;
import com.expertzlab.model.Sprint;
import com.expertzlab.model.Story;
import com.expertzlab.model.User;
import com.expertzlab.service.ProjectService;
import com.expertzlab.service.UserService;
import com.expertzlab.util.Util;
import com.expertzlab.service.SprintService;

@Controller ("SprintController")

public class SprintController {
	
	@Autowired
	private Util util;
	
	@Autowired
	private ProjectService projectService;
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SprintService sprintService;
	
	
	@RequestMapping(value={ "/project/createsprint/{id}"}, method = RequestMethod.GET)
	public ModelAndView sprint(@PathVariable("id") int id){
		
	
		
		Project project = projectService.findById(id);
		projectService.saveProject(project);
		Sprint sprint=new Sprint();
		sprint.setProject(project);
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("sprint", sprint);
	    modelAndView.addObject("sprints", project.getSprints());
		modelAndView.setViewName("sprint");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}

	@RequestMapping(value="/sprint/create" , method = RequestMethod.POST)
    public ModelAndView createsprintsave( @Valid Sprint sprint, BindingResult bindingResult) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sprint");
		
		DateTime sprintStartDate= new DateTime( sprint.getStartDate());
		DateTime sprintEndDate= new DateTime( sprint.getStartDate());
		
		/*if (sprintStartDate.isBefore( new DateTime (new Date()))) {
			bindingResult
			.rejectValue("startDate", "error.sprint",
					"Start date should not be less than current date");
		}
		*/
		if (sprintEndDate.isBefore( new DateTime (new Date()))) {
			bindingResult
			.rejectValue("endDate", "error.sprint",
					"End date should not be less than current date");
		}
		
		
		if (bindingResult.hasErrors()) {
			
		
			
			//modelAndView.setViewName("registration");
		}else {
			   int projectid= sprint.getProject().getId();
			     Project project= projectService.findById(projectid);
			     sprintService.saveSprint(sprint,project);	
			    	modelAndView.addObject("sprint", sprint);
				
					modelAndView.addObject("successMessage", "sprint has been created successfully");
				    modelAndView.addObject("sprints",project.getSprints());
				    modelAndView.addObject("role", util.getRole());
		}
		
   
		return modelAndView;
    }
	
	
	
	@RequestMapping("/sprint/backlog/{id}")
    public ModelAndView showSprintBacklog(  @PathVariable("id") int id) {
		
		Sprint sprint =sprintService.findById(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("storys", sprint.getStories());
		modelAndView.addObject("sprint", sprint);
	    modelAndView.addObject("role", util.getRole());
	    modelAndView.setViewName("sprintbacklog");
		return modelAndView;
    }
	
	
	@RequestMapping("/sprint/edit/{id}")
    public ModelAndView editView(@PathVariable("id") int id) {
      Sprint sprint = sprintService.findById(id);
      
        ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("sprint", sprint);
		modelAndView.addObject("sprints", sprint.getProject().getSprints());
		

		modelAndView.addObject("projects", projectService.findAll());
		modelAndView.setViewName("sprint-edit");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
    
    }
	
	@RequestMapping(value = "/sprint/update", method = RequestMethod.POST)
	public ModelAndView updateSprint(@Valid Sprint sprint, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		   int projectid= sprint.getProject().getId();
		     Project project= projectService.findById(projectid);
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("sprint-edit");
			modelAndView.addObject("sprints", sprintService.findAll());
		
		} else {
			sprintService.updateSprint( sprint);
			modelAndView.addObject("successMessage", "Sprint has been updated successfully");
			modelAndView.setViewName("sprint-edit");
			modelAndView.addObject("sprints", project.getSprints());
		}
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}
	
	@RequestMapping("/sprint/delete/{id}")
	
    public ModelAndView deleteSprint(@PathVariable("id") int id) {
        Sprint sprint = sprintService.findById(id);
        int   projectId= sprint.getProject().getId();
        sprintService.deleteSprint(sprint);
        ModelAndView modelAndView = new ModelAndView();
        
        Sprint s =new Sprint();
        Project project= projectService.findById(projectId);
        s.setProject(project);
        
		modelAndView.addObject("sprint", s);
		modelAndView.addObject("sprints", project.getSprints());
		modelAndView.setViewName("sprint");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
    }
	
	
	

	
	@RequestMapping("/releasesprint/{id}")
	public ModelAndView releasesprint(@PathVariable("id") int id) {
		
		
		ModelAndView modelAndView = new ModelAndView();
		Sprint sprint =sprintService.findById(id);
		Set<Story> stories=  sprint.getStories();
		
		boolean openstoriesFound= false;
		for (Story story : stories) {
			if (story.getStatus().equals("open") || story.getStatus().equals("in-progress") ) {
				openstoriesFound=true;
			}
		}
	/*	
		if (openstoriesFound) {	
			bindingResult
					.rejectValue("email", "error.user",
							"User with this email already exists");
		}*/
		
		if (openstoriesFound) {
			modelAndView.addObject("successMessage", "Can not release sprint due to unfinsed stories exists in the sprint");
			
			modelAndView.addObject("storys", sprint.getStories());
			modelAndView.addObject("sprint", sprint);
		    modelAndView.addObject("role", util.getRole());
		    modelAndView.setViewName("sprintbacklog");
		
		} else {
			
			sprintService.releaseSprint(sprint);
			modelAndView.addObject("successMessage", "Sprint released successfully");
			
			modelAndView.addObject("storys", sprint.getStories());
			modelAndView.addObject("sprint", sprint);
		    modelAndView.addObject("role", util.getRole());
		    modelAndView.setViewName("sprintbacklog");
		}
		
		
		
		return modelAndView;
	        
	}
	
}


