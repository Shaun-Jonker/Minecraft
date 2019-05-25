package com.shaun.plugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Hello implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		
		
		if(cmd.getName().equalsIgnoreCase("hello")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				player.sendMessage(ChatColor.DARK_RED + "Hello" + ChatColor.BLUE + player.getName());
								
			}else {
				System.out.println("You cant use this command through the console");
				
			}
		}
			
		return false;
	
	}

}
