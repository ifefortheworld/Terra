package com.ireland.security.context;

import org.springframework.beans.factory.Aware;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;


/**
 * 主要用于AbstractSecurityInterceptor的子类,如:FilterSecurityInterceptor 和 MethodSecurityInterceptor,
 * 标识其可以设置SecurityMetadataSource
 * 
 * @author 吉林大学珠海学院1队
 *
 */
public interface SecurityMetadataSourceAware extends Aware
{
	 void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource newSource);
}
