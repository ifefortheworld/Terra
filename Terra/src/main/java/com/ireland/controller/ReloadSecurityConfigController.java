package com.ireland.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ireland.security.securitymetadata.ReloadableExpressionBasedFilterInvocationSecurityMetadataSource;

@Controller
public class ReloadSecurityConfigController
{
	private ReloadableExpressionBasedFilterInvocationSecurityMetadataSource securityMetadataSource; 

	@Resource(name="securityMetadataSource")
	public void setSecurityMetadataSource(ReloadableExpressionBasedFilterInvocationSecurityMetadataSource securityMetadataSource)
	{
		this.securityMetadataSource = securityMetadataSource;
	}


	@RequestMapping(value="/refreshSecurityConfig")
	@ResponseBody
	public String refreshSecurityConfig()
	{
		securityMetadataSource.reload();
		
		return "RefreshSecurityConfig Success";
	}

}
