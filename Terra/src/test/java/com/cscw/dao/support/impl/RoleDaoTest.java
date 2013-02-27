package com.cscw.dao.support.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.ireland.dao.AuthorityDao;
import com.ireland.dao.RoleDao;
import com.ireland.model.Authority;
import com.ireland.model.Role;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/mongo-config.xml","/applicationContext.xml"})
public class RoleDaoTest
{
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private AuthorityDao authorityDao;

	@Test
	public void insert()
	{
		Authority auth = authorityDao.findOne("authority", "index");
		
		Role role = new Role();
		role.setRole("ROLE_LEADER");
		role.setName("领导");
	
		List<Authority> authorities = new ArrayList<Authority>();
		
		authorities.add(auth);
		
		role.setAuthorities(authorities);
		
		
		roleDao.insert(role);
		
	}
	
	
	@Test
	public void save()
	{
		Authority auth = authorityDao.findOne("authority", "index");
		
		
		Role role = roleDao.findOne("role", "ROLE_ADMIN");
		
		role.setName("管理员");
			
		
		List<Authority> authorities = new ArrayList<Authority>();
		
		authorities.add(auth);
		
		role.setAuthorities(authorities);
		
		roleDao.save(role);
		
	}
	
	
	

}
