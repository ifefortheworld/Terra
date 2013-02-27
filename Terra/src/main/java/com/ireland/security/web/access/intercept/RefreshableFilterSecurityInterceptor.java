package com.ireland.security.web.access.intercept;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.ExpressionBasedFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;

import com.ireland.security.authentication.config.SecurityMetadata;
import com.ireland.security.authentication.config.SecurityMetadataProvider;
import com.ireland.security.context.RefreshableSecurityMetadataSourceAware;




/**
 * 应用于SecurityMetadata存放在数据库时,数据库修改后,可手动刷新整个SecurityMetadataSource的FilterSecurityInterceptor
 * 
 * 刷新的内容只要是SecurityMetadata部分:相当于配置文件的<intercept-url pattern="" access=""/>部分
 * 
 * PS: CHANNEL_FILTER[ChannelProcessingFilter] 的 SecurityMetadataSource不受此影响,迟点写个RefreshableChannelProcessingFilter
 * 
 * @author 吉林大学珠海学院1队
 *
 */


public class RefreshableFilterSecurityInterceptor extends FilterSecurityInterceptor 
											   implements RefreshableSecurityMetadataSourceAware
{
	private SecurityMetadataProvider securityMetadataProvider;

	private SecurityExpressionHandler<FilterInvocation> expressionHandler;
	
	
	public RefreshableFilterSecurityInterceptor(
												SecurityMetadataProvider securityMetadataProvider,
												SecurityExpressionHandler<FilterInvocation> expressionHandler)
	{
		super();
		this.securityMetadataProvider = securityMetadataProvider;
		this.expressionHandler = expressionHandler;
		
		
		//重新加载数据库里的配置信息,刷新配置信息
		loadSecurityMetadatas();
		
	}

	

	/**
	 * 重新加载数据库里的配置信息,刷新配置信息
	 * 
	 */
	public void loadSecurityMetadatas()
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
		
		
		this.setSecurityMetadataSource(new ExpressionBasedFilterInvocationSecurityMetadataSource(requestMap,this.expressionHandler));
	}



	@Override
	public void refreshSecurityMetadataSource()
	{
		//重新加载数据库里的配置信息,刷新配置信息
		loadSecurityMetadatas();
		
		try
		{
			//重新加载配置信息后要验证信息的正确性
			this.afterPropertiesSet();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
