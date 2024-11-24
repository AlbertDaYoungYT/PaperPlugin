package com.albertdayoung.allgamblingandcasino.gui.components.buttons;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.gui.MainCasino;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class BackToMainMenuButton {
    

    public static void invoke(GuiContainer<Player, ItemStack> container, int row, int column) {
        container.setItem(row, column, ItemBuilder.from(Material.RED_BED)
                .name(Component.text("Back"))
                .asGuiItem((player, context) -> {
                    MainCasino.open(player);
                })
        );
    }
}
