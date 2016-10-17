package com.paddez.distro;

import com.paddez.distro.Day;
import com.paddez.distro.Slot;

import java.util.List;
import java.util.ArrayList;

public class AppointmentBook
{
	private List<Day> week = new ArrayList<Day>();

	public boolean cancel(Time t, int day)
	{
		Day d = week.get(day - 1);
		return d.remove(t);
	}

	public ArrayList<String> getList(int day)
	{
		Day d = week.get(day-1);
		return d.getList();
	}

	public ArrayList<String> getList()
	{
		ArrayList<String> total = new ArrayList<>();
		for (Day tmp : week ) {
			total.addAll(tmp.getList());
		}

		return total;
	}

	public boolean add(Procedure a, int day)
	{
		Day d = week.get(day-1);

		for (Day tmp:week ) {
			if (tmp.getDay() == day) {
				d = new Day(day);
				boolean btmp = tmp.freeTime(a);
				if(btmp)
				{
					tmp.add(a, day);
					return true;
				}
				else
				{
					//Time is busy, add to wait
					d.waiting();
					Slot temp = d.get(a.getTime());
					d.addWaiting(a.getTime(), a.getName() + " " + a.getType());
					temp.printWaiting();
					return true;
				}
			}
		}

		return false;
	}

	public String checkSlot(Time t, int day)
	{
		Slot s = week.get(day-1).get(t);
		return s.info();
	}

	public String getInfo(Time t, int day)
	{
		Slot s = week.get(day-1).get(t);
		return s.info();
	}

	public boolean isBusy(Time t, int day)
	{
		Slot s = week.get(day-1).get(t);
		return s.isBusy();
	}

	public Slot getSlot(Time time)
	{
		return null;
	}

	public AppointmentBook()
	{
		for (int i = 1; i <= 5; i++) {
			week.add(new Day(i));
		}
	}
}
