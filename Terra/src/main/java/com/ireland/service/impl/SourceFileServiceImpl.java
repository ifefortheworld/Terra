package com.ireland.service.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ireland.dao.CommentDao;
import com.ireland.dao.LocalFileDao;
import com.ireland.dao.SourceFileDao;
import com.ireland.dao.RoleDao;
import com.ireland.dao.FileDao;
import com.ireland.dao.UserDao;
import com.ireland.model.User;
import com.ireland.model.business.SourceFile;
import com.ireland.model.business.File;
import com.ireland.service.SourceFileService;
import com.ireland.service.FileService;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


/**
 * 
 * @KEN
 *
 */

@Service("sourceFileService")
public class SourceFileServiceImpl implements SourceFileService
{
	@Autowired
	private FileDao fileDao;


	@Autowired
	private SourceFileDao sourceFileDao;
	
	@Autowired
	private LocalFileDao localFileDao;
	

	
	
	@Override
	public SourceFile calculateAndUpdateFileHashCode(String id)
	{
		SourceFile sourceFile = sourceFileDao.findOne(id);
		
		//文件不存在,或文件的特征值已存在,则直接返回
		if(sourceFile == null)
			return null;
		
		//若已存在,直接返回
		if(StringUtils.hasText(sourceFile.getFileHashCode()))
			return sourceFile;
			
		
		Resource resource = localFileDao.read(sourceFile.getStorageLocation());
		
		String hash = null;
		try
		{
			hash = DigestUtils.md5Hex(new BufferedInputStream(resource.getInputStream(),1024*1024));		//1MB的缓存
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		sourceFile.setFileHashCode(hash);

		sourceFileDao.set(sourceFile.getId(), "fileHashCode", hash);		//(为了效率)只更新hashcode
		
		return sourceFile;
	}
	


	@Override
	public SourceFile updateOrMerageSourceFile(String id)
	{
		//0:在数据库中查出指定ID的SourceFile 1:如果指定ID的SourceFile未计算特征值,则计算并更新它(同步到数据库)
		SourceFile sourceFile = calculateAndUpdateFileHashCode(id);
		
		if(sourceFile == null) return null;
		
		
		//2:查找是否存在其它特征值一样的文件,若存在多个一样的情况(这种情况很少),只取第一个
		SourceFile sameFile = sourceFileDao.findOne(query(where("fileHashCode").is(sourceFile.getFileHashCode()).norOperator(where("id").is(sourceFile.getId())) ));
	
		
		//3:若特征值相同的文件不存在,更新SourceFile的特征值到数据库,并返回更新后的SourceFile
		if(sameFile == null) return sourceFile;

		
		SourceFile lessRefSourceFile;			//引用少的SourceFile
		SourceFile moreRefSourceFile;			//引用多的SourceFile
		
		
		//先确定哪个引用数的多少,将引用少的合并到引用多的
		if(sourceFile.getFileCount() <= sameFile.getFileCount())
		{
			lessRefSourceFile = sourceFile;
			moreRefSourceFile = sameFile;
		}
		else
		{
			lessRefSourceFile = sameFile;
			moreRefSourceFile = sourceFile;
		}
		
		
		//4:若存在,则将引用计数少的lessRefSourceFile合并到引用计数多的moreRefSourceFile,(将引用计数器合并,引用的文件id列表合并),
		
		//先更新terraFileid到moreRefSourceFile的引用
		Set<String> terraFileIds  = lessRefSourceFile.getFileIds();
		
		fileDao.updateMulti(query(where("id").in(terraFileIds)), new Update().set("sourceFileId", moreRefSourceFile.getId()));
		

		//更新moreRefSourceFile
		moreRefSourceFile.getFileIds().addAll(lessRefSourceFile.getFileIds());
	
		moreRefSourceFile.setFileCount(moreRefSourceFile.getFileIds().size());
		
		sourceFileDao.update(moreRefSourceFile.getId(), 
										new Update().set("referenceCount", moreRefSourceFile.getFileCount())
													.set("referenceIds",   moreRefSourceFile.getFileIds())
													);
		
		//删除计数计数少的那个lessRefSourceFile
		sourceFileDao.delete(lessRefSourceFile.getId());			
		
		
		//返回合并后的SourceFile
		return moreRefSourceFile;
	}
}
