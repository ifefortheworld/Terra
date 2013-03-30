package com.ireland.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.ireland.dao.support.SimpleMongoDao;
import com.ireland.model.business.File;



/**
 * 
 * @KEN
 *
 */

@Repository("fileDao")
public class FileDao extends SimpleMongoDao<File, String>
{
	
	@Autowired
	public FileDao(MongoTemplate mongoTemplate)
	{
		super(mongoTemplate, File.class);
	}
	
}
