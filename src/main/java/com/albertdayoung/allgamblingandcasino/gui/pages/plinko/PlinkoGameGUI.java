package com.albertdayoung.allgamblingandcasino.gui.pages.plinko;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.gui.components.theme.Theme;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class PlinkoGameGUI {
    GuiContainer<Player, ItemStack> container;
    ArrayList<Integer> stepsTheBallTook;

    int gameHeight;
    int row;
    int col;


    public PlinkoGameGUI(ArrayList<Integer> stepsTheBallTook, int row, int col, int gameHeight) {
        this.stepsTheBallTook = stepsTheBallTook;
        this.gameHeight = gameHeight;
        this.row = row;
        this.col = col;
    }


    public final void update() {
        for (int y = 0; y < this.gameHeight; y++) {
            for (int x = y-(y*2); x < y; x++) {
                this.container.setItem(this.row+y, this.col+x, ItemBuilder.from(Theme.INACTIVE_PLINKO_MATERIAL)
                                        .name(Component.text(" "))
                                        .asGuiItem((player, context) -> {
                                            
                                        })
                );
            }
        }
    }


    public void setContainer(GuiContainer<Player, ItemStack> container) {
        this.container = container;
    }

    public GuiContainer<Player, ItemStack> getContainer() {
        return this.container;
    }
}
