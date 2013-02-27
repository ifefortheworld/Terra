package com.ireland.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ireland.security.context.RefreshableSecurityMetadataSourceAware;

@Controller
public class RefreshSecurityConfigController
{
	private RefreshableSecurityMetadataSourceAware refreshableSecurityMetadataSourceAware;
	
	@Resource(name="refreshableFilterSecurityInterceptor")
	public void setRefreshableSecurityMetadataSourceAware(
			RefreshableSecurityMetadataSourceAware refreshableSecurityMetadataSourceAware)
	{
		this.refreshableSecurityMetadataSourceAware = refreshableSecurityMetadataSourceAware;
	}
	
	@RequestMapping(value="/refreshSecurityConfig")
	@ResponseBody
	public String refreshSecurityConfig()
	{
		refreshableSecurityMetadataSourceAware.refreshSecurityMetadataSource();
		
		
		return "RefreshSecurityConfig Success";
	}

}
