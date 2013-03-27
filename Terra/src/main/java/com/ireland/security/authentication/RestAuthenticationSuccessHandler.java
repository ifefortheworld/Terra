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
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
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


public class RestAuthenticationSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements AuthenticationSuccessHandler
{
	
	/**
	 * 
	 * 用REST+JSON方式(非重定向或请求转发)响应登录成功的事件,这种方式适用于浏览器或移动客户端,通用性较高
	 * 
	 * 登录后的URL将放置在HTTP响应头的"Location"属性里
	 * 
	 */
	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
	{
        String targetUrl = determineTargetUrl(request, response);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
        
		//200 OK
		response.setStatus(HttpServletResponse.SC_OK);
		
		response.setHeader("Location", targetUrl);
	}


    /**
     * Calls the {@code handle()} method to response JSON to the Client , and
     * then calls {@code clearAuthenticationAttributes()} to remove any leftover session data.
     */
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        handle(request, response, authentication);
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
    
}
