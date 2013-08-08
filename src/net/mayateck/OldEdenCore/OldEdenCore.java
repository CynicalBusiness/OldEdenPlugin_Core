package net.mayateck.OldEdenCore;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.event.player.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class OldEdenCore extends JavaPlugin{
	public static String version = "0.00.01";
	public static boolean isConn = false;
	public static String head = ChatColor.DARK_GRAY+"||"+ChatColor.AQUA+"Core"+ChatColor.DARK_GRAY+"|| ";
	public Plugin basePlugin = this;
	
	@Override
	public void onEnable(){
		getLogger().info("#======# Old Eden Core v"+version+" #======#");
		getLogger().info("Initializing Command Data...");
			getCommand("money").setExecutor(new EconomyHandler(this));
			getCommand("alert").setExecutor(new AlertsHandler(this));
		getLogger().info("Requesting response from configuration...");
			this.saveDefaultConfig();
		getLogger().info("Initialization complete.");
		getLogger().info("#======================================#");
	}
	
	@Override
	public void onDisable(){
		getLogger().info("#======# Old Eden Core v"+version+" #======#");
		getLogger().info("Shutting down...");
		// TODO: Wrap-up.
		getLogger().info("Shut-down complete.");
		getLogger().info("#======================================#");
	}
	
	public boolean onCommand(CommandSender s, Command cmd, String l, String[] args){
		if (cmd.getName().equalsIgnoreCase("eden")){
			if (args[0].equalsIgnoreCase("reload") && s.hasPermission("eden.reload")){
			}
		}
		return false;
	}
	
	public void onPlayerJoin(PlayerJoinEvent evt){
		Player player = evt.getPlayer();
		String name = player.getName();
//		if (plyrs.contains(name)){
			player.sendMessage("Welcome back, "+name+".");
//		} else {
//			player.sendMessage("Welcome to Old Eden "+name+"!");
			// TODO: New Player handling.
//		}
	}
}
