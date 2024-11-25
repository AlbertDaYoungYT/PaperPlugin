package com.albertdayoung.allgamblingandcasino.gui.pages;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.albertdayoung.allgamblingandcasino.PeakGambling;
import com.albertdayoung.allgamblingandcasino.gui.components.BlankingFill;
import com.albertdayoung.allgamblingandcasino.gui.components.TwoItemCasinoFill;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.BackToMainMenuButton;
import com.albertdayoung.allgamblingandcasino.gui.components.helpers.GuiContainerLayout;

import dev.triumphteam.gui.paper.Gui;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class PlayerAccount extends GuiContainerLayout {
    public static void open(Player _player) {
        String BALANCE = String.format("$%s", PeakGambling.getEconomy().getBalance(_player));
        String BALANCE_TEXT = String.format("[%s] - You have (%s) in your account", PeakGambling.PLUGIN, BALANCE);

        Gui.of(3)
            .title(Component.text(_player.getName() + "'s Account"))
            .statelessComponent(container -> {
                TwoItemCasinoFill.loopInvoke(container, 1, 9, 1, 3);
                BlankingFill.invoke(container, 2, 8, 2, 3);

                BackToMainMenuButton.invoke(container, 3, 5);

                container.setItem(2, 5, ItemBuilder.from(Material.PAPER)
                        .name(Component.text(BALANCE))
                        .asGuiItem((player, context) -> {
                            player.sendMessage(BALANCE_TEXT);
                        })
                );
            })
            .build()
            .open(_player);
    }
}
