package com.cscw.dao.support.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.ireland.dao.TrackDao;
import com.ireland.model.business.Track;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/mongo-config.xml","/applicationContext.xml"})
public class TrackDaoTest
{
	@Autowired
	private TrackDao trackDao;

	@Test
	public void insert()
	{
		Track a = new Track();
		a.setLicenseNumber("吉A9999S");
		a.setModel("东风 大力神重卡");
		a.setTravelledDistance(1000);
		a.setLoad(40);
		
		trackDao.insert(a);
		
		Track b = new Track();
		b.setLicenseNumber("吉B8250S");
		b.setModel("东风 天龙重卡");
		b.setTravelledDistance(800);
		b.setLoad(35);
		
		trackDao.insert(b);
		
		Track c = new Track();
		c.setLicenseNumber("吉A8888S");
		c.setModel("东风 新天龙重卡");
		c.setTravelledDistance(800);
		c.setLoad(35);
		
		trackDao.insert(c);
		
		Track d = new Track();
		d.setLicenseNumber("吉A7777S");
		d.setModel("东风 新天龙重卡");
		d.setTravelledDistance(1800);
		d.setLoad(40);
		
		trackDao.insert(d);
	}
	
	
	

}
