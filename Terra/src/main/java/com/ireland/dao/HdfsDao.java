package com.ireland.dao;

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
			
		} catch (IOException e)
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
		
		FSDataOutputStream outputStream = null;
		
		try
		{

			try
			{
				//2MB 
				byte[] buff = new byte[2097152];
				
				int len = in.read(buff);
				
				outputStream = hdfs.create(dst);
				
				outputStream.write(buff, 0, len);
				
			} catch (Exception e)
			{
				e.printStackTrace();

			} finally
			{
				if (outputStream != null)
				{
					outputStream.close();
				}
			}

			FileStatus files[] = hdfs.listStatus(dst);
			for (FileStatus file : files)
			{
				System.out.println(file.getPath());
			}

		} catch (IOException e1)
		{
			e1.printStackTrace();
		}

	}
	
	

}
