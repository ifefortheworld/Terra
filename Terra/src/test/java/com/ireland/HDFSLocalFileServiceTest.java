package com.ireland;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ireland.dao.LocalFileDao;
import com.ireland.dao.SourceFileDao;
import com.ireland.model.business.SourceFile;
import com.ireland.service.SourceFileService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/mongo-config.xml","/applicationContext.xml"})
public class HDFSLocalFileServiceTest
{
	@Autowired
	private LocalFileDao localFileDao;
	
	@Test
	public void test()
	{
		localFileDao.delete("/staticfiles/0e049492-9d12-450d-87ca-b7bd4ed0d999");
	}

}
