package com.ireland.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.ireland.dao.support.SimpleMongoDao;
import com.ireland.model.Role;


@Repository("roleDao")
public class RoleDao extends SimpleMongoDao<Role, String>
{
	@Autowired
	public RoleDao(MongoTemplate mongoTemplate)
	{
		super(mongoTemplate, Role.class);
	}

}
