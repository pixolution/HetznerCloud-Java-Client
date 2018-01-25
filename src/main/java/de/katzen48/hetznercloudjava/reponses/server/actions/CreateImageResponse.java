package de.katzen48.hetznercloudjava.reponses.server.actions;

import de.katzen48.hetznercloudjava.resources.Image;
import de.katzen48.hetznercloudjava.resources.ServerAction;

public class CreateImageResponse 
{
	private Image image;
	private ServerAction action;
	
	public Image getImage() 
	{
		return image;
	}
	
	public ServerAction getAction() 
	{
		return action;
	}
}
