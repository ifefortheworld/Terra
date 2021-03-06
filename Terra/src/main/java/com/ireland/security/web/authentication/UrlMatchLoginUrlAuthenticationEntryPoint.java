package com.ireland.security.web.authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;

/**
 * 
 * 
 * @author 吉林大学珠海学院1队
 * 
 */
interface LoginUrlMatcher
{

	boolean matches(HttpServletRequest request);

	String getLoginUrl();
}

class DefaultLoginUrlMatcher implements LoginUrlMatcher
{

	private static final Log logger = LogFactory.getLog(DefaultLoginUrlMatcher.class);
	
	private final RequestMatcher requestMatcher;
	
	private final String loginUrl;

	public DefaultLoginUrlMatcher(String pattern, String loginUrl)
	{

		this.requestMatcher = new AntPathRequestMatcher(pattern);
		this.loginUrl = loginUrl;
	}

	public RequestMatcher getRequestMatcher()
	{
		return requestMatcher;
	}

	@Override
	public boolean matches(HttpServletRequest request)
	{
		return requestMatcher.matches(request);
	}

	@Override
	public String getLoginUrl()
	{
		return this.loginUrl;
	}

	@Override
	public String toString()
	{
		return "LoginUrlMatcher [" + requestMatcher
				+ ", loginUrl=" + loginUrl + "]";
	}

}

/**
 * 基于URL模式匹配的LoginUrlAuthenticationEntryPoint, 适用于多个不同的入口点,如一个系统有web和wap两个入口点的情况
 * 
 * @author 吉林大学珠海学院1队
 * 
 */

public class UrlMatchLoginUrlAuthenticationEntryPoint extends
		LoginUrlAuthenticationEntryPoint
{
	private List<LoginUrlMatcher> loginUrlMatchers;


	/**
	 * 
	 * @param loginFormUrl //默认的登录地址
	 *            URL where the login page can be found. Should either be
	 *            relative to the web-app context path (include a leading
	 *            {@code /}) or an absolute URL.
	 */
	public UrlMatchLoginUrlAuthenticationEntryPoint(String loginFormUrl)
	{
		super(loginFormUrl);

	}

	
	/**
	 * 
	 * 当pattern访问被拒绝并需要重定向到登录页面时,需要根据访问的不同pattern而被重定向到不同的登录页面
	 *   <map>
              <entry key="/mobile**" value="/mobile/login"/>
              <entry key="/mobile/**" value="/mobile/login"/>
              <entry key="/**" value="/login"/>
          </map>
          
	 * @param patternLoginFormUrlMap
	 */
	public void setPatternLoginUrlMap(Map<String, String> patternLoginUrlMap)
	{
		loginUrlMatchers = new ArrayList<LoginUrlMatcher>(patternLoginUrlMap.size());

		for (Map.Entry<String,String> patternLoginUrl : patternLoginUrlMap.entrySet())
		{
			loginUrlMatchers.add(new DefaultLoginUrlMatcher(patternLoginUrl.getKey(),patternLoginUrl.getValue()));
		}
	}

	
	/**
	 * 
	 * 根据访问的不同pattern而被重定向到不同的登录页面
	 */
	@Override
	protected String determineUrlToUseForThisRequest(
			HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception)
	{
		//根据访问的不同pattern而被重定向到不同的登录页面
		for (LoginUrlMatcher loginUrlMatcher : loginUrlMatchers)
		{
			if (loginUrlMatcher.matches(request))
			{
				return loginUrlMatcher.getLoginUrl();
			}
		}

		//其它情况重定向到默认的URL
		return super.determineUrlToUseForThisRequest(request, response,exception);
	}

}
