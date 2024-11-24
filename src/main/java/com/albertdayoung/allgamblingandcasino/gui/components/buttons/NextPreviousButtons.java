package com.albertdayoung.allgamblingandcasino.gui.components.buttons;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import dev.triumphteam.nova.MutableState;
import net.kyori.adventure.text.Component;

public class NextPreviousButtons {
    MutableState<Integer> pageNumber;
    GuiContainer<Player, ItemStack> container;

    public NextPreviousButtons(MutableState<Integer> pageNumber) {
        this.pageNumber = pageNumber;
    }


    public void invoke(int row, int column) {
                
        this.container.setItem(row, column+1, ItemBuilder.from(Material.ARROW)
                                                    .name(Component.text(String.format("Go to Page %s", this.pageNumber.get()+1)))
                                                    .asGuiItem((player, context) -> {
                                                        this.pageNumber.update(previous -> previous + 1);
                                                    })
                                        );
                
        if (!(this.pageNumber.get() == 0)) {
            this.container.setItem(row, column-1, ItemBuilder.from(Material.ARROW)
                                                        .name(Component.text(String.format("Go to Page %s", this.pageNumber.get()-1)))
                                                        .asGuiItem((player, context) -> {
                                                            this.pageNumber.update(previous -> previous - 1);
                                                        })
                                            );
        }
    }


    public void setContainer(GuiContainer<Player, ItemStack> container) {
        this.container = container;
    }

    public GuiContainer<Player, ItemStack> getContainer() {
        return this.container;
    }
}
