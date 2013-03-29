package com.ireland.security.web.access.expression;

import org.springframework.security.access.expression.AbstractSecurityExpressionHandler;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionHandler;

/**
 * 
 * 增加了对UrlTemplate相关(如路径变量PathVariable)的控制的SpEL 4 WebSecurityExpressionRoot
 * 
 * 注意:只适用于model.User的情况!
 * 
 * @KEN
 * @since 2012-06-01
 *
 */

public class UrlTemplateWebSecurityExpressionHandler extends AbstractSecurityExpressionHandler<FilterInvocation> implements WebSecurityExpressionHandler
{
	private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
	
	@Override
	protected SecurityExpressionRoot createSecurityExpressionRoot(Authentication authentication, FilterInvocation fi)
	{
		UrlTemplateWebSecurityExpressionRoot root = new UrlTemplateWebSecurityExpressionRoot(authentication, fi);
				
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(trustResolver);
        root.setRoleHierarchy(getRoleHierarchy());
	    
		return root;
	}

}
