package net.mayateck.OldEdenCore;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EconomyHandler implements CommandExecutor {
	private OldEdenCore plugin;
	 
	public EconomyHandler(OldEdenCore plugin) {
		this.plugin = plugin;
	}
 
	public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
		String name = s.getName();
		if (cmd.getName().equalsIgnoreCase("money")){
			if (!(s instanceof Player)){
				s.sendMessage("The economy commands are player-executible only.");
			} else {
				if (args.length==0){
					int funds = plugin.getConfig().getInt("players."+name+".funds");
					s.sendMessage(OldEdenCore.serverHead+"You have "+ChatColor.BLUE+funds+" Bits"+ChatColor.RESET+".");
					return true;
				} else {
					if (args[0].equalsIgnoreCase("admin")){
						if (args.length==1){
							s.sendMessage(OldEdenCore.head+ChatColor.RED+"Invalid parameter number. Usage: /money admin [args]");
							return true;
						} if (args[1].equalsIgnoreCase("set")){
							if (args.length==4){
								if (plugin.getConfig().contains("players."+args[2]) && s.hasPermission("eden.eco.set")){
									int funds = Integer.parseInt(args[3]);
									plugin.getConfig().set("players."+args[2]+".funds", funds);
									plugin.saveConfig();
									s.sendMessage(OldEdenCore.head+"Success! "+ChatColor.BLUE+args[2]+ChatColor.RESET+" now has "+ChatColor.BLUE+funds+" Bits"+ChatColor.RESET+".");
									return true;
								} else {
									s.sendMessage(OldEdenCore.head+ChatColor.RED+"Player not found or invalid permissions.");
									return true;
								}
							} else {
								s.sendMessage(OldEdenCore.head+ChatColor.RED+"Invalid parameter number.");
								s.sendMessage(OldEdenCore.head+ChatColor.RED+"Usage: /money admin set [player] [amount]");
								return true;
							}
						} else {
							s.sendMessage(OldEdenCore.head+ChatColor.RED+"Unknown sub-command.");
							return true;
						}
					} else {
						s.sendMessage(OldEdenCore.head+ChatColor.RED+"Unknown sub-command.");
						return true;
					}
				}
			}
		}
		return false;
	}
}
