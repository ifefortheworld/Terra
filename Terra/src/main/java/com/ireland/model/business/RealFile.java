package com.ireland.model.business;



import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;




/**
 * 代表真实存放在硬盘上的文件,同一时刻可能有多TerraFile引用同一份文件
 * 
 * @KEN
 * 
 */

@Document
public class RealFile
{
	@Id
	private String id;

	
	/**
	 * 文件的实际存放路径,如本地 或 HDFS里的路径
	 */
	private String storageLocation;
	
	
	/**
	 * 引用计数,表示这份文件被多少个TerraFile引用
	 * 当引用计数为0的时候,此文件才能被删除
	 */
	private Integer referenceCount = 0;
	
	/**
	 * 引用本文件的所有TerraFile的ID,其数量一定与referenceCount相等
	 */
	private List<String> referenceIds;

	
	/**
	 * 文件的MD5值,用于文件去重
	 */
	private String fileHashCode;
	
	
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	
	public String getStorageLocation()
	{
		return storageLocation;
	}

	public void setStorageLocation(String storageLocation)
	{
		this.storageLocation = storageLocation;
	}

	public Integer getReferenceCount()
	{
		return referenceCount;
	}

	public void setReferenceCount(Integer referenceCount)
	{
		this.referenceCount = referenceCount;
	}

	public List<String> getReferenceIds()
	{
		return referenceIds;
	}

	public void setReferenceIds(List<String> referenceIds)
	{
		this.referenceIds = referenceIds;
	}

	/**
	 * @return the fileHashCode
	 */
	public String getFileHashCode()
	{
		return fileHashCode;
	}

	/**
	 * @param fileHashCode the fileHashCode to set
	 */
	public void setFileHashCode(String fileHashCode)
	{
		this.fileHashCode = fileHashCode;
	}
}
