package com.massivecraft.factions.cmd;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.Perm;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.primitive.TypeBoolean;
import com.massivecraft.massivecore.util.IdUtil;
import com.massivecraft.massivecore.util.Txt;

public class CmdFactionsAdmin extends FactionsCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdFactionsAdmin()
	{
		// Aliases
		this.addAliases("admin");

		// Parameters
		this.addParameter(TypeBoolean.getOn(), "on/off", "flip");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.ADMIN.node));
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		boolean target = this.readArg(!msender.isUsingAdminMode());
		
		// Apply
		msender.setUsingAdminMode(target);		
		
		// Inform
		String desc = Txt.parse(msender.isUsingAdminMode() ? "<g>ENABLED" : "<b>DISABLED");
		
		String messageYou = Txt.parse("<i>%s %s <i>admin bypass mode.", msender.getDisplayName(msender), desc);
		String messageLog = Txt.parse("<i>%s %s <i>admin bypass mode.", msender.getDisplayName(IdUtil.getConsole()), desc);
		
		msender.message(messageYou);
		Factions.get().log(messageLog);
	}
	
}
