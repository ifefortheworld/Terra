package controller.classSituationController.save;

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
import java.util.Date;
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
	
	
	//表格提交的URL
	private String submitTableUrl = "http://192.168.1.246:8080/edusupervisor/ClassSituation/submit";
		

	

	
	
	public String submitForm() throws Exception
	{

        

        ClassSituation classSituation =  
        		new ClassSituation("计算机学院", "软件1班,软件2班", "教一205", "第1,2节", 100, 85, 5, "尽到早退情况严重!5人", 5, "5人旷课情况", 3, "3人请假", 8, "好多学生吃早餐!", 20, "天呀,都在玩iPhone!", 5, "有一些人上课睡觉!", 5, "拖鞋短裤情况", true, "老师还是很准时上课的!", null, null, "督导员1号", 5, null, "王小军", "无有其它情况!", null);
        
        
        
        
        String json = gson.toJson(classSituation);
        
        
        HttpPost httpost = new HttpPost(submitTableUrl);
        
        HttpEntity gsonEntity = new StringEntity(json,"UTF-8");

        httpost.setEntity(gsonEntity);
        
        httpost.setHeader("Content-Type","application/json;charset=UTF-8");

        HttpResponse response = httpclient.execute(httpost);
        
        HttpEntity entity = response.getEntity();
        
        String responseText = EntityUtils.toString(entity, HTTP.UTF_8);

 
        EntityUtils.consume(entity);
        
        
       
        
        
        if(response.getStatusLine().getStatusCode() == 201)
        {
        	System.out.println("提交成功");
        	
        	 Map<String,String> map = gson.fromJson(responseText, HashMap.class);
             
             String id = map.get("id");
        	
        	return id;
        }
        else if(response.getStatusLine().getStatusCode() == 400)
        {
        	System.out.println("表单参数有误!");
        }
        else
        {
        	System.out.println("提交失败!");
        }
        
        
        return null;
	}
	
}



public class SubmitFormTest
{

	public static void main(String[] args) throws Exception
	{
		 
		AndroidClient client = new AndroidClient();
		
		
         	
        client.submitForm();
       
         
	}

}
