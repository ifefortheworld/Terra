package com.ireland.security.userdetails;

import org.springframework.security.provisioning.UserDetailsManager;

/**
 * UserDetailsManager 的扩展,增加一些常用的用户管理操作!
 * 
 * @author Spring
 *
 */

public interface UserDetailsExtManager extends UserDetailsManager
{
	/**
	 * 删除指定ID的用户
	 * @param id
	 */
	void deleteUserById(String id);
}
