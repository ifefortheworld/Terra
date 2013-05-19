package com.ireland.security.authentication.dao;


import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;





/**
 * 只适用于com.ireland.model.User这个UserDetails的SaltSource实现!
 * 
 * 取User的username作为salt
 * 
 * @KEN
 *
 */

@Component("saltSource")
public class SimplePropertySaltSource implements SaltSource
{

	@Override
	public Object getSalt(UserDetails user)
	{
		return user.getUsername();
	}

}
