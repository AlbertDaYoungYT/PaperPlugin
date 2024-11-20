package com.albertdayoung.allgamblingandcasino;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

import com.albertdayoung.allgamblingandcasino.commands.CasinoCommand;

import io.papermc.lib.PaperLib;


/*
- TODO: Plinko
- TODO: Slots
- TODO: Roulette
- TODO: 
*/



public class PaperPlugin extends JavaPlugin {

	public static YamlConfiguration translationConfig;
	public static YamlConfiguration mainConfig;

	@Override
	public void onEnable() {
		PaperLib.suggestPaper(this);
		mainConfig 			= YamlConfiguration.loadConfiguration(new File(getDataFolder(), "config.yml"));
		translationConfig 	= YamlConfiguration.loadConfiguration(new File(getDataFolder(), "translation.yml"));

		this.getCommand("casino").setExecutor(new CasinoCommand(this));
		
		//PaperListeners.initialize();
  
		saveDefaultConfig();
	}


	public static final YamlConfiguration getMainConfig() {
		return mainConfig;
	}

	public static final YamlConfiguration getTranslationConfig() {
		return translationConfig;
	}
}
