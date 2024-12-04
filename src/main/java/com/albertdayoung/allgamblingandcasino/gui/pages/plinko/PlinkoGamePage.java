package com.albertdayoung.allgamblingandcasino.gui.pages.plinko;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.albertdayoung.allgamblingandcasino.gambling.PlinkoGame;
import com.albertdayoung.allgamblingandcasino.gui.components.BlankingFill;
import com.albertdayoung.allgamblingandcasino.gui.components.BlankingFillWithType;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.BackToMainMenuButton;
import com.albertdayoung.allgamblingandcasino.gui.components.theme.Theme;

import dev.triumphteam.gui.paper.Gui;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class PlinkoGamePage {
    Integer betAmount;

    public PlinkoGamePage(Integer betAmount) {
        this.betAmount = betAmount;
    }
    
    public void open(Player _player) {

        PlinkoGame game = new PlinkoGame();
        
        Gui.of(6)
            .title(Component.text("Plinko:"))
            .component(component -> {
                
                final var stepsTheBallTook = component.remember(new ArrayList<Integer>());

                component.render(container -> {

                    BlankingFillWithType.invoke(container, Material.GRAY_STAINED_GLASS_PANE, 1, 9, 1, 6);
                    BlankingFill.invoke(container, 2, 8, 2, 5);

                    container.setItem(6, 5, ItemBuilder.from(Theme.PRIMARY_BUTTON_MATERIAL)
                            .name(Component.text("Drop ball"))
                            .asGuiItem((player, context) -> {
                                
                            })
                    );
                    BackToMainMenuButton.invoke(container, 6, 4);
                });

            })
            .build()
            .open(_player);
    }
}
