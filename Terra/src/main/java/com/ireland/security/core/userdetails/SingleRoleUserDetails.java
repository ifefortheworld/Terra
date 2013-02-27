package com.ireland.security.core.userdetails;



import org.springframework.security.core.userdetails.UserDetails;

import com.ireland.model.Role;

/**
 * 
 * 拥有单个角色的"分组权限"(一个角色实质代表一个分组,每个角色里有一组权限)的UserDetails,
 * 这使得"角色"和"权限"的概念上更明确了!
 * 
 * 
 * @author 吉林大学珠海学院1队
 *
 */


public interface SingleRoleUserDetails extends UserDetails
{
	/**
	 * 返回用户的角色
	 */
	Role getRole();
}
