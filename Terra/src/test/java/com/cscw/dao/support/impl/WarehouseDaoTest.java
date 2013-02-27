package com.cscw.dao.support.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.ireland.dao.WarehouseDao;
import com.ireland.model.business.Warehouse;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/mongo-config.xml","/applicationContext.xml"})
public class WarehouseDaoTest
{
	@Autowired
	private WarehouseDao warehouseDao;

	@Test
	public void save()
	{
		Warehouse a = warehouseDao.findOne("shortName", "CPD");
		a.setName("发货仓库CPD");
		a.setShortName("CPD");
		a.setAddress("上海嘉定区园汽路1000号");
		a.setArea(42000);
		a.setType(2);
		
		warehouseDao.save(a);
		
		Warehouse b = warehouseDao.findOne("shortName", "1101");
		b.setName("发货仓库1101");
		b.setShortName("1101");
		b.setAddress("上海嘉定区民丰路24号");
		b.setArea(3600);
		b.setType(0);
		
		warehouseDao.save(b);
		
		Warehouse c = warehouseDao.findOne("shortName", "1102");
		c.setName("发货仓库1102");
		c.setShortName("1102");
		c.setAddress("上海嘉定于塘路379号");
		c.setArea(15000);
		c.setType(0);
		
		warehouseDao.save(c);
		
		
		Warehouse d = new Warehouse();
		d.setName("发货仓库1103");
		d.setShortName("1103");
		d.setAddress("上海嘉定区园工路1169号");
		d.setArea(1000);
		d.setType(0);
		
		warehouseDao.insert(d);
		
		Warehouse e = new Warehouse();
		e.setName("发货仓库1104");
		e.setShortName("1104");
		e.setAddress("上海嘉定区园国路1366号");
		e.setArea(14500);
		e.setType(0);
		
		warehouseDao.insert(e);
		
		
		Warehouse f = new Warehouse();
		f.setName("发货仓库1105");
		f.setShortName("1105");
		f.setAddress("昆山市淀山湖镇北苑路288号");
		f.setArea(25000);
		f.setType(0);
		
		warehouseDao.insert(f);
		
		Warehouse g = new Warehouse();
		g.setName("发货仓库9106");
		g.setShortName("9106");
		g.setAddress("上海嘉定区泰丰路225号");
		g.setArea(2118);
		g.setType(0);
		
		warehouseDao.insert(g);
		
		Warehouse h = new Warehouse();
		h.setName("非发货仓库1001");
		h.setShortName("1001");
		h.setAddress("上海市嘉定区安亭镇墨玉北路98号");
		h.setArea(5000);
		h.setType(1);
		
		warehouseDao.insert(h);
		
		Warehouse i = new Warehouse();
		i.setName("非发货仓库1002");
		i.setShortName("1002");
		i.setAddress("上海市嘉定区和静东路318号");
		i.setArea(9000);
		i.setType(1);
		
		warehouseDao.insert(i);
	}
	
	
	
	

}
