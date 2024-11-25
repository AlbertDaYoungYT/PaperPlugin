package com.albertdayoung.allgamblingandcasino.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.PeakGambling;
import com.albertdayoung.allgamblingandcasino.gui.components.BlankingFill;
import com.albertdayoung.allgamblingandcasino.gui.components.TwoItemCasinoFill;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.PlayerAccountButton;
import com.albertdayoung.allgamblingandcasino.gui.components.helpers.GuiContainerLayout;
import com.albertdayoung.allgamblingandcasino.gui.pages.Leaderboard;
import com.albertdayoung.allgamblingandcasino.gui.pages.PlayerAccount;
import com.albertdayoung.allgamblingandcasino.utils.PlayerHead;

import dev.triumphteam.gui.click.ClickContext;
import dev.triumphteam.gui.click.action.RunnableGuiClickAction;
import dev.triumphteam.gui.component.functional.FunctionalGuiComponentRender;
import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.Gui;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class MainCasino extends GuiContainerLayout {
    public static void open(Player _player) {
        Gui.of(3)
            .title(Component.text("Casino"))
            .statelessComponent(new FunctionalGuiComponentRender<Player, ItemStack>() {
            @Override
            public void render(GuiContainer<Player, ItemStack> container) {
                TwoItemCasinoFill.loopInvoke(container, 2, 9, 1, 3);
                BlankingFill.invoke(container, 3, 8, 2, 2);

                openPlayerAccount(container, _player, 1, 1);
                openLeaderboard(container, _player, 2, 1);
                openPlayerAccount(container, _player, 3, 1);

                playRouletteButton(container, _player, 2, 5);
                playBetOnDeaths(container, _player, 2, 6);
            }
        })
            .build()
            .open(_player);
    }

    public static void playRouletteButton(GuiContainer<Player, ItemStack> container, Player _player, int row, int column) {
        container.setItem(row, column, ItemBuilder.from(Material.MUSIC_DISC_CHIRP)
                .name(Component.text("Play Roulette (UNFINISHED)"))
                .lore(Component.text(" "))
                .asGuiItem((Player player, ClickContext context) -> {
                    double playerBalance = PeakGambling.getEconomy().getBalance(player);
                    if (playerBalance > 0.0) {
                        MainRoulette.open(_player);
                        player.sendMessage("Roulette is still in development!");
                    } else {
                        player.sendMessage("You don't have enough money to gamble!");
                    }
        })
        );
    }

    public static void playBetOnDeaths(GuiContainer<Player, ItemStack> container, Player _player, int row, int column) {
        container.setItem(row, column, ItemBuilder.from(Material.PLAYER_HEAD)
                .name(Component.text("Place a bet on Player Death"))
                .lore(Component.text(" "))
                .asGuiItem((Player player, ClickContext context) -> {
                        double playerBalance = PeakGambling.getEconomy().getBalance(player);
                        if (playerBalance > 0.0) { 
                            MainBetOnDeath betOnDeathGui = new MainBetOnDeath();
                            betOnDeathGui.open(_player);
                        } else {
                            player.sendMessage("You don't have enough money to place a bet!");
                        }
                })
        );
    }

    public static void openLeaderboard(GuiContainer<Player, ItemStack> container, Player _player, int row, int column) {
        container.setItem(row, column, ItemBuilder.from(Material.OAK_SIGN)
                .name(Component.text("Check the Leaderboard"))
                .lore(Component.text("Leaderboard is currently unfinished!"))
                .asGuiItem((player, context) -> {
                    Leaderboard leaderboardGui = new Leaderboard();
                    leaderboardGui.open(_player);
                })
        );
    }

    public static void openPlayerAccount(GuiContainer<Player, ItemStack> container, Player _player, int row, int column) {
        container.setItem(row, column, ItemBuilder.from(PlayerHead.getPlayerHead(_player.getUniqueId()))
                .name(Component.text("Casino Account"))
                .lore(Component.text("Check your account stats"))
                .asGuiItem((player, context) -> {
                    PlayerAccount.open(_player);
                })
        );
    }
}
