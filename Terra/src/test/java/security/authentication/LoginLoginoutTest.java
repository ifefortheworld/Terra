package security.authentication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


import com.google.gson.Gson;







/**
 * 登录的HttpClient测试for安卓
 * 
 * @KEN
 *
 */

/*
 * 
 * 	xhr.open("POST", "/edusupervisor/j_spring_security_check", true);
	
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	
	xhr.send("j_username="+$id("username").value+"&j_password="+$id("password").value);
 */


class AndroidClient
{
	private static Gson gson = new Gson();
	
	private DefaultHttpClient httpclient = new DefaultHttpClient(new ThreadSafeClientConnManager());
	
	//登录URL
	private String loginUrl = "http://localhost:8080/edusupervisor/j_spring_security_check";
	
	//退出URL
	private String logoutUrl;
	
	//表格提交的URL
	private String submitTableUrl;
		
	public AndroidClient() throws Exception
	{
		
	}
	
	//软件退出时手动执行
	public void close()
	{
		if(httpclient != null)
		{
			// When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
		}
	}
	
	
	//登录
	public  void login(String username,String password)
	{

		StatusLine statusLine = null;
		
        try {

            HttpPost httpost = new HttpPost(loginUrl);

            List <NameValuePair> params = new ArrayList <NameValuePair>();
            
            //输入督导员的用户名/密码
            params.add(new BasicNameValuePair("j_username", username));
            params.add(new BasicNameValuePair("j_password", password));

            httpost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

            HttpResponse response = httpclient.execute(httpost);
            
             statusLine = response.getStatusLine();
            
            int statusCode = statusLine.getStatusCode();
           
            
            if(statusCode == 200)//登录成功
            {
            	
            	//督导员登录后的下一个可能状态:提交表单
                this.submitTableUrl = response.getFirstHeader("x-cws-SubmitTableUrl").getValue();
                
              //督导员登录后的下一个可能状态:退出
                this.logoutUrl = response.getFirstHeader("x-cws-LogoutUrl").getValue();
            }
            else
            {
            	 
                String responseText = EntityUtils.toString(response.getEntity(),"utf-8");
                Map<String,String> map = gson.fromJson(responseText, HashMap.class);
                
                
            	if(statusCode == 404) //用户不存在
            	{
            		String reason = map.get("reason");
            	}
            	else if(statusCode == 401) //密码错误
            	{
            		String reason = map.get("reason");
            	}
            	else if(statusCode == 403) //禁止访问
            	{
            		String reason = map.get("reason");
            	}
            }
           
         
    
        } catch (IOException e)
		{
			e.printStackTrace();
		}
        
        
    
	}
	
	
	
	//登录
	public  boolean loginOut()
	{

		StatusLine statusLine = null;
		
        try {

            HttpDelete httpDelete = new HttpDelete(logoutUrl);

            HttpResponse response = httpclient.execute(httpDelete);
            
             statusLine = response.getStatusLine();
            
            int statusCode = statusLine.getStatusCode();
            
            
            if(statusCode == 200)//退出成功
            {
            		return true;
            }
           
         
    
        } catch (IOException e)
		{
			e.printStackTrace();
		}
        
        
        return false;
	
	}
	
	
	
	
	
}



public class LoginLoginoutTest
{

	public static void main(String[] args) throws Exception
	{
		 
		AndroidClient client = new AndroidClient();
		
		 
		client.login("4564564", "123456");
		
		client.login("super1", "5456456");
		
		
		client.login("super1", "123456");

		
         

        
         
         
         client.loginOut();
         
	}

}
