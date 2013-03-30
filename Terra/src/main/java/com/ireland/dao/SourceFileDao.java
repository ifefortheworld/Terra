package com.ireland.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.ireland.dao.support.SimpleMongoDao;
import com.ireland.model.business.SourceFile;



/**
 * 
 * @KEN
 *
 */

@Repository("sourceFileDao")
public class SourceFileDao extends SimpleMongoDao<SourceFile, String>
{
	
	@Autowired
	public SourceFileDao(MongoTemplate mongoTemplate)
	{
		super(mongoTemplate, SourceFile.class);
	}
	
}
