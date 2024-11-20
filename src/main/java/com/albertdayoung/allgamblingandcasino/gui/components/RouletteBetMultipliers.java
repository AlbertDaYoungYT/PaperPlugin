package com.albertdayoung.allgamblingandcasino.gui.components;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class RouletteBetMultipliers {
    
    public static final Material MULTIPLIERSTAT_MATERIAL = Material.GREEN_WOOL;
    public static final Material PLUSMINUS_MATERIAL = Material.ARROW;

    public static final void invoke(GuiContainer<Player, ItemStack> container) {
        container.setItem(6, 5, ItemBuilder.from(MULTIPLIERSTAT_MATERIAL)
                                                    .name(Component.text(String.format("Current Bet Multiplier Amount", 5)))
                                                    .amount(5)
                                                    .asGuiItem()
                                        );
        container.setItem(6, 4, ItemBuilder.from(PLUSMINUS_MATERIAL)
                                                    .name(Component.text("Decrement bet multiplier"))
                                                    .asGuiItem()
                                        );
        container.setItem(6, 6, ItemBuilder.from(PLUSMINUS_MATERIAL)
                                                    .name(Component.text("Increment bet multiplier"))
                                                    .asGuiItem()
                                        );

    }
}
