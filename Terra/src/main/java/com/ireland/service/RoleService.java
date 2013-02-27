package com.ireland.service;

import java.util.List;

import com.ireland.model.Role;

public interface RoleService
{
	/**
	 * 通过Role name 查询Role对象
	 * 
	 * @param roleName
	 * @return
	 */
	
	Role getRole(String roleName);
	
	/**
	 * 取得所有角色
	 * 
	 */
	List<Role> getAllRole();
	
	
	/**
	 * 增加一个角色
	 * 
	 * @param role
	 * @return
	 */
	
	void addRole(Role role);
	
	
	/**
	 * 删除一个角色,此操作并不会删除关联的权限!
	 * 而所有用户对此角色的引用将会被移除
	 * 
	 * 1:移除所有用户对此角色的引用
	 * 2:删除此角色
	 * 
	 * @param roleName
	 */
	
	void deleteRole(String roleName);

	
	
	/**
	 * 向角色增加一个已存在的权限
	 * 
	 * @param roleName
	 * @param authorityName
	 */
	void addAuthorityToRole(String roleName,String authorityName);
	
	
	
	
	/**
	 * 向角色移除一个权限(如果该角色并未持有该权限,则不进行任何操作)
	 * 注意:仅移除角色与权限的关联关系
	 * 
	 * @param roleName
	 * @param authorityName
	 */
	void removeAuthorityFromRole(String roleName,String authorityName);
	



}
