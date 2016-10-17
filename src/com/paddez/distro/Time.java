package com.paddez.distro;
import java.util.*;
public class Time implements Comparable
{
	private final int TOTAL = 1439;
	private int minute;

	@Override
	public int compareTo(Object obj)
	{
		final int BEFORE = -1;
		final int EQUAL = 0;
		final int AFTER = 1;

		Time time = (Time) obj;
		if(this == time) return EQUAL;

		if(this.minute < time.getTotalMinutes()) return BEFORE;
		if(this.minute > time.getTotalMinutes()) return AFTER;
		if(this.minute == time.getTotalMinutes()) return EQUAL;

		return BEFORE;
	} 

	public Time(int hour, int minute)
	{
		this.minute = (hour * 60) + minute;
	}

	public Time(int minutes)
	{
		this.minute = minutes;
	}

	public String getTimeString()
	{
		String tmp = "";
		if(minute%60 == 0)
			tmp = "00";
		else
			tmp = "30";
		return ""+minute/60 + ":"+tmp;
	}

	public int getHour()
	{
		return minute/60;
	}

	public int getMinute()
	{
		return minute%60;
	}

	public int getTotalMinutes()
	{
		return minute;
	}
} 
