package com.albertdayoung.allgamblingandcasino.gui.pages;



import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.PeakGambling;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.BackToMainMenuButton;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.NextPreviousButtons;
import com.albertdayoung.allgamblingandcasino.gui.components.helpers.GuiContainerLayout;
import com.albertdayoung.allgamblingandcasino.utils.LeaderboardSort;
import com.albertdayoung.allgamblingandcasino.utils.PlayerHead;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.Gui;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import dev.triumphteam.nova.MutableState;
import net.kyori.adventure.text.Component;

public class Leaderboard extends GuiContainerLayout {
    MutableState<Integer> pageNumber;
    GuiContainer<Player, ItemStack> leaderboardContainer;
    LeaderboardSort sorter;
    List<String> players;

    public Leaderboard() {
        this.sorter = new LeaderboardSort(Bukkit.getOfflinePlayers());
    }


    public void open(Player _player) {
        Gui.of(2)
            .title(Component.text("Leaderboard"))
            .component(component -> {

                this.pageNumber = component.remember(0);
                NextPreviousButtons navButtons = new NextPreviousButtons(this.pageNumber);

                component.render(container -> {
                    this.leaderboardContainer = container;
                    navButtons.setContainer(container);
                
                    
                    showLeaderboard(1, 1);
                                        
                    BackToMainMenuButton.invoke(container, 2, 5);
                    navButtons.invoke(2, 5);
                });

            })
            .build()
            .open(_player);
    }

    public void showLeaderboard(int row, int column) {
        
        this.players = this.sorter.sort();
        int playerListLength = players.size();

        for (int playerIndex = 0+(this.pageNumber.get()*9); playerIndex < 9+(this.pageNumber.get()*9); playerIndex++) {
            if (playerIndex < playerListLength) {
                OfflinePlayer currentPlayer = Bukkit.getOfflinePlayer(UUID.fromString(this.players.get(playerIndex)));
                this.leaderboardContainer.setItem(row, column, ItemBuilder.from(PlayerHead.getPlayerHead(currentPlayer.getUniqueId()))
                        .name(Component.text(currentPlayer.getName()))
                        .lore(Component.text(String.format("Balance $%s", PeakGambling.getEconomy().getBalance(currentPlayer))))
                        .asGuiItem((player, context) -> {})
                );
                column++;
            }
        }
    }
}
