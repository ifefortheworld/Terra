package com.ireland.model.business;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.ireland.utils.MyDate;



/**
 * 文件
 * 
 * @KEN
 * 
 */

@Document
public class File
{
	@Id
	private String id;

	/**
	 * 文件名字
	 */
	@NotNull
	private String name;
	
	
	/**
	 * 持有者的名称 
	 */
	@Deprecated
	private String owner;
	
	
	/**
	 * 持有者的ID
	 */
	@Indexed
	private String ownerId;

	/**
	 * 上传时间
	 */
	@DateTimeFormat(iso=ISO.DATE)
	private Date uploadDate;
	
	/**
	 * 类型: Text,Video,Audio,image,Bag,Other
	 */
	private String type;

	/**
	 * 描述
	 */
	private String detail;

	/**
	 * 关联的标签
	 */
	@DBRef
	private List<Tag> tags;
	
	/**
	 * 评论列表
	 */
	@DBRef
	private List<Comment> comments;

	/**
	 * 是否 分享
	 */
	private Boolean isShared = true;


	/**
	 * 文件的下载URL
	 */
	@Indexed
	private String fileUrl;
	
	/**
	 * 文件的原始名
	 */
	private String fileOriginalName;

	
	/**
	 * 对应的真实文件的id
	 */
	private String sourceFileId;
	
	/**
	 * 浏览数
	 */
	private int viewsCnt;

	/**
	 * 喜爱数
	 */
	private int favsCnt;
	
	/**
	 * 下载数
	 */
	private int downsCnt;
	
	
	// get & set -----------------------------------
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

	public String getOwner()
	{
		return owner;
	}

	public void setOwner(String owner)
	{
		this.owner = owner;
	}

	/**
	 * @return the ownerId
	 */
	public String getOwnerId()
	{
		return ownerId;
	}

	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(String ownerId)
	{
		this.ownerId = ownerId;
	}

	public Date getUploadDate()
	{
		if(uploadDate!= null && !(uploadDate instanceof MyDate))
			this.uploadDate = new MyDate(uploadDate.getTime());
		
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate)
	{
		this.uploadDate = uploadDate;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getDetail()
	{
		return detail;
	}

	public void setDetail(String detail)
	{
		this.detail = detail;
	}

	public List<Tag> getTags()
	{
		return tags;
	}

	public void setTags(List<Tag> tags)
	{
		this.tags = tags;
	}

	public List<Comment> getComments()
	{
		return comments;
	}

	public void setComments(List<Comment> comments)
	{
		this.comments = comments;
	}



	public Boolean getIsShared()
	{
		return isShared;
	}

	public void setIsShared(Boolean isShared)
	{
		this.isShared = isShared;
	}



	/**
	 * @return the fileUrl
	 */
	public String getFileUrl()
	{
		return fileUrl;
	}

	/**
	 * @param fileUrl the fileUrl to set
	 */
	public void setFileUrl(String fileUrl)
	{
		this.fileUrl = fileUrl;
	}

	public String getFileOriginalName()
	{
		return fileOriginalName;
	}

	public void setFileOriginalName(String fileOriginalName)
	{
		this.fileOriginalName = fileOriginalName;
	}

	
	public String getSourceFileId()
	{
		return sourceFileId;
	}

	public void setSourceFileId(String sourceFileId)
	{
		this.sourceFileId = sourceFileId;
	}

	public int getViewsCnt()
	{
		return viewsCnt;
	}

	public void setViewsCnt(int viewsCnt)
	{
		this.viewsCnt = viewsCnt;
	}

	public int getFavsCnt()
	{
		return favsCnt;
	}

	public void setFavsCnt(int favsCnt)
	{
		this.favsCnt = favsCnt;
	}

	public int getDownsCnt()
	{
		return downsCnt;
	}

	public void setDownsCnt(int downsCnt)
	{
		this.downsCnt = downsCnt;
	}

}
