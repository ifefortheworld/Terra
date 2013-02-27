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
 * @author 吉林大学珠海学院1队
 * 
 */

@Controller
public class FileController
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
	 * 文件列表
	 * pageNumber: 1~n
	 */

	@RequestMapping(value = "/{username}/file-list")
	public String filelist(@PathVariable("username") String username, 
						   @RequestParam(value="page",required=false)Integer pageNumber,
						   @RequestParam(value="sort",required=false)String sort,
						   Model model)
	{
		//默认按上传日期倒序,取出前10条文件

		//默认第1页
		if(pageNumber == null) pageNumber = 1;
				
		//默认按uploadDate排序
		if(sort == null) sort ="uploadDate";
		
		Page<TerraFile> page = terraFileDao.findAll(Criteria.where("owner").is(username), new PageRequest(pageNumber - 1, 10, Direction.DESC,sort));
		
		//统计未分享的数量
		Long unsharedCnt = terraFileDao.count("isShared", Boolean.FALSE);
		
		model.addAttribute("page",page);
		model.addAttribute("files",page.iterator());
		model.addAttribute("unsharedCnt", unsharedCnt);
		
		return "box/file-list";
	}
	
	
	
	/**
	 * 查询本人的文件
	 */
	@RequestMapping(value = "/files/{id}/update")
	public String getOrdersTable(@PathVariable("id") String id,Model model)
	{
		TerraFile file = terraFileDao.findOne(id);
		
		model.addAttribute("file",file);
		
		return "files/update";
	}
	
	
	
	
	/**
	 * 上传文件
	 * 
	 * @param file
	 * @param _tags
	 * @return
	 */
	@RequestMapping(value = "/files/upload_",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Map<String,String> uploadFile(@Valid TerraFile file,@RequestParam("_tags")String _tags)
	{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		//以','拆分tag
		String[] strs = _tags.trim().split(",");
		
	    List<Tag> tags = new ArrayList<Tag>(strs.length);
	    
	    for(String str : strs)
	    {
	    	
	    	Tag tag = tagDao.findOne("name", str);
	    	
	    	////如果 对应的tag不存在,则新建一个
	    	if(tag == null)
	    	{
	    		tag = new Tag();
	    		tag.setName(str);
	    		tag.setFileCnt(1);
	    		
	    		tagDao.save(tag);
	    	}
	    	else//tag 已存在,则更新
	    	{
		    	//更新tag的人气
		    	tag.setFileCnt(tag.getFileCnt()+1);
		    	
		    	//使数据库的fileCnt 加1
		    	tagDao.inc(tag.getId(), "fileCnt", 1);
	    		
	    	}
	    	
	    	tags.add(tag);
	    }
	    
		file.setTags(tags);
		
		file.setUploadDate(new Date());
		file.setOwner(user.getUsername());
		file.setComments(new ArrayList<Comment>());
		
		
		terraFileDao.insert(file);
		
		
		Map<String,String> res = new HashMap<String,String>();
		res.put("status", "SUCCESS");
		res.put("Location", "/files/"+file.getId()+"/update");
		return res;
	}


	/**
	 * 对文件发表评论
	 * 
	 */
	@RequestMapping(value = "/files/{fileId}/coments",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Map<String,String> uploadFileComents(@Valid Comment comment)
	{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		comment.setOwner(user.getUsername());

		commentDao.insert(comment);
		
		Map<String,String> res = new HashMap<String,String>();
		res.put("status", "SUCCESS");
		return res;
	}


}
