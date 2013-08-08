package net.mayateck.OldEdenCore;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class EconomyHandler implements CommandExecutor {
	private OldEdenCore plugin;
	 
	public EconomyHandler(OldEdenCore plugin) {
		this.plugin = plugin;
	}
 
	public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
		if (cmd.getName().equalsIgnoreCase("money")){
			if (!(s instanceof Player)){
				s.sendMessage("The economy commands are player-executible only.");
			} else {
				if (args.length==0){
					String name = s.getName();
					String funds = HTTPGetData.getGeneralData("econ.php", "get=fetch&name="+name);
					s.sendMessage(funds);
					return true;
				} else {
					if (args[0]=="admin"){
						if (args.length==1){
							s.sendMessage(OldEdenCore.head+ChatColor.RED+"Invalid parameter number. Usage: /money admin [args]");
						} if (args[1]=="set"){
							if (args.length==2 || args.length==3 || args.length>=5){
								s.sendMessage(OldEdenCore.head+ChatColor.RED+"Invalid parameter number. Usage: /money admin set [player] [amount]");
							} 
						}
					}
				}
			}
		}
		return false;
	}
}
