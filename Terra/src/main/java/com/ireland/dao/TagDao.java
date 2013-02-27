package com.ireland.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.ireland.dao.support.SimpleMongoDao;
import com.ireland.model.business.Tag;



/**
 * 
 * @author 吉林大学珠海学院1队
 *
 */

@Repository("tagDao")
public class TagDao extends SimpleMongoDao<Tag, String>
{
	
	@Autowired
	public TagDao(MongoTemplate mongoTemplate)
	{
		super(mongoTemplate, Tag.class);
	}
	
}
