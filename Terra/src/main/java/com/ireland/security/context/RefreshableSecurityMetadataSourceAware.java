package com.ireland.security.context;



/**
 * 主要用于AbstractSecurityInterceptor的子类,如:FilterSecurityInterceptor 和 MethodSecurityInterceptor,
 * 标识其可以在"运行时"设置新的SecurityMetadataSource,如从硬盘里重新读取Metadata!
 * 
 * 主要用于配置信息后可被通知要重新加载MetadataSource
 * 
 * @author 吉林大学珠海学院1队
 *
 */

public interface RefreshableSecurityMetadataSourceAware extends SecurityMetadataSourceAware
{
	/**
	 * 重新加载SecurityMetadataSource的配置!(如从硬盘或配置文件)
	 * @param newSource
	 */
	 void refreshSecurityMetadataSource();
}
