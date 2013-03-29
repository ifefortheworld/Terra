package com.ireland.service;

import java.util.List;

import com.ireland.model.User;

/**
 * 
 * @KEN
 * 
 */

public interface UserService {
	/**
	 * 通过ID查询到用户
	 * 
	 * @param id
	 * @return
	 */
	User getUser(String id);



	/**
	 * 统计所有用户数量
	 * 
	 * @return
	 */
	Long count();




	List<User> getUserByProperty(String propertyName, Object value);

	User getOneUserByProperty(String propertyName, Object value);

	List<User> getAllUser();


}
