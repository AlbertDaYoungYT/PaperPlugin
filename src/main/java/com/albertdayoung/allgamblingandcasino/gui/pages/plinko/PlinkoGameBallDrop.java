package com.albertdayoung.allgamblingandcasino.gui.pages.plinko;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.gui.components.theme.Theme;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import dev.triumphteam.nova.MutableState;
import net.kyori.adventure.text.Component;

public class PlinkoGameBallDrop implements Runnable {
    MutableState<ArrayList<Integer>> stepsTheBallTook;
    GuiContainer<Player, ItemStack> container;
    int currentBallX;
    int currentBallY;
    int gameHeight;

    public PlinkoGameBallDrop(GuiContainer<Player, ItemStack> container, MutableState<ArrayList<Integer>> stepsTheBallTook, int currentBallX, int currentBallY, int gameHeight) {
        this.stepsTheBallTook = stepsTheBallTook;
        this.currentBallX = currentBallX;
        this.currentBallY = currentBallY;
        this.gameHeight = gameHeight;
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.gameHeight; i++) {
            this.currentBallX = this.currentBallX + this.stepsTheBallTook.get().get(i);
            this.currentBallY = this.currentBallY - 1;
    
            this.container.setItem(this.currentBallY, this.currentBallX, ItemBuilder.from(Theme.ACTIVE_PLINKO_MATERIAL)
                                    .name(Component.text("BALL!"))
                                    .asGuiItem((player, context) -> {
                                        
                                    })
            );
            try {
                wait(500);
            } catch (InterruptedException ex) {
            }
        }
    }
}
