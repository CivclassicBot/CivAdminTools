package com.civclassic.civadmintools.commands;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import vg.civcraft.mc.namelayer.NameAPI;

public class NameLayerGetGroups extends AdminCommand {
	
	public NameLayerGetGroups(Command parent) {
		super(parent);
		setArguments(1, 1);
	}

	public void exec(CommandSender sender, String[] args) {
		UUID id = getUUID(args[0]);
		String name = NameAPI.getCurrentName(id);
		Set<String> groups = new HashSet<String>(NameAPI.getGroupManager().getAllGroupNames(id));
		sender.sendMessage(ChatColor.GREEN + "Groups for " + name + " are: ");
		for(String group : groups) {
			sender.sendMessage("  " + group);
		}
	}
}
