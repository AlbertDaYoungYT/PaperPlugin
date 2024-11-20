package com.albertdayoung.allgamblingandcasino.gui;

import org.bukkit.entity.Player;

import com.albertdayoung.allgamblingandcasino.gui.components.BlankingFill;
import com.albertdayoung.allgamblingandcasino.gui.components.TwoItemCasinoFill;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.CasinoPlayRouletteButton;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.PlayerCasinoAccountButton;

import dev.triumphteam.gui.paper.Gui;
import net.kyori.adventure.text.Component;

public class MainCasino {
    public static void open(Player _player) {
        Gui.of(3)
            .title(Component.text("Casino"))
            .statelessComponent(container -> {
                TwoItemCasinoFill.loopInvoke(container, 1, 9, 1, 3);
                BlankingFill.invoke(container, 2, 8, 2, 2);

                PlayerCasinoAccountButton.invoke(container, _player, 2, 3);
                CasinoPlayRouletteButton.invoke(container, _player, 2, 5);
                
            })
            .build()
            .open(_player);
    }
}
