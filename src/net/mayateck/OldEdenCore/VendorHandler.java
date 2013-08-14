package net.mayateck.OldEdenCore;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class VendorHandler implements Listener {
	private OldEdenCore plugin;
	public VendorHandler(OldEdenCore plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onVendorInteract(EntityDamageByEntityEvent evt){
		Entity damager = evt.getDamager();
		Entity vendor = evt.getEntity();
		if (damager instanceof Player && vendor.getType()==EntityType.VILLAGER){
			plugin.getLogger().info("[DEBUD] Fired EntityDamageByEntityEvent from Player->Villager");
			if (plugin.getConfig().contains("npcs.vendor_"+vendor.getEntityId())){
				Player plyr = (Player)damager;
				evt.setCancelled(true);
				plugin.getLogger().info("[DEBUG] Player holding "+plyr.getItemInHand().getTypeId());
				if (plyr.getItemInHand().getTypeId()!=Material.BEDROCK.getId()){
					String vsrc = "npcs.vendor_"+vendor.getEntityId();
					String name = plugin.getConfig().getString(vsrc+".name");
					String msg = plugin.getConfig().getString(vsrc+".msg");
					plyr.sendMessage(OldEdenCore.head+"[DEBUG] Interact works!");
				} else {
					plyr.sendMessage(OldEdenCore.head+"VENDOR_"+vendor.getEntityId()+".");
					plyr.sendMessage(OldEdenCore.head+"Please edit vendors manually.");
					plyr.sendMessage(OldEdenCore.head+"To kill, type "+ChatColor.RED+"/vendor kill"+ChatColor.RESET+". (CANNOT BE UNDONE!)");
				}
			}
		}
	}

}
