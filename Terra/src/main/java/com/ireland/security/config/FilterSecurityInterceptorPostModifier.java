package com.ireland.security.config;

import java.lang.reflect.Field;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Component;

import com.ireland.security.web.access.intercept.ReloadableExpressionBasedFilterInvocationSecurityMetadataSource;


/**
 * 
 * <http/>这个标签默认会配置一个DefaultWebInvocationPrivilegeEvaluator,
 * 而它会持有<http/>的FILTER_SECURITY_INTERCEPTOR,
 * 而我这里使用了反射将自定义的FILTER_SECURITY_INTERCEPTOR替换了原来的FILTER_SECURITY_INTERCEPTOR
 * 
 * @author 吉林大学珠海学院1队
 *
 */


@Component
public class FilterSecurityInterceptorPostModifier  
{
	private ReloadableExpressionBasedFilterInvocationSecurityMetadataSource securityMetadataSource;
	
	private FilterSecurityInterceptor filterSecurityInterceptor;


	@Resource(name="securityMetadataSource")
	public void setSecurityMetadataSource(ReloadableExpressionBasedFilterInvocationSecurityMetadataSource securityMetadataSource)
	{
		this.securityMetadataSource = securityMetadataSource;
	}

	@Autowired
	public void setFilterSecurityInterceptor(FilterSecurityInterceptor filterSecurityInterceptor)
	{
		this.filterSecurityInterceptor = filterSecurityInterceptor;
	}
	
	
	@PostConstruct
	public void init()
	{
		filterSecurityInterceptor.setSecurityMetadataSource(securityMetadataSource);
	}

}
