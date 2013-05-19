package com.ireland.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;

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
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
import com.ireland.dao.LocalFileDao;
import com.ireland.dao.SourceFileDao;
import com.ireland.dao.TagDao;
import com.ireland.dao.FileDao;
import com.ireland.index.FileIndexer;
import com.ireland.model.Role;
import com.ireland.model.User;

import com.ireland.model.business.Comment;
import com.ireland.model.business.SourceFile;
import com.ireland.model.business.Tag;
import com.ireland.model.business.File;

import com.ireland.service.AuthorityService;
import com.ireland.service.RoleService;
import com.ireland.service.FileService;
import com.ireland.service.UserService;

/**
 * 订单管理
 *
 *
 * @KEN
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
	
	private AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();
	
	@Autowired
	private FileDao fileDao;
	
	@Autowired
	private FileIndexer fileIndexer;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private SourceFileDao sourceFileDao;
	
	@Autowired
	private TagDao tagDao;

	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private LocalFileDao localFileDao;
	
	
	
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
		
		Page<File> page = (type == null) ? fileDao.findAll(Criteria.where("owner").is(user.getUsername()), new PageRequest(pageNumber - 1, 10, Direction.DESC,sort))
											  : fileDao.findAll(Criteria.where("owner").is(user.getUsername()).and("type").is(type), new PageRequest(pageNumber - 1, 10, Direction.DESC,sort));
		//统计未分享的数量
		Long unsharedCnt = (type == null)? fileDao.count("isShared", Boolean.FALSE)
										 : fileDao.count(Criteria.where("isShared").is(Boolean.FALSE).and("type").is(type));
		
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
		File file = fileDao.findOne(id);
		
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
		
		File file = fileDao.findOne(id);
		
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
			fileDao.inc(file.getId(), "viewsCnt", 1);
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
	@Deprecated //文件上传到HDFS时耗时严重,长时间占用窗口的Servlet 线程,改用asyncUploadFile
	//@RequestMapping(value = "/files/upload_",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Map<String,String> _uploadFile(@Valid File file,@RequestParam(value="_tags",required=false)String _tags,
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
		
		
		//从header中解译出上传的文件名
		String value = partFile.getHeader("content-disposition");
		
		String fileName = value.substring(value.lastIndexOf("=")+2, value.length()-1);
		
		String uuid = UUID.randomUUID().toString();
		String fileUrl = "/staticfiles/"+uuid+"-"+fileName;
		
		try
		{
			localFileDao.write(partFile.getInputStream(), fileUrl);
		} catch (IOException e)
		{
			e.printStackTrace();
			res.put("status", "FAIL");
			res.put("reason", "hafs upload fail!");
			return res;
		}
		
		file.setFileOriginalName(fileName);
		//file.setFileUrl(fileUrl);
			
		fileDao.insert(file);
			
		res.put("status", "SUCCESS");
		res.put("Location", "/files/"+file.getId());
		return res;
	}
	
	@RequestMapping(value = "/files/upload_",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Callable<Map<String,String>> asyncUploadFile(@Valid final File file,@RequestParam(value="_tags",required=false)String _tags,
																@RequestPart(value="file",required=false) final Part partFile,
																HttpServletRequest request)
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
		file.setOwnerId(user.getId());
		file.setComments(new ArrayList<Comment>());
		
		
		//从header中解译出上传的文件名
		String value = partFile.getHeader("content-disposition");
		
		final String fileName = value.substring(value.lastIndexOf("=")+2, value.length()-1);
		
		final String filePostFix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		
		String uuid = UUID.randomUUID().toString();
		String fileUrl = "/staticfiles/"+uuid+filePostFix;				//文件的访问的URL,每份文件的URL都是唯一的,但可能引用同一个SourceFile
		final String storageLocation = "/staticfiles/"+uuid;			//文件实际存放路径
		
		file.setFileUrl(fileUrl);
		file.setFileOriginalName(fileName);
		
		final SourceFile sourceFile = new SourceFile();
		sourceFile.setSize(partFile.getSize());
		sourceFile.setStorageLocation(storageLocation);
		
		
		return new Callable<Map<String,String>>()
		{

			@Override
			public Map<String, String> call() throws Exception
			{
				Map<String,String> res = new HashMap<String,String>();
				
				try
				{
					localFileDao.write(partFile.getInputStream(), storageLocation);
				} catch (IOException e)
				{
					e.printStackTrace();
					res.put("status", "FAIL");
					res.put("reason", "hafs upload fail!");
					return res;
				}
				
				fileDao.insert(file);
				

				//设置引用计数				
				Set<String> referenceIds = new HashSet<String>(1);
				referenceIds.add(file.getId());
				
				sourceFile.setFileIds(referenceIds);
				sourceFile.setFileCount(1);

				sourceFileDao.insert(sourceFile);
				
				file.setSourceFileId(sourceFile.getId());
				
				fileDao.set(file.getId(), "sourceFileId", file.getSourceFileId());
				
				
				//索引这个File
				fileIndexer.add(file);
				
				res.put("status", "SUCCESS");
				res.put("Location", "/files/"+file.getId());
				return res;
			}
		};
		
	}

	/**
	 * 下载文件
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/staticfiles/{path}")
	@ResponseBody
	public org.springframework.core.io.Resource downloadFile(@PathVariable("path")String path,HttpServletRequest request,HttpServletResponse response)
	{
		String url = request.getRequestURI();
		
		//根据URL查找对应的File
		File file = fileDao.findOne("fileUrl", url);
		
		//查不到文件,返回404
		if(file == null)
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
		//处理资源受保护的情况
		if(file.getIsShared() == false)
		{
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			//如果用户未登录或来自记住密码等,重定向到登录页面
			if(authentication == null || authenticationTrustResolver.isAnonymous(authentication) || authenticationTrustResolver.isRememberMe(authentication)
					|| !UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication.getClass()))
			{
				throw new AuthenticationCredentialsNotFoundException("please login in!");
			}
			
			
			User user = (User) authentication.getPrincipal();
			
			
			//用户已登录,处理非本人访问,返回403页面
			if(!user.getId().equals(file.getOwnerId()))
			{
				throw new AccessDeniedException(null);
			}
		}
		
		SourceFile sourceFile = sourceFileDao.findOne(file.getSourceFileId());
		
		//取得真实存储的路径
		String storageLocation = sourceFile.getStorageLocation();
		
		//从HDFS读取文件,并返回结果
		org.springframework.core.io.Resource resource = localFileDao.read(storageLocation);

		//404
		if(resource == null)
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
			
		return resource;
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
			fileService.delete(id);   //删除文件\所有评论\更新或删除SourceFile
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
		fileDao.inc(id, "downsCnt", 1);
	    		
		Map<String,String> res = new HashMap<String,String>();
		res.put("status", "SUCCESS");
		return res;
	}

}
