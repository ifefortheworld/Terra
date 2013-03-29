package com.ireland.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.ireland.dao.support.SimpleMongoDao;
import com.ireland.model.business.RealFile;



/**
 * 
 * @KEN
 *
 */

@Repository("realFileDao")
public class RealFileDao extends SimpleMongoDao<RealFile, String>
{
	
	@Autowired
	public RealFileDao(MongoTemplate mongoTemplate)
	{
		super(mongoTemplate, RealFile.class);
	}
	
}
