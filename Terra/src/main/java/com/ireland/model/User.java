package com.ireland.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import com.ireland.security.core.userdetails.SingleRoleUserDetails;

/**
 * 访问数据库,提取个人信息
 * 
 * 一个用户只有一个角色
 * 
 * @author 吉林大学珠海学院1队
 * 
 */

@Document
public class User implements SingleRoleUserDetails 
{
	@Id
	private String id;

	/**
	 * 登录的用户名
	 */
	
	@NotEmpty(message = "用户名不能为空")
	@Size(min = 3, max = 24)
	@Indexed(unique = true)
	private String username;

	/**
	 * 登录的密码
	 */
	@NotEmpty(message = "密码不能为空")
	@Size(min = 6, max = 40)
	private String password;



	
	/**
	 *  粗粒度的分组权限
	 */
	@Valid
	@DBRef
	private Role role;

	
	/*
	 * 真实姓名
	 */
	private String trueName;
	

	
//UserDetails----------------------------------spring security---------------------------------------//

	@Override
	public String getUsername()
	{
		return username;
	}
	
	
	@Override
	public String getPassword()
	{
		return password;
	}

	
	@Override
	public List<? extends GrantedAuthority> getAuthorities()
	{
		Role role = getRole();
		
		if(role != null)
			return role.getAuthorities();
		
		return new ArrayList<Authority>();
	}

	
	
	
	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}


	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}


	@Override
	public boolean isEnabled()
	{
		return true;
	}
//END UserDetails----------------------------------spring security---------------------------------------//

	

	@Override
	public Role getRole()
	{
		return role;
	} 


//Getter and setter------------------------------------------------------
	
	public String getId()
	{
		return id;
	}


	public void setId(String id)
	{
		this.id = id;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}


	public void setPassword(String password)
	{
		this.password = password;
	}


	public void setRole(Role role)
	{
		this.role = role;
	}


	public String getTrueName()
	{
		return trueName;
	}


	public void setTrueName(String trueName)
	{
		this.trueName = trueName;
	}

}