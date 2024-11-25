package com.albertdayoung.allgamblingandcasino.gui.components.stat;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.PeakGambling;
import com.albertdayoung.allgamblingandcasino.utils.LeaderboardSort;
import com.albertdayoung.allgamblingandcasino.utils.PlayerHead;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import dev.triumphteam.nova.MutableState;
import net.kyori.adventure.text.Component;

public class SelectPlayerToBetOn {
    MutableState<Integer> pageNumber;
    MutableState<Double> betAmount;
    MutableState<UUID> betTarget;
    MutableState<DamageCause> betTargetReason;
    GuiContainer<Player, ItemStack> container;
    LeaderboardSort sorter;
    List<String> players;

    public static final Material SELECTED_MATERIAL = Material.GOLD_BLOCK;

    public SelectPlayerToBetOn(MutableState<Integer> pageNumber, MutableState<UUID> betTarget, MutableState<DamageCause> betTargetReason, MutableState<Double> betAmount) {
        this.betTarget = betTarget;
        this.betAmount = betAmount;
        this.pageNumber = pageNumber;
        this.betTargetReason = betTargetReason;
        this.sorter = new LeaderboardSort(Bukkit.getOfflinePlayers());
    }

    public final void invoke(int row, int column) {
        
        this.players = this.sorter.sort();
        int playerListLength = players.size();
        Material usedMaterial;

        for (int playerIndex = 0+(this.pageNumber.get()*9); playerIndex < 9+(this.pageNumber.get()*9); playerIndex++) {
            if (playerIndex < playerListLength) {
                OfflinePlayer currentPlayer = Bukkit.getOfflinePlayer(UUID.fromString(this.players.get(playerIndex)));
                if (currentPlayer.getUniqueId().equals(this.betTarget.get())) {
                    usedMaterial = Material.GRAY_WOOL;
                } else {
                    usedMaterial = PlayerHead.getPlayerHead(currentPlayer.getUniqueId()).getType();
                }
                this.container.setItem(row, column, ItemBuilder.from(usedMaterial)
                        .name(Component.text(currentPlayer.getName()))
                        .asGuiItem((player, context) -> {
                            this.betTarget.update(previous -> currentPlayer.getUniqueId());
                        })
                );
                column++;
            }
        }
        container.setItem(row, column, ItemBuilder.from(Material.GOLD_BLOCK)
                .name(Component.text(String.format("Bet on $%s", Bukkit.getServer().getPlayer(this.betTarget.get()))))
                .asGuiItem((player, context) -> {
                    PeakGambling.deathBets.placeBet(player, this.betTarget.get(), this.betTargetReason.get(), this.betAmount.get());
                    player.sendMessage(String.format("It works ($%s)", Bukkit.getServer().getPlayer(this.betTarget.get())));
                })
        );
    }

    public void setContainer(GuiContainer<Player, ItemStack> container) {
        this.container = container;
    }
}
