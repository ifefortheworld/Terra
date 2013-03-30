package com.ireland.model.business;



import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;




/**
 * 代表真实存放在硬盘上的文件(源文件),同一时刻可能有多TerraFile引用同一份源文件
 * 
 * @KEN
 * 
 */

@Document
public class SourceFile
{
	@Id
	private String id;

	
	/**
	 * 文件的实际存放路径,如本地 或 HDFS里的路径(源文件的逻辑路径)
	 */
	private String storageLocation;
	
	/**
	 * 文件的MD5值(或其它特征值),用于文件去重 (源文件的哈希值)
	 */
	@Indexed
	private String fileHashCode;
	
	/**
	 * 文件大小,单位Byte
	 */
	private Long size;
	
	/**
	 * 引用计数,表示这份文件被多少个TerraFile引用
	 * 当引用计数为0的时候,此文件才能被删除
	 */
	private Integer referenceCount = 0;
	
	/**
	 * 引用本文件的所有TerraFile的ID,其数量一定与referenceCount相等
	 */
	private Set<String> referenceIds;

	
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

	public Set<String> getReferenceIds()
	{
		return referenceIds;
	}

	public void setReferenceIds(Set<String> referenceIds)
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

	/**
	 * @return the size
	 */
	public Long getSize()
	{
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Long size)
	{
		this.size = size;
	}
}
