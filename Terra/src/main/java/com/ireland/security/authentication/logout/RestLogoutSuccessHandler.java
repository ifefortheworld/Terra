package com.ireland.security.authentication.logout;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * REST风格的logoutSuccesshandler,成功退出,则返回OK(200),logoutSuccessUrl装将作为响应头Location返回
 * 
 * @author 吉林大学珠海学院1队
 */

public class RestLogoutSuccessHandler implements LogoutSuccessHandler
{
	private String logoutSuccessUrl;

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException
	{
		
		response.setStatus(HttpServletResponse.SC_OK);  //200
		
		response.setHeader("Location", logoutSuccessUrl);
		
	}

	
	public void setLogoutSuccessUrl(String logoutSuccessUrl)
	{
		this.logoutSuccessUrl = logoutSuccessUrl;
	}

}
