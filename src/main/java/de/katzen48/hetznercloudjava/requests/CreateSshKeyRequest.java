package de.katzen48.hetznercloudjava.requests;

import com.google.gson.annotations.SerializedName;

public class CreateSshKeyRequest 
{
	private String name;
	@SerializedName("public_key")
	private String publicKey;
	
	
	public CreateSshKeyRequest(String name, String publicKey)
	{
		this.name = name;
		this.publicKey = publicKey;
	}


	public String getName() 
	{
		return name;
	}

	public String getPublicKey() 
	{
		return publicKey;
	}
}
