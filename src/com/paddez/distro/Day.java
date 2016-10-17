package com.paddez.distro;

import java.util.*;

public class Day implements Comparable
{
	private String name;
	private int day;

	private List<Slot> slots = new ArrayList<Slot>();

	public void waiting()
	{
		for(Slot s : slots)
		{

			System.out.println(s.hasWaiting());
		}
	}

	public static int getDayNum(String day)
	{
		int dayNum = 0;
		if(day == null)
			return dayNum;

		switch(day.toLowerCase())
		{
			case "monday":
				dayNum = 1;
				break;
			case "tuesday":
				dayNum = 2;
				break;
			case "wednesday":
				dayNum = 3;
				break;
			case "thursday":
				dayNum = 4;
				break;
			case "friday":
				dayNum = 5;
				break;
		}

		return dayNum;
	}

	public ArrayList<String> getList()
	{
		ArrayList<String> ret = new ArrayList<>();
		for(Slot tmp: slots)
		{
				ret.add(tmp.getType()+" "+tmp.getTime().getTimeString()+" "+this.day+" "+tmp.getName());
		}

		return ret;
	}

	public String getSlotString(Time t)
	{
		for(Slot tmp: slots)
		{
			if(tmp.equals(t))
			{
				return tmp.info();
			}
		}

		return "Error";
	}

	public Slot get(Time t)
	{
		Slot s = new Slot(t);
		for (Slot tmp:slots) {
			if (tmp.equals(s)) {
				return tmp;
			}
		}

		return null;
	}

	public boolean equals(Day d)
	{
		return this.day == d.getDay();
	}


	@Override
	public int compareTo(Object obj)
	{
		Day d = (Day) obj;
		final int BEFORE = -1;
		final int EQUAL = 0;
		final int AFTER = 1;

		if(this == d) return EQUAL;
		if(this.day > d.getDay()) return BEFORE;
		if(this.day < d.getDay()) return AFTER;
		if(this.day == d.getDay()) return EQUAL;

		return -1;
	}

	public boolean addWaiting(Time t, String s)
	{
		ListIterator<Slot> it = slots.listIterator();
		Slot tmp;

		while (it.hasNext()) {
			tmp = it.next();
			if (tmp.getTime().compareTo(t) == 0) {
				System.out.println(tmp.info());
				tmp.addWaiting(s);
				return true;
			}
		}
		return false;
	}


	public boolean remove(Time t)
	{
		ListIterator<Slot> it = slots.listIterator();
		Slot tmp;

		while (it.hasNext()) {
			tmp = it.next();
			if (tmp.getTime().compareTo(t) == 0 && tmp.isBusy()) {
				if (tmp.getSlots() == 1) {
					System.out.println("Has Waiting? - " + tmp.hasWaiting());
					if (tmp.hasWaiting()) {
						tmp.getNext();
						return true;
					}
					else
					{
						it.set(new Slot(t));
						return true;
					}
				}
				else if(tmp.getSlots() == 2)
				{
					if (tmp.hasWaiting()) {
						tmp.getNext();
						tmp = it.next();
						if (tmp.hasWaiting()) {
							tmp.getNext();
							return true;
						}
						else
						{
							Time new_t = new Time(t.getTotalMinutes() + 30);
							it.set(new Slot(new_t));
							return true;
						}

					}
				}
			}
		}
		return false;

	}

	public boolean freeTime(Procedure p)
	{
		Time t = p.getTime();
		ListIterator<Slot> it = slots.listIterator();
		Slot tmp;
		while(it.hasNext()) //Iterate through the list
		{				
			tmp = it.next(); 
			if(tmp.getTime().compareTo(t) == 0 && !tmp.isBusy())
			{
				//One slot, not busy. Has room.
				if(p.getSlots() == 1)
					return true;
				else if(p.getSlots() == 2 && it.hasNext())
				{
				//two slots, move to next slot, check if busy.
					tmp = it.next();
					if(!tmp.isBusy())
					{
						return true;
					}
				}
				else
					return false;
			}
		}

		return false;
	}

	public boolean add(Procedure p, int day)
	{
		Time t = p.getTime();
		p.day = day;
		ListIterator<Slot> it = slots.listIterator();
		Slot tmp;

		while(it.hasNext())//Iterate through the list
		{
			tmp = it.next();
			if(tmp.getTime().compareTo(t) == 0 && !tmp.isBusy())
			{
				if(p.getSlots() == 1) //One slot, not busy. Assign and exit.
				{
					it.set(p);
					return true;
				}
				else if(p.getSlots() == 2 && it.hasNext()) //Two slots, need to check next
				{
					it.set(p);
					tmp = it.next();
					Time new_t = new Time(t.getTotalMinutes() + 30);
					Slot new_p = new Procedure(new_t, p.getType(), day);
					if(!tmp.isBusy()) //if next is not busy, assign.
					{
						new_p.day = day;
						it.set(new_p);
						return true;
					}
					return false;					

				}
				return false;
			}
		}
		return false;
	}

	public Day(int day)
	{
		// 540 minutes = 9am
		int tmp = 540;

		switch(day)
		{
			case 1: this.name = "Monday";
					break;
			case 2: this.name = "Tuesday";
					break;
			case 3: this.name = "Wednesday";
					break;
			case 4:	this.name = "Thursday";
					break;
			case 5: this.name = "Friday";
					break;
			case 6: this.name = "Saturday";
					break;
			case 7: this.name = "Sunday";
		}
		this.day = day;
		//Populate Day
		slots.add(new Slot(tmp));
		for (int i = 0; i < 15 ; i++) {
			tmp = tmp + (30);
			slots.add(new Slot(tmp));
		}
	}


	public String toString()
	{
		return name;
	}

	public int getDay()
	{
		return day;
	}
} 
