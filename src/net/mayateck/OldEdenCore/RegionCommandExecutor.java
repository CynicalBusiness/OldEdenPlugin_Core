package net.mayateck.OldEdenCore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegionCommandExecutor implements CommandExecutor{
	private OldEdenCore plugin;
	public RegionCommandExecutor(OldEdenCore plugin) {
		this.plugin = plugin;
	}
	
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("land")){
			if (args.length==3){
				if (args[0].equalsIgnoreCase("create") && s.hasPermission("eden.land.create")){
					String regionName = args[1];
					if (!(plugin.getConfig().contains("regions."+regionName))){
						String fullname = "";
						for (int i=1; i<=args.length-1; i++){
							fullname = fullname+" "+args[i];
						}
						plugin.getConfig().set("regions."+regionName+".fullname", "'"+fullname+"'");
						plugin.getConfig().createSection("regions."+regionName+".one");
						plugin.getConfig().createSection("regions."+regionName+".two");
						plugin.getConfig().createSection("regions."+regionName+".home");
						plugin.saveConfig();
						s.sendMessage(OldEdenCore.head+"Created "+ChatColor.BLUE+regionName+ChatColor.RESET+" successfully.");
						return true;
					} else {
						s.sendMessage(OldEdenCore.head+"Region already exists!");
						return true;
					}
				} else {
					s.sendMessage(OldEdenCore.head+ChatColor.RED+"Invalid parameter number.");
					return true;
				}
			} else if(args.length==2){
				if (args[0].equalsIgnoreCase("sethome") && s.hasPermission("eden.land.sethome")){
					String regionName = args[1];
					if (plugin.getConfig().contains("regions."+regionName)){
						Player plyr = (Bukkit.getServer().getPlayer(s.getName()));
						Location loc = plyr.getLocation();
						plugin.getConfig().set("regions."+regionName+".home.world", loc.getWorld());
						plugin.getConfig().set("regions."+regionName+".home.pitch", loc.getPitch());
						plugin.getConfig().set("regions."+regionName+".home.yaw", loc.getYaw());
						plugin.getConfig().set("regions."+regionName+".home.x", loc.getX());
						plugin.getConfig().set("regions."+regionName+".home.y", loc.getY());
						plugin.getConfig().set("regions."+regionName+".home.z", loc.getZ());
						plugin.saveConfig();
						s.sendMessage(OldEdenCore.head+"Set home for "+ChatColor.BLUE+regionName+ChatColor.RESET+" successfully.");
						return true;
					} else {
						s.sendMessage(OldEdenCore.head+"Couldn't find that region!");
						return true;
					}
				} else if (args[0].equalsIgnoreCase("set1") && s.hasPermission("eden.land.set")){
					String regionName = args[1];
					if (plugin.getConfig().contains("regions."+regionName)){
						Player plyr = (Bukkit.getServer().getPlayer(s.getName()));
						Location loc = plyr.getLocation();
						plugin.getConfig().set("regions."+regionName+".one.world", loc.getWorld());
						plugin.getConfig().set("regions."+regionName+".one.x", loc.getX());
						plugin.getConfig().set("regions."+regionName+".one.z", loc.getZ());
						plugin.saveConfig();
						s.sendMessage(OldEdenCore.head+"Set pos1 for "+ChatColor.BLUE+regionName+ChatColor.RESET+" successfully.");
						return true;
					} else {
						s.sendMessage(OldEdenCore.head+"Couldn't find that region!");
						return true;
					}
				} else if (args[0].equalsIgnoreCase("set2") && s.hasPermission("eden.land.set")){
					String regionName = args[1];
					if (plugin.getConfig().contains("regions."+regionName)){
						Player plyr = (Bukkit.getServer().getPlayer(s.getName()));
						Location loc = plyr.getLocation();
						plugin.getConfig().set("regions."+regionName+".two.world", loc.getWorld());
						plugin.getConfig().set("regions."+regionName+".two.x", loc.getX());
						plugin.getConfig().set("regions."+regionName+".two.z", loc.getZ());
						plugin.saveConfig();
						s.sendMessage(OldEdenCore.head+"Set pos2 for "+ChatColor.BLUE+regionName+ChatColor.RESET+" successfully.");
						return true;
					} else {
						s.sendMessage(OldEdenCore.head+"Couldn't find that region!");
						return true;
					}
				} else if (args[0].equalsIgnoreCase("hop") && s.hasPermission("eden.land.hop")){
					String regionName = args[1];
					if (plugin.getConfig().contains("regions."+regionName)){
						Player plyr = (Bukkit.getServer().getPlayer(s.getName()));
						double locx = plugin.getConfig().getDouble("regions."+regionName+".home.x");
						double locy = plugin.getConfig().getDouble("regions."+regionName+".home.y");
						double locz = plugin.getConfig().getDouble("regions."+regionName+".home.z");
						float yaw = plugin.getConfig().getInt("regions."+regionName+".home.yaw");
						float pitch = plugin.getConfig().getInt("regions."+regionName+".home.pitch");
						Location newLoc = new Location(plyr.getLocation().getWorld(), locx, locy, locz, yaw, pitch);
						plyr.teleport(newLoc);
						s.sendMessage(OldEdenCore.head+"Jumped to "+ChatColor.BLUE+regionName+ChatColor.RESET+".");
						return true;
					} else {
						s.sendMessage(OldEdenCore.head+"Couldn't find "+ChatColor.RED+regionName+ChatColor.RESET+"!");
						return true;
					}
				}
			} else {
				s.sendMessage(OldEdenCore.head+ChatColor.RED+"Invalid parameter number.");
				return true;
			}
		}
		return false;
	}

}
