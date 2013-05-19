package com.ireland.security.authentication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

/**
 * REST 风格 的 AuthenticationFailureHandler,用户名不存在则返回 404,密码错误或无权限访问则返回401 UNAUTHORIZED
 * 
 * @KEN
 */

//{"reason":"用户名不存在"}

@Component("authenticationFailureHandler")
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler
{
	private static Gson gson = new Gson();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,HttpServletResponse response,
					AuthenticationException exception)
			throws IOException, ServletException
	{
		Map<String,String> res = new HashMap<String,String>();
		
		//用户名不存在
		if(exception instanceof UsernameNotFoundException)
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
			res.put("reason",  "用户名不存在");
		}
		else //密码错误
		if(exception instanceof BadCredentialsException)
		{
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //401
			res.put("reason",  "密码错误");
		}
		else  //账号存在问题
		if(exception instanceof AccountStatusException)
		{
			response.setStatus(HttpServletResponse.SC_FORBIDDEN); //403
			
			//账号已过期
			if(exception instanceof AccountExpiredException)
			{
				res.put("reason", "账号已过期");
				
			}//凭证已过期
			else if(exception instanceof CredentialsExpiredException)
			{
				res.put("reason", "凭证已过期");
			}//账号已关闭
			else if(exception instanceof DisabledException)
			{
				res.put("reason",  "账号已关闭");
			}//账号被锁定
			else if(exception instanceof LockedException)
			{
				res.put("reason", "账号被锁定");
			}
			else
			{
				res.put("reason",  "账号状态存在问题");
			}
		}
		else//禁止访问
		{
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);			//403
			res.put("reason", "禁止访问");
		}

		response.getWriter().println(gson.toJson(res));
		
		response.flushBuffer();
	}

}
