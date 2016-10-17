package com.paddez.distro;

import java.util.*;
import java.security.MessageDigest;
public class AuthService
{
	private HashMap<String, byte[]> db;
	private final String ADMIN_PASSWORD = "password";
	private MessageDigest pw_gen = null;

	public AuthService()
	{
		db = new HashMap<>();
		byte[] digest = null;
		//Add admin
		try{
			pw_gen = MessageDigest.getInstance("MD5");
			digest = pw_gen.digest(ADMIN_PASSWORD.getBytes("UTF-8"));

		} catch(Exception e){}

		db.put("admin", digest);

	}

	public byte[] getHash(String s)
	{

		byte[] ret = null;
		if(pw_gen == null)
			return ret;

		try{
			pw_gen = MessageDigest.getInstance("MD5");
			ret = pw_gen.digest(s.getBytes("UTF-8"));
		} catch(Exception e){}

		return ret;
	}

	public boolean validate(String user, String pw)
	{
		byte[] pw_hash = getHash(pw);
		byte[] storedpw = db.get(user);
		if(storedpw == null)
			return false;
		if( MessageDigest.isEqual(storedpw, pw_hash ))
		{
			return true;
		}

		return false;
	}

	public boolean addUser(String user, byte[] hash)
	{
		db.put(user, hash);
		return true;
	}
}
