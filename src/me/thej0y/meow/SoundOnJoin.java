package me.thej0y.meow;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SoundOnJoin implements Listener{

	public static Meow plugin;
	public SoundOnJoin(Meow instance){
	plugin = instance;
	} 
	
	@EventHandler
    public void PlayerJoining(PlayerJoinEvent event){

			for(Player p : plugin.getServer().getOnlinePlayers()){
		            p.playSound(p.getLocation(), plugin.JoinSound, 1, 0);
		    }
	}
}
