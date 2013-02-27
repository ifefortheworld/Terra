package com.cscw.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import com.ireland.dao.RoleDao;
import com.ireland.model.Role;
import com.ireland.service.AuthorityService;
import com.ireland.service.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-db.xml",
		"/applicationContext-service.xml", "/applicationContext.xml"
		})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class RoleServiceImplTest {
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

	@Autowired
	private RoleDao roleDao;

	/**
	 * 增加一个test角色
	 * 
	 * @param role
	 * @return
	 */
	@Test
	public void addARole() {
		Role role = new Role("test", "test", null);

		roleService.addRole(role);
	}

	/**
	 * 向test角色增加权限
	 * 
	 * @param roleName
	 * @param authorityName
	 */
	@Test
	public void addAuthorityToRoleTest() {
		String roleName = "ROLE_ADMIN";

		roleService.addAuthorityToRole(roleName, "Auth_authority");
		roleService.addAuthorityToRole(roleName, "Auth_role");
		roleService.addAuthorityToRole(roleName, "Auth_userAuthConfig");
		
	}

	/**
	 * 增加一个角色
	 * 
	 * @param role
	 * @return
	 */
	@Test
	public void addRole() {

		Role role;

		role = new Role("ROLE_LEADER", "学校领导", null);
		roleService.addRole(role);

		role = new Role("ROLE_ADMIN", "管理员", null);
		roleService.addRole(role);

		role = new Role("ROLE_DEAN", "学院院长", null);
		roleService.addRole(role);

		role = new Role("ROLE_SUPERVISOR", "学生督导员", null);
		roleService.addRole(role);

		role = new Role("ROLE_ASSISTANT", "辅导员", null);
		roleService.addRole(role);

		role = new Role("ROLE_TEACHER", "教师", null);
		roleService.addRole(role);

		role = new Role("ROLE_STUDENT", "学生", null);
		roleService.addRole(role);
		
		role = new Role("ROLE_SECRETARY", "学院书记", null);
		roleService.addRole(role);
		
		role = new Role("ROLE_VICE_SECRETARY", "学院副书记", null);
		roleService.addRole(role);
	}

	/**
	 * 删除一个角色,此操作并不会删除关联的权限! 而所有用户对此角色的引用将会被移除
	 * 
	 * 1:移除所有用户对此角色的引用 2:删除此角色
	 * 
	 * @param roleName
	 */

	@Test
	public void deleteRoleTest() {
		roleService.deleteRole("ROLE_DEAN");
	}

	
	/**
	 * 向角色移除一个权限(如果该角色并未持有该权限,则不进行任何操作) 注意:仅移除角色与权限的关联关系
	 * 
	 * @param roleName
	 * @param authorityName
	 */
	@Test
	public void removeAuthorityFromRoleTest() {
		//roleService.removeAuthorityFromRole("ROLE_ADMIN", "AUTH_addAuthority");
		roleService.removeAuthorityFromRole("ROLE_ADMIN", "AUTH_addAuthorityToRole");
		roleService.removeAuthorityFromRole("ROLE_ADMIN", "AUTH_addRole");
		roleService.removeAuthorityFromRole("ROLE_ADMIN", "AUTH_addRoleToUser");
		roleService.removeAuthorityFromRole("ROLE_ADMIN", "AUTH_deleteRole");
	}

	/**
	 * 
	 * 
	 */
	@Test
	public void getAllRole() {

		List<Role> list = roleDao.getAll();
	}

	@Test
	public void getRoleInUser() {

		List<Role> list = roleService.getRoleInUser("testusername2");
		for (Role role : list) {

			System.out.println("\n\n\n" + role.getRole());
		}
	}

	@Test
	public void getRoleNotInUser() {

		List<Role> list = roleService.getRoleNotInUser("testusername2");
		for (Role role : list) {

			System.out.println("\n\n\n" + role.getRole());
		}
	}
	
	@Test
	public void updataRole() {
		
		roleService.updateRole("ROLE_STUDENT", "ROLE_RESTUDENT", "修改的学生");
	}
}
