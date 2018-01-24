package de.katzen48.hetznercloudjava.resources;

import java.sql.Timestamp;

import com.google.gson.annotations.SerializedName;

public class Image 
{
	private int id;
	private Type type;
	private Status status;
	private String name;
	private String description;
	@SerializedName("image_size")
	private float imageSize;
	@SerializedName("disk_size")
	private float diskSize;
	private Timestamp created;
	@SerializedName("created_from")
	private ImageCreator imageCreator;
	@SerializedName("bound_to")
	private int boundServer;
	@SerializedName("os_flavor")
	private OSFlavor osFlavor;
	@SerializedName("os_version")
	private String osVersion;
	@SerializedName("rapid_deploy")
	private boolean rapidDeploy;

	
	public int getId()
	{
		return id;
	}

	public Type getType()
	{
		return type;
	}

	public Status getStatus() 
	{
		return status;
	}

	public String getName() 
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public float getImageSize() 
	{
		return imageSize;
	}

	public float getDiskSize()
	{
		return diskSize;
	}

	public Timestamp getCreated() 
	{
		return created;
	}

	public ImageCreator getImageCreator()
	{
		return imageCreator;
	}

	public int getBoundServer() 
	{
		return boundServer;
	}

	public OSFlavor getOsFlavor() 
	{
		return osFlavor;
	}

	public String getOsVersion() 
	{
		return osVersion;
	}

	public boolean isRapidDeploy() 
	{
		return rapidDeploy;
	}

	public static enum Type
	{
		system, snapshot, backup;
	}
	
	public static enum Status
	{
		available, creating;
	}
	
	public static enum OSFlavor
	{
		ubuntu, centos, debian, fedora, unknown;
	}
}
