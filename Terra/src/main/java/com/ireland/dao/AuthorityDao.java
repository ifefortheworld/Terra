package com.ireland.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.ireland.dao.support.SimpleMongoDao;
import com.ireland.model.Authority;


@Repository("authorityDao")
public class AuthorityDao extends SimpleMongoDao<Authority, String>
{
	@Autowired
	public AuthorityDao(MongoTemplate mongoTemplate)
	{
		super(mongoTemplate, Authority.class);
	}

}
