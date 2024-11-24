package com.albertdayoung.allgamblingandcasino.gui;

import org.bukkit.entity.Player;

import com.albertdayoung.allgamblingandcasino.gambling.RouletteGame;
import com.albertdayoung.allgamblingandcasino.gui.components.RouletteBetEvenOdds;
import com.albertdayoung.allgamblingandcasino.gui.components.RouletteBetGroups;
import com.albertdayoung.allgamblingandcasino.gui.components.RouletteBetHalfs;
import com.albertdayoung.allgamblingandcasino.gui.components.RouletteBetMultipliers;
import com.albertdayoung.allgamblingandcasino.gui.components.RouletteBetNumbers;
import com.albertdayoung.allgamblingandcasino.gui.components.RouletteBetRedBlack;
import com.albertdayoung.allgamblingandcasino.gui.components.helpers.GuiContainerLayout;

import dev.triumphteam.gui.paper.Gui;
import dev.triumphteam.nova.ListState;
import net.kyori.adventure.text.Component;

public class PlayRouletteMain extends GuiContainerLayout {
    public static void open(Player _player) {
        RouletteGame game = new RouletteGame(_player, 10);
        Gui.of(6)
            .title(Component.text("Bet on Roulette"))
            .component(component -> {

                final var betMultiplierValue = component.remember(1);
                final ListState<String> bets = component.rememberMutableList();
                game.setBetMultiplier(betMultiplierValue);
                game.setBets(bets);

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
}