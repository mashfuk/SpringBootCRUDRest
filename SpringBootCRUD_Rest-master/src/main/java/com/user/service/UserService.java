package com.user.service;

import java.util.List;

import com.user.beans.User;


public interface UserService {

	public User saveUser(User inUser);
	public List<User> getAllUsers();
	public User findbyId(Long id);
	public User updateUser(User inUser);
	public int deleteUser(Long id);
	
}
