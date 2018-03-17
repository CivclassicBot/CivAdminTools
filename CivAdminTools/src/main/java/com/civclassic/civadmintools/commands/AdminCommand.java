package com.civclassic.civadmintools.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import vg.civcraft.mc.civmodcore.command.PlayerCommand;
import vg.civcraft.mc.namelayer.NameAPI;

public abstract class AdminCommand extends PlayerCommand	 {

	public AdminCommand(Command parent) {
		super(parent.getName());
		setIdentifier(parent.getName());
		setDescription(parent.getDescription());
		setUsage(parent.getUsage());
		setArguments(0, 0);
	}

	public abstract void exec(CommandSender sender, String[] args);
	
	public boolean execute(CommandSender sender, String[] args) {
		exec(sender, args);
		return true;
	}
	
	public List<String> tabComplete(CommandSender arg0, String[] arg1) {
		return new ArrayList<String>();
	}
	
	public UUID getUUID(String arg) {
		UUID id;
		try {
			id = UUID.fromString(arg);
		} catch (IllegalArgumentException ex) {
			id = NameAPI.getUUID(arg);
		}
		return id;
	}
}
