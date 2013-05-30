package me.thej0y.meow;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigFixer {
	
    private File CustomConfigFile = null;
    private FileConfiguration CustomConfig = null;
    private Map<String, Object> LanguageFileParam = null;
    
	public static Meow plugin;
	public ConfigFixer(Meow instance){
	plugin = instance;
	} 
	
	public void ConfigCheck(){
		
	  File configFile = new File(plugin.getDataFolder() + File.separator + "config.yml");
	  if (configFile.exists()){
		 
		  final Map<String, Object> defParams = new HashMap<String, Object>();
		  FileConfiguration config = plugin.getConfig();
		  config.options().copyDefaults(true);	
		  defParams.put("CooldownInSeconds", "30");
		  defParams.put("ReplaceMeowWith", "meow");
		  defParams.put("rMeowCooldown", "true");
		  defParams.put("SoundOnJoin", "true");
		  defParams.put("SoundToPlay", "plong");
		  defParams.put("OnlinePlayerChecker", "true");
		  defParams.put("Metrics", "true");
		  defParams.put("Blockers", "");
		  
		  for (final Entry<String, Object> e : defParams.entrySet())
		    if(!config.contains(e.getKey())){
		      config.set(e.getKey(), e.getValue());
		      plugin.saveConfig();
		    }
	 	}
	  
	  if (CustomConfigFile.exists()){
		CustomConfig = YamlConfiguration.loadConfiguration(CustomConfigFile);
	  LanguageFileParam = new HashMap<String, Object>();
	  CustomConfig.options().copyDefaults(true);	
	  LanguageFileParam.put("MoewdBy" , "You have been meow by: ");
	  LanguageFileParam.put("YouMeowd" , "You meowed ");
	  LanguageFileParam.put("PnotOnline" , "Player is not online");
	  LanguageFileParam.put("SelfMeow" , "do not meow yourself fool! Do /meow <player>");
	  LanguageFileParam.put("SoundEnabled" , "Sounds Enabled!");
	  LanguageFileParam.put("SoundDisabled" , "Sounds Disabled!");
	  LanguageFileParam.put("SecondToUse" , "seconds to use this command again");
	  LanguageFileParam.put("PleaseWait" , "Please wait ");
	  LanguageFileParam.put("AsLoaded" , "has been loaded.");
	  LanguageFileParam.put("Asunloaded" , "has been disabled.");
	  LanguageFileParam.put("HelpToSee" , "/meowhelp to see commands");
	  LanguageFileParam.put("PlayerNotFound" , "The player could not be found");
	  LanguageFileParam.put("NoPerm" , "You do not have the permission to use this command");
	  LanguageFileParam.put("CantFindMeow" , "Could not trace last meow.");
	  LanguageFileParam.put("EverbodyMoewd" , "Everybody been meowed by: ");
	  LanguageFileParam.put("YouMeowEvery" , "You meowed everybody!");
	  LanguageFileParam.put("ConfReloaded" , "configuration files reloaded.");
	  LanguageFileParam.put("ConfReload" , "Reload config files");
	  LanguageFileParam.put("SendEverybody" , "Send a Meow to everyone (Bypass mutes)");
	  LanguageFileParam.put("SoundToggle" , "Toggle Meow sounds on/off");
	  LanguageFileParam.put("SendaMeow" , "Send a Meow to this player");
	  LanguageFileParam.put("ReplyToMeow" , "Reply to a Meow!");
	  LanguageFileParam.put("PluginBy" , "Plugin by ");
	  LanguageFileParam.put("GotMeowdBack" , "You have been meowed back by ");
	  LanguageFileParam.put("MeowdBack" , "You meowed back ");
	  LanguageFileParam.put("MBDisabled" , "MeowBacks are disabled.");
	  LanguageFileParam.put("PlayerListFailed" , "Have not been able to create playerlist.yml");
	  LanguageFileParam.put("JoinDisabled" , "SoundOnJoin back to plong...Wrong SoundToPlay");
	  LanguageFileParam.put("MSDisabled" , "/meow sound back to meow...Wrong SoundToPlay");
	  LanguageFileParam.put("MTuse" , "Usage: /mt <player> <message>");
	  LanguageFileParam.put("SendaTell" , "Send a Meow with a custom message to this player");
	  
	  for (final Entry<String, Object> z : LanguageFileParam.entrySet()){
	    if(!CustomConfig.contains(z.getKey())){
	    	CustomConfig.set(z.getKey(), z.getValue());
	    	try {
				CustomConfig.save(CustomConfigFile);
			} catch (IOException e1) {
				System.out.println("[Meow] Failed to save language.yml after fixing it");
				e1.printStackTrace();
			}
	    }
	  }
	  }
	}
}

