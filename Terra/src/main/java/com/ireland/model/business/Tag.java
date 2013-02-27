package com.ireland.model.business;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * 标签
 * 
 * @author KEN
 *
 */
@Document
public class Tag
{
	@Id
	private String id;
	
	/**
	 * 标签名
	 */
	@Indexed(unique=true)
	private String name;
	
	/**
	 * 关联的文件数量
	 */
	private int fileCnt;

	
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getFileCnt()
	{
		return fileCnt;
	}

	public void setFileCnt(int fileCnt)
	{
		this.fileCnt = fileCnt;
	}
}
