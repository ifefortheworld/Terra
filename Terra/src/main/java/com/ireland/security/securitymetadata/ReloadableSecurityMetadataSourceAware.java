package com.ireland.security.securitymetadata;

import org.springframework.beans.factory.Aware;



/**
 * 主要用于SecurityMetadataSource
 * 
 * 标识其可以在"运行时"设置新的SecurityMetadataSource,如从硬盘里重新读取Metadata!
 * 
 * 主要用于配置信息后可被通知要重新加载MetadataSource
 * 
 * @author KEN
 *
 */

public interface ReloadableSecurityMetadataSourceAware extends Aware
{
	/**
	 * 重新加载配置!(如从硬盘或配置文件)
	 */
	 void reload();
}
