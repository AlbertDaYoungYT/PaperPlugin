package com.albertdayoung.allgamblingandcasino.gui.components.buttons;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.gui.Leaderboard;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class LeaderboardButton {
    

    public static void invoke(GuiContainer<Player, ItemStack> container, Player _player, int row, int column) {
        container.setItem(row, column, ItemBuilder.from(Material.OAK_SIGN)
                .name(Component.text("Check the Leaderboard"))
                .lore(Component.text("Leaderboard is currently unfinished!"))
                .asGuiItem((player, context) -> {
                    Leaderboard.open(_player);
                })
        );
    }
}
