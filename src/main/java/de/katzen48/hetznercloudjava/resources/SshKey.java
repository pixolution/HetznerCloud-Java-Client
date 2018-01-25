package de.katzen48.hetznercloudjava.resources;

import com.google.gson.annotations.SerializedName;

public class SshKey 
{
	private int id;
	private String name;
	private String fingerprint;
	@SerializedName("public_key")
	private String publicKey;
	
	public int getId() 
	{
		return id;
	}
	public String getName() 
	{
		return name;
	}
	public String getFingerprint()
	{
		return fingerprint;
	}
	public String getPublicKey()
	{
		return publicKey;
	}
}
