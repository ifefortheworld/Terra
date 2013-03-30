package com.ireland.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ireland.dao.CommentDao;
import com.ireland.dao.SourceFileDao;
import com.ireland.dao.RoleDao;
import com.ireland.dao.TerraFileDao;
import com.ireland.dao.UserDao;
import com.ireland.model.User;
import com.ireland.model.business.SourceFile;
import com.ireland.model.business.TerraFile;
import com.ireland.service.TerraFileService;



/**
 * 
 * @KEN
 *
 */

@Service("terraFileServiceImpl")
public class TerraFileServiceImpl implements TerraFileService
{
	@Autowired
	private TerraFileDao terraFileDao;
	
	@Autowired
	private CommentDao commentDao;

	@Autowired
	private SourceFileDao sourceFileDao;
	
	/**
	 * 删除指定ID的TerraFile,及它的所有评论,
	 * 并更新或删除对应的RealFile
	 * @param id
	 */
	@Override
	public void delete(String id)
	{
		TerraFile file = terraFileDao.findOne(id);
		
		if(file == null) return;
		
		//更新或删除SourceFile
		String sourceFileId = file.getSourceFileId();
		if(sourceFileId != null)
		{
			SourceFile sourceFile = sourceFileDao.findOne(sourceFileId);
			
			if(sourceFile != null)
			{
				Integer referenceCount = sourceFile.getReferenceCount();
				
				if(referenceCount >= 2)		//更新引用计数器	
				{
					sourceFile.setReferenceCount(referenceCount-1);
					
					Set<String> referenceIds = sourceFile.getReferenceIds();
					referenceIds.remove(id);
					
					sourceFile.setReferenceIds(referenceIds);
					
					sourceFileDao.save(sourceFile);
				}
				else		//引用计数为0,可删除
				{
					sourceFileDao.delete(sourceFile);
				}
			}
		}

		terraFileDao.delete(id);           //删除文件
		
		//有评论的时候才要删除
		if(file.getComments() != null && file.getComments().size() > 0)
		{
			commentDao.delete("fileId", id);   //删除文件的所有评论
		}
	}
}
