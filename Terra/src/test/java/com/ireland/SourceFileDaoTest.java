package com.ireland;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ireland.dao.SourceFileDao;
import com.ireland.model.business.SourceFile;
import com.ireland.service.SourceFileService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/mongo-config.xml","/applicationContext.xml"})
public class SourceFileDaoTest
{
	@Autowired
	private SourceFileDao sourceFileDao;
	
	@Autowired
	private SourceFileService sourceFileService;
	
	@Test
	public void test()
	{
		SourceFile file = sourceFileService.calculateAndUpdateFileHashCode("5156b596caffc24eceb1c947");
	}

	
	@Test
	public void test2()
	{
		SourceFile file = sourceFileService.updateOrMerageSourceFile("5156b531caffc24eceb1c945");
	}

	@Test
	public void test3()
	{
		int cnt = sourceFileService.deduplicationForNewSourceFile();
	}
	

}
