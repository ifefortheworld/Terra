package com.ireland.security.web.access.expression;

import java.util.Calendar;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;

/**
 * 
 * 增加了日期控制的SpEL 4 WebSecurityExpressionRoot
 * 
 * 
 * @KEN
 * @since 2012-05-30
 *
 */


public class DateWebSecurityExpressionRoot extends WebSecurityExpressionRoot
{

	public DateWebSecurityExpressionRoot(Authentication a,FilterInvocation fi)
	{
		super(a, fi);
	}
	
	//Util-------------------------------------------------------------
	
	
	/**
	 * 将指定日期转换为Date
	 * @param year  e.g: 2012
	 * @param month ,1--12
	 * @param day   ,1--31
	 * @return
	 */
	protected Date toDate(int year, int month, int day)
	{
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(year, month - 1, day,0,0,0);
		
		return calendar.getTime();
	}
	
	/**
	 * 将指定日期转换为Calendar
	 * @param year  e.g: 2012
	 * @param month ,1--12
	 * @param day   ,1--31
	 * @return
	 */
	protected Calendar toCalendar(int year, int month, int day)
	{
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(year, month - 1, day,0,0,0);
		
		return calendar;
	}
	
	
	//Method------------------------------------------------------------------
	
	
	/**
	 * 如果当前时间早于给定的时间,则返回true
	 * 
	 * 截止日期（Deadline)
	 * @param year  e.g: 2012
	 * @param month ,1--12
	 * @param day   ,1--31
	 * 
	 * @return
	 */
	public boolean before(int year, int month, int day)
	{
		//截止日期（Deadline)
		Date deadline = this.toDate(year, month, day);
		
		if(new Date().before(deadline))
			return true;
		
		return false;
	}
	
	
	
	/**
	 * 如果当前时间晚于给定的时间,则返回true
	 * 
	 * 开始日期（start date)
	 * @param year  e.g: 2012
	 * @param month ,1--12
	 * @param day   ,1--31
	 * @return
	 */
	public boolean after(int year, int month, int day)
	{
		//开始日期（start date)
		Date startDate = this.toDate(year, month, day);
				
		if(new Date().after(startDate))
			return true;
		
		return false;
	}
	
	
	
	
	/**
	 * 如果当前日期等于给定的日期,则返回true
	 * @param year  e.g: 2012
	 * @param month ,1--12
	 * @param day   ,1--31
	 * @return
	 */
	public boolean equals(int year, int month, int day)
	{
		//设置的日期
		Calendar date = this.toCalendar(year, month, day);
	
		//当前的时期
		Calendar rightNow = Calendar.getInstance();
		

		if(rightNow.get(Calendar.YEAR) ==  date.get(Calendar.YEAR) &&
		   rightNow.get(Calendar.MONTH) ==  date.get(Calendar.MONTH) &&
		   rightNow.get(Calendar.DATE) ==  date.get(Calendar.DATE) )
		{
			return true;
		}
		
		return false;
	}
	
	
	
		
	/**
	 * 如果当前时间在指定范围内(begin <= cur <= end),则返回true
	 * 
	 * 开始日期（start date)
	 * @param year1
	 * @param month1
	 * @param day1
	 * 
	 * 截止日期（Deadline)
	 * @param year2
	 * @param month2
	 * @param day2
	 * @return
	 */
	public boolean between(int year1, int month1, int day1,int year2, int month2, int day2)
	{
		if(this.after(year1, month2, day1) && this.before(year2, month2, day2))
			return true;
		
		return false;
	}
	
}
