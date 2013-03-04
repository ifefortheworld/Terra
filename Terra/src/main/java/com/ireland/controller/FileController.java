package com.ireland.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


import com.ireland.dao.CommentDao;
import com.ireland.dao.HdfsDao;
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
	
	@Autowired
	private HdfsDao hdfsDao;
	
	
	
	/**
	 * 文件列表
	 * pageNumber: 1~n
	 */

	@RequestMapping(value = {"/myspace/file-list","/myspace"})
	public String filelist( 
						   @RequestParam(value="page",required=false)Integer pageNumber,
						   @RequestParam(value="sort",required=false)String sort,
						   @RequestParam(value="type",required=false)String type,
						   Model model)
	{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//默认按上传日期倒序,取出前10条文件
		
		//清除如&sort=,这类无效参数
		if(sort != null && sort.isEmpty()) sort = null;
		if(type != null && type.isEmpty()) type = null;
		
		//默认第1页
		if(pageNumber == null) pageNumber = 1;
				
		//默认按uploadDate排序
		if(sort == null) sort ="uploadDate";
		
		Page<TerraFile> page = (type == null) ? terraFileDao.findAll(Criteria.where("owner").is(user.getUsername()), new PageRequest(pageNumber - 1, 10, Direction.DESC,sort))
											  : terraFileDao.findAll(Criteria.where("owner").is(user.getUsername()).and("type").is(type), new PageRequest(pageNumber - 1, 10, Direction.DESC,sort));
		//统计未分享的数量
		Long unsharedCnt = (type == null)? terraFileDao.count("isShared", Boolean.FALSE)
										 : terraFileDao.count(Criteria.where("isShared").is(Boolean.FALSE).and("type").is(type));
		
		model.addAttribute("page",page);
		model.addAttribute("files",page.iterator());
		model.addAttribute("unsharedCnt", unsharedCnt);
		
		return "box/file-list";
	}
	
	
	
	/**
	 * 查询本人的文件
	 */
	@Deprecated
	@RequestMapping(value = "/files/{id}/update")
	public String getOwnFile(@PathVariable("id") String id,Model model)
	{
		TerraFile file = terraFileDao.findOne(id);
		
		//查询此文件最有价值的评论(按votes倒序的第1条)
		Page<Comment> coment = commentDao.findAll(Criteria.where("fileId").is(id), new PageRequest(0, 1, Direction.DESC, "votes"));
		Comment valComment = coment.hasContent() ? coment.iterator().next() : null;
		
		//(按时间由新到旧)查询文件的前5条评论
		Page<Comment> page = commentDao.findAll(Criteria.where("fileId").is(id), new PageRequest(0, 5, Direction.DESC, "date"));
		
		model.addAttribute("file",file);
		model.addAttribute("page",page);
		model.addAttribute("comments",page.iterator());
		model.addAttribute("valComment",valComment);
		
		return "files/update";
	}
	
	/**
	 * 本人或游客浏览文件
	 */
	@RequestMapping(value = "/files/{id}")
	public String getFileDetails(@PathVariable("id") String id,Model model)
	{
		
		User user = SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User ? (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal(): null;
		
		TerraFile file = terraFileDao.findOne(id);
		
//评论---------------------------------------------------------------------------------		
		//查询此文件最有价值的评论(按votes倒序的第1条)
		Page<Comment> coment = commentDao.findAll(Criteria.where("fileId").is(id), new PageRequest(0, 1, Direction.DESC, "votes"));
		Comment valComment = coment.hasContent() ? coment.iterator().next() : null;
		
		//(按时间由新到旧)查询文件的前5条评论
		Page<Comment> page = commentDao.findAll(Criteria.where("fileId").is(id), new PageRequest(0, 5, Direction.DESC, "date"));
//评论END---------------------------------------------------------------------------------
		
//------正在浏览别人的文件,使文件的浏览数+1
		if(user != null && (user.getUsername() != file.getOwner()))
		{	
			file.setViewsCnt(file.getViewsCnt()+1);
			terraFileDao.inc(file.getId(), "viewsCnt", 1);
		}
		
		model.addAttribute("file",file);
		model.addAttribute("page",page);
		model.addAttribute("comments",page.iterator());
		model.addAttribute("valComment",valComment);
		model.addAttribute("user",user);
		
		return "files/detail";
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
	public Map<String,String> uploadFile(@Valid TerraFile file,@RequestParam(value="_tags",required=false)String _tags,
																@RequestPart(value="file",required=false) Part partFile,
																HttpServletRequest request)
	{
		Map<String,String> res = new HashMap<String,String>();
		
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
		
		//
		//取得"/"表示的tomcat中的实际路径
		String uploadPath = request.getServletContext().getRealPath("/WEB-INF/staticfiles/");
		
		//从header中解译出上传的文件名
		String value = partFile.getHeader("content-disposition");
		
		String fileName = value.substring(value.lastIndexOf("=")+2, value.length()-1);
		
		String uuid = UUID.randomUUID().toString();
		String fileUrl = "/staticfiles/"+uuid+"-"+fileName;
		
		try
		{
			hdfsDao.upload(partFile.getInputStream(), fileUrl);
		} catch (IOException e)
		{
			e.printStackTrace();
			res.put("status", "FAIL");
			res.put("reason", "hafs upload fail!");
			return res;
		}
/*		try
		{
			partFile.write(uploadPath + "\\"+uuid+"-"+fileName);
			
		} catch (IOException e)
		{
			e.printStackTrace();
			res.put("status", "FAIL");
			return res;
		}*/
		
		file.setFileOriginalName(fileName);
		file.setFileUrl(fileUrl);
			
		terraFileDao.insert(file);
			
		res.put("status", "SUCCESS");
		res.put("Location", "/files/"+file.getId());
		return res;
	}

	/**
	 * 下载文件
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/staticfiles/{path}")
	@ResponseBody
	public org.springframework.core.io.Resource downloadFile(@PathVariable("path")String path,HttpServletRequest request)
	{
		String url = request.getRequestURI();
		//return hdfsDao.read("/staticfiles/"+path);
		return hdfsDao.read(url);
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
		
		//发表者
		comment.setOwner(user.getUsername());
		//评论时间
		comment.setDate(new Date());

		commentDao.insert(comment);
		
		Map<String,String> res = new HashMap<String,String>();
		res.put("status", "SUCCESS");
		return res;
	}

	/**
	 * 批量删除文件,及其所有评论
	 * 
	 */
	@RequestMapping(value = "/files/delete",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Map<String,Object> deleteFiles(@RequestParam("files") String fileIdList)
	{		
		String[] fileIds = fileIdList.split(",");
		
		for(String id : fileIds)
		{
			terraFileDao.delete(id);           //删除文件
			commentDao.delete("fileId", id);   //删除文件的所有评论
		}
		
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("status", "SUCCESS");
		res.put("cnt",fileIds.length);
		return res;
	}
	
	
	/**
	 * 为down下载统计: /files/downsCnt/inc
	 * 
	 */
	@RequestMapping(value = "/files/downsCnt/inc",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Map<String,String> downsCntInc(@RequestParam("file_id")String id)
	{
		//使数据库的downsCnt 加1
		terraFileDao.inc(id, "downsCnt", 1);
	    		
		Map<String,String> res = new HashMap<String,String>();
		res.put("status", "SUCCESS");
		return res;
	}

}
