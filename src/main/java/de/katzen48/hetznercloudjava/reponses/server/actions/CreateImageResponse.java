package de.katzen48.hetznercloudjava.reponses.server.actions;

import de.katzen48.hetznercloudjava.resources.Image;
import de.katzen48.hetznercloudjava.resources.ApiAction;

public class CreateImageResponse 
{
	private Image image;
	private ApiAction action;
	
	public Image getImage() 
	{
		return image;
	}
	
	public ApiAction getAction() 
	{
		return action;
	}
}
