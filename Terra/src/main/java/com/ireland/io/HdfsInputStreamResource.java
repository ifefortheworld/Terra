package com.ireland.io;

import java.io.IOException;
import java.io.InputStream;

import org.apache.hadoop.fs.FileStatus;
import org.springframework.core.io.InputStreamResource;

/**
 * 针对HDFS优化的InputStreamResource
 * 
 * @author KEN
 *
 */
public class HdfsInputStreamResource extends InputStreamResource
{

	private final FileStatus fileStatus;

	/**
	 * FileStatus表示文件的状态,inputStream为文件的数据源
	 * @param fileStatus
	 * @param inputStream
	 */
	public HdfsInputStreamResource(FileStatus fileStatus,InputStream inputStream)
	{
		super(inputStream);
		
		this.fileStatus = fileStatus;
	}
	
	@Override
	public long contentLength() throws IOException
	{
		return fileStatus.getLen();
	}
}
