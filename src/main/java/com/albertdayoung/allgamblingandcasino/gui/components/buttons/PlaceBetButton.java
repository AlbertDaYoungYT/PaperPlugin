package com.albertdayoung.allgamblingandcasino.gui.components.buttons;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.gui.PlaceBetOnDeathTypeMenu;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import dev.triumphteam.nova.MutableState;
import net.kyori.adventure.text.Component;

public class PlaceBetButton {
    MutableState<Integer> betAmount;
    GuiContainer<Player, ItemStack> container;

    public PlaceBetButton(MutableState<Integer> betAmount) {
        this.betAmount = betAmount;
    }



    public void invoke(Player _player, int row, int column) {
        container.setItem(row, column, ItemBuilder.from(Material.GOLD_BLOCK)
                .name(Component.text(String.format("Bet $%s", this.betAmount.get())))
                .asGuiItem((player, context) -> {
                    PlaceBetOnDeathTypeMenu.open(_player, this.betAmount);
                    //_player.sendMessage(String.format("It works ($%s)", this.betAmount.get().toString()));
                })
        );
    }


    public GuiContainer<Player, ItemStack> getContainer() {
        return container;
    }

    public void setContainer(GuiContainer<Player, ItemStack> container) {
        this.container = container;
    }
}
