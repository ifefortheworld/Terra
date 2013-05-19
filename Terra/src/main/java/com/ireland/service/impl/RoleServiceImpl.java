package com.ireland.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ireland.dao.AuthorityDao;
import com.ireland.dao.RoleDao;
import com.ireland.dao.UserDao;
import com.ireland.model.Authority;
import com.ireland.model.Role;
import com.ireland.model.User;
import com.ireland.service.RoleService;


@Service("roleService")
public class RoleServiceImpl implements RoleService
{
	private RoleDao roleDao;
	
	@Resource(name="roleDao")
	public void setRoleDao(RoleDao roleDao)
	{
		this.roleDao = roleDao;
	}
	
	private AuthorityDao authorityDao;

	@Resource(name="authorityDao")
	public void setAuthorityDao(AuthorityDao authorityDao)
	{
		this.authorityDao = authorityDao;
	}
	
	private UserDao userDao;
	
	@Resource(name="userDao")
	public void setUserDao(UserDao userDao)
	{
		this.userDao = userDao;
	}
	
	

	//-------------------------------------------------------------------

	@Override
	public Role getRole(String roleName)
	{
		return roleDao.findOne("role", roleName);
	}
	
	@Override
	public List<Role> getAllRole()
	{
		return roleDao.findAll();
	}

	@Transactional
	@Override
	public void addRole(Role role)
	{
		roleDao.insert(role);
	}


	@Override
	public void deleteRole(String roleName)
	{
		Role role = roleDao.findOne("role", roleName);
		
		//修改所有持有此Role的user
		List<User> users = userDao.findAll("role", role);
		
		for(User user : users)
		{
			user.setRole(null);
			
			userDao.save(user);
		}
		
			
		roleDao.delete(role);	
	}

	
	@Override
	public void addAuthorityToRole(String roleName, String authorityName)
	{
		
		Role role = roleDao.findOne("role", roleName);
		
		
		//如果未找到对应的角色,直接返回
		if(role == null) return;
		
		Authority authority = authorityDao.findOne("authority", authorityName);
		
		//如果未找到对应的权限,直接返回
		if(authority == null) return;
		
		
		//如果角色未持有此权限,则给角色增加权限
		List<Authority> authorities = role.getAuthorities();
		
		if(!authorities.contains(authority))
		{
			authorities.add(authority);
			
			//更新Role
			roleDao.save(role);
		}
	}


	@Override
	public void removeAuthorityFromRole(String roleName, String authorityName)
	{
		Role role = roleDao.findOne("role", roleName);
		
		//如果未找到对应的角色,直接返回
		if(role == null) return;
		
		Authority authority = authorityDao.findOne("authority", authorityName);
		
		//如果未找到对应的权限,直接返回
		if(authority == null) return;
		
		//如果角色持有此权限,则给角色删除权限
		List<Authority> authorities = role.getAuthorities();
		
		if(authorities.contains(authority))
		{
			authorities.remove(authority);
			
			//更新Role
			roleDao.save(role);
		}
	}
	
	
}
