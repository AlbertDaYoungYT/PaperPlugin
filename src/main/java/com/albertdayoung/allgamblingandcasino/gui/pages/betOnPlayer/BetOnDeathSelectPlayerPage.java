package com.albertdayoung.allgamblingandcasino.gui.pages.betOnPlayer;



import java.util.ArrayList;
import java.util.Arrays;
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
import static com.albertdayoung.allgamblingandcasino.gui.components.theme.Theme.defaultChatMessage;
import com.albertdayoung.allgamblingandcasino.utils.PlayerHead;
import com.albertdayoung.allgamblingandcasino.utils.dataclasses.DeathOptionsData;

import dev.triumphteam.gui.click.ClickContext;
import dev.triumphteam.gui.click.action.RunnableGuiClickAction;
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
    List<OfflinePlayer> players;

    Player guiPlayer;

    public static final Material SELECTED_MATERIAL = GOLD_BLOCK;

    public BetOnDeathSelectPlayerPage(DeathOptionsData betCause, Integer betAmount) {
        this.pageNumber = null;
        this.betTarget = null;
        this.betCause = betCause;
        this.betAmount = betAmount;

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
        OfflinePlayer[] offlinePlayersArray = Bukkit.getOfflinePlayers();

        // Convert the array to a List for easy sorting and modification
        this.players = new ArrayList<>(Arrays.asList(offlinePlayersArray));

        // Remove your own player from the list if it exists
        UUID yourUUID = this.guiPlayer.getUniqueId();
        removeYourselfFromPlayerList(yourUUID);
        removeOfflinePlayersFromPlayerList();

        // Sort the list alphabetically by name
        sortPlayersByName(this.players);

        int playerListLength = this.players.size();

        for (int playerIndex = 0 + (this.pageNumber.get() * 9);
            playerIndex < 9 + (this.pageNumber.get() * 9);
            playerIndex++) {
            if (playerIndex < playerListLength) {
                OfflinePlayer currentPlayer = this.players.get(playerIndex);
                selectPlayerItem(row, column++, currentPlayer);
            }
        }

        // Add the "Bet" button
        addBetButton(2, 5);
    }

    private void removeYourselfFromPlayerList(UUID yourUUID) {
        this.players.removeIf(player -> player.getUniqueId().equals(yourUUID));
        this.players.removeIf(player -> player.getName().equals(Bukkit.getPlayer(yourUUID).getName()));
    }

    private void removeOfflinePlayersFromPlayerList() {
        this.players.removeIf(player -> player.isOnline() == false);
    }

    private void sortPlayersByName(List<OfflinePlayer> players) {
        players.sort((player1, player2) ->
            player1.getName().compareTo(player2.getName())
        );
    }

    private void selectPlayerItem(int row, int column, OfflinePlayer currentPlayer) {
        ItemStack itemStack = PlayerHead.getPlayerHead(currentPlayer.getUniqueId());
        if (currentPlayer.getUniqueId().equals(this.betTarget.get())) {
            itemStack = new ItemStack(Material.GRAY_WOOL);
        }

        this.selectPageContainer.setItem(row, column, ItemBuilder.from(itemStack)
                .name(Component.text(currentPlayer.getName()))
                .asGuiItem((player, context) -> {
                    this.betTarget.update(previous -> currentPlayer.getUniqueId());
                })
        );
    }

    private void addBetButton(int row, int column) {
        this.selectPageContainer.setItem(row, column, ItemBuilder.from(Material.GOLD_BLOCK)
                .name(Component.text(String.format("Bet on $%s", Bukkit.getServer().getPlayer(Bukkit.getServer().getOfflinePlayer(this.betTarget.get()).getName() != null ? Bukkit.getServer().getOfflinePlayer(this.betTarget.get()).getName() : "Unknown Player"))))
                .asGuiItem(new RunnableGuiClickAction<Player>() {
            @Override
            public void run(Player player, ClickContext context) {
                if (!(BetOnDeathSelectPlayerPage.this.betTarget.get().toString().equals("00000000-0000-0000-0000-000000000000"))) {
                    PeakGambling.deathBets.placeBet(player.getUniqueId(), BetOnDeathSelectPlayerPage.this.betTarget.get(), BetOnDeathSelectPlayerPage.this.betCause.getCauseOptions(), BetOnDeathSelectPlayerPage.this.betAmount);
                    PeakGambling.getEconomy().withdrawPlayer(player, BetOnDeathSelectPlayerPage.this.betAmount);

                    player.sendMessage(defaultChatMessage(String.format("You bet <black>(<red>$%s<black>)", BetOnDeathSelectPlayerPage.this.betAmount)));

                    player.closeInventory();
                } else {
                    player.sendMessage(defaultChatMessage("Please select a Player"));
                }
            }
        })
        );
    }
}
