package me.thej0y.meow;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Meow extends JavaPlugin{

	 String prefix = "[Meow] ";
	
	 private File BlockerFile = null;
	 private FileConfiguration BlockerFileConfig = null;
	 OnlinePlayerCheck OpC = new OnlinePlayerCheck(this);
	 PluginConf pConf = new PluginConf(this);
	 SoundOnJoin SoJ = new SoundOnJoin(this);
	 //ConfigFixer ConfFix = new ConfigFixer(this);//BROKEN
	 
     private HashMap<String, Long> cooldown = new HashMap<String,Long>();
     public Integer cooldownInSeconds = 30;
 	 
 	 public Sound MeowSound = Sound.CAT_MEOW;
 	 public Sound JoinSound = Sound.CHICKEN_EGG_POP;
     public Sound Meow = Sound.CAT_MEOW;
 	 public Sound EggPop = Sound.CHICKEN_EGG_POP;
 	 public Sound Teleport = Sound.ENDERMAN_TELEPORT;
 	 public Sound Explode = Sound.EXPLODE;
 	 public Sound Plong = Sound.NOTE_PIANO;
 	 
	 public String MoewdBy;
	 public String YouMeowd;
	 public String PnotOnline;
	 public String SelfMeow;
	 public String SoundEnabled;
	 public String SoundDisabled;
	 public String SecondToUse;
	 public String PleaseWait;
	 public String AsLoaded;
	 public String Asunloaded;
	 public String HelpToSee;
	 public String PlayerNotFound;
	 public String NoPerm;
	 public String CantFindMeow;
	 public String EverbodyMoewd;
	 public String YouMeowEvery;
	 public String ConfReloaded;
	 public String ConfReload;
	 public String SendEverybody;
	 public String SoundToggle;
	 public String SendaMeow;
	 public String ReplyToMeow;
	 public String PluginBy;
	 public String GotMeowdBack;
	 public String MeowdBack;
	 public String MBDisabled;
	 public String PlayerListFailed;
	 public String JoinDisabled;
	 public String MSDisabled;
	 public String MTuse;
	 public String SendaTell;
	 List<String> configPlayers;
	 
     HashMap<String, String> MeowBack = new HashMap<String, String>();
 	 
     protected Logger log;

 public void onDisable(){
     System.out.println(prefix + "Disabled");
 }

 	public void onEnable(){
 	    try{
 	    	pConf.loadConfiguration();
 	    	//ConfFix.ConfigCheck();//BROKEN
 			}catch (Exception e){
 				System.out.println("[Meow] Something failed to load");
 				e.printStackTrace();
 			}
     System.out.println(prefix + "Enabled!");
 }

	@SuppressWarnings("unchecked")
	@Override
 public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

		if(command.getName().equalsIgnoreCase("meow") || command.getName().equalsIgnoreCase("m")){
			if(sender.hasPermission("Meow.meow")){
			   	if (args.length == 0){
			        sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.LIGHT_PURPLE + HelpToSee);
				   		}else{
				   			Player player = Bukkit.getPlayerExact(args[0]);
		                    if(player == null){
				   				sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.RED + PlayerNotFound);
				   				return false;
				   			}else{
		                        Player p = getServer().getPlayer(args[0]);	 
		                        String playerName = p.getName();	 

		                        if(sender.hasPermission("Meow.nocool")){
		                            if (!configPlayers.contains(playerName)){
		                                p.playSound(p.getLocation(), MeowSound, 1, 0);
		                            }
		                            p.sendMessage(ChatColor.GOLD + prefix + ChatColor.LIGHT_PURPLE + MoewdBy + sender.getName());
		                            sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.LIGHT_PURPLE + YouMeowd + p.getName());
		                            String AlMeowBacker = MeowBack.get(sender.getName());
		                            if(AlMeowBacker == null){
		                                MeowBack.put(player.getName(), sender.getName());
		                            }else{
		                                MeowBack.remove(sender.getName());
		                                MeowBack.put(player.getName(), sender.getName());
		                            }
		                        }else{
		                            if(cooldown.containsKey(sender.getName())){
		                                Long lastUse = cooldown.get(sender.getName());
		                                Long diff = System.currentTimeMillis() - lastUse;
		                                if(diff <= cooldownInSeconds * 1000){
			                                sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.DARK_RED + PleaseWait + Long.toString(cooldownInSeconds - (diff / 1000), 0)  + " " + SecondToUse);
			                                return false;
		                                }
		                            }
		                            boolean self = false;
		                            if(args.length != 1){
		                                self = true;
		                            }else if (!sender.getName().equalsIgnoreCase(args[0])){
		                                if(p != null && p.isOnline()){
		                                    if (!configPlayers.contains(playerName)){
				   							p.playSound(p.getLocation(), MeowSound, 1, 0);
		                                    }
		                                    p.sendMessage(ChatColor.GOLD + prefix + ChatColor.LIGHT_PURPLE + MoewdBy + sender.getName());
		                                    cooldown.put(sender.getName(), System.currentTimeMillis());
		                                    sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.LIGHT_PURPLE + YouMeowd + p.getName());
		                                    String AlMeowBacker = MeowBack.get(sender.getName());
		                                    if(AlMeowBacker == null){
		                                        MeowBack.put(player.getName(), sender.getName());
		                                    }else{
		                                        MeowBack.remove(sender.getName());
		                                        MeowBack.put(player.getName(), sender.getName());
		                                    }
		                                }else{
				   						sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.RED + PnotOnline);
		                                }
		                            }else{
		                                self = true;
		                                if(self){
		                                    if (!configPlayers.contains(playerName)){
				   							p.playSound(p.getLocation(), MeowSound, 1, 0);
		                                    }
				   						sender.sendMessage(ChatColor.RED + SelfMeow);
		                                }
		                            }
		                        }
		                    }
		                }
		      }else{
		    	  sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.DARK_RED + NoPerm);
		      }
	 }else if(command.getName().equalsIgnoreCase("meowstop") || command.getName().equalsIgnoreCase("ms")){	 
		 BlockerFile = new File(getDataFolder(), "blockers.yml");
		 BlockerFileConfig = YamlConfiguration.loadConfiguration(BlockerFile);
		 Player player = (Player)sender;
         String playerName = player.getName();
         if (configPlayers.contains(playerName)){
         	((List<String>) BlockerFileConfig.getList("Blockers")).remove(player.getName());
         	try {
				BlockerFileConfig.save(BlockerFile);
			} catch (IOException e) {
				System.out.println("[Meow] Failed to save blocker.yml after removing a player");
			}
         	configPlayers.remove(playerName);
       	sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.RED + SoundEnabled);	     	 
     }else{	 
        	 ((List<String>) BlockerFileConfig.getList("Blockers")).add(player.getName());	  
        	 configPlayers.add(playerName);
        	 try {
 				BlockerFileConfig.save(BlockerFile);
 			} catch (IOException e) {
 				System.out.println("[Meow] Failed to save blocker.yml after adding a player");
 				e.printStackTrace();
 			}
        	 sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.RED + SoundDisabled);
         }
     }else if(command.getName().equalsIgnoreCase("rmeow") || command.getName().equalsIgnoreCase("rm")){
    	 if(sender.hasPermission("Meow.meow")){
    		 Long diff = null;
    		 Long lastUse = null;
    			 if(getConfig().getBoolean("rMeowCooldown") == true){
    				 if(cooldown.containsKey(sender.getName())){
    			         lastUse = cooldown.get(sender.getName());
    			         diff = System.currentTimeMillis() - lastUse;
    				 }
    			 }
    				 if(getConfig().getBoolean("rMeowCooldown") == false || diff == null || diff >= cooldownInSeconds * 1000){
    					    Player MewBacker = (Player)sender;
    				 		String targetName = MeowBack.get(MewBacker.getName());
    				 		if(targetName != null){
    						    Player target = Bukkit.getPlayerExact(targetName);
    						    if(target != null){
    						    	if(!configPlayers.contains(target)){
    						    		target.playSound(target.getLocation(), MeowSound, 1, 0);
    						    	}
    						    	target.sendMessage(ChatColor.GOLD + prefix + ChatColor.LIGHT_PURPLE + GotMeowdBack + sender.getName());
    				                sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.LIGHT_PURPLE + MeowdBack + target.getName());
    				                if(getConfig().getBoolean("rMeowCooldown") == true && !sender.hasPermission("Meow.nocool") && !sender.isOp()){
    				                	cooldown.put(sender.getName(), System.currentTimeMillis());
    				                }
    				                String AlMeowBacker = MeowBack.get(sender.getName());
    				                if(AlMeowBacker == null){
    				                MeowBack.put(target.getName(), sender.getName());
    				                }else{
    				                MeowBack.remove(sender.getName());
    				                MeowBack.put(target.getName(), sender.getName());
    				                
    				                }		    	
    						    }else{
    						    	 sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.RED + PnotOnline);
    						    	 MeowBack.remove(sender.getName());
    						    }
    				 		}else{
    					    	 sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.RED + CantFindMeow);
    					    }
    				 }else{
    					 sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.DARK_RED + PleaseWait + Long.toString(cooldownInSeconds - (diff / 1000), 0)  + " " + SecondToUse);
    				 }		
    		}else{
    			 sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.DARK_RED + NoPerm);	
    		}
     }else if(command.getName().equalsIgnoreCase("meowhelp") || command.getName().equalsIgnoreCase("mh")){
    	 if(sender.isOp() || sender.hasPermission("Meow.meow")){
    		 sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.BLUE + PluginBy + ChatColor.AQUA + " TheJ0y" + ChatColor.GOLD + " V:" + getDescription().getVersion());
    		 sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.LIGHT_PURPLE + "[/m] /meow [Player] " + ChatColor.DARK_PURPLE + SendaMeow);
    		 sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.LIGHT_PURPLE + "[/mt] /meowtell [Player] <msg>" + ChatColor.DARK_PURPLE + SendaTell);
    		 sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.LIGHT_PURPLE + "[/rm] /rmeow " + ChatColor.DARK_PURPLE + ReplyToMeow);
    		 sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.LIGHT_PURPLE + "[/ms] /meowstop " + ChatColor.DARK_PURPLE + SoundToggle) ;
    		 if(sender.hasPermission("Meow.server") || sender.isOp()){
    			 sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.LIGHT_PURPLE + "[/ma] /meowall " + ChatColor.DARK_PURPLE + SendEverybody);
    		 }
    		 if(sender.hasPermission("Meow.reload") || sender.isOp()){
    			 sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.LIGHT_PURPLE + "[/mload] /meowreload " + ChatColor.DARK_PURPLE + ConfReload);
    		 }
    	 }else{
    		 sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.DARK_RED + NoPerm);
    	 }
    	 }
     else if(command.getName().equalsIgnoreCase("meowreload") || command.getName().equalsIgnoreCase("mload")){
    	 if(sender.isOp() || sender.hasPermission("Meow.reload")){	
    		 pConf.loadConfiguration();
    		 sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.LIGHT_PURPLE + ConfReloaded);
    	 }else{
    		 sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.DARK_RED + NoPerm);
    		 }
     }
     else if(command.getName().equalsIgnoreCase("meowtell") || command.getName().equalsIgnoreCase("mt")){
    	 if(!sender.hasPermission("Meow.meow") && !sender.isOp()){
    		 sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.DARK_RED + NoPerm);
    	 }else{
    		 Long lastUse = null;
             Long diff = null;
    		 if(cooldown.containsKey(sender.getName())){
             lastUse = cooldown.get(sender.getName());
             diff = System.currentTimeMillis() - lastUse;
    		 }
             if(sender.isOp() || sender.hasPermission("Meow.nocool") || diff == null || diff >= cooldownInSeconds * 1000){
            	 if(args.length < 2){
            		 sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.RED + MTuse);
            	 }else{
                 Player p = getServer().getPlayer(args[0]);
                 if(p != null && p.isOnline()){
                	 StringBuilder sb = new StringBuilder();
                	 for (int i = 1;i < args.length;i++){
                	 sb.append(args[i]).append(" ");
                	 }
                	 String allArgs = sb.toString().trim();
                	 Player player = Bukkit.getPlayerExact(args[0]);
                	 if (!configPlayers.contains(p.getName())){
                         p.playSound(p.getLocation(), MeowSound, 1, 0);
                	 }
                     p.sendMessage(ChatColor.GOLD + prefix + ChatColor.LIGHT_PURPLE + sender.getName() + ": " + allArgs);
                     sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.LIGHT_PURPLE + "To " + p.getName() + ": " + allArgs);
                     if(!sender.hasPermission("Meow.nocool") && !sender.isOp()){
                         cooldown.put(sender.getName(), System.currentTimeMillis());
                     }
                     String AlMeowBacker = MeowBack.get(sender.getName());
                     if(AlMeowBacker == null){
                    	 MeowBack.put(player.getName(), sender.getName());
                     }else{
                    	 MeowBack.remove(sender.getName());
                         MeowBack.put(player.getName(), sender.getName());
                     }
                 }else{
                	 sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.RED + PnotOnline);
                 }
            	 
             }
             }else{
            	 sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.RED + PleaseWait + Long.toString(cooldownInSeconds - (diff / 1000), 0) + " " + SecondToUse);
             }
     }
     }else if(command.getName().equalsIgnoreCase("meowall") || command.getName().equalsIgnoreCase("ma")){
    		 if(sender.isOp() || sender.hasPermission("Meow.server")){
    				 Long lastUse = null;
    	             Long diff = null;
    	    		 if(cooldown.containsKey(sender.getName())){
    	             lastUse = cooldown.get(sender.getName());
    	             diff = System.currentTimeMillis() - lastUse;
    	    		 }
    	    		 if(sender.isOp() || sender.hasPermission("Meow.nocool") || diff == null || diff >= cooldownInSeconds * 1000){
    				 for(Player p : Bukkit.getOnlinePlayers()){
    					 if(!p.equals(sender)){
    					 if (!configPlayers.contains(p.getName())){
    					 p.playSound(p.getLocation(), MeowSound, 1, 0);
    					 }
    					 p.sendMessage(ChatColor.GOLD + prefix + ChatColor.LIGHT_PURPLE + EverbodyMoewd + sender.getName() + "! (Global Meow)");
    					 }
    				 }
    				 sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.LIGHT_PURPLE + YouMeowEvery);
    				 if(!sender.hasPermission("Meow.nocool") && !sender.isOp()){
    				 cooldown.put(sender.getName(), System.currentTimeMillis());
    				 }
    	    		 }else{
    	    			 sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.RED + PleaseWait + Long.toString(cooldownInSeconds - (diff / 1000), 0) + " " + SecondToUse);
    	    		 }
    			 }else{
	         sender.sendMessage(ChatColor.GOLD + prefix + ChatColor.DARK_RED + NoPerm);
    		 }	 
    	 }
     return true;
     }
}// the end...