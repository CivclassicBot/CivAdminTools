package com.civclassic.civadmintools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.civclassic.civadmintools.commands.NameLayerGetGroups;
import com.civclassic.civadmintools.commands.NameLayerSharedGroups;
import com.civclassic.civadmintools.commands.ExilePearlGetLocation;

import vg.civcraft.mc.civmodcore.command.Command;

public class CommandHandler {

	public Map<String, Command> commands = new HashMap<String, Command>();

	public CommandHandler(CivAdminTools plugin) {
		addCommand(new NameLayerGetGroups(plugin.getCommand("nlgg")));
		addCommand(new ExilePearlGetLocation(plugin.getCommand("epla")));
		addCommand(new NameLayerSharedGroups(plugin.getCommand("nlsg")));
	}
	
	private void addCommand(Command command) {
		commands.put(command.getIdentifier().toLowerCase(), command);
	}
	
	public boolean execute(CommandSender sender, org.bukkit.command.Command cmd, String[] args) {
		if(commands.containsKey(cmd.getName().toLowerCase())) {
			Command command = commands.get(cmd.getName().toLowerCase());
			if(args.length < command.getMinArguments() || args.length > command.getMaxArguments()) {
				helpPlayer(command, sender);
				return true;
			}
			
			command.execute(sender, args);
		}
		return true;
	}
	
	public List<String> complete(CommandSender sender, org.bukkit.command.Command cmd, String[] args){
		if (commands.containsKey(cmd.getName().toLowerCase())){
			Command command = commands.get(cmd.getName().toLowerCase());
			return command.tabComplete(sender, args);
		}
		return null;
	}
	
	public void helpPlayer(Command command, CommandSender sender){
		sender.sendMessage(new StringBuilder().append(ChatColor.RED + "Command: " ).append(command.getName()).toString());
		sender.sendMessage(new StringBuilder().append(ChatColor.RED + "Description: " ).append(command.getDescription()).toString());
		sender.sendMessage(new StringBuilder().append(ChatColor.RED + "Usage: ").append(command.getUsage()).toString());
	}
}
