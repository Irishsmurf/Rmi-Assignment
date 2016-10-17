package com.paddez.distro;

import com.paddez.distro.Time;

import java.util.LinkedList;

public class Slot implements Comparable
{
	private String type = "Free";
	private int slots = 0;
	private boolean busy = false;
	private Time time;
	private String patient = "N/A";
	public int day;
	public LinkedList<String> waiting = new LinkedList<String>();

	public void printWaiting()
	{
		System.out.println(waiting.peek());
	}

	public void addWaiting(String wait)
	{
		waiting.offer(wait);
	}

	public String getName()
	{
		return patient;
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

	public boolean hasWaiting()
	{
		return !waiting.isEmpty();
	}

	public String info()
	{
		return type;
	}
	public boolean isBusy()
	{
		return busy;
	}

	public boolean equals(Time t)
	{
		if(t == null){
			return false;
		}

		return time.compareTo(t) == 0;
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

	@Override
	public int compareTo(Object obj)
	{
		Slot s = (Slot) obj;
		if (!(s instanceof Slot)) {
			return 1;
		}
		return time.compareTo(s.getTime()) ;
	}

	public Slot()
	{
		//Empty
	}
	public Slot(Time t)
	{
		this.time = t;
	}

	public Slot(int hour, int minute)
	{
		time = new Time(hour, minute);
		if(time.compareTo(new Time(13,0)) == 0 || time.compareTo(new Time(13, 30)) == 0)
		{
			type = "Lunch";
			busy = true;
		}
	}

	public Slot(int minutes)
	{
		time = new Time(minutes);
		if(time.compareTo(new Time(13,0)) == 0 || time.compareTo(new Time(13, 30)) == 0)
		{
			type = "Lunch";
			busy = true;
		}
	}

	public Time getTime()
	{
		return time;
	}

	public int getSlots()
	{
		return slots;
	}

	public String getType()
	{
		return type;
	}

}
