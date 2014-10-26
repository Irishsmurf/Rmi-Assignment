package com.paddez.distro;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding( style = Style.RPC)
public interface RmiClinicInf
{
    @WebMethod boolean connectable();
    @WebMethod String getInfo(int hour, int minute, int day);
    @WebMethod boolean isAvailable(int hour, int minute, int day);
    @WebMethod boolean addAppointment(int type, int hour, int minute, int day, String name);
    @WebMethod String cancelAppointment(int hour, int minute, int day);
    @WebMethod String[] getAppointments(Integer day);
}
