package me.thej0y.meow;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.mcstats.MetricsLite;

public class PluginConf {
	
    private File LanguageFile= null;
    private File BlockerFile = null;
    private File ConfigFile = null;
    private FileConfiguration LanguageFileConfig = null;
    private FileConfiguration BlockerFileConfig = null;
    private FileConfiguration ConfigFileConfig = null;
	public static Meow plugin;
	public PluginConf(Meow instance){
	plugin = instance;
	}
	
    public void loadConfiguration(){
    	LanguageFile = new File(plugin.getDataFolder(), "language.yml");
    	if(!LanguageFile.exists()){
    	plugin.saveResource("language.yml", false);
    	}
    	BlockerFile = new File(plugin.getDataFolder(), "blockers.yml");
    	if(!BlockerFile.exists()){
    	plugin.saveResource("blockers.yml", false);
    	}
    	plugin.saveDefaultConfig();
    	
    	ConfigFile = new File(plugin.getDataFolder(), "config.yml");
    	
    	LanguageFileConfig = YamlConfiguration.loadConfiguration(LanguageFile);
    	BlockerFileConfig = YamlConfiguration.loadConfiguration(BlockerFile);
    	ConfigFileConfig = YamlConfiguration.loadConfiguration(ConfigFile);
    	
    	try {
    		LanguageFileConfig.load(LanguageFile);
		} catch (Exception e1) {
			System.out.println("[Meow] Failed to load language.yml");
			e1.printStackTrace();
		}
    	try {
    		BlockerFileConfig.load(BlockerFile);
		} catch (Exception e1) {
			System.out.println("[Meow] Failed to load blockers.yml");
			e1.printStackTrace();
		}

    plugin.cooldownInSeconds = ConfigFileConfig.getInt("CooldownInSeconds");
    plugin.configPlayers = BlockerFileConfig.getStringList("Blockers");
    
    plugin.MoewdBy = LanguageFileConfig.getString("MoewdBy");
    plugin.YouMeowd = LanguageFileConfig.getString("YouMeowd");
    plugin.PnotOnline = LanguageFileConfig.getString("PnotOnline");
    plugin.SelfMeow = LanguageFileConfig.getString("SelfMeow");
    plugin.SoundEnabled = LanguageFileConfig.getString("SoundEnabled");
    plugin.SoundDisabled = LanguageFileConfig.getString("SoundDisabled");
    plugin.SecondToUse = LanguageFileConfig.getString("SecondToUse");
    plugin.PleaseWait = LanguageFileConfig.getString("PleaseWait");
    plugin.AsLoaded = LanguageFileConfig.getString("AsLoaded");
    plugin.Asunloaded = LanguageFileConfig.getString("Asunloaded");
    plugin.HelpToSee = LanguageFileConfig.getString("HelpToSee");
    plugin.PlayerNotFound = LanguageFileConfig.getString("PlayerNotFound");
    plugin.NoPerm = LanguageFileConfig.getString("NoPerm");
    plugin.CantFindMeow = LanguageFileConfig.getString("CantFindMeow");
    plugin.EverbodyMoewd =  LanguageFileConfig.getString("EverbodyMoewd");
    plugin.YouMeowEvery = LanguageFileConfig.getString("YouMeowEvery");
    plugin.ConfReloaded = LanguageFileConfig.getString("ConfReloaded");
    plugin.ConfReload = LanguageFileConfig.getString("ConfReload");
    plugin.SendEverybody = LanguageFileConfig.getString("SendEverybody");
    plugin.SoundToggle = LanguageFileConfig.getString("SoundToggle");
    plugin.SendaMeow = LanguageFileConfig.getString("SendaMeow");
    plugin.ReplyToMeow = LanguageFileConfig.getString("ReplyToMeow");
    plugin.PluginBy = LanguageFileConfig.getString("PluginBy");
    plugin.GotMeowdBack = LanguageFileConfig.getString("GotMeowdBack");
    plugin.MeowdBack = LanguageFileConfig.getString("MeowdBack");
    plugin.MBDisabled = LanguageFileConfig.getString("MBDisabled");
    plugin.PlayerListFailed = LanguageFileConfig.getString("PlayerListFailed");
    plugin.JoinDisabled = LanguageFileConfig.getString("JoinDisabled");
    plugin.MSDisabled = LanguageFileConfig.getString("MSDisabled");
    plugin.MTuse = LanguageFileConfig.getString("MTuse");
    plugin.SendaTell = LanguageFileConfig.getString("SendaTell");

    PluginManager pm = plugin.getServer().getPluginManager();
    
    if(ConfigFileConfig.getBoolean("OnlinePlayerChecker") == true){
		try {
			pm.registerEvents(plugin.OpC, plugin);
		} catch (Exception e) {
			System.out.println(plugin.prefix + " OnlinePlayerChecker failed to start. Please report this to JoeyCo(BukkitDev)");
		}
	}
    if(ConfigFileConfig.getBoolean("SoundOnJoin") == true){
		try {
		pm.registerEvents(plugin.SoJ, plugin);
		} catch (Exception e) {
			System.out.println(plugin.prefix + " SoundOnJoin failed to start. Please report this to JoeyCo(BukkitDev)");
		}
	}
    if(ConfigFileConfig.getBoolean("Metrics") == true) {
		try {
			MetricsLite metrics = new MetricsLite(plugin);
			metrics.start();
		} catch (IOException e) {
			System.out.println(plugin.prefix + " Metrics failed to start. Please report this to JoeyCo(BukkitDev)");
		}
	} 
	 String SoundForMeow = ConfigFileConfig.getString("ReplaceMeowWith").toLowerCase();
    	if(SoundForMeow.equalsIgnoreCase("meow")){
    		plugin.MeowSound = plugin.Meow;
    	}
    	else if(SoundForMeow.equalsIgnoreCase("eggpop")){
			 plugin.MeowSound = plugin.EggPop;
		 }
		 else if(SoundForMeow.equalsIgnoreCase("teleport")){
			 plugin.MeowSound = plugin.Teleport;
		 }
		 else if(SoundForMeow.equalsIgnoreCase("explode")){
			 plugin.MeowSound = plugin.Explode;
		 }
		 else if(SoundForMeow.equalsIgnoreCase("plong")){
			 plugin.MeowSound = plugin.Plong;
		 }else{
		 plugin.MeowSound = plugin.Meow;
		 System.out.println(plugin.prefix + plugin.MSDisabled);
		 }
    	if(ConfigFileConfig.getBoolean("SoundOnJoin") == true){
    		String SoundPlay = ConfigFileConfig.getString("SoundToPlay").toLowerCase();
    		 if(SoundPlay.equalsIgnoreCase("eggpop")){
	    		plugin.JoinSound = plugin.EggPop;
			 }
    		 else if(SoundPlay.equalsIgnoreCase("meow")){
				 plugin.JoinSound = plugin.Meow;
			 }
			 else if(SoundPlay.equalsIgnoreCase("teleport")){
				 plugin.JoinSound = plugin.Teleport;
			 }
			 else if(SoundPlay.equalsIgnoreCase("explode")){
				 plugin.JoinSound = plugin.Explode;
			 }
			 else if(SoundPlay.equalsIgnoreCase("plong")){
				 plugin.JoinSound = plugin.Plong;
			 }
			 else{
				 plugin.JoinSound = plugin.EggPop;
				 System.out.println(plugin.prefix + plugin.JoinDisabled);
				 }
    	}
    }
}
