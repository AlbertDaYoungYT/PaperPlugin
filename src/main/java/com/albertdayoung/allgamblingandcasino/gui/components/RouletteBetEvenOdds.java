package com.albertdayoung.allgamblingandcasino.gui.components;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class RouletteBetEvenOdds {
    
    public static final Material EVEN_MATERIAL = Material.LIGHT_GRAY_WOOL;
    public static final Material ODD_MATERIAL = Material.GRAY_WOOL;
    
    
    public static final void invoke(GuiContainer<Player, ItemStack> container) {
        container.setItem(6, 2, ItemBuilder.from(EVEN_MATERIAL)
                                                    .name(Component.text("Bet Even"))
                                                    .asGuiItem()
                                        );
        container.setItem(6, 3, ItemBuilder.from(ODD_MATERIAL)
                                                    .name(Component.text("Bet Odds"))
                                                    .asGuiItem()
                                        );
    }
}
