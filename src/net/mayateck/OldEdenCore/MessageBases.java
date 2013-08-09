package net.mayateck.OldEdenCore;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

@SuppressWarnings("unused")
public class MessageBases implements Listener{
	private OldEdenCore plugin;
	public MessageBases(OldEdenCore plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPreCommand(PlayerCommandPreprocessEvent evt){
		String msg = evt.getMessage();
		Player plyr = evt.getPlayer();
		plugin.getLogger().info("Command executed. Searching for pre-process.");
		if (msg.contains("/say") && plyr.hasPermission("eden.alert.sys")){
			evt.setCancelled(true);
			msg = msg.replaceFirst("/say", "");
			char amp = '&';
			msg = ChatColor.translateAlternateColorCodes(amp, msg);
			Bukkit.getServer().broadcastMessage(AlertsHandler.alertSys+msg);
		}
	}
	 
}
