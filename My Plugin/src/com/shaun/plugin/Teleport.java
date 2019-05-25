package com.shaun.plugin;


import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import net.md_5.bungee.api.ChatColor;

public class Teleport implements CommandExecutor, Listener {
	
	HashMap<Player, Player> tpa = new HashMap<Player, Player>();

	
	@Override
	public boolean onCommand (CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player source = (Player) sender;
		
		if (commandLabel.equalsIgnoreCase("tp")){
			if(args.length == 1){
				Player target = source.getServer().getPlayer(args[0]);
				if(target != null){
					this.tpa.put(target, source);
					target.sendMessage(ChatColor.DARK_RED + source.getName() + " wants to tp to you, Type /tpaccept or /tpdeny)?");
					source.sendMessage(ChatColor.DARK_RED + "tp request sent to " + target.getName());
				}
			}
			
		}else if(commandLabel.equalsIgnoreCase("tpaccept")){
			if (this.tpa. get(source) != null){
				tpa.get(source).teleport(source);
				source.sendMessage(ChatColor.DARK_RED +"You successfully accepted the tp!");
				this.tpa.get(source).sendMessage(ChatColor.DARK_RED +"Your tp has been accepted!");
				this.tpa.put(source, null);
				
			}
		}else if(commandLabel.equalsIgnoreCase("tpdeny")){
			if(this.tpa.get(source) !=null) {
				source.sendMessage(ChatColor.DARK_RED +"You successfully denied the tp!");
				this.tpa.get(source).sendMessage(ChatColor.DARK_RED +"Your tp has been denied!");
				this.tpa.put(source, null);
			}
		}
		
		return true;
	}
}