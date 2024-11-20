package com.albertdayoung.allgamblingandcasino.gui.components;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class RouletteBetRedBlack {
    
    public static final Material RED_MATERIAL = Material.RED_WOOL;
    public static final Material BLACK_MATERIAL = Material.BLACK_WOOL;
    
    
    public static final void invoke(GuiContainer<Player, ItemStack> container) {
        container.setItem(6, 7, ItemBuilder.from(RED_MATERIAL)
                                                    .name(Component.text("Bet Red"))
                                                    .asGuiItem()
                                        );
        container.setItem(6, 8, ItemBuilder.from(BLACK_MATERIAL)
                                                    .name(Component.text("Bet Black"))
                                                    .asGuiItem()
                                        );
    }
}
