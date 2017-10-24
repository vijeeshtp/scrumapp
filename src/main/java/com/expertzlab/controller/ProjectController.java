package com.expertzlab.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.expertzlab.model.ChartPoint;
import com.expertzlab.model.Project;
import com.expertzlab.model.Sprint;
import com.expertzlab.model.User;
import com.expertzlab.model.Story;
import com.expertzlab.service.ProjectService;
import com.expertzlab.service.UserService;
import com.expertzlab.util.Util;
import com.expertzlab.service.SprintService;
import com.expertzlab.service.StoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Controller
public class ProjectController {
	
	@Autowired
	private Util util;
	
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserService userService;
	

	@Autowired
	private StoryService storyService;
		
	@Autowired
	private SprintService sprintService;
	
	@RequestMapping(value="/project", method = RequestMethod.GET)
	public ModelAndView project(){
		ModelAndView modelAndView = new ModelAndView();
		Project project = new Project();
		modelAndView.addObject("project", project);
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("projects", projectService.findAll());
		modelAndView.setViewName("project");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}
	
	@RequestMapping(value = "/project", method = RequestMethod.POST)
	public ModelAndView createNewProject(@Valid Project project, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("project");
			modelAndView.addObject("projects", projectService.findAll());
		} else {
			projectService.saveProject(project);
			modelAndView.addObject("successMessage", "Project has been created successfully");
			//modelAndView.addObject("user", new User());
			//modelAndView.setViewName("registration");
			modelAndView.setViewName("project");
			modelAndView.addObject("projects", projectService.findAll());
		}
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/project/update", method = RequestMethod.POST)
	public ModelAndView updateProject(@Valid Project project, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("project-edit");
			modelAndView.addObject("projects", projectService.findAll());
		} else {
			projectService.saveProject(project);
			modelAndView.addObject("successMessage", "Project has been updated successfully");
			modelAndView.setViewName("project-edit");
			modelAndView.addObject("projects", projectService.findAll());
		}
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}
	
	
	 
	@RequestMapping("/project/edit/{id}")
    public ModelAndView editView(@PathVariable("id") int id) {
        Project project = projectService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("project", project);
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("projects", projectService.findAll());
		modelAndView.setViewName("project-edit");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
    }
	
	
	@RequestMapping("/project/delete/{id}")
    public ModelAndView deleteProject(@PathVariable("id") int id) {
        Project project = projectService.findById(id);
        projectService.deleteProject(project);
        ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("projects", projectService.findAll());
		modelAndView.setViewName("redirect:/project");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
    }
	
	
	@RequestMapping("/project/assign/{id}")
    public ModelAndView assignProject(@PathVariable("id") int id) {
        Project project = projectService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("project", project);
		modelAndView.addObject("users", userService.findAll());
		modelAndView.setViewName("project-assign");
	    modelAndView.addObject("projectUsers", projectService.findById(project.getId()).getUsers());
	    modelAndView.addObject("role", util.getRole());
		return modelAndView;
    }
	
	@RequestMapping("/project/assign/user")
    public ModelAndView assignUser(@Valid Project project, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Project projectToBeUpdated = projectService.findById(project.getId());
        
        
        User user = userService.findById(project.getAssignedUser());
        
        
       // projectToBeUpdated.getUsers().addAll(project.getUsers());
        projectService.saveProject(projectToBeUpdated,user);  
        modelAndView.addObject("successMessage", "New Member added");
        
        modelAndView.addObject("users", userService.findAll());
        modelAndView.addObject("projectUsers", projectService.findById(project.getId()).getUsers());
     
		modelAndView.setViewName("project-assign");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
    }
	
	
	
	@RequestMapping(value={ "/myprojects/{id}"}, method = RequestMethod.GET)
	public ModelAndView myproject(@PathVariable("id") int id){
	    Project project = projectService.findById(id);
	    ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("project", project);
		modelAndView.setViewName("myprojects");
		 modelAndView.addObject("sprints",project.getSprints());
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}
	
	
	
	@RequestMapping(value={ "/myprojects/burndown"}, method = RequestMethod.GET)
	public ModelAndView burndownchart(){
	  //  Project project = projectService.findById(id);
	    ModelAndView modelAndView = new ModelAndView();
		//modelAndView.addObject("project", project);
		modelAndView.setViewName("burndown");
		// modelAndView.addObject("message",getData());
		//modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}
	
	
	@RequestMapping(value={ "/sprint/burndown/{id}"}, method = RequestMethod.GET)
	public ModelAndView burndown(@PathVariable("id") int id){
		
		
		Sprint sprint= sprintService.findById(id);
		String data= getData(sprint);
		System.out.println("Data>>>>>"+ data);
	  //  Project project = projectService.findById(id);
	    ModelAndView modelAndView = new ModelAndView();
		//modelAndView.addObject("project", project);
		modelAndView.setViewName("burndown");
		 modelAndView.addObject("message",data);
		//modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}
	

	
	
	
	String  getData(Sprint sprint){
		List<ChartPoint> points =new ArrayList<>();
		
		Set<Story>stories= sprint.getStories();
		Date startDate=sprint.getStartDate();
		Date endDate=sprint.getEndDate();
		int burnRate=sprint.getBurnperDay();
		
		DateTime dateTime1 = new DateTime(startDate);
		DateTime dateTime2 = new DateTime(endDate);

		List<Date> allDates = new ArrayList();

		while( dateTime1.isBefore (dateTime2) ){
		   allDates.add( dateTime1.toDate() );
		   dateTime1 = dateTime1.plusDays(1);
		}
		System.out.println("allDates>>>>>"+ allDates);
		//int numberOfdays = Days.daysBetween(dateTime1, dateTime2).getDays();
		//int totalBurn = numberOfdays*burnRate;
		
		ChartPoint point1 = new ChartPoint();
		point1.setDate( new DateTime(startDate).minusDays(1).toDate());
		point1.setStandard(0);
		point1.setDone(0);
		points.add(point1);
		
		int stdBurned=0;
		for (Date day: allDates) {
			System.out.println("day>>>>>"+ day);
			ChartPoint point = new ChartPoint();
			point.setDate(day);
			stdBurned= stdBurned+burnRate;
			point.setStandard(stdBurned);
			//point.setDone(0);
			
			int burned=0;
			
			for (Story story:stories) {
				if (story.getStatus().equals("closed") ){
					/*
					 Date storyCloseDate= story.getResolvedDate();
					
					if ( storyCloseDate.equals(startDate) || 
							storyCloseDate.equals(day) || 
					    (storyCloseDate.after(startDate) && storyCloseDate.before(day) )){
						
						burned=burned+story.getDuration();//Duration is story point
						
					}
					*/
					
					DateTime storyCloseDate=  new DateTime (story.getResolvedDate());
					
					
					if ( storyCloseDate.toLocalDate(). isEqual(new DateTime(startDate).toLocalDate()) || 
							storyCloseDate.toLocalDate(). isEqual(new DateTime(day).toLocalDate()) || 
					    (storyCloseDate.isAfter(new DateTime(startDate)) && storyCloseDate.isBefore(new DateTime(day)) )){
						
						burned=burned+story.getDuration();//Duration is story point
						
					}	
					
				}
				
			}
			
			//if (burned>0) {
				point.setDone(burned);
					
			//}
			//else {
			//	point.setDone(null);
			//}
			
			if (day.getTime()> new Date ().getTime()){
				point.setDone(null);
			}
			
			points.add(point);
		}
		
		System.out.println("points>>>>>"+ points);
		
		///////////////////////
		String dataString ="";
		boolean isFirstItem=true;
		for (ChartPoint point : points) {
			DateTime date = new DateTime(point.getDate());
			int year=date.getYear();
			int month= date.getMonthOfYear();
			int day= date.getDayOfMonth();
			int standard= point.getStandard();
			Integer done= point.getDone();
			
			String pointStr =year+","+month+","+day+","+standard+","+done;
			
			if (isFirstItem) {
				dataString =dataString+ pointStr;
			}else {
				dataString = dataString+",#"+ pointStr;
			}
			isFirstItem=false;
		}
		
		System.out.println("dataString>>>>>"+ dataString);
		return dataString;
		
    	//return "2017,10,01,0,0,#2017,10,02,40,60,#2017,10,03,80,90,#2017,10,04,120,110,#2017,10,05,160,145,#2017,10,06,200,170,#2017,10,07,240,230,#2017,10,08,280,250,#2017,10,09,320,0#2017,10,10,360,0,#2017,10,11,400,0";

		
	}
	
	
	
}