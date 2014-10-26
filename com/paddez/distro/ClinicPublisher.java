package com.paddez.distro;

import javax.xml.ws.Endpoint;

public class ClinicPublisher
{
	public static void main(String [] args)
	{
		Endpoint.publish("http://127.0.0.1:9876/distro", new Clinic());
	}
}