package com.albertdayoung.allgamblingandcasino.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import com.albertdayoung.allgamblingandcasino.PaperPlugin;
import com.albertdayoung.allgamblingandcasino.gui.MainCasino;


public class CasinoCommand implements CommandExecutor {

    @SuppressWarnings("unused")
    private final PaperPlugin plugin;


    public CasinoCommand(PaperPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("casino")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
                Player player = (Player) sender;
                if (args.length < 1) {
                    MainCasino.MAINCASINOMEN_GUI.open(player);
                } else
                if (args.length < 2) {
                    if (args[0].equalsIgnoreCase("play")) {
                        
                    }
                }
			}
			return true;
		}
		return false;
    }
}