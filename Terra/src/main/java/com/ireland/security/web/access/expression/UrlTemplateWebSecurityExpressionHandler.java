package com.ireland.security.web.access.expression;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

/**
 * 
 * 增加了对UrlTemplate相关(如路径变量PathVariable)的控制的SpEL 4 WebSecurityExpressionRoot
 * 
 * 注意:只适用于model.User的情况!
 * 
 * @author 吉林大学珠海学院1队
 * @since 2012-06-01
 *
 */

@Component("urlTemplateWebSecurityExpressionHandler")
public class UrlTemplateWebSecurityExpressionHandler extends
		DateWebSecurityExpressionHandler
{
	
	@Override
	protected SecurityExpressionRoot createSecurityExpressionRoot(
			Authentication authentication, FilterInvocation fi)
	{
		UrlTemplateWebSecurityExpressionRoot root = new UrlTemplateWebSecurityExpressionRoot(authentication, fi);
		
		root.setPermissionEvaluator(getPermissionEvaluator());
	    
		return root;
	}

}
