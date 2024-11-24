package com.albertdayoung.allgamblingandcasino.gui.components.buttons;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import dev.triumphteam.nova.MutableState;
import net.kyori.adventure.text.Component;

public class IncrementDecrementButtons {
    MutableState<Integer> betAmount;
    GuiContainer<Player, ItemStack> container;

    public static Material INCREMENT_MATERIAL = Material.LIME_STAINED_GLASS_PANE;
    public static Material DECREMENT_MATERIAL = Material.RED_STAINED_GLASS_PANE;

    public IncrementDecrementButtons(MutableState<Integer> betAmount) {
        this.betAmount = betAmount;
    }
    

    public void invoke(Player _player, int row, int column) {
        this.container.setItem(row, column+0, ItemBuilder.from(DECREMENT_MATERIAL)
                .name(Component.text("Decrement by 50"))
                .amount(50)
                .asGuiItem((player, context) -> {
                    if (!(this.betAmount.get() < 50)) {
                        this.betAmount.update(previous -> previous - 50);
                    }
                })
        );
        this.container.setItem(row, column+1, ItemBuilder.from(DECREMENT_MATERIAL)
                .name(Component.text("Decrement by 25"))
                .amount(25)
                .asGuiItem((player, context) -> {
                    if (!(this.betAmount.get() < 25)) {
                        this.betAmount.update(previous -> previous - 25);
                    }
                })
        );
        this.container.setItem(row, column+2, ItemBuilder.from(DECREMENT_MATERIAL)
                .name(Component.text("Decrement by 10"))
                .amount(10)
                .asGuiItem((player, context) -> {
                    if (!(this.betAmount.get() < 10)) {
                        this.betAmount.update(previous -> previous - 10);
                    }
                })
        );
        this.container.setItem(row, column+3, ItemBuilder.from(DECREMENT_MATERIAL)
                .name(Component.text("Decrement by 1"))
                .amount(1)
                .asGuiItem((player, context) -> {
                    if (!(this.betAmount.get() < 1)) {
                        this.betAmount.update(previous -> previous - 1);
                    }
                })
        );


        
        this.container.setItem(row, column+5, ItemBuilder.from(INCREMENT_MATERIAL)
                .name(Component.text("Increment by 1"))
                .amount(1)
                .asGuiItem((player, context) -> {
                    this.betAmount.update(previous -> previous + 1);
                })
        );
        this.container.setItem(row, column+6, ItemBuilder.from(INCREMENT_MATERIAL)
                .name(Component.text("Increment by 10"))
                .amount(10)
                .asGuiItem((player, context) -> {
                    this.betAmount.update(previous -> previous + 10);
                })
        );
        this.container.setItem(row, column+7, ItemBuilder.from(INCREMENT_MATERIAL)
                .name(Component.text("Increment by 25"))
                .amount(25)
                .asGuiItem((player, context) -> {
                    this.betAmount.update(previous -> previous + 25);
                })
        );
        this.container.setItem(row, column+8, ItemBuilder.from(INCREMENT_MATERIAL)
                .name(Component.text("Increment by 50"))
                .amount(50)
                .asGuiItem((player, context) -> {
                    this.betAmount.update(previous -> previous + 50);
                })
        );
    }

    public GuiContainer<Player, ItemStack> getContainer() {
        return container;
    }

    public void setContainer(GuiContainer<Player, ItemStack> container) {
        this.container = container;
    }

    public MutableState<Integer> getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(MutableState<Integer> betAmount) {
        this.betAmount = betAmount;
    }
}
