package net.mayateck.OldEdenCore;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class OldEdenCore extends JavaPlugin{
	public static String version = "0.00.01";
	public static boolean isConn = false;
	
	@Override
	public void onEnable(){
		getLogger().info("#======# Old Eden Core v"+version+" #======#");
		getLogger().info("Initializing Command Data...");
			getCommand("money").setExecutor(new EconomyHandler(this));
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
}
