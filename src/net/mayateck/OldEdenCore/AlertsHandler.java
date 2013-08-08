package net.mayateck.OldEdenCore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
@SuppressWarnings("unused")

public class AlertsHandler implements CommandExecutor {
	private OldEdenCore plugin;
	 
	public AlertsHandler(OldEdenCore plugin) {
		this.plugin = plugin;
	}
	
	static String alertSys = ChatColor.DARK_GRAY+"||"+ChatColor.YELLOW+"SYSTEM"+ChatColor.DARK_GRAY+"|| ";
	static String alertNote = ChatColor.DARK_GRAY+"||"+ChatColor.WHITE+"NOTICE"+ChatColor.DARK_GRAY+"|| ";
	static String alertError = ChatColor.DARK_GRAY+"||"+ChatColor.RED+"ERROR"+ChatColor.DARK_GRAY+"|| ";
	static String alertBCast = ChatColor.DARK_GRAY+"||"+ChatColor.GOLD+"SYSTEM"+ChatColor.DARK_GRAY+"|| ";
	
	public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
		if (cmd.getName().equalsIgnoreCase("alert")){
			if (args.length==0 || args.length==1){
				s.sendMessage(OldEdenCore.head+ChatColor.RED+"Invalid parameter number. Usage: /alert [type] [message]");
			} else {
				String message = "";
				boolean doAlert = false;
				for (int i=1; i>args.length; i++){
					message = message+" "+args[i];
				}
				if(args[0]=="system" || args[0]=="sys") {
					message = alertSys+message;
					doAlert = true;
				} else if (args[0]=="note" || args[0]=="notic"){
					message = alertNote+message;
					doAlert = true;
				} else if (args[0]=="error"){
					message = alertError+message;
					doAlert = true;
				} else if (args[0]=="broadcast"){
					message = alertBCast+message;
					doAlert = true;
				} else {
					s.sendMessage(OldEdenCore.head+ChatColor.RED+"Invalid alert type.");
				}
				if (doAlert==true){
					Bukkit.getServer().broadcastMessage(message);
				}
			}
		}
		return false;
	}
}

