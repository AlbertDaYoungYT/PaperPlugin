package com.albertdayoung.allgamblingandcasino.gui;

import org.bukkit.entity.Player;

import com.albertdayoung.allgamblingandcasino.gui.components.RouletteBetEvenOdds;
import com.albertdayoung.allgamblingandcasino.gui.components.RouletteBetGroups;
import com.albertdayoung.allgamblingandcasino.gui.components.RouletteBetHalfs;
import com.albertdayoung.allgamblingandcasino.gui.components.RouletteBetMultipliers;
import com.albertdayoung.allgamblingandcasino.gui.components.RouletteBetNumbers;
import com.albertdayoung.allgamblingandcasino.gui.components.RouletteBetRedBlack;

import dev.triumphteam.gui.paper.Gui;
import net.kyori.adventure.text.Component;

public class PlayRouletteMain {
    public static void open(Player _player) {
        Gui.of(6)
            .title(Component.text("Roulette"))
            .statelessComponent(container -> {
                RouletteBetNumbers.invoke(container);
                RouletteBetMultipliers.invoke(container);
                RouletteBetGroups.invoke(container);
                RouletteBetHalfs.invoke(container);
                RouletteBetEvenOdds.invoke(container);
                RouletteBetRedBlack.invoke(container);

            })
            .build()
            .open(_player);
    }
}
