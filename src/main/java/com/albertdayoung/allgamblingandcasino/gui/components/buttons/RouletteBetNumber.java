package com.albertdayoung.allgamblingandcasino.gui.components.buttons;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.gambling.RouletteGame;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class RouletteBetNumber {
    int betNumber;
    GuiContainer<Player, ItemStack> container;

    public RouletteBetNumber(GuiContainer<Player, ItemStack> container, int betNumber) {
        this.betNumber = betNumber;
        this.container = container;
    }


    public void invoke(int row, int column, Material usedMaterial, RouletteGame game) {
                
        this.container.setItem(row, column, ItemBuilder.from(usedMaterial)
                                                    .name(Component.text(String.format("Bet on (%s)", this.betNumber)))
                                                    .amount(this.betNumber)
                                                    .asGuiItem((player, context) -> {
                                                        game.addBet(String.valueOf(this.betNumber));
                                                    })
                                        );
    }
}
