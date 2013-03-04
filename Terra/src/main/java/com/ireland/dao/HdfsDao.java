package com.ireland.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;

import com.ireland.io.HdfsInputStreamResource;

@Repository("hdfsDao")
public class HdfsDao
{
	private Configuration conf = new Configuration();

	private FileSystem hdfs = null;

	public HdfsDao() throws IOException
	{
		hdfs = FileSystem.get(conf);
	}

	public org.springframework.core.io.Resource read(String pathString)
	{
		Path path = new Path(pathString); 
		
		try
		{			
			FSDataInputStream inputStream = hdfs.open(path);
			
			FileStatus status = hdfs.getFileStatus(path);
			
			return new HdfsInputStreamResource(status,inputStream);
			
		}
		catch(FileNotFoundException e)
		{
			return null;//文件不存在
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 将in输入流的数据写到HDFS的path路径里!
	 * 
	 * @param in
	 * @param path
	 */
	public void upload(InputStream in, String path)
	{
		Path dst = new Path(path);
		
		FSDataOutputStream out = null;
		
		try
		{
			out = hdfs.create(dst);
			
			FileCopyUtils.copy(in, out);
			
		} catch (Exception e)
		{
			e.printStackTrace();

		} finally
		{
			if (out != null)
			{
				try
				{
					out.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	

}
