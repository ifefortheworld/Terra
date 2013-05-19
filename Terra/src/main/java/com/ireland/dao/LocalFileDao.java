package com.ireland.dao;

import java.io.InputStream;

import org.springframework.core.io.Resource;


/**
 * 
 * 用户上传的真实文件的读写Dao
 * @author KEN
 *
 */

public interface LocalFileDao
{
	
	Resource read(String path);
	
	
	void write(InputStream in, String path);
	
	
	boolean delete(String path);
}
