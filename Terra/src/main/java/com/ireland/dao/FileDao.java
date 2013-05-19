package com.ireland.dao;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	/**
	 * 这个方法能确保结果是按ids的顺序来排序的
	 * @param ids
	 * @return
	 */
	public List<File> findAll(List<String> ids)
	{
		Iterable<File> _list = findAll((Iterable<String>)ids);
		
		Map<String,File> map = new HashMap<String,File>(ids.size());
		
		for(File e: _list)
		{
			map.put(e.getId(), e);
		}
		
		List<File> list = new ArrayList<File>(ids.size());
		
		for(String id : ids)
		{
			File e = map.get(id);
			if(e != null)
				list.add(e);
		}
		
		return list;
	}
	
}
