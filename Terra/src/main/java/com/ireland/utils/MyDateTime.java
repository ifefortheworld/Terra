package com.ireland.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateTime extends Date
{
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	
	public MyDateTime()
	{
		super();
	}

	public MyDateTime(Date date)
	{
		super(date.getTime());
	}
	
	public MyDateTime(long date)
	{
		super(date);
	}
	
	//yyyy/MM/dd HH:mm
	@Override
	public String toString()
	{
		return format.format(this);
	}
}

