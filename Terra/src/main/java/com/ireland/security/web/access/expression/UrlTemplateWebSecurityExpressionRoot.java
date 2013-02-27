package com.ireland.security.web.access.expression;

import java.util.Map;


import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;

import com.ireland.model.User;


/**
 * 
 * 增加了对UrlTemplate相关(如路径变量PathVariable)的控制的SpEL 4 WebSecurityExpressionRoot
 * 
 * 注意:只适用于model.User的情况!
 * 
 * @author 吉林大学珠海学院1队
 * @since 2012-06-01
 *
 */

public class UrlTemplateWebSecurityExpressionRoot extends DateWebSecurityExpressionRoot
{
	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	

	public UrlTemplateWebSecurityExpressionRoot(Authentication a, FilterInvocation fi)
	{
		super(a, fi);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 判断当前访问的URL中的路径变量id,是否与当前登录用户的id一致
	 * 
	 * 如:
	 * pattern: /user/{id}
	 * 当前URL: /user/123
	 * 当前user.id == 123
	 * 
	 * @param pattern 当前请求的模板,如:/user/{id}
	 * @return 如果id一致,true
	 */
	public boolean idMatch(String pattern)
	{
		//取出当前的URL
		String path = request.getRequestURI();
		
		//利用给出的URL模板,从当前URL中取出所有路径变量
		Map<String,String> uriTemplateVariables = antPathMatcher.extractUriTemplateVariables(pattern, path);
		
		//取得URL中的id
		String id = uriTemplateVariables.get("id");

		if(id == null) return false;
		
		
		//取得当前用户
		Object use = this.getPrincipal();
		
		//如果当前用户的id与URL中的id一致,则返回true
		if(use.getClass().isAssignableFrom(User.class))
		{
			User user = (User) use;
			
			if(user.getId().equals(id))
				return true;
		}
		
		return false;
	}
	
	
	/**
	 * 判断当前访问的URL中的路径变量username,是否与当前登录用户的username一致
	 * 
	 * 如:
	 * pattern: /user/{username}
	 * 当前URL: /user/jack
	 * 当前user.username == jack
	 * 
	 * @param pattern 当前请求的模板,如:/user/{username}
	 * @return 如果username一致,true
	 */
	public boolean usernameMatch(String pattern)
	{
		//取出当前的URL
		String path = request.getRequestURI();
		
		//利用给出的URL模板,从当前URL中取出所有路径变量
		Map<String,String> uriTemplateVariables = antPathMatcher.extractUriTemplateVariables(pattern, path);
		
		//取得URL中的username
		String username = uriTemplateVariables.get("username");

		if(username == null) return false;
		
		
		//取得当前用户
		Object use = this.getPrincipal();
		
		//如果当前用户的username与URL中的username一致,则返回true
		if(use.getClass().isAssignableFrom(User.class))
		{
			User user = (User) use;
			
			if(user.getUsername().equals(username))
				return true;
		}
		
		return false;
	}


}
