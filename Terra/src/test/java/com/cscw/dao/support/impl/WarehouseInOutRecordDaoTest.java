package com.cscw.dao.support.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ireland.dao.ClientDao;
import com.ireland.dao.DriverDao;
import com.ireland.dao.TrackDao;
import com.ireland.dao.WarehouseDao;
import com.ireland.dao.WarehouseInOutRecordDao;
import com.ireland.model.business.Warehouse;
import com.ireland.model.business.WarehouseInOutRecord;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/mongo-config.xml","/applicationContext.xml"})
public class WarehouseInOutRecordDaoTest
{
	@Autowired
	private WarehouseInOutRecordDao dao;

	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private DriverDao driverDao;
	
	@Autowired
	private TrackDao trackDao;

	
	@Autowired
	private WarehouseDao warehouseDao;
	
	@Test
	public void insert()
	{
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		
		WarehouseInOutRecord a = new WarehouseInOutRecord();
		
		a.setNo("NO130102001");
		a.setClient(clientDao.findOne("shortName", "上海大众"));
		a.setTrack(trackDao.findOne("licenseNumber", "吉A9999S"));
		a.setWarehouse(warehouseDao.findOne("name", "广州天河仓"));
		
		a.setGood("轮胎");
		a.setQuantity(100F);
		a.setUnit("条");
		
		a.setDate(new Date());
		a.setCategory("入库");
		a.setHandlingCharge(1000F);
		a.setStorageCharge(2000F);
		a.setTransportationCharge(3000F);
		a.setOtherCharge(100F);
		
		dao.insert(a);
		
		WarehouseInOutRecord b = new WarehouseInOutRecord();
		
		b.setNo("NO130102002");
		b.setClient(clientDao.findOne("shortName", "上海通用"));
		b.setTrack(trackDao.findOne("licenseNumber", "吉B8250S"));
		b.setWarehouse(warehouseDao.findOne("name", "北京朝阳仓"));
		
		b.setGood("轴承");
		b.setQuantity(50F);
		b.setUnit("箱");
		
		b.setDate(new Date());
		b.setCategory("入库");
		b.setHandlingCharge(100F);
		b.setStorageCharge(200F);
		b.setTransportationCharge(300F);
		b.setOtherCharge(100F);
		
		dao.insert(b);
		
		WarehouseInOutRecord c = new WarehouseInOutRecord();
		
		c.setNo("NO130102003");
		c.setClient(clientDao.findOne("shortName", "浙江吉利"));
		c.setTrack(trackDao.findOne("licenseNumber", "吉A8888S"));
		c.setWarehouse(warehouseDao.findOne("name", "广州天河仓"));
		
		c.setGood("前置大灯");
		c.setQuantity(30F);
		c.setUnit("箱");
		
		c.setDate(new Date());
		c.setCategory("入库");
		c.setHandlingCharge(100F);
		c.setStorageCharge(200F);
		c.setTransportationCharge(300F);
		c.setOtherCharge(100F);
		
		dao.insert(c);
		
		WarehouseInOutRecord d = new WarehouseInOutRecord();
		
		d.setNo("NO130102004");
		d.setClient(clientDao.findOne("shortName", "安徽奇瑞"));
		d.setTrack(trackDao.findOne("licenseNumber", "吉A7777S"));
		d.setWarehouse(warehouseDao.findOne("name", "上海浦东仓"));
		
		d.setGood("发动机");
		d.setQuantity(10F);
		d.setUnit("台");
		
		d.setDate(new Date());
		d.setCategory("入库");
		d.setHandlingCharge(100F);
		d.setStorageCharge(200F);
		d.setTransportationCharge(300F);
		d.setOtherCharge(100F);
		
		dao.insert(d);
		
	
		
		
		
	}
	
	
	

}
