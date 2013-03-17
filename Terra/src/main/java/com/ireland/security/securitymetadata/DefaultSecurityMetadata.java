package com.ireland.security.securitymetadata;

import javax.persistence.Entity;


/**
 * 
 * @author 吉林大学珠海学院1队
 *
 * <intercept-url pattern="/url" access="permitAll" method="POST" requires-channel="https"/>
 * 
 * 以上元数据的持久化对象
 *
 *
 */
@Entity
public class DefaultSecurityMetadata implements SecurityMetadata
{
	private String id;
	
	/**
	 * The pattern which defines the URL path. 
	 * The content will depend on the type set in the containing http element, so will default to ant path syntax.
	 * URL的模式,可以用通配符,也可以是一个简单URL,如/home.jsp
	 */
	private String urlPattern;
	
	
	/**
	 * The access configuration attributes that apply for the configured path.
	 * Spring Security 的SPEL格式的!
	 */
	private String securityConfigAttribute;
	
	
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
	private String httpMethod;
	
	/**
	Attribute : requires-channel
	Used to specify that a URL must be accessed over http or https, or that there is no preference. 
	The value should be "http", "https" or "any", respectively.
	
	如果SecurityMetadata.requires_channel == null,则不限制channel
	 */
	private String httpChannel;

	
	

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	@Override
	public String getUrlPattern()
	{
		return urlPattern;
	}

	public void setUrlPattern(String urlPattern)
	{
		this.urlPattern = urlPattern;
	}

	
	@Override
	public String getSecurityConfigAttribute()
	{
		return securityConfigAttribute;
	}

	public void setSecurityConfigAttribute(String securityConfigAttribute)
	{
		this.securityConfigAttribute = securityConfigAttribute;
	}

	@Override
	public String getHttpMethod()
	{
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod)
	{
		this.httpMethod = httpMethod;
	}

	@Override
	public String getHttpChannel()
	{
		return httpChannel;
	}

	public void setHttpChannel(String httpChannel)
	{
		this.httpChannel = httpChannel;
	}

	
}
