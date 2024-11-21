package com.albertdayoung.allgamblingandcasino.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import com.albertdayoung.allgamblingandcasino.gui.components.RouletteBetEvenOdds;
import com.albertdayoung.allgamblingandcasino.gui.components.RouletteBetGroups;
import com.albertdayoung.allgamblingandcasino.gui.components.RouletteBetHalfs;
import com.albertdayoung.allgamblingandcasino.gui.components.RouletteBetMultipliers;
import com.albertdayoung.allgamblingandcasino.gui.components.RouletteBetNumbers;
import com.albertdayoung.allgamblingandcasino.gui.components.RouletteBetRedBlack;
import com.albertdayoung.allgamblingandcasino.roulette.RouletteGame;

import dev.triumphteam.gui.paper.Gui;
import net.kyori.adventure.text.Component;

public class PlayRouletteMain implements Listener {
    public static void open(Player _player) {
        RouletteGame game = new RouletteGame(_player, 10);
        Gui.of(6)
            .title(Component.text("Bet on Roulette"))
            .component(component -> {

                final var betMultiplierValue = component.remember(1);
                game.setBetMultiplier(betMultiplierValue);

                component.render(container -> {
                    RouletteBetNumbers.invoke(container, game);
                    RouletteBetMultipliers.invoke(container, game, betMultiplierValue);
                    RouletteBetGroups.invoke(container, game);
                    RouletteBetHalfs.invoke(container, game);
                    RouletteBetEvenOdds.invoke(container, game);
                    RouletteBetRedBlack.invoke(container, game);
                });

            })
            .build()
            .open(_player);
    }

    @EventHandler
    public void inventoryClose(InventoryCloseEvent event) {
        if (event.equals(this)) {
            MainCasino.open((Player) event.getPlayer());
        }
    }
}
