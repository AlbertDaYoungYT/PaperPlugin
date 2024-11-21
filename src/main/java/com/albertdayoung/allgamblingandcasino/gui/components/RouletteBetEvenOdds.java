package com.albertdayoung.allgamblingandcasino.gui.components;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.roulette.RouletteGame;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class RouletteBetEvenOdds {
    
    public static final Material EVEN_MATERIAL = Material.WHITE_STAINED_GLASS_PANE;
    public static final Material ODD_MATERIAL = Material.BLACK_STAINED_GLASS_PANE;
    
    
    public static final void invoke(GuiContainer<Player, ItemStack> container, RouletteGame game) {
        container.setItem(6, 2, ItemBuilder.from(EVEN_MATERIAL)
                                                    .name(Component.text("Bet Even"))
                                                    .asGuiItem((player, context) -> {
                                                        game.addBet("EVEN");
                                                    })
                                        );
        container.setItem(6, 3, ItemBuilder.from(ODD_MATERIAL)
                                                    .name(Component.text("Bet Odds"))
                                                    .asGuiItem((player, context) -> {
                                                        game.addBet("ODDS");
                                                    })
                                        );
    }
}
