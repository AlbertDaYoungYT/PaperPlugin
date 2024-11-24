package com.albertdayoung.allgamblingandcasino.gui.components.stat;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.PeakGambling;
import com.albertdayoung.allgamblingandcasino.utils.LeaderboardSort;
import com.albertdayoung.allgamblingandcasino.utils.PlayerHead;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import dev.triumphteam.nova.MutableState;
import net.kyori.adventure.text.Component;

public class LeaderboardPlayerStat {
    MutableState<Integer> pageNumber;
    GuiContainer<Player, ItemStack> container;
    LeaderboardSort sorter;
    List<String> players;

    public LeaderboardPlayerStat(MutableState<Integer> pageNumber) {
        this.pageNumber = pageNumber;
        this.sorter = new LeaderboardSort(Bukkit.getOfflinePlayers());
    }

    public final void invoke(int row, int column) {
        
        this.players = this.sorter.sort();
        int playerListLength = players.size();

        for (int playerIndex = 0+(this.pageNumber.get()*9); playerIndex < 9+(this.pageNumber.get()*9); playerIndex++) {
            if (playerIndex < playerListLength) {
                OfflinePlayer currentPlayer = Bukkit.getOfflinePlayer(UUID.fromString(this.players.get(playerIndex)));
                this.container.setItem(row, column, ItemBuilder.from(PlayerHead.getPlayerHead(currentPlayer.getUniqueId()))
                        .name(Component.text(currentPlayer.getName()))
                        .lore(Component.text(String.format("Balance $%s", PeakGambling.getEconomy().getBalance(currentPlayer))))
                        .asGuiItem((player, context) -> {})
                );
                column++;
            }
        }
    }

    public void setContainer(GuiContainer<Player, ItemStack> container) {
        this.container = container;
    }
}
