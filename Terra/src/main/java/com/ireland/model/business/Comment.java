package com.ireland.model.business;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/***
 * 评论
 * 
 * @author root
 *
 */

@Document
public class Comment
{
	@Id
	private String id;
	
	/**
	 * 评论者的名称
	 */
	private String owner;
	
	/**
	 * 被评论文件的id
	 */
	private String fileId;
	/**
	 * 内容
	 */
	@NotNull
	private String content;

	
	/**
	 * 价值系数
	 */
	private int votes;


	public String getId()
	{
		return id;
	}


	public void setId(String id)
	{
		this.id = id;
	}


	public String getOwner()
	{
		return owner;
	}


	public void setOwner(String owner)
	{
		this.owner = owner;
	}


	public String getFileId()
	{
		return fileId;
	}


	public void setFileId(String fileId)
	{
		this.fileId = fileId;
	}


	public String getContent()
	{
		return content;
	}


	public void setContent(String content)
	{
		this.content = content;
	}


	public int getVotes()
	{
		return votes;
	}


	public void setVotes(int votes)
	{
		this.votes = votes;
	}
}
