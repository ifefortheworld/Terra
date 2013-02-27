package com.cscw.dao.support.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.ireland.dao.ClientDao;
import com.ireland.model.business.Client;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/mongo-config.xml","/applicationContext.xml"})
public class ClientDaoTest
{
	@Autowired
	private ClientDao dao;

	@Test
	public void insert()
	{
		Client a = new Client();
		a.setName("上海大众汽车有限公司");
		a.setShortName("上海大众");
		
		dao.insert(a);
		
		Client b = new Client();
		b.setName("上海通用汽车有限公司");
		b.setShortName("上海通用");
		
		dao.insert(b);
		
		Client c = new Client();
		c.setName("安徽奇瑞汽车有限公司");
		c.setShortName("安徽奇瑞");
		
		dao.insert(c);
		
		Client d = new Client();
		d.setName("浙江吉利汽车有限公司");
		d.setShortName("浙江吉利");
		
		dao.insert(d);
	}
	
	
	

}
