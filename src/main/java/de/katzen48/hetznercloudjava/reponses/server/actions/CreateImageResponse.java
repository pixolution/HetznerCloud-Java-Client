package de.katzen48.hetznercloudjava.reponses.server.actions;

import de.katzen48.hetznercloudjava.actions.ServerAction;
import de.katzen48.hetznercloudjava.resources.Image;

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
