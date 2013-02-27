package com.ireland.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ireland.dao.RoleDao;
import com.ireland.dao.UserDao;
import com.ireland.model.User;
import com.ireland.service.UserService;



/**
 * 
 * @author 吉林大学珠海学院1队
 *
 */

@Service("userService")
public class UserServiceImpl implements UserService
{
	private UserDao userDao;
	
	@Resource(name="userDao")
	public void setUserDao(UserDao userDao)
	{
		this.userDao = userDao;
	}
	
	private RoleDao roleDao;
	
	@Resource(name="roleDao")
	public void setRoleDao(RoleDao roleDao)
	{
		this.roleDao = roleDao;
	}
	

	//---------------------------------------------------------------------
	
	@Override
	public List<User> getUserByProperty(String propertyName, Object value) {
		
		return userDao.findAll(propertyName, value);
	}
	
	@Override
	public User getOneUserByProperty(String propertyName, Object value) {
		
		return userDao.findOne(propertyName, value);
	}

	@Override
	public User getUser(String id)
	{
		return userDao.findOne(id);
	}

	@Override
	public List<User> getAllUser()
	{
		
		return userDao.findAll();
	}
	


	
	@Override
	public Long count()
	{

		return userDao.count();
	}
	
}
