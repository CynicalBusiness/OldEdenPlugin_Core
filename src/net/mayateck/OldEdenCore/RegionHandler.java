package net.mayateck.OldEdenCore;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

@SuppressWarnings("unused")
public class RegionHandler implements Listener{
	private OldEdenCore plugin;
	public RegionHandler(OldEdenCore plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent evt){
		Player plyr = evt.getPlayer();
		Location loc = plyr.getLocation();
		double locX = loc.getX();
		double locZ = loc.getZ();
		
	}
}
