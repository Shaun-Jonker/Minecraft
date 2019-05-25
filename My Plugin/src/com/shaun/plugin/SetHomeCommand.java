package com.shaun.plugin;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

import java.util.HashMap;
import java.util.Map;

public class SetHomeCommand implements CommandExecutor {

	static Map<String, Location> indivCoord = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("Only a player can use the / sethome command!"
);
            return true;
        }
        Player player = (Player) sender;
        indivCoord.put(player.getDisplayName(),player.getLocation());
        Location coord = indivCoord.get(player.getDisplayName());
        player.sendMessage(ChatColor.GREEN + "Your home tp has been saved:" + coord.getBlockX() + " " + coord.getBlockY() + " " + coord.getBlockZ());

        return true;
    }
}