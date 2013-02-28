package com.ireland.security.authentication;

import java.io.IOException;
import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ireland.dao.UserDao;
import com.ireland.model.User;




/**
 * REST 风格 的 AuthenticationSuccessHandler,验证成功的话就返回 200+Location
 * 
 * @author 吉林大学珠海学院1队
 */


@Component("restAuthenticationSuccessHandler")
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler
{
	private UserDao userDao;
	
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	
	@Resource(name="userDao")
	public void setUserDao(UserDao userDao)
	{
		this.userDao = userDao;
	}

	
	@Transactional(readOnly=true)
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException
	{
		
		//200 OK
		response.setStatus(HttpServletResponse.SC_OK);
		
		
		Collection<? extends GrantedAuthority> authorities =  authentication.getAuthorities();
		
		
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path ;
		
		String username = ((UserDetails) authentication.getPrincipal()).getUsername();
		
		response.setHeader("Location", basePath+"/myspace/file-list");
        //redirectStrategy.sendRedirect(request, response, "/");

		
		
		clearAuthenticationAttributes(request);
		
	}

	
	
    /**
     * Removes temporary authentication-related data which may have been stored in the session
     * during the authentication process.
     */
    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
    
    
    /**
     * Allows overriding of the behaviour when redirecting to a target URL.
     */
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}
