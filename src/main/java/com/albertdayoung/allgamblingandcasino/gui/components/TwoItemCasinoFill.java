package com.albertdayoung.allgamblingandcasino.gui.components;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import com.albertdayoung.allgamblingandcasino.PaperPlugin;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.item.GuiItem;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class TwoItemCasinoFill {

    public static boolean setAlternate = false;
    public static final @NotNull GuiItem<Player, ItemStack> primaryItem = ItemBuilder.from(Material.RED_STAINED_GLASS_PANE)
                                                                        .name(Component.text(PaperPlugin.mainConfig.getString("translation.gui.casino-border")))
                                                                        .asGuiItem();
    public static final @NotNull GuiItem<Player, ItemStack> alternateItem = ItemBuilder.from(Material.YELLOW_STAINED_GLASS_PANE)
                                                                        .name(Component.text(PaperPlugin.mainConfig.getString("translation.gui.casino-border")))
                                                                        .asGuiItem();
        
    
    public static final void invoke(GuiContainer<Player, ItemStack> container, int row, int x1, int x2, boolean startState) {
        
        if (startState) {
            setAlternate = false;
        }

        for (int column = x1;column <= x2;column++) {
            if (setAlternate) {
                setAlternate = false;
                container.setItem(row, column, alternateItem);
            } else {
                setAlternate = true;
                container.setItem(row, column, primaryItem);
            }
        }
    }

    public static final void loopInvoke(GuiContainer<Player, ItemStack> container, int x1, int x2, int y1, int y2) {
        
        boolean startState = false;

        for (int row = y1; row <= y2; row++) {
            if (setAlternate) {
                startState = false;
                invoke(container, row, x1, x2, startState);
            } else {
                startState = true;
                invoke(container, row, x1, x2, startState);
            }
            startState = true;
        }

        setAlternate = false;
    }
}
