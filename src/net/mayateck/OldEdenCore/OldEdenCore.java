package net.mayateck.OldEdenCore;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class OldEdenCore extends JavaPlugin implements Listener{
	public static String version = "0.02.05";
	public static boolean isConn = false;
	public static String head = ChatColor.DARK_GRAY+" || "+ChatColor.AQUA+"PLUGIN"+ChatColor.DARK_GRAY+" || "+ChatColor.RESET;
	public static String serverHead = ChatColor.DARK_GRAY+" || "+ChatColor.GRAY+"SERVER"+ChatColor.DARK_GRAY+" || "+ChatColor.RESET;
	Plugin plugin = this;
	
	@Override
	public void onEnable(){
		this.getLogger().info("#======# Old Eden Core v"+version+" #======#");
		this.getLogger().info("Initializing Core Data...");
			getCommand("money").setExecutor(new EconomyHandler(this));
			getCommand("alert").setExecutor(new AlertsHandler(this));
			getCommand("land").setExecutor(new RegionCommandExecutor(this));
			new PlayerListener(this);
			new MessageBases(this);
			new VendorHandler(this);
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
			if (args.length==0){
				s.sendMessage(head+"#======#"+ChatColor.BLUE+" Old Eden Core v"+version+ChatColor.RESET+" #======#");
				s.sendMessage(head+"Loooking for help? Try '/eden help'.");
				return true;
			} else if (args[0].equalsIgnoreCase("help") && s.hasPermission("eden.help") && args.length==1){
				s.sendMessage(head+"Help file is missing. Sorry about that.");
				return true;
			} else if (args[0].equalsIgnoreCase("reload") && s.hasPermission("eden.reload") && args.length==1){
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
