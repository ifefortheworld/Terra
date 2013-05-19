package com.ireland.service;

public interface FileService
{
	/**
	 * 删除指定ID的File,及它的所有评论,
	 * 并更新或删除对应的SourceFile
	 * @param id
	 */
	void delete(String id);

}
