package com.ireland.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;

/**
 * 
 * @author KEN
 *
 */
@Repository("localDiskFileDao")
public class LocalDiskFileDao implements LocalFileDao
{
	private String baseDir = "D:/terra";

	public LocalDiskFileDao()
	{
		super();
		
		//如果基本目录不存在,则创建它
		File dir = new File(baseDir+"/staticfiles");
		
		if(!dir.exists())
		{
			dir.mkdir();
		}
	}

	@Override
	public Resource read(String path)
	{
		return new FileSystemResource(baseDir+path);
	}

	@Override
	public void write(InputStream in, String path)
	{
		File file = new File(baseDir + path);
		
		try
		{
			FileOutputStream out = new FileOutputStream(file);
			
			FileCopyUtils.copy(in,out);
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	@Override
	public boolean delete(String path)
	{
		File file = new File(baseDir + path);
		
		return file.delete();		
	}

}
