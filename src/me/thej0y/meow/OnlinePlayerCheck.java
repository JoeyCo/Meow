package me.thej0y.meow;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnlinePlayerCheck implements Listener{

		public static Meow plugin;
		public OnlinePlayerCheck(Meow instance){
		plugin = instance;
		}
	
    @EventHandler
   public void onPlayerChat(AsyncPlayerChatEvent event){
    		String message = event.getMessage();
    		String[] mArgs = message.split("\\s");
    		String finalMessage = "";

    		for (int ran = 0; ran < mArgs.length; ran++){
    			Player player = Bukkit.getServer().getPlayerExact(mArgs[ran]);
    			// Change player to playerExact if you want exact names only.
    			if (player != null){
    				mArgs[ran] = (ChatColor.GREEN + "" + player.getName() + ChatColor.RESET);
    			}
    			finalMessage += (" " + mArgs[ran]);
    		
    		}
    		event.setMessage(finalMessage.substring(1));
	}
}
