package com.albertdayoung.allgamblingandcasino.gui.pages.betOnPlayer;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.albertdayoung.allgamblingandcasino.data.AllDeathOptions;
import com.albertdayoung.allgamblingandcasino.gui.components.BlankingFill;
import com.albertdayoung.allgamblingandcasino.gui.components.BlankingFillWithType;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.BackToMainMenuButton;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.PlaceBetOnPlayerDeathTypeButtons;
import com.albertdayoung.allgamblingandcasino.gui.components.helpers.GuiContainerLayout;
import com.albertdayoung.allgamblingandcasino.gui.components.theme.Theme;
import com.albertdayoung.allgamblingandcasino.utils.dataclasses.DeathOptionsData;

import dev.triumphteam.gui.paper.Gui;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import dev.triumphteam.nova.MutableState;
import net.kyori.adventure.text.Component;

public class BetOnDeathTypePage extends GuiContainerLayout {
    MutableState<DeathOptionsData> betCause;
    Integer betAmount;

    public BetOnDeathTypePage(Integer betAmount) {
        this.betCause = null;
        this.betAmount = betAmount;
    }


    public void open(Player _player) {
        AllDeathOptions deathOptions = new AllDeathOptions();
        
        Gui.of(6)
            .title(Component.text("Choose Death Type"))
            .component(component -> {

                this.betCause = component.remember(new DeathOptionsData(DamageCause.CUSTOM, Material.LIGHT_GRAY_STAINED_GLASS_PANE, " ", " "));

                component.render(container -> {

                    BlankingFillWithType.invoke(container, Material.GRAY_STAINED_GLASS_PANE, 1, 9, 1, 6);
                    BlankingFill.invoke(container, 2, 8, 2, 5);

                    int i = 0;
                    for (int row = 2;row <= 4;row++) {
                        for (int column = 3;column <= 7;column++) {
                            DeathOptionsData deathOption = deathOptions.getCauseOptions().get(i);
                            PlaceBetOnPlayerDeathTypeButtons deathTypeButton = new PlaceBetOnPlayerDeathTypeButtons(container, this.betCause, deathOption);
                            deathTypeButton.invoke(row, column);
                            i++;
                        }
                    }

                    container.setItem(6, 5, ItemBuilder.from(Theme.PRIMARY_BUTTON_MATERIAL)
                            .name(Component.text(String.format("Bet on (%s)", this.betCause.get().getCauseOptionsTitle())))
                            .asGuiItem((player, context) -> {
                                if (!this.betCause.get().getCauseOptions().name().equals("CUSTOM")) {
                                    BetOnDeathSelectPlayerPage selectPlayerPage = new BetOnDeathSelectPlayerPage(this.betCause.get(), this.betAmount);
                                    selectPlayerPage.open(_player);
                                }
                            })
                    );
                    BackToMainMenuButton.invoke(container, 6, 4);
                });

            })
            .build()
            .open(_player);
    }
}
