package com.paddez.distro;

import javax.xml.ws.Endpoint;
import com.paddez.distro.Clinic;

public class ClinicPublisher
{
	public static void main(String [] args)
	{
		Endpoint.publish("http://127.0.0.1:9876/distro", new Clinic());
	}
}
