package com.ireland.security.securitymetadata;


/**
 * 
 * @KEN
 *
 * <intercept-url pattern="/url" access="permitAll" method="POST" requires-channel="https"/>
 * 
 * 以上元数据的持久化对象
 *
 *
 */
public interface SecurityMetadata
{
	
	/**
	 * The pattern which defines the URL path. 
	 * The content will depend on the type set in the containing http element, so will default to ant path syntax.
	 * URL的模式,可以用通配符,也可以是一个简单URL,如/home.jsp
	 */
	String getUrlPattern();
	
	
	/**
	 * The access configuration attributes that apply for the configured path.
	 *
	 * 对应XML配置的<intercept-url access="" >元素
	 * 
	 * link:org.springframework.security.access.ConfigAttribute
	 * 
	 * Spring Security 的SPEL格式的!
	 */
	String getSecurityConfigAttribute();

	
	/**
	Attribute : method
	The HTTP Method for which the access configuration attributes should apply. If not specified, the 
	 attributes will apply to any method.
	
	Data Type : string
	Enumerated Values : 
		- GET
		- DELETE
		- HEAD
		- OPTIONS
		- POST
		- PUT
		- TRACE
		
		如果SecurityMetadata.method == null,则不限制HTTP方法
	 */
	String getHttpMethod();

	
	/**
	Attribute : requires-channel
	Used to specify that a URL must be accessed over http or https, or that there is no preference. 
	The value should be "http", "https" or "any", respectively.
	
	如果SecurityMetadata.requires_channel == null,则不限制channel
	 */
	String getHttpChannel();
}
