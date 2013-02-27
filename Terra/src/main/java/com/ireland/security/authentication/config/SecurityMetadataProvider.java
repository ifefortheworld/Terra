package com.ireland.security.authentication.config;

import java.util.List;




/**
 * SecurityMetadata的提供者
 * 
 * @author 吉林大学珠海学院1队
 *
 */
public interface SecurityMetadataProvider
{
	/**
	 * 取出全部SecurityMetadata,注意SecurityMetadata的顺序的安全性的影响
	 * @return 
	 */
	List<? extends SecurityMetadata> getSecurityMetadatas();
}
