package com.paddez.distro;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public class Clinic
{
	private RmiClinic utils;

	public Clinic()
	{
		utils = new RmiClinic();
	}

	@WebMethod 
	public boolean connectable()
	{
		return utils.connectable();
	}
    @WebMethod 
    public String getInfo(int hour, int minute, int day)
    {
    	return utils.getInfo(hour, minute, day);
    }
    @WebMethod 
    public boolean isAvailable(int hour, int minute, int day)
    {
    	return utils.isAvailable(hour,minute,day);
    }
    @WebMethod 
    public boolean addAppointment(int type, int hour, int minute, int day, String name)
    {
    	return utils.addAppointment(type, hour, minute, day, name);
    }
    @WebMethod 
    public String cancelAppointment(int hour, int minute, int day)
    {
    	return utils.cancelAppointment(hour, minute, day);
    }
    @WebMethod 
    public String[] getAppointments(Integer day)
    {
    	return utils.getAppointments(day);
    }
}