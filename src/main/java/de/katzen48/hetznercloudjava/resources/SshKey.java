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
	
	/**
	 * Change the Name of this SshKey
	 * @param cloud Provided Cloud
	 * @param name The new Name
	 * @return If the Name was changed
	 */
	public boolean changeName(HetznerCloud cloud, String name)
	{
		if(cloud.changeSshKeyName(id, name) != null)
		{
			this.name = name;
			return true;
		}
		
		return false;
	}
	
	/**
	 * Delete this SshKey
	 * @param cloud Provided Cloud
	 * @return If this SshKey was deleted
	 */
	public boolean delete(HetznerCloud cloud)
	{
		return cloud.deleteSshKey(id);
	}
	
	/**
	 * Get the ID
	 * @return ID
	 */
	public int getId() 
	{
		return id;
	}
	
	/**
	 * Get the Name of this SshKey
	 * @return Name
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * Get the FingerPrint of this SshKey
	 * @return FingerPrint
	 */
	public String getFingerprint()
	{
		return fingerprint;
	}
	
	/**
	 * Get the PublicKey of this SshKey
	 * @return PublicKey
	 */
	public String getPublicKey()
	{
		return publicKey;
	}
}
