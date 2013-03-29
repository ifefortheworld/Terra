package com.ireland.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


import com.ireland.dao.CommentDao;
import com.ireland.dao.TagDao;
import com.ireland.dao.TerraFileDao;
import com.ireland.model.Role;
import com.ireland.model.User;

import com.ireland.model.business.Comment;
import com.ireland.model.business.Tag;
import com.ireland.model.business.TerraFile;

import com.ireland.service.AuthorityService;
import com.ireland.service.RoleService;
import com.ireland.service.UserService;

/**
 * 订单管理
 *
 *
 * @KEN
 * 
 */

@Controller
public class CommentController
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
	
	
	@Autowired
	private TerraFileDao terraFileDao;
	
	@Autowired
	private TagDao tagDao;

	@Autowired
	private CommentDao commentDao;
	
	
	

	/**
	 * 为评论投票: /comments/inc,
	 * 
	 */
	@RequestMapping(value = "/comments/inc",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Map<String,String> uploadFile(@RequestParam("comment_id")String id)
	{
		//使数据库的fileCnt 加1
		commentDao.inc(id, "votes", 1);
	    		
		Map<String,String> res = new HashMap<String,String>();
		res.put("status", "SUCCESS");
		return res;
	}

}
