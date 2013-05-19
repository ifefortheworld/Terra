package com.ireland.task;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;


/**
 * 文件去重任务一般安排在每天的凌晨02:00 ~ 04:00
 * @author KEN
 *
 */
@Component
public class DeduplicationTaskScheduler
{
	protected final Log logger = LogFactory.getLog(getClass());
	
	/*1天的毫秒数 */
	public static final long ONE_DAY = 24 * 60 * 60 *1000;

	/*1小时的毫秒数*/
	public static final long ONE_HOUR = 60 * 60 *1000;
	
	/*1分钟的毫秒数*/
	public static final long ONE_MINUTE = 60 *1000;
	
	@Autowired
	private DeduplicationTask deduplicationTask;
	
	@Autowired
	private TaskScheduler taskScheduler;
	
	
	/*默认任务周期:1天*/
	private long period = ONE_DAY;
	
	
	@PostConstruct
	public void scheduleTask()
	{
		logger.info("DeduplicationTaskScheduler start scheduleTask.");
		
		
		long time = new Date().getTime();
		
		Date startTime = new Date( (time - time % ONE_DAY) + ONE_DAY + ONE_HOUR * 2);		//开始时间为第2天的02:00
		
		taskScheduler.scheduleAtFixedRate(deduplicationTask, startTime, period);
	}

}
