package com.ireland.model.business;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 用户的存储情况
 * 
 * @author KEN
 *
 */

@Document
public class Storage
{
	@Id
	private String id;
	
	/**
	 * 持有者的ID
	 */
	@Indexed
	private String ownerId;
	

}
