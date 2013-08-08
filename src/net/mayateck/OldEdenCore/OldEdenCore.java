package net.mayateck.OldEdenCore;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;

public class OldEdenCore extends JavaPlugin{
	public static String version = "0.00.01";
	public static boolean isConn = false;
	public static String head = ChatColor.DARK_GRAY+"||"+ChatColor.AQUA+"Core"+ChatColor.DARK_GRAY+"|| ";
	
	@Override
	public void onEnable(){
		getLogger().info("#======# Old Eden Core v"+version+" #======#");
		getLogger().info("Initializing Command Data...");
			getCommand("money").setExecutor(new EconomyHandler(this));
			getCommand("alert").setExecutor(new AlertsHandler(this));
		getLogger().info("Requesting response from database...");
			String r = HTTPGetData.getGeneralData("general.php", "get=ping");
			if (r=="true"){
				isConn = true;
				getLogger().info("Success! Database synced.");
			} else if(r=="false"){
				isConn = false;
				getLogger().info("Database could not be synced. (MySQL issue)");
			} else {
				isConn = false;
				getLogger().info("Database could not be synced. (HttpGet issue)");
			}
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
		
		return false;
	}
	
	public void onPlayerJoin(PlayerJoinEvent evt){
		Player player = evt.getPlayer();
		String name = player.getName();
		String r = HTTPGetData.getGeneralData("player.php", "get=setup&name="+name);
		if (r=="true"){
			player.sendMessage("Welcome back, "+name+".");
		} else if(r=="false"){
			player.sendMessage("Welcome to Old Eden "+name+"!");
			// TODO: New Player handling.
		} else {
			player.sendMessage("Error while syncing. Please report this on github with the current time.");
		}
	}
}
