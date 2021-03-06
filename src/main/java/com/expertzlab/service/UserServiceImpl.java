package com.expertzlab.service;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.expertzlab.model.Project;
import com.expertzlab.model.Role;
import com.expertzlab.model.User;
import com.expertzlab.repository.RoleRepository;
import com.expertzlab.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole(user.getUser_role());
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}
	
	
	
	@Override
	public List<User> findAll() {
		 return userRepository.findAll();
	}

	@Override
	public User findById(long id) {
		 return userRepository.findOne(id);
	}

	@Override
	public void updateUser(User inUser) {	
		
	User user=	userRepository.findByEmail(inUser.getEmail());
	
	user.setName(inUser.getName());
	user.setLastName(inUser.getLastName());
	user.setPassword(bCryptPasswordEncoder.encode(inUser.getPassword()));
    user.setActive(1);
    //Role userRole = roleRepository.findByRole(user.getUser_role());
   // user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	userRepository.save(user);// TODO Auto-generated method stub
		
	}
}