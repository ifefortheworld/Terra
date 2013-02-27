package controller.courseController.getCourse;


import java.io.IOException;

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
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.PathVariable;



import com.google.gson.Gson;




/**
 * 
 * @author 吉林大学珠海学院1队
 *
 */


class AndroidClient
{
	private static Gson gson = new Gson();
	
	private DefaultHttpClient httpclient = new DefaultHttpClient(new ThreadSafeClientConnManager());
	
	//登录URL
	//private String study_placesUrl = "http://localhost:8080/edusupervisor/courses/{study_place}/class_indexs";
	
	

	
	
	/**
	 * 根据{上课地点},{系统的当前时间(星期几)},{课程的节次}+课程起始周就确定一节课
	 * (大多数情况是一节课,对于实验室,因为实验室的信息录入不够明确,所以可能会返回多个课程) 
	 * 
	 * 
	 * 
	 * @param study_place  地点
	 * @param class_index  节次
	 * @return
	 * 	
	 */
	
	public Course[] getCourse(String study_place,String class_index) throws ClientProtocolException, IOException
	{
		
		//取得
  	  	HttpGet httpGet = new HttpGet("http://cscw.gdut.edu.cn:8888/edusupervisor/courses/"+study_place+"/"+class_index+".do");

  	  	
        HttpResponse response = httpclient.execute(httpGet);
        

        if(response.getStatusLine().getStatusCode() == 404)
        {
      	  System.out.println("没有这节课哦!");
        }
        else
        if(response.getStatusLine().getStatusCode() == 200)
        {
        	System.out.println("有课");
        	
        	HttpEntity entity = response.getEntity();
             
            String responseText = EntityUtils.toString(entity, HTTP.UTF_8);

        	
        	Course[] courses = gson.fromJson(responseText, Course[].class);
        	
        	return courses;
        }
        
        
		return null;
	}
	


	public Map<String,Object> getMap(String school_district,String date,String study_place,String section)throws ClientProtocolException, IOException {

		// 取得
		HttpGet httpGet = new HttpGet(
				"http://localhost:8080/edusupervisor/dudao/"
						+ school_district + "/" + date  + "/" + study_place + "/" + section );

		HttpResponse response = httpclient.execute(httpGet);

		if (response.getStatusLine().getStatusCode() == 404) {
			System.out.println("输入数据错误或此地点没有对应的课要上");
		} else if (response.getStatusLine().getStatusCode() == 200) {
			System.out.println("输入正确");

			HttpEntity entity = response.getEntity();

			String responseText = EntityUtils.toString(entity, HTTP.UTF_8);

			Map<String,Object> map = gson.fromJson(responseText, HashMap.class);
			
			

			return map;
		}

		return null;
	}
	
	public Map<String,List<List>> getMap2(String user_no,String start_date, String end_date )throws ClientProtocolException, IOException
	{
		HttpGet httpGet = new HttpGet(
				"http://localhost:8080/edusupervisor/dudao/check/"
						+ user_no + "/" + start_date  + "/" + end_date  );

		HttpResponse response = httpclient.execute(httpGet);

		if (response.getStatusLine().getStatusCode() == 404) {
			System.out.println("输入数据错误或此地点没有对应的课要上");
		} else if (response.getStatusLine().getStatusCode() == 200) {
			System.out.println("输入正确");

			HttpEntity entity = response.getEntity();

			String responseText = EntityUtils.toString(entity, HTTP.UTF_8);

			Map<String,List<List>> map = gson.fromJson(responseText, HashMap.class);
			
			

			return map;
		}

		return null;
		
	}


}



public class CourseControllerTest_getCourse
{

	public static void main(String[] args) throws Exception
	{
		 
		AndroidClient client = new AndroidClient();
		
		//Course[] courses = client.getCourse("1号大教室","第1,2节");

		//Map<String,Object> map=client.getMap("1", "2012-12-18", "1号大教室","1,2" );
		
		
		//Edu_CourseClass[] edus = new Gson().fromJson(.get("information"), Edu_CourseClass[].class);;
		
		//String name=(String)map.get("teacher_name");
		//System.out.println(name);
		
		
		Map<String,List<List>> map=client.getMap2("aak333", "2012-12-17", "2012-12-19");
		
		
		List<List> list=map.get("dudao");
	
		System.out.println(list.get(0).get(0));
		System.out.println(map);
		
	}

}




