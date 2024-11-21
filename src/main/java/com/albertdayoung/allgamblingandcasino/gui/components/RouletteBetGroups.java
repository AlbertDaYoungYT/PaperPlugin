package com.albertdayoung.allgamblingandcasino.gui.components;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.roulette.RouletteGame;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class RouletteBetGroups {

    public static final Material GROUP_INACTIVE_MATERIAL = Material.GRAY_WOOL;
    public static final Material GROUP_ACTIVE_MATERIAL = Material.GREEN_WOOL;
    
    
    public static final void invoke(GuiContainer<Player, ItemStack> container, RouletteGame game) {
        container.setItem(5, 2, ItemBuilder.from(GROUP_INACTIVE_MATERIAL)
                                                    .name(Component.text("Bet 1st (12)"))
                                                    .asGuiItem((player, context) -> {
                                                        game.addBet("GROUP-1");
                                                    })
                                        );
        container.setItem(5, 5, ItemBuilder.from(GROUP_INACTIVE_MATERIAL)
                                                    .name(Component.text("Bet 2nd (12)"))
                                                    .asGuiItem((player, context) -> {
                                                        game.addBet("GROUP-2");
                                                    })
                                        );
        container.setItem(5, 8, ItemBuilder.from(GROUP_INACTIVE_MATERIAL)
                                                    .name(Component.text("Bet 3rd (12)"))
                                                    .asGuiItem((player, context) -> {
                                                        game.addBet("GROUP-3");
                                                    })
                                        );
    }
}
