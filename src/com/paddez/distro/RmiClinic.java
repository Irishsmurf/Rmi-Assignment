package com.paddez.distro;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;

import java.security.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;	
import java.util.List;
import java.util.HashMap;
import java.util.ListIterator;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService(endpointInterface = "com.paddez.distro.RmiClinicInf")
public class RmiClinic implements RmiClinicInf
{

	private AppointmentBook appts = new AppointmentBook();
	private AuthService auth = new AuthService();

	public boolean connectable()
	{
		return true;
	}


	public String[] getAppointments(Integer day)
	{	
		ArrayList<String> ret;

		if (day == 7 || day == 6) {
			ret = appts.getList();
		}
		else
			ret = appts.getList(day);

	//Convert it to an array as WSDL does not allow top-level lists.
		String [] array = new String[ret.size()];

		int i = 0;
		for (String tmp : ret ) {
			array[i] = tmp;
			i++;
		}

		return array;
	}


	public String cancelAppointment(int hour, int minute, int day)
	{
		Time tmp = new Time(hour, minute);
		if (appts.cancel(tmp, day))
		{
			return "Successfully Cancelled";
		}
		else
			return "Error";
	}

	public String getInfo(int hour, int minute, int day)
	{
		Time t = new Time(hour, minute);
		return appts.getInfo(t, day);
	}

	public boolean isAvailable(int hour, int minute, int day)
	{ 
		Time t = new Time(hour, minute);
		return !appts.isBusy(t, day);
	}

	public boolean addAppointment(int type, int hour, int minute, int day, String name)
	{
		Procedure p;
		p = new Procedure(type, hour, minute, day, name);

		return appts.add(p, day);
	}


	public RmiClinic()
	{

	}

	public static void main(String [] args)
	{/*
		System.out.println("RMI Clinic Started...");

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
			System.out.println("Security Manager installed");
		}

		try {
				LocateRegistry.createRegistry(1099);
				System.out.println("RMI Registry created");
		}
		catch(RemoteException e){}

		try{
			RmiClinic clinic = new RmiClinic();
			Naming.rebind("//localhost/RmiClinic", clinic);
			System.out.println("Found in registry");
		}
		catch(Exception e)
		{
			System.err.println("RMI server exception:" + e);
            e.printStackTrace();
		}
	*/}
}

class Slot implements Comparable
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



//Class used for Storing appointments.
class AppointmentBook
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
