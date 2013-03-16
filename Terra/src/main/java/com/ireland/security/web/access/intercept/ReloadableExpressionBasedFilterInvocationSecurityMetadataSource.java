package com.ireland.security.web.access.intercept;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.ExpressionBasedFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;
import org.springframework.util.Assert;

import com.ireland.security.authentication.config.SecurityMetadata;
import com.ireland.security.authentication.config.SecurityMetadataProvider;

/**
 * 利用Proxy方式实现的可重新加载数据的 ExpressionBasedFilterInvocationSecurityMetadataSource
 * 
 * 应用于SecurityMetadata存放在数据库时,数据库修改后,可手动刷新整个SecurityMetadataSource的FilterSecurityInterceptor
 * 
 * 刷新的内容只要是SecurityMetadata部分:相当于配置文件的<intercept-url pattern="" access=""/>部分
 * 
 * PS: CHANNEL_FILTER[ChannelProcessingFilter] 的 SecurityMetadataSource不受此影响,迟点写个RefreshableChannelProcessingFilter
 * 
 * @author 吉林大学珠海学院1队
 *
 */

public class ReloadableExpressionBasedFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource
{
    private ExpressionBasedFilterInvocationSecurityMetadataSource securityMetadataSource;

	private SecurityMetadataProvider securityMetadataProvider;

	private SecurityExpressionHandler<FilterInvocation> expressionHandler;
    
	
	public ReloadableExpressionBasedFilterInvocationSecurityMetadataSource(SecurityMetadataProvider securityMetadataProvider,
			SecurityExpressionHandler<FilterInvocation> expressionHandler)
	{
		Assert.notNull(securityMetadataProvider, "A non-null securityMetadataProvider is required");
		Assert.notNull(expressionHandler, "A non-null expressionHandler is required");
		
		this.securityMetadataProvider = securityMetadataProvider;
		this.expressionHandler = expressionHandler;
		
		reload();
	}
	
	
	/**
	 * 重新加载数据库里的配置信息,刷新配置信息
	 * 
	 */
	public void reload()
	{
		//这里没有指定SecurityMetadata返回的顺序,可能出现点问题!
		List<? extends SecurityMetadata> metadatas = securityMetadataProvider.getSecurityMetadatas();

		
		LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
		
		
		//解释SecurityMetadata
		for(SecurityMetadata meta : metadatas)
		{
			//忽略没有URL的
			if(meta.getUrlPattern() != null && !meta.getUrlPattern().isEmpty())
			{
				//将URL和HTTP Method转换为一个RequestMatcher
				AntPathRequestMatcher matcher = new AntPathRequestMatcher(meta.getUrlPattern(),meta.getHttpMethod());
				
				Collection<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();
				
				//构造SecurityConfig
				attributes.add(new SecurityConfig(meta.getSecurityConfigAttribute()));
				
				requestMap.put(matcher, attributes);
			}
		}
		
		
		this.securityMetadataSource = new ExpressionBasedFilterInvocationSecurityMetadataSource(requestMap,this.expressionHandler);
	}
	
	
	
	//Proxy Method
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException
	{
		return securityMetadataSource.getAttributes(object);
	}

	//Proxy Method
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes()
	{
		return securityMetadataSource.getAllConfigAttributes();
	}

	//Proxy Method
	@Override
	public boolean supports(Class<?> clazz)
	{
		return securityMetadataSource.supports(clazz);
	}

}
