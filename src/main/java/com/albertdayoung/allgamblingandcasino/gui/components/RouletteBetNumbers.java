package com.albertdayoung.allgamblingandcasino.gui.components;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import com.albertdayoung.allgamblingandcasino.gambling.RouletteGame;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.RouletteBetNumber;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class RouletteBetNumbers {

    public static final Material oddItem = Material.RED_WOOL;
    public static final Material evenItem = Material.BLACK_WOOL;
    public static final Material zeroItem = Material.LIME_WOOL;

    public static final Material selectedItem = Material.LIGHT_GRAY_WOOL;

    public static int i = 1;
    
        
    public static final void invoke(GuiContainer<Player, ItemStack> container, RouletteGame game) {
        @NotNull
        Material usedMaterial;
        i = 1;

        for (int row = 1;row <= 4;row++) {
            for (int column = 1;column <= 9;column++) {
                if (i % 2 == 0) {
                    usedMaterial = evenItem;
                } else {
                    usedMaterial = oddItem;
                }

                RouletteBetNumber bet = new RouletteBetNumber(container, i);
                if (game.getBets().contains(String.valueOf(i))) {
                    usedMaterial = selectedItem;
                }
                bet.invoke(row, column, usedMaterial, game);

                i++;
            }
        }
        
        usedMaterial = zeroItem;
        if (game.getBets().contains("GREEN")) {
            usedMaterial = selectedItem;
        }
         
        container.setItem(6, 1, ItemBuilder.from(usedMaterial)
                                                    .name(Component.text(String.format("Bet on (%s)", 0)))
                                                    .asGuiItem((player, context) -> {
                                                        game.addBet("GREEN");
                                                    })
                                        );
    }
}
