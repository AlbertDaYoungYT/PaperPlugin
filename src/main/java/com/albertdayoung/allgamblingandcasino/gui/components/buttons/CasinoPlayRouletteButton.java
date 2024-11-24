package com.albertdayoung.allgamblingandcasino.gui.components.buttons;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.PeakGambling;
import com.albertdayoung.allgamblingandcasino.gui.PlayRouletteMain;

import dev.triumphteam.gui.click.ClickContext;
import dev.triumphteam.gui.click.action.RunnableGuiClickAction;
import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class CasinoPlayRouletteButton {
    

    public static void invoke(GuiContainer<Player, ItemStack> container, Player _player, int row, int column) {
        container.setItem(row, column, ItemBuilder.from(Material.MUSIC_DISC_CHIRP)
                .name(Component.text("Play Roulette (UNFINISHED)"))
                .lore(Component.text(" "))
                .asGuiItem(new RunnableGuiClickAction<Player>() {
                    @Override
                    public void run(Player player, ClickContext context) {
                        double playerBalance = PeakGambling.getEconomy().getBalance(player);
                        if (playerBalance > 0.0) {
                            PlayRouletteMain.open(_player);
                            player.sendMessage("Roulette is still in development!");
                        } else {
                            player.sendMessage("You don't have enough money to gamble!");
                        }
                    }
                })
        );
    }
}
