package com.ireland.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ireland.model.Role;
import com.ireland.model.User;
import com.ireland.service.AuthorityService;
import com.ireland.service.RoleService;
import com.ireland.service.UserService;

/**
 * 用户相关的操作
 * 
 * @KEN
 * 
 */

@Controller
public class UserController
{

	private UserDetailsManager userDetailsManager;

	@Resource(name = "userDetailsManager")
	public void setUserDetailsManager(UserDetailsManager userDetailsManager)
	{
		this.userDetailsManager = userDetailsManager;
	}

	private UserService userService;

	@Resource(name = "userService")
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	private AuthorityService authorityService;

	@Resource(name = "authorityService")
	public void setAuthorityService(AuthorityService authorityService)
	{
		this.authorityService = authorityService;
	}

	private RoleService roleService;

	@Resource(name = "roleService")
	public void setRoleService(RoleService roleService)
	{
		this.roleService = roleService;
	}

	// /Exception

	// for数据库异常
	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "数据库异常!")// 500
	public void handleDatabaseException(Exception ex)
	{
		ex.printStackTrace();
	}

	//

	@RequestMapping(value = "/doregister", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, String> register(
					HttpServletRequest request,
					HttpServletResponse response, 
					HttpSession session,
					
					User user, 
					BindingResult result,
					@RequestParam("ROLE") String roleName
					)
	{

		// 返回的结果
		Map<String, String> res = new HashMap<String, String>();

		// role 必需为 ROLE_SUPERVISOR 或 ROLE_ADMIN

		if (userDetailsManager.userExists(user.getUsername()))
		{

			response.setStatus(HttpServletResponse.SC_CONFLICT); // 409

			res.put("reason", "用户名为 '" + user.getUsername() + "' 的用户已存在!");

			return res;
		}

		
		// 用户名不存在 
		
		//查找Role
		Role role = roleService.getRole(roleName);
		if (role == null) 
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
			// 请求的参数有错

			res.put("reason", "请求的参数有错!");

			return res;
		} else
		{
			user.setRole(role);
			
			userDetailsManager.createUser(user);

			response.setStatus(HttpServletResponse.SC_CREATED); // 201

			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";

			// Location用户注册后的下一个状态:登录,的URL
			response.setHeader("Location", basePath + "login");

			return null;
		}

	}



	/**
	 * 
	 * 更新当前用户的密码
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	@RequestMapping(value = { "/user/changePassword","/mobile/user/changePassword" }, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String changePassword(HttpServletResponse response,
			@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword)
	{
		try
		{
			userDetailsManager.changePassword(oldPassword, newPassword);
		} catch (AccessDeniedException ex)
		{
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401

			// 当前用户未登录
			return "用户未登录,请重新登录!";
		} catch (BadCredentialsException ex)
		{
			response.setStatus(HttpServletResponse.SC_CONFLICT); // 409//没办法,上面用了401

			return "用户输入的旧密码有误!";
		} catch (AuthenticationException ex)
		{
			response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403

			return "密码修改不成功!";
		}

		return "密码修改成功!";
	}

}
