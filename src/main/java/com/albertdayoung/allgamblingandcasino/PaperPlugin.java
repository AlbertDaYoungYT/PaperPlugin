package com.albertdayoung.allgamblingandcasino;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.albertdayoung.allgamblingandcasino.commands.CasinoCommand;

import io.papermc.lib.PaperLib;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;


/*
- TODO: Roulette
- TODO: Plinko
- TODO: Slots
- TODO: Blackjack
*/



public class PaperPlugin extends JavaPlugin {

	public static final String PLUGIN = "PaperPlugin";

	public static YamlConfiguration mainConfig;
    
    private static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;

	@Override
	public void onEnable() {
		PaperLib.suggestPaper(this);
		mainConfig 			= YamlConfiguration.loadConfiguration(new File(getDataFolder(), "config.yml"));


        if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", PLUGIN));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        setupChat();
		
		this.getCommand("casino").setExecutor(new CasinoCommand(this));
		
		//PaperListeners.initialize();
		saveDefaultConfig();
  
	}
    
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    
    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }
    
    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }


	public static final YamlConfiguration getMainConfig() {
		return mainConfig;
	}
    
    public static Economy getEconomy() {
        return econ;
    }
    
    public static Permission getPermissions() {
        return perms;
    }
    
    public static Chat getChat() {
        return chat;
    }
}
