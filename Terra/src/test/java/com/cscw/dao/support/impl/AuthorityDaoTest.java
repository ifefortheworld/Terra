package com.cscw.dao.support.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.ireland.dao.AuthorityDao;
import com.ireland.model.Authority;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/mongo-config.xml","/applicationContext.xml"})
public class AuthorityDaoTest
{
	@Autowired
	private AuthorityDao authorityDao;

	@Test
	public void insert()
	{
		Authority authotiry = new Authority("index");
		
		authotiry.setUrlPattern("/");
		
		
		authorityDao.insert(authotiry);
		
	}
	
	
	
	
	@Test
	public void save()
	{
		Authority authotiry = authorityDao.findOne("authority", "index");
		
		authotiry.setDescription("访问主页");
		
		
		authorityDao.save(authotiry);
		
	}
	

}
