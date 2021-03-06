package com.massivecraft.massivecore.command.editor;

import java.util.Collection;

import com.massivecraft.massivecore.MassiveException;

public class CommandEditShow<O, V extends Collection<?>> extends CommandEditAbstract<O, V>
{	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CommandEditShow(EditSettings<O> settings, Property<O, V> property)
	{
		// Super	
		super(settings, property, false);
		
		// Aliases
		String alias = this.createCommandAlias();
		this.setAliases(alias);
		
		// Desc
		this.setDesc(alias + " " + this.getPropertyName());
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		this.show(sender);
	}
	
}
