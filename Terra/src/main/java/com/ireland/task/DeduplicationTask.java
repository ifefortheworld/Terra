package com.ireland.task;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ireland.service.SourceFileService;


@Component("deduplicationTask")
public class DeduplicationTask implements Runnable
{
	protected final Log logger = LogFactory.getLog(getClass());
	
	
	@Autowired
	private SourceFileService sourceFileService;

	@Override
	public void run()
	{
		if(logger.isDebugEnabled())
		{
			logger.debug("DeduplicationTask start at "+new Date());
		}
		
		int cnt = sourceFileService.deduplicationForNewSourceFile();
		
		if(logger.isDebugEnabled())
		{
			logger.debug("Deduplication cnt: "+cnt);
		}
	}
}
