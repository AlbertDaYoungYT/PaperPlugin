package com.albertdayoung.allgamblingandcasino.gui;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.albertdayoung.allgamblingandcasino.gui.components.BlankingFill;
import com.albertdayoung.allgamblingandcasino.gui.components.BlankingFillWithType;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.BackToMainMenuButton;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.IncrementDecrementButtons;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.PlaceBetButton;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.PlaceBetOnPlayerDeathButton;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.PlaceBetOnPlayerDeathTypeButtons;
import com.albertdayoung.allgamblingandcasino.gui.components.helpers.GuiContainerLayout;
import com.albertdayoung.allgamblingandcasino.utils.dataclasses.BetOnPlayerDeathOptions;
import com.albertdayoung.allgamblingandcasino.utils.dataclasses.DeathOptionsData;

import dev.triumphteam.gui.paper.Gui;
import dev.triumphteam.nova.MutableState;
import net.kyori.adventure.text.Component;

public class PlaceBetOnDeathTypeMenu extends GuiContainerLayout {
    public static void open(Player _player, MutableState<Integer> betAmount) {
        BetOnPlayerDeathOptions deathOptions = new BetOnPlayerDeathOptions();
        
        Gui.of(6)
            .title(Component.text("Place a Bet"))
            .component(component -> {

                final var betDeathOption = component.remember(new DeathOptionsData(DamageCause.CUSTOM, Material.LIGHT_GRAY_STAINED_GLASS_PANE, " ", " "));
                PlaceBetOnPlayerDeathButton navBetButton = new PlaceBetOnPlayerDeathButton(betDeathOption);

                component.render(container -> {
                    navBetButton.setContainer(container);

                    BlankingFillWithType.invoke(container, Material.GRAY_STAINED_GLASS_PANE, 1, 9, 1, 6);
                    BlankingFill.invoke(container, 2, 8, 2, 5);

                    int i = 0;
                    for (int row = 2;row <= 4;row++) {
                        for (int column = 3;column <= 7;column++) {
                            DeathOptionsData deathOption = deathOptions.getCauseOptions().get(i);
                            PlaceBetOnPlayerDeathTypeButtons deathTypeButton = new PlaceBetOnPlayerDeathTypeButtons(container, betDeathOption, deathOption);
                            deathTypeButton.invoke(row, column);
                            i++;
                        }
                    }

                    navBetButton.invoke(_player, 6, 5);
                    BackToMainMenuButton.invoke(container, 6, 4);
                });

            })
            .build()
            .open(_player);
    }
}
