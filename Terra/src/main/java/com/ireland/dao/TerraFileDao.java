package com.ireland.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.ireland.dao.support.SimpleMongoDao;
import com.ireland.model.business.TerraFile;



/**
 * 
 * @author 吉林大学珠海学院1队
 *
 */

@Repository("terraFileDao")
public class TerraFileDao extends SimpleMongoDao<TerraFile, String>
{
	
	@Autowired
	public TerraFileDao(MongoTemplate mongoTemplate)
	{
		super(mongoTemplate, TerraFile.class);
	}
	
}
