package controller.classSituationController.update_pic;

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
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
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
	private String classPicUrl = "http://cscw.gdut.edu.cn:8888/edusupervisor/class/3c9c5a82-3122-45f7-3bf9-15f9d37f0ca4/pic.do";
		

	

	
	
	public void uploadPic() throws Exception
	{

        

		File pic = new File("D:/Tulips.jpg");
		
		
     
   
        
        
        MultipartEntity mutipartEntity = new MultipartEntity();
        
   
        mutipartEntity.addPart("pic", new FileBody(pic));
        
        
        HttpPut httpPut = new HttpPut(classPicUrl);
        
        httpPut.setEntity(mutipartEntity);
        

        HttpResponse response = httpclient.execute(httpPut);
        
        HttpEntity entity = response.getEntity();
        
        String responseText = EntityUtils.toString(entity, HTTP.UTF_8);

 
        EntityUtils.consume(entity);
        
        
        if(response.getStatusLine().getStatusCode() == 200)
        {
        	System.out.println("提交成功");
        }
        else if(response.getStatusLine().getStatusCode() == 400)
        {
        	System.out.println("表单参数有误!");
        }
        else
        {
        	System.out.println("提交失败!");
        }
        
        	
	}
	
}



public class UpdatePicTest
{

	public static void main(String[] args) throws Exception
	{
		 
		AndroidClient client = new AndroidClient();
		
		
         	
        client.uploadPic();
       
         
	}

}
