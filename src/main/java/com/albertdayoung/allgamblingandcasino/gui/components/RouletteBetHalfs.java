package com.albertdayoung.allgamblingandcasino.gui.components;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.roulette.RouletteGame;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class RouletteBetHalfs {
    
    public static final Material HALFS_MATERIAL = Material.GRAY_DYE;
    
    
    public static final void invoke(GuiContainer<Player, ItemStack> container, RouletteGame game) {
        container.setItem(5, 1, ItemBuilder.from(HALFS_MATERIAL)
                                                    .name(Component.text("Bet 1st Half (1-18)"))
                                                    .asGuiItem((player, context) -> {
                                                        game.addBet("HALFS-1");
                                                    })
                                        );
        container.setItem(5, 9, ItemBuilder.from(HALFS_MATERIAL)
                                                    .name(Component.text("Bet 2nd Half (19-36)"))
                                                    .asGuiItem((player, context) -> {
                                                        game.addBet("HALFS-2");
                                                    })
                                        );
    }
}
