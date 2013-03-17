package com.ireland.security.web.access.expression;

import org.springframework.security.access.expression.AbstractSecurityExpressionHandler;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionHandler;

/***
 * 使用DateWebSecurityExpressionRoot,与DefaultWebSecurityExpressionHandler对比,
 * 增加了日期的控制
 * 
 * 
 * @author 吉林大学珠海学院1队
 * @since 2012-05-30
 */

public class DateWebSecurityExpressionHandler extends AbstractSecurityExpressionHandler<FilterInvocation> implements WebSecurityExpressionHandler
{
	private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

	@Override
	protected SecurityExpressionRoot createSecurityExpressionRoot(
			Authentication authentication, FilterInvocation fi)
	{
		DateWebSecurityExpressionRoot root = new DateWebSecurityExpressionRoot(authentication, fi);
		
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(trustResolver);
        root.setRoleHierarchy(getRoleHierarchy());
	    
		return root;
	}

}
