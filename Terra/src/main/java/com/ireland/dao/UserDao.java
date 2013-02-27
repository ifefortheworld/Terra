package com.ireland.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.ireland.dao.support.SimpleMongoDao;
import com.ireland.model.User;



/**
 * 
 * @author 吉林大学珠海学院1队
 *
 */

@Repository("userDao")
public class UserDao extends SimpleMongoDao<User, String>
{
	
	@Autowired
	public UserDao(MongoTemplate mongoTemplate)
	{
		super(mongoTemplate, User.class);
	}
	
}
