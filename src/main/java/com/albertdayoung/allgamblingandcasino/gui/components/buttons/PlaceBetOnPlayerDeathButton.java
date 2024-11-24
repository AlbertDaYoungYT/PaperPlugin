package com.albertdayoung.allgamblingandcasino.gui.components.buttons;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.utils.dataclasses.DeathOptionsData;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import dev.triumphteam.nova.MutableState;
import dev.triumphteam.nova.State;
import net.kyori.adventure.text.Component;

public class PlaceBetOnPlayerDeathButton {
    MutableState<DeathOptionsData> betDeathOption;
    GuiContainer<Player, ItemStack> container;

    public PlaceBetOnPlayerDeathButton(MutableState<DeathOptionsData> betDeathOption) {
        this.betDeathOption = betDeathOption;
    }



    public void invoke(Player _player, int row, int column) {
        container.setItem(row, column, ItemBuilder.from(Material.GOLD_BLOCK)
                .name(Component.text(String.format("Bet on (%s)", this.betDeathOption.get().getCauseOptionsTitle())))
                .asGuiItem((player, context) -> {
                    //nextPage.open(_player);
                    _player.sendMessage(String.format("It works ($%s)", this.betDeathOption.get().getCauseOptions().name()));
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
