package com.cscw.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import com.ireland.model.Authority;
import com.ireland.model.Role;
import com.ireland.service.AuthorityService;
import com.ireland.service.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext-db.xml","/applicationContext-service.xml","/applicationContext.xml"})//,"/applicationContext-em.xml", "/applicationContext-trigger.xml
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class AuthorityServiceImplTest
{
	private AuthorityService authorityService;

	@Resource(name="authorityService")
	public void setAuthorityService(AuthorityService authorityService)
	{
		this.authorityService = authorityService;
	}
	
	private RoleService roleService;
	
	@Resource(name="roleService")
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}


	/**
	 * 向数据库增加一个权限
	 * @param authority
	 * @return 
	 */
	@Test
	public void addAnAuthorityTest()
	{
		Authority authority = new Authority("new_Auth3");
		
		authorityService.addAuthority(authority);
		
	}
	
	
	/**
	 * 向数据库增加一个权限
	 * @param authority
	 * @return 
	 */
	@Test
	public void addAuthorityTest()
	{
		Authority authority;
		
		authority = new Authority("Auth_authority");
		authority.setModule_URL("/authority");
		authorityService.addAuthority(authority);
		
		authority = new Authority("Auth_role");
		authority.setModule_URL("/role");
		authorityService.addAuthority(authority);
		
		authority = new Authority("Auth_userAuthConfig");
		authority.setModule_URL("/userAuthConfig");
		authorityService.addAuthority(authority);

	}
	
	
	/**
	 * 删除一个权限,所有角色,用户对此权限的引用将被移除
	 * 
	 * 1:移除所有角色对此权限的引用
	 * 2:移除所有用户对引权限的引用
	 * 3:删除引权限
	 * 
	 * @param authorityName
	 */
	@Test
	public void deleteAuthority()
	{
		
		authorityService.deleteAuthority("f2d899d6-ceea-44d4-8ad0-5c988b0226d5");
	}
	
	@Test
	public void getAuthorityInRoleTest() {
		
		
		for(Authority authority : authorityService.getAuthorityInRole("ROLE_ADMIN")) {
			
			System.out.println("\n\n\n" + authority.getAuthority());
		}
	}

	@Test
	public void getAuthorityNotInRoleTest() {
		
		for(Authority authority : authorityService.getAuthorityNotInRole("ROLE_ADMIN")) {
			
			System.out.println("\n\n\n" + authority.getAuthority());
		}
	}

	@Test
	public void getAllAuthorityInUserTest() {
		
		for(Authority authority : authorityService.getAllAuthorityInUser("testusername2")) {
			
			System.out.println("\n\n\n" + authority.getAuthority());
		}
	}
	
	@Test
	public void getAuthorityNotInUserTest() {
		
		for(Authority authority : authorityService.getAuthorityNotInUser("testusername2")) {
			
			System.out.println("\n\n\n" + authority.getAuthority());
		}
	}
	
	@Test
	public void getAllAuthorityTest() {
		
		for(Authority authority : authorityService.getAllAuthority()) {
			
			System.out.println("\n\n\n" + authority.getAuthority());
		}
	}
	
	@Test
	public void getAuthorityTest() {
		
		Authority authority = authorityService.getAuthority("reAUTH_deleteAuthority");
		
		System.out.println("\n\n\n"+authority.getAuthority());
	}
	
	@Test
	public void getAuthorityByIdTest() {
		
		Authority authority = authorityService.getAuthorityById("2f5fcb43-d219-4468-84e7-5fd6eb5768ae");
		
		System.out.println("\n\n\n"+authority.getAuthority());
	}
	
	@Test
	public void updataAuthorityTest() {
		
		authorityService.updateAuthoroty("AUTH_deleteAuthority", "reAUTH_deleteAuthority");
	}
	
	@Test
	public void getMapAuthorityToRole() {
		
		Map<String, List<Authority>> map = authorityService.getMapAuthorityToRole();
		
		List<Role> roles = roleService.getAllRole();
		
		for(Role role : roles) {
			String roleName = role.getRole();
			System.out.println("\n"+roleName+":");
			for(Authority authority : map.get(roleName)) {
				System.out.println(authority.getAuthority());
			}
		}
	}


}
