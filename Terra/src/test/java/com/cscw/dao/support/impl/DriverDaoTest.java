package com.cscw.dao.support.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.ireland.dao.DriverDao;
import com.ireland.dao.TrackDao;
import com.ireland.model.business.Driver;
import com.ireland.model.business.Track;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/mongo-config.xml","/applicationContext.xml"})
public class DriverDaoTest
{
	@Autowired
	private DriverDao driverDao;
	
	@Autowired
	private TrackDao trackDao;

	@Test
	public void insert()
	{
		Track ta = trackDao.findOne("licenseNumber", "吉A9999S");
		
		Driver a = new Driver();
		a.setName("陈明德");
		a.setTrack(ta);
		
		driverDao.insert(a);
		
		Track tb = trackDao.findOne("licenseNumber", "吉B8250S");
		
		Driver b = new Driver();
		b.setName("冯军");
		b.setTrack(tb);
		
		driverDao.insert(b);
		
		Track tc = trackDao.findOne("licenseNumber", "吉A8888S");
		
		Driver c = new Driver();
		c.setName("张小龙");
		c.setTrack(tc);
		
		driverDao.insert(c);
		
		Track td = trackDao.findOne("licenseNumber", "吉A7777S");
		
		Driver d = new Driver();
		d.setName("王一辉");
		d.setTrack(td);
		
		driverDao.insert(d);
	}
	
	
	

}
