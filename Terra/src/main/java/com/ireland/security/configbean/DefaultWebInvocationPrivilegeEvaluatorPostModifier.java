package com.ireland.security.configbean;

import java.lang.reflect.Field;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator;
import org.springframework.stereotype.Component;

import com.ireland.security.web.access.intercept.RefreshableFilterSecurityInterceptor;

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
public class DefaultWebInvocationPrivilegeEvaluatorPostModifier  
{
	private DefaultWebInvocationPrivilegeEvaluator webInvocationPrivilegeEvaluator;
	
	private RefreshableFilterSecurityInterceptor filterSecurityInterceptor;

	@Autowired
	public void setWebInvocationPrivilegeEvaluator(
			DefaultWebInvocationPrivilegeEvaluator webInvocationPrivilegeEvaluator)
	{
		this.webInvocationPrivilegeEvaluator = webInvocationPrivilegeEvaluator;
	}

	@Resource(name="refreshableFilterSecurityInterceptor")
	public void setFilterSecurityInterceptor(RefreshableFilterSecurityInterceptor filterSecurityInterceptor)
	{
		this.filterSecurityInterceptor = filterSecurityInterceptor;
	}
	
	
	@PostConstruct
	public void init()
	{
		try
		{
			
			Field field = webInvocationPrivilegeEvaluator.getClass().getDeclaredField("securityInterceptor");
			
			field.setAccessible(true);
			
		
			field.set(webInvocationPrivilegeEvaluator, filterSecurityInterceptor);
					
		}
		catch (IllegalArgumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NoSuchFieldException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
