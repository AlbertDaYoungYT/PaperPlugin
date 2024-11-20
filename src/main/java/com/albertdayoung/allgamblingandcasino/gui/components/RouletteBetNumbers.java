package com.albertdayoung.allgamblingandcasino.gui.components;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class RouletteBetNumbers {

    public static final Material oddItem = Material.RED_WOOL;
    public static final Material evenItem = Material.BLACK_WOOL;
    
    
    public static final void invoke(GuiContainer<Player, ItemStack> container) {
        @NotNull
        Material usedMaterial;
        int i = 1;

        for (int row = 1;row <= 4;row++) {
            for (int column = 1;column <= 9;column++) {
                if (i % 2 == 0) {
                    usedMaterial = evenItem;
                } else {
                    usedMaterial = oddItem;
                }
                
                container.setItem(row, column, ItemBuilder.from(usedMaterial)
                                                            .name(Component.text(String.format("Bet on (%s)", i)))
                                                            .amount(i)
                                                            .asGuiItem()
                                                );

                i++;
            }
        }
    }
}
