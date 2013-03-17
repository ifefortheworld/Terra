package com.ireland.security.config;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.RequestCacheAwareFilter;
import org.springframework.stereotype.Component;



/**
 * 由于通过<http/>标签默认会配置上一个RequestCacheAwareFilter,而项目里却没使用默认的RequestCache机制,
 * 原来RequestCacheAwareFilter中的的HttpSessionRequestCache是将登录前的请求存放到Session里面,
 * 然后登录成功后利用SavedRequestAwareAuthenticationSuccessHandler重新取出Session里的请求,然后重定向回去原来的页面
 * 
 * 
 * 故在应用的上下文启动完成前将其删除!
 * 
 * @author KEN
 *
 */



@Component
public class RequestCacheAwareFilterDeleter  
{
	@Autowired
	private FilterChainProxy filterChainProxy;
		
	
	
	@PostConstruct
	public void init() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		List<SecurityFilterChain> filterChains = filterChainProxy.getFilterChains();
		
		for(SecurityFilterChain filterChain : filterChains)
		{
			List<Filter> filters = filterChain.getFilters();
		
			List<Filter> filtersToDelete = new ArrayList<Filter>();
		
			for(Filter filter : filters)
			{
				if(filter instanceof RequestCacheAwareFilter)
				{
					filtersToDelete.add(filter);
				}
			}
			
			for(Filter filter : filtersToDelete)
			{
				filters.remove(filter);
			}
		}
		
		//---将修改后的filterChains,利用反射写用filterChainProxy里
		Field field = filterChainProxy.getClass().getDeclaredField("filterChains");
		field.setAccessible(true);
		
		field.set(filterChainProxy, filterChains);
		
	}

}
