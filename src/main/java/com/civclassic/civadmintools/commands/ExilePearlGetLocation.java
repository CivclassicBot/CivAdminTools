package com.civclassic.civadmintools.commands;

import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.devotedmc.ExilePearl.ExilePearl;
import com.devotedmc.ExilePearl.ExilePearlPlugin;

import net.md_5.bungee.api.ChatColor;

public class ExilePearlGetLocation extends AdminCommand {

	public ExilePearlGetLocation(Command parent) {
		super(parent);
		setArguments(1, 1);
	}

	public void exec(CommandSender sender, String[] args) {
		UUID id = getUUID(args[0]);
		ExilePearl pearl = ExilePearlPlugin.getApi().getPearl(id);
		if(pearl == null) {
			sender.sendMessage(ChatColor.RED + "Player isn't pearled");
		} else {
			sender.sendMessage(ChatColor.GREEN + pearl.getPlayerName() + " is " + pearl.getLocationDescription());
		}
	}
}
