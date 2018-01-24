package de.katzen48.hetznercloudjava.resources;

import com.google.gson.annotations.SerializedName;

public class ServerType 
{
	private int id;
	private String name;
	private String description;
	private int cores;
	private long memory;
	private long disk;
	private Price price;
	@SerializedName("storage_type")
	private StorageType storageType;
	
	public int getId() 
	{
		return id;
	}

	public String getName() 
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public int getCores() 
	{
		return cores;
	}

	public long getMemory()
	{
		return memory;
	}

	public long getDisk() 
	{
		return disk;
	}

	public Price getPrice() 
	{
		return price;
	}

	public StorageType getStorageType() 
	{
		return storageType;
	}

	public static enum StorageType
	{
		local, network;
	}
}
