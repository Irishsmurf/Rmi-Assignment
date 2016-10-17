package com.paddez.distro;

import com.paddez.distro.Slot;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;


class Procedure extends Slot
{
	public String type;
	public int slots;
	public boolean busy = true;
	public Time time;
	public int day;
	public String patient;
	public LinkedList<String> waiting;

	public void printWaiting()
	{
		System.out.println(waiting.peek());
	}

	public String getName()
	{
		return patient;
	}

	public boolean hasWaiting()
	{
		return !waiting.isEmpty();
	}

	public Procedure(int type0, int hour, int minute, int day, String name)
	{
		switch (type0) {
			case 1: type = "Consultation";
					slots = 1;
					break;

			case 2: type = "Minor-Surgical";
					slots = 2;
					break;
		}
		this.day = day;
		this.time = new Time(hour, minute);
		this.patient = name;
		this.waiting = new LinkedList<String>();
	}

	public void addWaiting(String wait)
	{
		waiting.offer(wait);
	}

	public boolean getNext()
	{
		if (waiting.isEmpty()) {
			return false;
		}

		String tmp = waiting.remove();
		String [] delim = tmp.split(" ");

		patient = delim[0];
		type = delim[1];

		return true;
	}

	public boolean isBusy()
	{
		return busy;
	}

	public boolean equals(Slot s)
	{

		if (s == null) {
			return false;
		}
		if (s == this) {
			return true;
		}

		if(this == null)
			return false;


		return time.compareTo(s.getTime()) == 0;
	}
	public Procedure()
	{
		type = "Nothing";
		slots = 1;
		time = new Time(9, 00);
	}

	public Procedure(Time t, String type, int day)
	{
		this.time = t;
		this.type = type;
		this.day = day;
	}
	public String getType()
	{
		return type;
	}


	public String info()
	{
		return type;
	}

	public Time getTime()
	{
		return time;
	}

	public int getSlots()
	{
		return slots;
	}
}
