package com.ireland.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 根据路径变量直接跳转到对应视频的控制器
 * 
 * @author 吉林大学珠海学院1队
 * @since 2012/5/11
 */


@Controller
public class PathVariableViewController
{
	
	@RequestMapping(value="/p/{viewName}")
	public String handleRequest(@PathVariable("viewName")String viewName)
	{
		return viewName;
	}

}
