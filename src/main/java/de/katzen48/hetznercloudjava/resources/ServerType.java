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
	private Price[] prices;
	@SerializedName("storage_type")
	private StorageType storageType;
	
	/**
	 * Get the ID
	 * @return ID
	 */
	public int getId() 
	{
		return id;
	}

	/**
	 * Get the Name of this ServerType
	 * @return Name
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * Get the Description of this ServerType
	 * @return Description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Get the CoreCount of this ServerType
	 * @return CoreCount
	 */
	public int getCores() 
	{
		return cores;
	}

	/**
	 * Get the MemorySize for this ServerType
	 * @return MemorySize
	 */
	public long getMemory()
	{
		return memory;
	}

	/**
	 * Get the maximum available DiskSpace for this ServerTyoe
	 * @return maximum available DiskSpace
	 */
	public long getDisk() 
	{
		return disk;
	}

	/**
	 * Get the Prices for this ServerType
	 * @return Prices
	 */
	public Price[] getPrices() 
	{
		return prices;
	}

	/**
	 * Get the StorageType of this ServerType
	 * @return StorageType
	 */
	public StorageType getStorageType() 
	{
		return storageType;
	}

	public static enum StorageType
	{
		local, network;
	}
}
