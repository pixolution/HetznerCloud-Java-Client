package de.katzen48.hetznercloudjava.resources;

import java.sql.Timestamp;

import com.google.gson.annotations.SerializedName;

import de.katzen48.hetznercloudjava.HetznerCloud;

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

	
	/**
	 * Update the Description of this Image
	 * @param cloud Provided Cloud
	 * @param description The new Description
	 * @return If the Description was updated
	 */
	public boolean updateDescription(HetznerCloud cloud, String description)
	{
		if(!cloud.updateImageDescription(id, description).description.equals(this.description))
		{
			this.description = description;
			return true;
		}
		return false;
	}
	
	/**
	 * Update the Type of this Image
	 * @param cloud Provided Cloud
	 * @param type The new Type
	 * @return If the Type was updated
	 */
	public boolean updateType(HetznerCloud cloud, Type type)
	{
		if(!cloud.updateImageType(id, type).type.equals(this.type))
		{
			this.type = type;
			return true;
		}
		return false;
	}
	
	/**
	 * Update the Description and Type of this Image
	 * @param cloud Provided Cloud
	 * @param description The new Description
	 * @param type The new Type
	 * @return If the Description and Type were updated
	 */
	public boolean update(HetznerCloud cloud, String description, Type type)
	{
		Image image = cloud.updateImage(id, description, type);
		if(!image.type.equals(this.type) && !image.description.equals(this.description))
		{
			this.type = type;
			this.description = description;
			return true;
		}
		return false;
	}
	
	/**
	 * Delete this Image
	 * @param cloud Provided Cloud
	 * @return If this Image was deleted
	 */
	public boolean delete(HetznerCloud cloud)
	{
		return cloud.deleteImage(id);
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
	 * Get the Type
	 * @return Type
	 */
	public Type getType()
	{
		return type;
	}

	/**
	 * Get the Status
	 * @return Status
	 */
	public Status getStatus() 
	{
		return status;
	}

	/**
	 * Get the Name
	 * @return Name
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * Get the Description
	 * Returns null, if no Description was provided
	 * @return Description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Get the ImageSize
	 * @return Size
	 */
	public float getImageSize() 
	{
		return imageSize;
	}

	/**
	 * Get the DiskSize
	 * @return DiskSize
	 */
	public float getDiskSize()
	{
		return diskSize;
	}

	/**
	 * When Image was created
	 * @return Timestamp
	 */
	public Timestamp getCreated() 
	{
		return created;
	}

	/**
	 * Get who created the Image
	 * @return ImageCreator
	 */
	public ImageCreator getImageCreator()
	{
		return imageCreator;
	}

	/**
	 * Get the Server, which the Image is boundTo
	 * Returns null, if Image is not bound
	 * @return ServerID
	 */
	public int getBoundServer() 
	{
		return boundServer;
	}

	/**
	 * Get the OsFlavor
	 * @return OsFlavor
	 */
	public OSFlavor getOsFlavor() 
	{
		return osFlavor;
	}

	/**
	 * Get the OsVersion
	 * @return OsVersion
	 */
	public String getOsVersion() 
	{
		return osVersion;
	}

	/**
	 * If RapidDeploy is available
	 * @return If RapidDeploy is available
	 */
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
