package com.ireland.service;

import com.ireland.model.business.SourceFile;

public interface SourceFileService
{
	/**
	 * 计算指定ID的SourceFile的特征值,用于文件去重,这里是用MD5算法,
	 * @param id SourceFile的ID
	 * @return 更新特征值后的SourceFile,若不存在,返回null 
	 */
	SourceFile calculateAndUpdateFileHashCode(String id);
	
	
	/**
	 * 0:在数据库中查出指定ID的SourceFile
	 * 1:如果指定ID的SourceFile未计算特征值,则调用calculateAndUpdateFileHashCode计算并更新特征值到数据库
	 * 
	 * 2:查找是否存在其它特征值一样的文件
	 * 
	 * 3:若不存在,返回更新后的SourceFile
	 * 
	 * 4:若存在,则将引用计数最少的SourceFile合并到引用计数多的SourceFile,(将引用计数器合并,引用的文件id列表合并),
	 *   删除计数计数少的那个SourceFile及其本地源文件,并返回合并后的SourceFile
	 *   
	 * 
	 * 
	 * @param id 要更新或合并的SourceFile的ID
	 * @return 更新或合并后的SourceFile
	 */
	SourceFile updateOrMerageSourceFile(String id);
	
	
	/**
	 * 将SourceFile与存放在硬盘上的源文件 一起删除掉
	 * 
	 * @param sourceFile 
	 * @return 
	 */
	boolean deleteSourceFileWithLocalFile(SourceFile sourceFile);
	
	
	/**
	 * 对所有新上传的SourceFile(特征为:未计算特征值)进行文件去重
	 * 
	 * @return 成功删除的副本数
	 */
	int deduplicationForNewSourceFile();
}
