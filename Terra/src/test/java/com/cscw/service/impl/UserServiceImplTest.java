package com.cscw.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import com.ireland.dao.UserDao;
import com.ireland.model.Authority;
import com.ireland.model.Role;
import com.ireland.model.User;
import com.ireland.service.AuthorityService;
import com.ireland.service.RoleService;
import com.ireland.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-db.xml",
		"/applicationContext-service.xml", "/applicationContext.xml", "/applicationContext-security.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class UserServiceImplTest {
	private AuthorityService authorityService;

	@Resource(name = "authorityService")
	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	private RoleService roleService;

	@Resource(name = "roleService")
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	private UserService userService;

	@Resource(name = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private UserDetailsManager userDetailsManager;

	@Resource(name = "userDetailsManager")
	public void setUserDetailsManager(UserDetailsManager userDetailsManager) {

		this.userDetailsManager = userDetailsManager;
	}

	@Autowired
	private UserDao userDao;

	/**
	 * 向用户增加角色
	 * 
	 * @param username
	 * @param roleName
	 */
	@Test
	public void addRoleToUserTest() {
		/*userService.addRoleToUser("testusername1", "ROLE_ADMIN");
		userService.addRoleToUser("testusername1", "ROLE_TEACHER");
		userService.addRoleToUser("testusername1", "ROLE_DEAN");
		userService.addRoleToUser("testusername1", "ROLE_SUPERVISOR");
		userService.addRoleToUser("testusername1", "ROLE_ASSISTANT");*/
		
		//userService.addRoleToUser("testusername2", "ROLE_TEACHER");
		userService.addRoleToUser("testusername2", "ROLE_ADMIN");

		

	}

	/**
	 * 
	 * 从用户中删除角色
	 */
	@Test
	public void removeRoleFromUserTest() {

		//userService.removeRoleFromUser("testusername2", "ROLE_TEACHER");
		
		userService.removeRoleFromUser("testusername2", "ROLE_ADMIN");//该role里必须有权限

	}

	@Test
	public void createUser() {

		for (int i = 0; i <= 10; i++) {

			User user = new User();
			user.setUsername("testusername" + i);
			user.setPassword("testpassword" + i);
			user.setUser_No("311100"+i);
			user.setGender("male");
			user.setRegistrationTime(new Date());

			userDetailsManager.createUser(user);
		}
	}
	
	@Test
	public void createaUser() {

			User user = new User();
			user.setUsername("test2");
			user.setPassword("test");
			user.setUser_No("311100");
			user.setGender("male");
			user.setRegistrationTime(new Date());

			userDetailsManager.createUser(user);
		
	}

	@Test
	@Transactional
	public void getUserTest() {

		List<User> list = userDao.getAll();

	}
	
	@Test
	@Transactional
	public void getRoleInUser() {
		
		User user = userDao.findOneByProperty("username", "testusername2");
		
		for(Role role : user.getRoles()) {
			
			System.out.println("\n\n\n" + role.getRole());
		}
	}
	
	@Test
	@Transactional
	public void getAuthorityInUser() {
		
		User user = userDao.findOneByProperty("username", "testusername2");
		
		for(Authority authority : user.getAuthorities()) {
			
			System.out.println("\n\n\n" + authority.getAuthority());
		}
	}
}
