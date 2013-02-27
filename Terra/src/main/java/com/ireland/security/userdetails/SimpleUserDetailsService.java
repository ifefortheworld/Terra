package com.ireland.security.userdetails;


import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ireland.dao.UserDao;
import com.ireland.model.User;






/**
 * 
 * UserDetailsManager  for com.ireland.model.User
 * 
 * 为类型model.User定制的UserDetailsManager,如果非此类型,则抛出异常
 * 
 * SimpleUserDetailsManager 同时 负责密码的加密部分,
 * 
 * 如注册时,提供的user的密码应该是未加密的,而HibernateUserDetailsManager利用passwordEncoder加密后再存放到数据库中
 * 
 * @author 吉林大学珠海学院1队
 *
 */

@Service("userDetailsService")
public class SimpleUserDetailsService implements UserDetailsService
{
	protected final Log logger = LogFactory.getLog(getClass());
	
	protected UserDao userDao;

	@Resource(name="userDao")
	public void setUserDao(UserDao userDao)
	{
		this.userDao = userDao;
	}



	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException
	{
		
		User user = userDao.findOne("username", username);
		
		if(user == null)
			throw new UsernameNotFoundException("用户" + username + " 不存在!!!");
		
		return user;
	}

}
