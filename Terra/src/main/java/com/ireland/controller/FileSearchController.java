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
import org.springframework.data.domain.PageImpl;
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
import com.ireland.service.FileService;



/**
 * 
 *
 *
 * @KEN
 * 
 */

@Controller
public class FileSearchController
{

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

	@RequestMapping("/search")
	public String search(@RequestParam("q")String query,
						   Model model)
	{
		//查询 目标File的id列表
		List<String> ids = fileIndexer.query(query);
		
		//查找ids里的file
		List<File> list = (List<File>) fileDao.findAll(ids);
		
		Page<File> page = new PageImpl<File>(list,new PageRequest(0, list.size()>0?list.size():1),list.size());  
				
		model.addAttribute("page",page);
		model.addAttribute("files",page.iterator());
		
		return "search/result";
	}
	
	
	
	
}
