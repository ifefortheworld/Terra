package com.ireland.service;

import com.ireland.model.business.SourceFile;

public interface SourceFileService
{
	/**
	 * 计算指定ID的RealFile的特征值,用于文件去重,这里是用MD5算法,
	 * @param id RealFile的ID
	 * @return 更新特征值后的RealFile,若不存在,返回null 
	 */
	SourceFile calculateAndUpdateFileHashCode(String id);
	
	
	/**
	 * 0:在数据库中查出指定ID的RealFile
	 * 1:如果指定ID的RealFile未计算特征值,则调用calculateAndUpdateFileHashCode计算并更新特征值到数据库
	 * 
	 * 2:查找是否存在其它特征值一样的文件
	 * 
	 * 3:若不存在,更新RealFile的特征值到数据库,并返回更新后的RealFile
	 * 
	 * 4:若存在,则将引用计数最少的RealFile合并到引用计数多的RealFile,(将引用计数器合并,引用的文件id列表合并),
	 *   删除计数计数少的那个RealFile,并返回合并后的RealFile
	 *   
	 * 
	 * 
	 * @param id 要更新或合并的RealFile的ID
	 * @return 更新或合并后的RealFile
	 */
	SourceFile updateOrMerageSourceFile(String id);
}
