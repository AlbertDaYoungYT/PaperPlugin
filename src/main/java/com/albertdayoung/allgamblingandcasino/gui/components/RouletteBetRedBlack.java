package com.albertdayoung.allgamblingandcasino.gui.components;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import com.albertdayoung.allgamblingandcasino.gambling.RouletteGame;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class RouletteBetRedBlack {
    
    public static final Material RED_MATERIAL = Material.RED_STAINED_GLASS_PANE;
    public static final Material BLACK_MATERIAL = Material.BLACK_STAINED_GLASS_PANE;
    
    public static final Material SELECTED_MATERIAL = Material.LIGHT_GRAY_STAINED_GLASS_PANE;
    
    
    public static final void invoke(GuiContainer<Player, ItemStack> container, RouletteGame game) {
        @NotNull
        Material usedMaterial;

        usedMaterial = RED_MATERIAL;

        if (game.getBets().contains("RED")) {
            usedMaterial = SELECTED_MATERIAL;
        }
        container.setItem(6, 7, ItemBuilder.from(usedMaterial)
                                                    .name(Component.text("Bet Red"))
                                                    .asGuiItem((player, context) -> {
                                                        game.addBet("RED");
                                                    })
                                        );

        usedMaterial = BLACK_MATERIAL;

        if (game.getBets().contains("BLACK")) {
            usedMaterial = SELECTED_MATERIAL;
        }
        
        container.setItem(6, 8, ItemBuilder.from(usedMaterial)
                                                    .name(Component.text("Bet Black"))
                                                    .asGuiItem((player, context) -> {
                                                        game.addBet("BLACK");
                                                    })
                                        );
    }
}
