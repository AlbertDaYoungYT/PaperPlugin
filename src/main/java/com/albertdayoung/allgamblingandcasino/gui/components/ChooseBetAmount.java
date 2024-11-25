package com.albertdayoung.allgamblingandcasino.gui.components;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.nova.MutableState;

public class ChooseBetAmount {
    MutableState<Integer> betAmount;
    GuiContainer<Player, ItemStack> container;


    public ChooseBetAmount(MutableState<Integer> betAmount) {
        this.betAmount = betAmount;
    }


    public final void invoke(int row, int column) {

    }
}
