package com.ireland.service;

public interface TerraFileService
{
	/**
	 * 删除指定ID的TerraFile,及它的所有评论,
	 * 并更新或删除对应的RealFile
	 * @param id
	 */
	void delete(String id);

}
