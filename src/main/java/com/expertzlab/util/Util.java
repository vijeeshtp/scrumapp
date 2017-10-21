package com.expertzlab.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.expertzlab.model.Role;
import com.expertzlab.model.User;
import com.expertzlab.service.UserService;

@Component
public class Util {
	
	@Autowired
	UserService userService;
	
	public String getRole () {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		String role= null;
		if (user==null) return "";
		for ( Role r:user.getRoles()) {
			r.getRole();
			if (r.getRole()!=null) return r.getRole();
		}
		return role;
	}
	
}
