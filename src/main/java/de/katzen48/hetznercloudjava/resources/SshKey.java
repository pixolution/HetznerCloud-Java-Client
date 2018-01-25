package de.katzen48.hetznercloudjava.resources;

import com.google.gson.annotations.SerializedName;

import de.katzen48.hetznercloudjava.HetznerCloud;

public class SshKey 
{
	private int id;
	private String name;
	private String fingerprint;
	@SerializedName("public_key")
	private String publicKey;
	
	public boolean changeName(HetznerCloud cloud, String name)
	{
		if(cloud.changeSshKeyName(id, name) != null)
		{
			this.name = name;
			return true;
		}
		
		return false;
	}
	
	public boolean delete(HetznerCloud cloud)
	{
		return cloud.deleteSshKey(id);
	}
	
	
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
