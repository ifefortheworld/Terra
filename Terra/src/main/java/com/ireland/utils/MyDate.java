package com.ireland.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate extends Date
{
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	
	public MyDate()
	{
		super();
	}

	public MyDate(Date date)
	{
		super(date.getTime());
	}
	
	public MyDate(long date)
	{
		super(date);
	}
	
	//yyyy/MM/dd
	@Override
	public String toString()
	{
		return format.format(this);
	}
}

