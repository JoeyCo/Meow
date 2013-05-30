package me.thej0y.meow;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.mcstats.MetricsLite;

public class PluginConf {
	
    private File CustomConfigFile = null;
    private FileConfiguration CustomConfig = null;
    
	public static Meow plugin;
	public PluginConf(Meow instance){
	plugin = instance;
	}
	
    public void loadConfiguration(){
    	CustomConfigFile = new File(plugin.getDataFolder(), "language.yml");
    	if(!CustomConfigFile.exists()){
    	plugin.saveResource("language.yml", false);
    	}
    	plugin.saveDefaultConfig();
    	
        CustomConfig = YamlConfiguration.loadConfiguration(CustomConfigFile);
    	
    	plugin.reloadConfig();
    	try {
			CustomConfig.load(CustomConfigFile);
		} catch (Exception e1) {
			System.out.println("[Meow] Failed to load language.yml");
			e1.printStackTrace();
		}
    	
    	
    plugin.cooldownInSeconds = plugin.getConfig().getInt("CooldownInSeconds");
    plugin.configPlayers = plugin.getConfig().getStringList("Blockers");
    
    plugin.MoewdBy = CustomConfig.getString("MoewdBy");
    plugin.YouMeowd = CustomConfig.getString("YouMeowd");
    plugin.PnotOnline = CustomConfig.getString("PnotOnline");
    plugin.SelfMeow = CustomConfig.getString("SelfMeow");
    plugin.SoundEnabled = CustomConfig.getString("SoundEnabled");
    plugin.SoundDisabled = CustomConfig.getString("SoundDisabled");
    plugin.SecondToUse = CustomConfig.getString("SecondToUse");
    plugin.PleaseWait = CustomConfig.getString("PleaseWait");
    plugin.AsLoaded = CustomConfig.getString("AsLoaded");
    plugin.Asunloaded = CustomConfig.getString("Asunloaded");
    plugin.HelpToSee = CustomConfig.getString("HelpToSee");
    plugin.PlayerNotFound = CustomConfig.getString("PlayerNotFound");
    plugin.NoPerm = CustomConfig.getString("NoPerm");
    plugin.CantFindMeow = CustomConfig.getString("CantFindMeow");
    plugin.EverbodyMoewd =  CustomConfig.getString("EverbodyMoewd");
    plugin.YouMeowEvery = CustomConfig.getString("YouMeowEvery");
    plugin.ConfReloaded = CustomConfig.getString("ConfReloaded");
    plugin.ConfReload = CustomConfig.getString("ConfReload");
    plugin.SendEverybody = CustomConfig.getString("SendEverybody");
    plugin.SoundToggle = CustomConfig.getString("SoundToggle");
    plugin.SendaMeow = CustomConfig.getString("SendaMeow");
    plugin.ReplyToMeow = CustomConfig.getString("ReplyToMeow");
    plugin.PluginBy = CustomConfig.getString("PluginBy");
    plugin.GotMeowdBack = CustomConfig.getString("GotMeowdBack");
    plugin.MeowdBack = CustomConfig.getString("MeowdBack");
    plugin.MBDisabled = CustomConfig.getString("MBDisabled");
    plugin.PlayerListFailed = CustomConfig.getString("PlayerListFailed");
    plugin.JoinDisabled = CustomConfig.getString("JoinDisabled");
    plugin.MSDisabled = CustomConfig.getString("MSDisabled");
    plugin.MTuse = CustomConfig.getString("MTuse");
    plugin.SendaTell = CustomConfig.getString("SendaTell");

    PluginManager pm = plugin.getServer().getPluginManager();
    
    if(plugin.getConfig().getBoolean("OnlinePlayerChecker") == true){
		try {
			pm.registerEvents(plugin.OpC, plugin);
		} catch (Exception e) {
			System.out.println(plugin.prefix + " OnlinePlayerChecker failed to start. Please report this to JoeyCo(BukkitDev)");
		}
	}
    if(plugin.getConfig().getBoolean("SoundOnJoin") == true){
		try {
		pm.registerEvents(plugin.SoJ, plugin);
		} catch (Exception e) {
			System.out.println(plugin.prefix + " SoundOnJoin failed to start. Please report this to JoeyCo(BukkitDev)");
		}
	}
    if(plugin.getConfig().getBoolean("Metrics") == true) {
		try {
			MetricsLite metrics = new MetricsLite(plugin);
			metrics.start();
		} catch (IOException e) {
			System.out.println(plugin.prefix + " Metrics failed to start. Please report this to JoeyCo(BukkitDev)");
		}
	} 
	 String SoundForMeow = plugin.getConfig().getString("ReplaceMeowWith").toLowerCase();
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
    	if(plugin.getConfig().getBoolean("SoundOnJoin") == true){
    		String SoundPlay = plugin.getConfig().getString("SoundToPlay").toLowerCase();
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
