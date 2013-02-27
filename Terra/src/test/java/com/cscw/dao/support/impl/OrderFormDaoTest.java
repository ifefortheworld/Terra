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
import com.ireland.dao.OrderFormDao;
import com.ireland.dao.TrackDao;
import com.ireland.dao.WarehouseDao;
import com.ireland.dao.WarehouseInOutRecordDao;
import com.ireland.model.business.OrderForm;
import com.ireland.model.business.Warehouse;
import com.ireland.model.business.WarehouseInOutRecord;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/mongo-config.xml","/applicationContext.xml"})
public class OrderFormDaoTest
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
	
	@Autowired
	private OrderFormDao orderFormDao;
	
	@Test
	public void insert()
	{
		for(int i=0; i<10; i++)
		{
			OrderForm f = new OrderForm();
			
			f.setcContactPerson("梁志峰");
			f.setClient(clientDao.findOne("shortName", "上海大众"));
			f.setContractNumber("15989146997");
			f.setCost(1000L);
			f.setFinished(false);
			f.setGoods("齿轮");
			f.set
			
			
		}
	
		
		
		
	}
	
	
	

}
