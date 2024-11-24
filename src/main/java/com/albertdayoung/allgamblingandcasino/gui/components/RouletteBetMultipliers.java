package com.albertdayoung.allgamblingandcasino.gui.components;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.gambling.RouletteGame;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import dev.triumphteam.nova.MutableState;
import net.kyori.adventure.text.Component;

public class RouletteBetMultipliers {
    
    public static final Material MULTIPLIERSTAT_MATERIAL = Material.GOLD_BLOCK;
    public static final Material PLUSMINUS_MATERIAL = Material.ARROW;

    public static final void invoke(GuiContainer<Player, ItemStack> container, RouletteGame game, MutableState<Integer> betMultiplierValue) {
        container.setItem(6, 5, ItemBuilder.from(MULTIPLIERSTAT_MATERIAL)
                                                    .name(Component.text(String.format("Bet %s time(s)", betMultiplierValue.get())))
                                                    .amount(betMultiplierValue.get())
                                                    .asGuiItem((player, context) -> {
                                                        if (game.getBets().isEmpty()) {
                                                            player.sendMessage("You haven't bet on anything");
                                                        } else {
                                                            game.roll();
                                                            player.sendMessage(
                                                                game.getBets().toString() + ", " + 
                                                                game.getBetMultipliers().toString()
                                                                );
                                                            game.reset();

                                                        }
                                                    })
                                        );
        container.setItem(6, 4, ItemBuilder.from(PLUSMINUS_MATERIAL)
                                                    .name(Component.text("Decrement bet multiplier"))
                                                    .asGuiItem((player, context) -> {
                                                        game.decrementBetMultiplier(1);
                                                    })
                                        );
        container.setItem(6, 6, ItemBuilder.from(PLUSMINUS_MATERIAL)
                                                    .name(Component.text("Increment bet multiplier"))
                                                    .asGuiItem((player, context) -> {
                                                        game.incrementBetMultiplier(1);
                                                    })
                                        );

    }
}
