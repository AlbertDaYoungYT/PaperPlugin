package com.albertdayoung.allgamblingandcasino;

import org.bukkit.plugin.java.JavaPlugin;

import com.albertdayoung.allgamblingandcasino.commands.CasinoCommand;

import io.papermc.lib.PaperLib;


public final class PaperPlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		PaperLib.suggestPaper(this);

		this.getCommand("casino").setExecutor(new CasinoCommand(this));
		
		//PaperListeners.initialize();

		saveDefaultConfig();
	}
}
