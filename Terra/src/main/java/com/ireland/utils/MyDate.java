package com.ireland.utils;

import java.util.Date;

public class MyDate extends Date
{
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
	
		String m = String.valueOf(this.getMonth()+1);
		
		if(m.length() < 2)
			m = "0"+m;
			
		String d = String.valueOf(this.getDate());
		
		if(d.length() < 2)
			d = "0"+d;
		
		return String.valueOf(this.getYear()+1900)+'/'+m+'/'+d;
	}
}

