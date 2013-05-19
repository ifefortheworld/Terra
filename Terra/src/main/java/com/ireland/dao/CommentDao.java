package com.ireland.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.ireland.dao.support.SimpleMongoDao;
import com.ireland.model.business.Comment;



/**
 * 
 * @KEN
 *
 */

@Repository("commentDao")
public class CommentDao extends SimpleMongoDao<Comment, String>
{
	
	@Autowired
	public CommentDao(MongoTemplate mongoTemplate)
	{
		super(mongoTemplate, Comment.class);
	}
	
}
