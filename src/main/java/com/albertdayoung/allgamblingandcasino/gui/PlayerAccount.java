package com.albertdayoung.allgamblingandcasino.gui;

import org.bukkit.entity.Player;

import com.albertdayoung.allgamblingandcasino.gui.components.BlankingFill;
import com.albertdayoung.allgamblingandcasino.gui.components.TwoItemCasinoFill;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.BackToMainMenuButton;
import com.albertdayoung.allgamblingandcasino.gui.components.helpers.GuiContainerLayout;
import com.albertdayoung.allgamblingandcasino.gui.components.stat.PlayerCasinoBalanceStat;

import dev.triumphteam.gui.paper.Gui;
import net.kyori.adventure.text.Component;

public class PlayerAccount extends GuiContainerLayout {
    public static void open(Player _player) {
        Gui.of(3)
            .title(Component.text(_player.getName() + "'s Account"))
            .statelessComponent(container -> {
                TwoItemCasinoFill.loopInvoke(container, 1, 9, 1, 3);
                BlankingFill.invoke(container, 2, 8, 2, 3);

                BackToMainMenuButton.invoke(container, 3, 5);
                PlayerCasinoBalanceStat.invoke(container, _player, 2, 5);
            })
            .build()
            .open(_player);
    }
}
