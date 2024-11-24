package com.albertdayoung.allgamblingandcasino.gui.components;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.item.GuiItem;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class BlankingFillWithType {

    public static final void invoke(GuiContainer<Player, ItemStack> container, Material material, int x1, int x2, int y1, int y2) {

        for (int row = y1;row <= y2;row++) {
            for (int column = x1;column <= x2;column++) {
                container.setItem(row, column, ItemBuilder.from(material)
                    .name(Component.text(" "))
                    .asGuiItem()
                );
            }
        }
    }
}
