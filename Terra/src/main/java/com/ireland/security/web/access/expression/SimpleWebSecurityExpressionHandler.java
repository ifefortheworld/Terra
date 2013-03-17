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
 * WebSecurityExpressionRoot 里,有hasAuthority,但在spel里却不能写为!hasAuthority
 * 
 * 注意:只适用于model.User的情况!
 * 
 * @author 吉林大学珠海学院1队
 * @since 2012-06-01
 *
 */

public class SimpleWebSecurityExpressionHandler extends AbstractSecurityExpressionHandler<FilterInvocation> implements WebSecurityExpressionHandler
{
	private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
	
	@Override
	protected SecurityExpressionRoot createSecurityExpressionRoot(Authentication authentication, FilterInvocation fi)
	{
		SimpleWebSecurityExpressionRoot root = new SimpleWebSecurityExpressionRoot(authentication, fi);
		
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(trustResolver);
        root.setRoleHierarchy(getRoleHierarchy());
	    
		return root;
	}

}
