package com.albertdayoung.allgamblingandcasino.gui;

import org.bukkit.entity.Player;

import com.albertdayoung.allgamblingandcasino.gui.components.buttons.IncrementDecrementButtons;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.PlaceBetButton;
import com.albertdayoung.allgamblingandcasino.gui.components.helpers.GuiContainerLayout;

import dev.triumphteam.gui.paper.Gui;
import net.kyori.adventure.text.Component;

public class PlaceBetOnPlayerDeath extends GuiContainerLayout {
    public static void open(Player _player) {
        Gui.of(1)
            .title(Component.text("Place a Bet"))
            .component(component -> {

                final var betAmount = component.remember(10);
                PlaceBetButton navBetButtons = new PlaceBetButton(betAmount);
                IncrementDecrementButtons navIncDecButtons = new IncrementDecrementButtons(betAmount);

                component.render(container -> {
                    navBetButtons.setContainer(container);
                    navIncDecButtons.setContainer(container);
                
                    navIncDecButtons.invoke(_player, 1, 1);
                    navBetButtons.invoke(_player, 1, 5);
                });

            })
            .build()
            .open(_player);
    }
}
