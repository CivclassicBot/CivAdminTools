package com.civclassic.civadmintools.commands;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import vg.civcraft.mc.namelayer.GroupManager;
import vg.civcraft.mc.namelayer.NameAPI;

public class NameLayerSharedGroups extends AdminCommand {

	public NameLayerSharedGroups(Command parent) {
		super(parent);
		setArguments(2, 2);
	}
	
	public void exec(CommandSender sender, String[] args) {
		UUID one = getUUID(args[0]);
		UUID two = getUUID(args[1]);
		if(one == two) {
			sender.sendMessage(ChatColor.RED + "Can't compare the groups of the same player dumbass");
		} else {
			GroupManager gMan = NameAPI.getGroupManager();
			Set<String> groups = new HashSet<String>(gMan.getAllGroupNames(one));
			groups.retainAll(gMan.getAllGroupNames(two));
			sender.sendMessage(ChatColor.GREEN + NameAPI.getCurrentName(one) + " and " + NameAPI.getCurrentName(two) + " share the following groups: ");
			for(String group : groups) {
				sender.sendMessage("  " + group);
			}
		}
	}
}
