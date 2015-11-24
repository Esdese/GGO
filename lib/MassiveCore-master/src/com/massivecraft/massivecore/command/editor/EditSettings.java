package com.massivecraft.massivecore.command.editor;

import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.type.Type;
import com.massivecraft.massivecore.command.type.sender.TypeSender;
import com.massivecraft.massivecore.util.Txt;

public class EditSettings<O>
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// This is the type of the object we are editing.
	protected final Type<O> objectType;
	public Type<O> getObjectType() { return this.objectType; }
	
	// This property is used to get the object we are editing from the sender.
	protected Property<CommandSender, O> usedProperty;
	public Property<CommandSender, O> getUsedProperty() { return this.usedProperty; }
	public void setUsedProperty(Property<CommandSender, O> usedProperty) { this.usedProperty = usedProperty; }
	
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	public EditSettings(Type<O> objectType, Property<CommandSender, O> objectProperty)
	{
		this.objectType = objectType;
		this.usedProperty = objectProperty;
	}
	
	public EditSettings(Type<O> objectType)
	{
		this(objectType, null);
	}
	
	public EditSettings<CommandSender> createUsedSettings()
	{
		final EditSettings<O> main = this;
		return new EditSettings<CommandSender>(TypeSender.get(), new PropertyThis<CommandSender>(TypeSender.get())) {
			
			@Override
			public Permission getPropertyPermission(Property<CommandSender,?> property)
			{
				return main.getUsedPermission();
			};
			
		};
	}
	
	// -------------------------------------------- //
	// OBJECT
	// -------------------------------------------- //
	
	public O getUsed(CommandSender sender)
	{
		return this.getUsedProperty().getValue(sender);
	}
	
	public void setUsed(CommandSender sender, O used)
	{
		this.getUsedProperty().setValue(sender, used);
	}
	
	// -------------------------------------------- //
	// USED COMMAND
	// -------------------------------------------- //
	
	public CommandEditUsed<O> createCommandEditUsed()
	{
		return new CommandEditUsed<O>(this);
	}
	
	// -------------------------------------------- //
	// PERMISSONS
	// -------------------------------------------- //
	
	public Permission getPropertyPermission(Property<O, ?> property)
	{
		return null;
	}
	
	public Permission getUsedPermission()
	{
		return null;
	}
	
	// -------------------------------------------- //
	// TYPE READ UTILITY
	// -------------------------------------------- //
	
	public O getUsedOrCommandException(CommandSender sender) throws MassiveException
	{
		O ret = this.getUsed(sender);
		if (ret != null) return ret;
		String noun = this.getObjectType().getTypeName();
		String aan = Txt.aan(noun);
		throw new MassiveException().addMsg("<b>You must select %s %s for use to skip the optional argument.", aan, noun);
	}
	
}
