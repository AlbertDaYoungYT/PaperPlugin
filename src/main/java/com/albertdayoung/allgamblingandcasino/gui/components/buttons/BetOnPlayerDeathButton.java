package com.albertdayoung.allgamblingandcasino.gui.components.buttons;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.PeakGambling;
import com.albertdayoung.allgamblingandcasino.gui.PlaceBetOnPlayerDeath;

import dev.triumphteam.gui.click.ClickContext;
import dev.triumphteam.gui.click.action.RunnableGuiClickAction;
import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class BetOnPlayerDeathButton {
    

    public static void invoke(GuiContainer<Player, ItemStack> container, Player _player, int row, int column) {
        container.setItem(row, column, ItemBuilder.from(Material.PLAYER_HEAD)
                .name(Component.text("Place a bet on Player Death"))
                .lore(Component.text(" "))
                .asGuiItem(new RunnableGuiClickAction<Player>() {
            @Override
            public void run(Player player, ClickContext context) {
                double playerBalance = PeakGambling.getEconomy().getBalance(player);
                if (playerBalance > 0.0) {
                    PlaceBetOnPlayerDeath.open(_player);
                } else {
                    player.sendMessage("You don't have enough money to place a bet!");
                }
            }
        })
        );
    }
}
