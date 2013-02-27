package com.ireland.security.userdetails;


import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;


import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ireland.model.User;






/**
 * 
 * UserDetailsManager  for com.ireland.model.User
 * 
 * 为类型model.User定制的UserDetailsManager,如果非此类型,则抛出异常
 * 
 * SimpleUserDetailsManager 同时 负责密码的加密部分,
 * 
 * 如注册时,提供的user的密码应该是未加密的,而HibernateUserDetailsManager利用passwordEncoder加密后再存放到数据库中
 * 
 * @author 吉林大学珠海学院1队
 *
 */

@Service("userDetailsManager")
public class SimpleUserDetailsManager extends SimpleUserDetailsService implements UserDetailsManager
{
	
	protected Validator validator;

	private AuthenticationManager authenticationManager;
	
	private PasswordEncoder passwordEncoder;
	
	private SaltSource saltSource;
	

	@Resource(name="authenticationManager")
	public void setAuthenticationManager(AuthenticationManager authenticationManager)
	{
		this.authenticationManager = authenticationManager;
	}
	
	@Resource(name="passwordEncoder")
	public void setPasswordEncoder(PasswordEncoder passwordEncoder)
	{
		this.passwordEncoder = passwordEncoder;
	}

	@Resource(name="saltSource")
	public void setSaltSource(SaltSource saltSource)
	{
		this.saltSource = saltSource;
	}
	
	
	@Resource(name="validator")
	public void setValidator(Validator validator)
	{
		this.validator = validator;
	}

	

	
	/*
	 * JSR-303,Hibernate Validator 校验User的值是否合法,不合法则抛出 throw new IllegalArgumentException(message);
	 * 
	 * 验证USER的属性及其权限
	 * 
	 */
	boolean JSR303Validate(User user)
	{
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		
		if(constraintViolations.size() == 0)	
			return true;
		
		//输出错误信息
		if(logger.isDebugEnabled())
		{
			for(ConstraintViolation<User> violation : constraintViolations)
				logger.debug(violation.getMessage());
		}
		
		return false;
	}
	
	

	
	
	/*
	 * 先校验user的属性的合法性,加密密码,然后再将新的user保存到数据库
	 	提供的user的密码应该是raw Password 
	 */
	
	@Override
	public void createUser(UserDetails use)
	{
		Assert.isTrue(use instanceof User,"SimpleUserDetailsManager can only create com.ireland.model.User!");
		
		User user = (User)use;
		
    	Assert.isTrue(JSR303Validate(user), "some properties of user the provided user is wrong,and not pass the JSR303 validate!");

    	String encPassword;
    	
		if(saltSource == null)
		{
			encPassword = passwordEncoder.encodePassword(user.getPassword(),null);
		}
		else
		{
			//对明文密码进行SHA加密
			encPassword = passwordEncoder.encodePassword(user.getPassword(), saltSource.getSalt(user));
		}
		
		user.setPassword(encPassword);
		
		
		//保存进数据库
		userDao.save(user);
	}
	
	
	/**
	 * 先验证USER的属性的合法
	 * 如果User的id为空,则先根据用户名查出其ID
	 * 将提供的user更新到数据库
	 * 
	 * 不能用于修改密码,否则抛出异常..因为通常修改密码要更高的权限
	 * 
	 */

	@Override
	public void updateUser(UserDetails use)
	{
		Assert.isTrue(use instanceof User,"SimpleUserDetailsManager can only update com.ireland.model.User!");
		
		User user = (User)use;
		
		//先查询出老用户
		User oldUser = userDao.findOne("username", user.getUsername());
				
		
		//用户不存在,直接返回
		if(oldUser == null)
			return;
		
		
		//取得ID
		if(user.getId() == null)
		{			
			user.setId(oldUser.getId());
		}
		
	
		//不能用此方法修改密码,故要确保给出的新用户信息的密码与原来的密码一致!
		Assert.isTrue(user.getPassword().equals(oldUser.getPassword()));
		
		
    	Assert.isTrue(JSR303Validate(user), "some properties of user the provided user is wrong,and not pass the JSR303 validate!");

		
		
		userDao.save(user);
	}


	
	//只有管理员才可以删除账号!
	//@PreAuthorize("hasAuthority('AUTH_deleteUser')")
	@Override
	public void deleteUser(String username)
	{

		User user = userDao.findOne("username", username);
		
		userDao.delete(user);		
	}

	
	/*
	 * 修改"当前"用户的密码,参考了: JdbcUserDetailsManager.changePassword()
	 * 
	 */
	@Override
	public void changePassword(String oldPassword, String newPassword)
	{
	    Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

        if (currentUser == null || currentUser instanceof AnonymousAuthenticationToken) {
            // This would indicate bad coding somewhere
            throw new AccessDeniedException("Can't change password as no Authentication or Anonymous Authentication  object found in context for current user.");
        }

        String username = currentUser.getName();

        // If an authentication manager has been set, re-authenticate the user with the supplied password.
        if (authenticationManager != null) {
            logger.debug("Reauthenticating user '"+ username + "' for password change request.");

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
        } else {
            logger.debug("No authentication manager set. can't change Password!");  //AuthenticationManager不存在,不能认证旧密码,故不能修改密码!
            
            return;
        }

        logger.debug("Changing password for user '"+ username + "'");

        User user = (User) loadUserByUsername(username);
        
        user.setPassword(newPassword);
        
    	Assert.isTrue(JSR303Validate(user), "some properties of user the provided user is wrong,and not pass the JSR303 validate!");
    	
    	
    	//加密
    	String encPassword;
		if(saltSource == null)
		{
			encPassword = passwordEncoder.encodePassword(newPassword,null);
		}
		else
		{
			//对明文密码进行SHA加密
			encPassword = passwordEncoder.encodePassword(newPassword, saltSource.getSalt(user));
		}
		
		
		user.setPassword(encPassword);
    
		
		//更新User的密码,MongoDB的save的含意为插入或更新
		userDao.save(user);
		
		//修改SecurityContext
        SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(currentUser, newPassword));
	}
	
	
	//
    protected Authentication createNewAuthentication(Authentication currentAuth, String newPassword) {
        UserDetails user = loadUserByUsername(currentAuth.getName());

        UsernamePasswordAuthenticationToken newAuthentication =
                new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        newAuthentication.setDetails(currentAuth.getDetails());

        return newAuthentication;
    }

	
	@Override
	public boolean userExists(String username)
	{
		return userDao.exists("username", username);
	}


}
