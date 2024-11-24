package com.albertdayoung.allgamblingandcasino.gui;

import org.bukkit.entity.Player;

import com.albertdayoung.allgamblingandcasino.gui.components.BlankingFill;
import com.albertdayoung.allgamblingandcasino.gui.components.TwoItemCasinoFill;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.BetOnPlayerDeathButton;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.CasinoPlayRouletteButton;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.LeaderboardButton;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.PlayerAccountButton;
import com.albertdayoung.allgamblingandcasino.gui.components.helpers.GuiContainerLayout;

import dev.triumphteam.gui.paper.Gui;
import net.kyori.adventure.text.Component;

public class MainCasino extends GuiContainerLayout {
    public static void open(Player _player) {
        Gui.of(3)
            .title(Component.text("Casino"))
            .statelessComponent(container -> {
                TwoItemCasinoFill.loopInvoke(container, 2, 9, 1, 3);
                BlankingFill.invoke(container, 3, 8, 2, 2);

                PlayerAccountButton.invoke(container, _player, 1, 1);
                LeaderboardButton.invoke(container, _player, 2, 1);
                PlayerAccountButton.invoke(container, _player, 3, 1);

                CasinoPlayRouletteButton.invoke(container, _player, 2, 5);
                BetOnPlayerDeathButton.invoke(container, _player, 2, 6);
                
            })
            .build()
            .open(_player);
    }
}
