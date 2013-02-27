package com.ireland.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;

import com.ireland.model.Authority;

public interface AuthorityService
{
	/**
	 * 向数据库增加一个权限
	 * @param authority
	 * @return 
	 */
	Authority addAuthority(Authority authority);
	
	
	/**
	 * 删除一个权限,所有角色,用户对此权限的引用将被移除
	 * 
	 * 1:移除所有角色对此权限的引用
	 * 2:删除此权限
	 * 
	 * @param id
	 */
	void deleteAuthority(String authorityName);
	
	
	/**
	 * 取得一个权限
	 * @param id
	 * @return
	 */
	Authority getAuthorityById(String id);
	
	/***
	 * 权限的名字得到对应权限
	 * @param authority
	 * @return
	 */
	Authority getAuthority(String authority);
	
	/**
	 * 取得所有的权限
	 * 
	 */
	List<Authority> getAllAuthority();
	
	/**
	 * 取得某角色的所有权限
	 * 
	 */
	List<Authority> getAuthorityInRole(String roleName);
	
	/**
	 * 取得某角色的没有的权限
	 * 
	 */
	List<Authority> getAuthorityNotInRole(String roleName);
	
	/**
	 * 取得某用户未拥有的权限
	 * 
	 * @param roleName
	 * @return
	 */
	List<? extends GrantedAuthority> getAuthorityNotInUser(String userName) ;

	


	/**
	 * 
	 * 取得用户拥有的权限
	 * @param userName
	 * @return
	 */
	List<? extends GrantedAuthority> getAllAuthorityInUser(String userName);


	/**
	 * 得到角色内对应权限的Map
	 * 
	 * @return
	 */
	Map<String, List<Authority>> getMapAuthorityToRole();



	
	
}
