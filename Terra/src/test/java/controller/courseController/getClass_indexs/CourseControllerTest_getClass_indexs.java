package controller.courseController.getClass_indexs;


import java.io.IOException;

import java.util.ArrayList;
import java.util.List;



import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;



import com.google.gson.Gson;





/**
 * 
 * @KEN
 *
 */


class AndroidClient
{
	private static Gson gson = new Gson();
	
	private DefaultHttpClient httpclient = new DefaultHttpClient(new ThreadSafeClientConnManager());
	
	//登录URL
	//private String study_placesUrl = "http://localhost:8080/edusupervisor/courses/{study_place}/class_indexs";
	
	

	
	
	/**
	 * 系统今天的时间,查询今天哪些课室有课!
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * 
	 */
	
	public List<String> getClass_indexs(String study_place) throws ClientProtocolException, IOException
	{
		
		//取得
  	  	HttpGet httpGet = new HttpGet("http://localhost:8080/edusupervisor/courses/"+study_place+"/class_indexs.do");

  	  	
        HttpResponse response = httpclient.execute(httpGet);
        

        if(response.getStatusLine().getStatusCode() == 404)
        {
      	  System.out.println("今天这个课室没课哦!");
        }
        else
        if(response.getStatusLine().getStatusCode() == 200)
        {
        	System.out.println("今天这个课室N个课!");
        	
        	HttpEntity entity = response.getEntity();
             
            String responseText = EntityUtils.toString(entity, HTTP.UTF_8);

        	
        	List<String> study_places = gson.fromJson(responseText, ArrayList.class);
        	
        	return study_places;
        }
        
        
		return null;
	}
	
}



public class CourseControllerTest_getClass_indexs
{

	public static void main(String[] args) throws Exception
	{
		 
		AndroidClient client = new AndroidClient();
		
		List<String> course_times = client.getClass_indexs("教4-207");
	
	}

}
