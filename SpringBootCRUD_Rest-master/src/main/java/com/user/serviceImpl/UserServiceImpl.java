package com.user.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.UserException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

import com.user.beans.User;
import com.user.repository.UserRepository;
import com.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User saveUser(User inUser) {

		com.user.entity.User outUser2 = new com.user.entity.User();

		BeanUtils.copyProperties(inUser, outUser2);

		outUser2 = userRepository.save(outUser2);

		BeanUtils.copyProperties(outUser2, inUser);
		return inUser;
	}

	@Override
	public List<User> getAllUsers() {

		List<com.user.entity.User>alluser=userRepository.findAll();
		List<User> outList=new ArrayList<User>();
		for (com.user.entity.User user : alluser) {
			User usr=new User();
			BeanUtils.copyProperties(user, usr);
			outList.add(usr);
			
		}

		return outList;
	}

	@Override
	public User findbyId(Long id) {
		// TODO Auto-generated method stub
	com.user.entity.User dbUser=userRepository.findById(id);
	User outUser=null;
	if(dbUser!=null){
		outUser=new User();
		BeanUtils.copyProperties(dbUser, outUser);
	}
	
	
		return outUser;
	}

	@Override
	public User updateUser(User inUser) {
		// TODO Auto-generated method stub
		com.user.entity.User dbUser=userRepository.findById(inUser.getId());
		if(dbUser!=null){dbUser.setFirstname(inUser.getFirstname());
		dbUser.setInstitute(inUser.getInstitute());
		dbUser.setLastname(inUser.getLastname());
		userRepository.save(dbUser);
		BeanUtils.copyProperties(dbUser, inUser);
		return inUser;

		}else {
			return null;
		}
	}

	@Override
	public int deleteUser(Long id) {
	
	try{
		com.user.entity.User dbUser=userRepository.findById(id);
		if(dbUser==null){return -1;}
		else {
			userRepository.delete(id);
			return 1;
		}
		
	}catch(Exception e){
		
	}
	return 0;
		
	
	}

}
