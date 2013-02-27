package com.ireland.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import com.ireland.dao.AuthorityDao;
import com.ireland.dao.RoleDao;
import com.ireland.dao.UserDao;
import com.ireland.model.Authority;
import com.ireland.model.Role;
import com.ireland.model.User;
import com.ireland.security.authentication.config.SecurityMetadata;
import com.ireland.security.authentication.config.SecurityMetadataProvider;
import com.ireland.service.AuthorityService;


@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService,SecurityMetadataProvider
{
	
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
	
	private RoleDao roleDao;
	
	@Resource(name="roleDao")
	public void setRoleDao(RoleDao roleDao)
	{
		this.roleDao = roleDao;
	}
	
	
	//---------------------------------------------------------------------------------
	
	//SecurityMetadataProvider-----------------------------------------------------
		@Override
		public List<? extends SecurityMetadata> getSecurityMetadatas()
		{
			return authorityDao.findAll();
		}
	//END SecurityMetadataProvider-----------------------------------------------------
		
		
	  
	@Override
	public Authority addAuthority(Authority authority)
	{
		authorityDao.insert(authority);
		
		return authority;
	}
	

	@Override
	public void deleteAuthority(String authorityName)
	{
		Authority authority = authorityDao.findOne("authority", authorityName);
		
		List<Role> roles = roleDao.findAll("authority", authority);
		
		//对持有该Authority的Role中删除些Authority
		for(Role role : roles)
		{
			List<Authority> authorities = role.getAuthorities();
			
			authorities.remove(authority);
			
			role.setAuthorities(authorities);
			
			//更新,MongoDB的Save指插入或更新已有的
			roleDao.save(role);
		}
		
		authorityDao.delete(authority);
	}
	
	
	
	
	
	
	@Override
	public List<Authority> getAuthorityInRole(String roleName) 
	{

		Role role = roleDao.findOne("role", roleName);
		
		return role.getAuthorities();
	}
	
	  
	@Override
	public List<Authority> getAuthorityNotInRole(String roleName) 
	{

		List<Authority> authorities, authoritiesInRole;
		
		authoritiesInRole = getAuthorityInRole(roleName);
		authorities = getAllAuthority();
		authorities.removeAll(authoritiesInRole);
		
		return authorities;
	}

	  
	/**
	 * 得到所有角色与对应劝降的map
	 */
	@Override
	public Map<String, List<Authority>> getMapAuthorityToRole() 
	{

		Map<String, List<Authority>> map = new HashMap<String, List<Authority>>();

		for(Role role : roleDao.findAll()) 
			map.put(role.getRole(), role.getAuthorities());
		
		return map;
	}
	
	
	/**
	 * 取得不在用户里的权限
	 * 
	 */
	  
	@Override
	public List<Authority> getAuthorityNotInUser(String userName) 
	{

		List<Authority> authorities = authorityDao.findAll();
		
		authorities.removeAll(getAllAuthorityInUser(userName));
		
		return authorities;
	}
	
	/**
	 * 取得所有在用户里的权限
	 * 
	 */
	  
	@Override
	public List<? extends GrantedAuthority> getAllAuthorityInUser(String userName) 
	{

		User user = userDao.findOne("username", userName);
		
		return user.getAuthorities();
	}

	  
	  
	@Override
	public List<Authority> getAllAuthority() {

		return authorityDao.findAll();
	}
	
	@Override
	public Authority getAuthority(String authority) {
		
		return authorityDao.findOne("authority", authority);
	}

	@Override
	public Authority getAuthorityById(String id) {
		
		return authorityDao.findOne(id);
	}

}
