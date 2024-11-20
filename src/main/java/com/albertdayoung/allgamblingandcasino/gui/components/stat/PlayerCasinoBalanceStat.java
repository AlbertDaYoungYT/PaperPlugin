package com.albertdayoung.allgamblingandcasino.gui.components.stat;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.PaperPlugin;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class PlayerCasinoBalanceStat {

    public static final void invoke(GuiContainer<Player, ItemStack> container, Player _player, int row, int column) {
        String BALANCE = String.format("$%s", PaperPlugin.getEconomy().getBalance(_player));
        String BALANCE_TEXT = String.format("[%s] - You have (%s) in your account", PaperPlugin.PLUGIN, BALANCE);
        
        container.setItem(row, column, ItemBuilder.from(Material.PAPER)
                .name(Component.text(BALANCE_TEXT))
                .asGuiItem((player, context) -> {
                    player.sendMessage(BALANCE_TEXT);
                })
        );
    }
}
