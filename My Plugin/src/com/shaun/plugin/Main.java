package com.shaun.plugin;


import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	public HashMap<Player, Player> tpa = new HashMap<Player, Player>();
	
	@Override
	public void onEnable() {

// 		This is to say the plugin is enabled.
		
		System.out.println("YEAH SURGE! THE PLUGIN IS WORKING!");
		
//		This gets the config file defaults and lets user interaction.
		
		this.getConfig().options().copyDefaults();
		saveDefaultConfig();
		
//		This is need to register events within the world.
		
		Bukkit.getPluginManager().registerEvents(this, this);
		

//		Commands Start here.
		
		getCommand("hello").setExecutor(new Hello());
		getCommand("heal").setExecutor(new HealCommand());
//		getCommand("tp").setExecutor(new Teleport());
//		getCommand("tpaccept").setExecutor(new Teleport());
//		getCommand("tpdeny").setExecutor(new Teleport());

//		getCommand("sethome").setExecutor(new Sethome());
//		getCommand("home").setExecutor(new HomeTp());
		
				
		}
		
	
	
	@Override
	public void onDisable() {
		
//		getCommand("stop").setExecutor(new Stop());

		
		System.out.println("YEAH SURGE! THE PLUGIN IS DISABLED!");
				
	}
	
	
//		Welcome message to players
	
	@EventHandler
	public void onJoin(PlayerJoinEvent j) {
		Player p = j.getPlayer();
		p.sendMessage(ChatColor.GOLD + "Welcome back, " + p.getName() + "!");
		
		}
		
	@EventHandler
	public void onMove(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		p.sendMessage(ChatColor.DARK_RED + "You died, Unlucky!");
		
	}
	
	
//	These are the setspawn and spawn commands.
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		 if (cmd.getName().equalsIgnoreCase("setspawn")) {
             this.getConfig().set("spawn.world", p.getLocation().getWorld().getName());
             this.getConfig().set("spawn.x", p.getLocation().getX());
             this.getConfig().set("spawn.y", p.getLocation().getY());
             this.getConfig().set("spawn.z", p.getLocation().getZ());
             this.getConfig().set("spawn.pitch", p.getLocation().getPitch());
             this.getConfig().set("spawn.yaw", p.getLocation().getYaw());

             saveConfig();
             p.sendMessage(ChatColor.GREEN + "Spawn set!");
             return true;
		
		 }
         
         if (cmd.getName().equalsIgnoreCase("spawn")) {
                 if (getConfig().getConfigurationSection("spawn") == null) {
                         p.sendMessage(ChatColor.RED + "The spawn has not yet been set!");
                         return true;
                 }
                 World w = Bukkit.getServer().getWorld(getConfig().getString("spawn.world"));
                 double x = getConfig().getDouble("spawn.x");
                 double y = getConfig().getDouble("spawn.y");
                 double z = getConfig().getDouble("spawn.z");
                 float pitch = (float) getConfig().getDouble("spawn.pitch");
                 float yaw = (float) getConfig().getDouble("spawn.yaw");
                 Location loc = new Location(w, x, y ,z);
                 loc.setYaw((float) yaw);
                 p.getLocation().setPitch((float) pitch);
                 p.teleport(loc);
                 p.sendMessage(ChatColor.GREEN + "Welcome to the spawn!");
         }
         
	
	if (label.equalsIgnoreCase("tp")){
		if(args.length == 1){
			Player target = p.getServer().getPlayer(args[0]);
			if(target != null){
				this.tpa.put(target, p);
				target.sendMessage(ChatColor.DARK_RED + p.getName() + " wants to tp to you, Type /tpaccept or /tpdeny)?");
				p.sendMessage(ChatColor.DARK_RED + "tp request sent to " + target.getName());
			}
		}
		
	}else if(label.equalsIgnoreCase("tpaccept")){
		if (this.tpa. get(p) != null){
			tpa.get(p).teleport(p);
			p.sendMessage(ChatColor.DARK_RED +"You successfully accepted the tp!");
			this.tpa.get(p).sendMessage(ChatColor.DARK_RED +"Your tp has been accepted!");
			this.tpa.put(p, null);
			
		}
	}else if(label.equalsIgnoreCase("tpdeny")){
		if(this.tpa.get(p) !=null) {
			p.sendMessage(ChatColor.DARK_RED +"You successfully denied the tp!");
			this.tpa.get(p).sendMessage(ChatColor.DARK_RED +"Your tp has been denied!");
			this.tpa.put(p, null);
		}
	}
	
	return true;
}
}


	

//	private HashMap<Player, Player> tpa = new HashMap<Player, Player>();
//
//	public HashMap<Player,Player> getTpa(){
//			return tpa;
		

