package com.expertzlab.service;
import java.util.List;

import com.expertzlab.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public void updateUser(User user);
	public List<User> findAll();
	public User findById(long id);
}