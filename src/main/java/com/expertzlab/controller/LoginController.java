package com.expertzlab.controller;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.expertzlab.model.Project;
import com.expertzlab.model.Role;
import com.expertzlab.model.User;
import com.expertzlab.service.ProjectService;
import com.expertzlab.service.UserService;
import com.expertzlab.util.Util;

@Controller("loginController")
public class LoginController {
	
	@Autowired
	private Util util;
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private ProjectService projectService;
	

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}
	
	
	@RequestMapping(value={"/activity"}, method = RequestMethod.GET)
	public ModelAndView activity(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("activity");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}
	
	
	@RequestMapping(value={"/scrum"}, method = RequestMethod.GET)
	public ModelAndView scrum(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("scrum");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {	
			bindingResult
					.rejectValue("email", "error.user",
							"User with this email already exists");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", user);
			modelAndView.setViewName("registration");
			//modelAndView.setViewName("/home");
			
			
		}
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView home(){
		//List<Project> aaa= new ArrayList();
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		//modelAndView.addObject("projects", projectService.findAll());
		modelAndView.addObject("projects", user.getProjects());
		modelAndView.setViewName("/home");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}
	

	@RequestMapping(value= {"/admin/home"}, method = RequestMethod.GET)
	public ModelAndView adminHome(){
		//List<Project> aaa= new ArrayList();
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.addObject("projects", projectService.findAll());
		//modelAndView.addObject("projects", user.getProjects());
		modelAndView.addObject("role", util.getRole());
		modelAndView.setViewName("/home");
		return modelAndView;
	}

	@RequestMapping(value= {"/user/home","/sholder/home","/owner/home","/master/home"}, method = RequestMethod.GET)
	public ModelAndView userHome(){
		//List<Project> aaa= new ArrayList();
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		//modelAndView.addObject("projects", projectService.findAll());
		modelAndView.addObject("projects", user.getProjects());
		modelAndView.addObject("role", util.getRole());
		modelAndView.setViewName("/home");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/user/edit", method = RequestMethod.GET)
	public ModelAndView editprofile(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/editprofile");
		return modelAndView;
	}
		
		
		
	}
	
	