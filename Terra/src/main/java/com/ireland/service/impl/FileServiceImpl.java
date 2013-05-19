package com.ireland.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.ireland.dao.CommentDao;
import com.ireland.dao.SourceFileDao;
import com.ireland.dao.RoleDao;
import com.ireland.dao.FileDao;
import com.ireland.dao.UserDao;
import com.ireland.index.FileIndexer;
import com.ireland.model.User;
import com.ireland.model.business.SourceFile;
import com.ireland.model.business.File;
import com.ireland.service.FileService;
import com.ireland.service.SourceFileService;



/**
 * 
 * @KEN
 *
 */

@Service("fileService")
public class FileServiceImpl implements FileService
{
	@Autowired
	private FileDao fileDao;
	
	@Autowired
	private FileIndexer fileIndexer;
	
	@Autowired
	private CommentDao commentDao;

	@Autowired
	private SourceFileDao sourceFileDao;
	
	@Autowired
	private SourceFileService sourceFileService;
	
	/**
	 * 删除指定ID的File,及它的所有评论,及File的索引
	 * 并更新或删除对应的SourceFile
	 * @param id
	 */
	@Override
	public void delete(String id)
	{
		File file = fileDao.findOne(id);
		
		if(file == null) return;
		
		//更新或删除SourceFile
		String sourceFileId = file.getSourceFileId();
		if(sourceFileId != null)
		{
			SourceFile sourceFile = sourceFileDao.findOne(sourceFileId);
			
			if(sourceFile != null)
			{
				Integer fileCount = sourceFile.getFileCount();
				
				if(fileCount >= 2)		//更新引用计数器	
				{
					sourceFile.setFileCount(fileCount-1);
					
					sourceFile.getFileIds().remove(id);
										
					sourceFileDao.update(sourceFile.getId(), 
							new Update().set("fileCount", sourceFile.getFileCount())
										.set("fileIds",   sourceFile.getFileIds())
										);
				}
				else		//引用计数为0,可删除SourceFile和对应的本地文件
				{
					sourceFileService.deleteSourceFileWithLocalFile(sourceFile);
				}
			}
		}

		fileDao.delete(id);           //删除File
		fileIndexer.delete(id);		  //删除File的索引
		
		//有评论的时候才要删除
		if(file.getComments() != null && file.getComments().size() > 0)
		{
			commentDao.delete("fileId", id);   //删除文件的所有评论
		}
	}
}
