package net.mayateck.OldEdenCore;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class OldEdenCore extends JavaPlugin implements Listener{
	public static String version = "0.00.01";
	public static boolean isConn = false;
	public static String head = ChatColor.DARK_GRAY+" || "+ChatColor.AQUA+"PLUGIN"+ChatColor.DARK_GRAY+" || ";
	public static String serverHead = ChatColor.DARK_GRAY+" || "+ChatColor.GRAY+"SERVER"+ChatColor.DARK_GRAY+" || ";
	Plugin plugin = this;
	
	@Override
	public void onEnable(){
		this.getLogger().info("#======# Old Eden Core v"+version+" #======#");
		this.getLogger().info("Initializing Command Data...");
			getCommand("money").setExecutor(new EconomyHandler(this));
			getCommand("alert").setExecutor(new AlertsHandler(this));
			
			new PlayerListener(this);
		this.getLogger().info("Requesting response from configuration...");
			this.saveDefaultConfig();
		this.getLogger().info("Initialization complete.");
		this.getLogger().info("#======================================#");
	}
	
	@Override
	public void onDisable(){
		this.getLogger().info("#======# Old Eden Core v"+version+" #======#");
		this.getLogger().info("Shutting down...");
		// TODO: Wrap-up.
		this.getLogger().info("Shut-down complete.");
		this.getLogger().info("#======================================#");
	}
	
	public boolean onCommand(CommandSender s, Command cmd, String l, String[] args){
		if (cmd.getName().equalsIgnoreCase("eden")){
			if (args[0].equalsIgnoreCase("reload") && s.hasPermission("eden.reload") && args.length==1){
				plugin.reloadConfig();
				s.sendMessage(head+"Data files successfully reloaded.");
				return true;
			} else {
				s.sendMessage(head+"The command was invalid or you lack sufficient permission.");
				s.sendMessage(head+"Try '/eden help'.");
				return true;
			}
		}
		return false;
	}
}
