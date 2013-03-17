package com.ireland.model;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import com.ireland.security.securitymetadata.SecurityMetadata;

/**
 * Basic concrete implementation of a {@link GrantedAuthority}. Stores a
 * {@code String} representation of an authority granted to the
 * {@link org.springframework.security.core.Authentication Authentication}
 * object.
 * 
 * @author Luke Taylor 基于简单字符串表示的权限(注意,最好仅用于表示权限,而不用于表示角色)
 * 
 * @author 吉林大学珠海学院1队, chenqipeng
 * 
 *         PS:如果一个功能没在用Authority在数据里配置,则默认可以直接访问这个功能
 */

@Document
public final class Authority implements GrantedAuthority, SecurityMetadata
{

	/**
	 * 业务功能内部编码-->module_id
	 */
	@Id
	private String id;

	/**
	 * 业务模块名称 -->Module_Name
	 */
	@NotEmpty(message = "权限不能为空")
	@Indexed(unique = true)
	private String authority;


	
// SecurityMetadata-------------------------------------------------
	/**
	 * UrlPattern
	 */
	@NotEmpty(message = "url不能为空")
	private String urlPattern;

	/**
	 * HttpMethod,null为不限制
	 */
	private String httpMethod;

	/**
	 * HttpChannel,null为不限制
	 */
	private String httpChannel;

	/**
	 * 开始时间,null为不限制
	 */
	private Date beginTime;

	/**
	 * 结束时间,null为不限制
	 */
	private Date endTime;

	/**
	 * 开关状态,默认true
	 */
	private boolean enable = true;

// END SecurityMetadata-------------------------------------------------

	
	
    //业务属性-------------------------------------------------
	
	/**
	 * 权限说明
	 */
	private String description;
	
	
	
    //END业务属性-------------------------------------------------

	// GrantedAuthority--------------------------------
	@Override
	public String getAuthority()
	{
		return authority;
	}


// SecurityMetadata-------------------------------------------------------

	@Override
	public String getUrlPattern()
	{
		return urlPattern;
	}

	@Override
	public String getHttpMethod()
	{
		return httpMethod;
	}

	@Override
	public String getHttpChannel()
	{
		return httpChannel;
	}

	// *implements SecurityMetadata
	/**
	 * 返回访问该功能的SpringSecurity配置
	 */
	@Override
	public String getSecurityConfigAttribute()
	{
		// 如果功能不开放,直接返回denyAll,使所以请求都不访问此功能
		if (!isEnable())
			return "denyAll";

		// 构造基本权限控制
		// hasAuthority('AUTH_XXX');
		String spel = " hasAuthority('" + this.getAuthority() + "') ";

		// 构造日期控制
		// after(2011, 12, 31)
		if (this.getBeginTime() != null)
			spel += " and after(" + (this.getBeginTime().getYear() + 1900)
					+ "," + (this.getBeginTime().getMonth() + 1) + ","
					+ this.getBeginTime().getDate() + ") ";

		// 构造日期控制
		// before(2011, 12, 31)
		if (this.getEndTime() != null)
			spel += " and before(" + (this.getEndTime().getYear() + 1900) + ","
					+ (this.getEndTime().getMonth() + 1) + ","
					+ this.getEndTime().getDate() + ") ";

		return spel;
	}

// SecurityMetadata-------------------------------------------------------

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}

		if (obj instanceof Authority)
		{
			return authority.equals(((Authority) obj).authority);
		}

		return false;
	}

	@Override
	public int hashCode()
	{
		return this.authority.hashCode();
	}

	@Override
	public String toString()
	{
		return this.authority;
	}

	
	
//Constractor--------------------------------------	
	public Authority(String authority)
	{

		Assert.hasText(authority,
				"A granted authority textual representation is required");

		this.authority = authority;
	}
	
	
//gettter and set--------------------
	
	

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}


	public void setAuthority(String authority)
	{
		this.authority = authority;
	}

	public Date getBeginTime()
	{
		return beginTime;
	}

	public void setBeginTime(Date beginTime)
	{
		this.beginTime = beginTime;
	}

	public Date getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	public boolean isEnable()
	{
		return enable;
	}

	public void setEnable(boolean enable)
	{
		this.enable = enable;
	}

	public void setUrlPattern(String urlPattern)
	{
		this.urlPattern = urlPattern;
	}

	public void setHttpMethod(String httpMethod)
	{
		this.httpMethod = httpMethod;
	}

	public void setHttpChannel(String httpChannel)
	{
		this.httpChannel = httpChannel;
	}


	public String getDescription()
	{
		return description;
	}


	public void setDescription(String description)
	{
		this.description = description;
	}
	
}