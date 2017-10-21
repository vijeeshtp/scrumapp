package com.expertzlab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.expertzlab.util.Util;

@Controller
public class HomeController {

	@Autowired
	private Util util;
	
	@RequestMapping ( value={"/myhome"}, method= RequestMethod.GET)
	public ModelAndView myHome(){
		ModelAndView model= new ModelAndView();
		model.setViewName("myhome");
		model.addObject("role", util.getRole());
		return model;
		
	}
	
}
