package com.ireland.model;



import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 抽象的"角色",每个"角色"都拥有特定的一组"权限"..... ---for 分组权限
 * 每个用户可能有多种"角色",利用"角色"实现粗粒度的"分组权限"的分配
 * 为了与完全基于"角色"的权限机制兼容,Role的authorities属性至少包含"权限":ROLE_XXX
 * 
 * @KEN, chenqipeng
 * 
 */


@Document
public class Role {

	/**
	 * 用户组（角色）内部编码 
	 */
	@Id
	private String id;

	/**
	 * 用户组（角色）名
	 */
	@NotEmpty(message = "角色不能为空")
	@Indexed(unique = true)
	private String role; 

	
	@DBRef
	private List<Authority> authorities;

	
    //业务属性-------------------------------------------------
	
	/**
	 * 角色中文名
	 */
	private String name;
	
	
	/**
	 * 开关状态,默认true
	 */
	private Boolean enable = true;
	
	
	
    //END业务属性-------------------------------------------------


	
//get & set------------------------------------------------------------
	
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	public List<Authority> getAuthorities()
	{
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities)
	{
		this.authorities = authorities;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}


	public Boolean getEnable()
	{
		return enable;
	}


	public void setEnable(Boolean enable)
	{
		this.enable = enable;
	}
	
}
