package com.albertdayoung.allgamblingandcasino.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import com.albertdayoung.allgamblingandcasino.PeakGambling;
import com.albertdayoung.allgamblingandcasino.gui.MainCasino;

import net.kyori.adventure.text.Component;


public class MainDebugCommand implements CommandExecutor {

    @SuppressWarnings("unused")
    private final PeakGambling plugin;


    public MainDebugCommand(PeakGambling plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("bets")) {
			Bukkit.getServer().broadcast(Component.text(plugin.deathBets.getBets().toString()));
			return true;
		}
		return false;
    }
}