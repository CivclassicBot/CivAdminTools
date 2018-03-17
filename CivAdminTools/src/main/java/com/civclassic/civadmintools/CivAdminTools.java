package com.civclassic.civadmintools;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import vg.civcraft.mc.civmodcore.ACivMod;

public class CivAdminTools extends ACivMod {
	
	private CommandHandler cHandler;
	
	public void onEnable() {
		cHandler = new CommandHandler(this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		return cHandler.execute(sender, cmd, args);
	}
	
	public String getPluginName() {
		return "CivAdminTools";
	}
	
	@EventHandler(ignoreCancelled = true)
    public void onInventoryClickEvent(InventoryClickEvent event) {
        if (event.getAction() == InventoryAction.SWAP_WITH_CURSOR){
            ItemStack slotItems = event.getCurrentItem();
            ItemStack handItems = event.getCursor();
            if (slotItems.getType() == Material.GOLD_PICKAXE && handItems.getType() == Material.GOLD_PICKAXE){
                event.setCancelled(true); //let's do this thing
                int totalDura = 33-slotItems.getDurability() + 33-handItems.getDurability();
                if (totalDura > 33){ totalDura = 33; }
                handItems.setAmount(0);
                slotItems.setDurability((short)(33-totalDura));
            }
        }
    }
}