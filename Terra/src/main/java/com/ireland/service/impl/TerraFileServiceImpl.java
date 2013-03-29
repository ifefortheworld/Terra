package com.ireland.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ireland.dao.CommentDao;
import com.ireland.dao.RealFileDao;
import com.ireland.dao.RoleDao;
import com.ireland.dao.TerraFileDao;
import com.ireland.dao.UserDao;
import com.ireland.model.User;
import com.ireland.model.business.RealFile;
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
	private RealFileDao realFileDao;
	
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
		
		//更新或删除RealFile
		String realFileId = file.getRealFileId();
		if(realFileId != null)
		{
			RealFile realFile = realFileDao.findOne(realFileId);
			
			if(realFile != null)
			{
				Integer referenceCount = realFile.getReferenceCount();
				
				if(referenceCount >= 2)		//更新引用计数器	
				{
					realFile.setReferenceCount(referenceCount-1);
					
					List<String> referenceIds = realFile.getReferenceIds();
					referenceIds.remove(id);
					
					realFile.setReferenceIds(referenceIds);
					
					realFileDao.save(realFile);
				}
				else		//引用计数为0,可删除
				{
					realFileDao.delete(realFile);
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
