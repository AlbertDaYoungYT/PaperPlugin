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

public class FourRowCasinoBorder {

    public static boolean setAlternate = false;
    public static final @NotNull GuiItem<Player, ItemStack> primaryItem = ItemBuilder.from(Material.RED_STAINED_GLASS_PANE)
                                                                        .name(Component.text("<!i>" + PaperPlugin.translationConfig.get("gui", "casino-null")))
                                                                        .asGuiItem();
    public static final @NotNull GuiItem<Player, ItemStack> alternateItem = ItemBuilder.from(Material.YELLOW_STAINED_GLASS_PANE)
                                                                        .name(Component.text("<!i>" + PaperPlugin.translationConfig.get("gui", "casino-null")))
                                                                        .asGuiItem();
        
    
    public static final void invoke(GuiContainer<Player, ItemStack> container, int row, boolean startState) {
        
        if (startState) {
            setAlternate = false;
        }

        for (int column = 1;column <= 9;column++) {
            if (setAlternate) {
                setAlternate = false;
                container.setItem(row, column, alternateItem);
            } else {
                setAlternate = true;
                container.setItem(row, column, primaryItem);
            }
        }
    }

    public static final void loopInvoke(GuiContainer<Player, ItemStack> container) {
        
        boolean startState = false;

        for (int row = 1; row <= 3; row++) {
            if (setAlternate) {
                startState = false;
                invoke(container, row, startState);
            } else {
                startState = true;
                invoke(container, row, startState);
            }
            startState = true;
        }
    }
}
