package com.ireland.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccessDeniedController
{
	@RequestMapping(value="/access-denied",method=RequestMethod.GET)
	public String accessDenied(HttpServletRequest request,ModelMap model)
	{
		
		AccessDeniedException ex = (AccessDeniedException) request.getAttribute(WebAttributes.ACCESS_DENIED_403);
		
		StringWriter sw = new StringWriter();
		
		ex.printStackTrace(new PrintWriter(sw));
		
		model.addAttribute("errorTrace", sw.toString());
		
		model.addAttribute("errorDetails",ex.getMessage());
		
		
		return "system/403";
	}

}
