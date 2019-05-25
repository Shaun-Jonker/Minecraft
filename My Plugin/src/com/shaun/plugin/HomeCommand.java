package com.shaun.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class HomeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		  if (!(sender instanceof Player)) {
	            System.out.println(ChatColor.RED + "Only players can use the / home command!"
);
	            return true;
	        }
	        Player player = (Player) sender;
	        if (SetHomeCommand.indivCoord.get(player.getDisplayName()) == null) {
	            player.sendMessage(ChatColor.RED + "Unfortunately, you do not have a point at home."
);
	            return true;
	        }
	        player.teleport(SetHomeCommand.indivCoord.get(player.getDisplayName()));
	        player.sendMessage(ChatColor.DARK_GREEN + "You have successfully tp'd back home!");
	        return true;
	    }
	}