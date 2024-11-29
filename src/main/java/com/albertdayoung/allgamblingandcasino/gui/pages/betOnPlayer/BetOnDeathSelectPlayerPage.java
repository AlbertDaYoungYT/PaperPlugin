package com.albertdayoung.allgamblingandcasino.gui.pages.betOnPlayer;



import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import static org.bukkit.Material.GOLD_BLOCK;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.PeakGambling;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.NextPreviousButtons;
import com.albertdayoung.allgamblingandcasino.gui.components.helpers.GuiContainerLayout;
import com.albertdayoung.allgamblingandcasino.utils.LeaderboardSort;
import com.albertdayoung.allgamblingandcasino.utils.PlayerHead;
import com.albertdayoung.allgamblingandcasino.utils.dataclasses.DeathOptionsData;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.Gui;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import dev.triumphteam.nova.MutableState;
import net.kyori.adventure.text.Component;

public class BetOnDeathSelectPlayerPage extends GuiContainerLayout {
    MutableState<Integer> pageNumber;
    MutableState<UUID> betTarget;
    DeathOptionsData betCause;
    Integer betAmount;
    GuiContainer<Player, ItemStack> selectPageContainer;
    LeaderboardSort sorter;
    List<String> players;

    Player guiPlayer;

    public static final Material SELECTED_MATERIAL = GOLD_BLOCK;

    public BetOnDeathSelectPlayerPage(DeathOptionsData betCause, Integer betAmount) {
        this.pageNumber = null;
        this.betTarget = null;
        this.betCause = betCause;
        this.betAmount = betAmount;
        this.sorter = new LeaderboardSort(Bukkit.getOfflinePlayers());

        this.guiPlayer = null;
    }



    public void open(Player _player) {
        this.guiPlayer = _player;
        Gui.of(2)
            .title(Component.text("Select a Player"))
            .component(component -> {

                this.pageNumber = component.remember(0);
                this.betTarget = component.remember(new UUID(0, 0));
                NextPreviousButtons navButtons = new NextPreviousButtons(pageNumber);

                component.render(container -> {
                    this.selectPageContainer = container;
                    navButtons.setContainer(container);
                    
                    showPlayerSelector(1, 1);
                    navButtons.invoke(2, 5);
                });

            })
            .build()
            .open(_player);
    }

    public final void showPlayerSelector(int row, int column) {
        
        this.players = this.sorter.sort();
        this.players.remove(this.guiPlayer.getUniqueId().toString());
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
                this.selectPageContainer.setItem(row, column, ItemBuilder.from(usedMaterial)
                        .name(Component.text(currentPlayer.getName()))
                        .asGuiItem((player, context) -> {
                            this.betTarget.update(previous -> currentPlayer.getUniqueId());
                        })
                );
                column++;
            }
        }

        this.selectPageContainer.setItem(2, 5, ItemBuilder.from(Material.GOLD_BLOCK)
                .name(Component.text(String.format("Bet on $%s", Bukkit.getServer().getPlayer(this.betTarget.get()))))
                .asGuiItem((player, context) -> {
                    if (this.betTarget.get() != new UUID(0, 0)) {
                        PeakGambling.deathBets.placeBet(player.getUniqueId(), this.betTarget.get(), this.betCause.getCauseOptions(), this.betAmount);
                        PeakGambling.getEconomy().withdrawPlayer(player, this.betAmount);
                        
                        player.closeInventory();
                    }
                })
        );
    }
}
