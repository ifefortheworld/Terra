package com.ireland.security.web.access.expression;


import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

/**
 * 
 * WebSecurityExpressionRoot 里,有hasAuthority,但在spel里却不能写为!hasAuthority
 * 故写以下的工具
 * 
 * 
 * @author KEN
 * @since 2013-03-17
 *
 */


public class SimpleWebSecurityExpressionRoot extends UrlTemplateWebSecurityExpressionRoot
{

	public SimpleWebSecurityExpressionRoot(Authentication a,FilterInvocation fi)
	{
		super(a, fi);
	}
	
	//Util-------------------------------------------------------------
    public boolean notHasAuthority(String authority)
    {
    	return !super.hasAuthority(authority);
    }

    public boolean notHasAnyAuthority(String... authorities)
    {
    	return !super.hasAnyAuthority(authorities);
    }

    public boolean notHasRole(String role)
    {
    	return !super.hasRole(role);
    }

    public boolean notHasAnyRole(String... roles)
    {
    	return !super.hasAnyRole(roles);
    }

    public boolean isNotAnonymous()
    {
    	return !super.isAnonymous();
    }

    public boolean isNotAuthenticated()
    {
    	return !super.isAuthenticated();
    }

    public boolean isNotRememberMe()
    {
    	return !super.isRememberMe();
    }

    public boolean isNotFullyAuthenticated()
    {
    	return !super.isFullyAuthenticated();
    }

    public boolean notHasPermission(Object target, Object permission)
    {
    	return !super.hasPermission(target, permission);
    }

    public boolean notHasPermission(Object targetId, String targetType, Object permission)
    {
    	return !super.hasPermission(targetId, targetType, permission);
    }
    
    public boolean notHasIpAddress(String ipAddress)
    {
    	return !super.hasIpAddress(ipAddress);
    }

}
