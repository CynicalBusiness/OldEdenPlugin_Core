package net.mayateck.OldEdenCore;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener{
	private OldEdenCore plugin;
	public PlayerListener(OldEdenCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent evt){
		Player player = evt.getPlayer();
		String pname = player.getName();
		plugin.getLogger().info("Player "+pname+" connected.");
		if (plugin.getConfig().contains("players."+pname)){
			player.sendMessage(OldEdenCore.serverHead+"Welcome back, "+pname+".");
			plugin.getLogger().info("PlayerData found.");
		} else {
			player.sendMessage(OldEdenCore.serverHead+"Welcome to Old Eden!");
			plugin.getLogger().info("PlayerData not found. Creating...");
			player.sendMessage(OldEdenCore.serverHead+"Please wait while first-time setup is run.");
			plugin.getConfig().createSection("players."+pname);
			plugin.getConfig().createSection("players."+pname+".regionID");
				plugin.getConfig().set("players"+pname+".regionID", 0);
			plugin.getConfig().createSection("players."+pname+".funds");
				plugin.getConfig().set("players"+pname+".funds", 0);
			plugin.saveConfig();
			player.sendMessage(OldEdenCore.serverHead+"Complete! Welcome "+pname+"!");
		}
	}
}
