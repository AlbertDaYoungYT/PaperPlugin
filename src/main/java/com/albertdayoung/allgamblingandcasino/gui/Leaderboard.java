package com.albertdayoung.allgamblingandcasino.gui;



import org.bukkit.entity.Player;

import com.albertdayoung.allgamblingandcasino.gui.components.buttons.BackToMainMenuButton;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.NextPreviousButtons;
import com.albertdayoung.allgamblingandcasino.gui.components.helpers.GuiContainerLayout;
import com.albertdayoung.allgamblingandcasino.gui.components.stat.LeaderboardPlayerStat;

import dev.triumphteam.gui.paper.Gui;
import net.kyori.adventure.text.Component;

public class Leaderboard extends GuiContainerLayout {
    public static void open(Player _player) {
        Gui.of(2)
            .title(Component.text("Leaderboard"))
            .component(component -> {

                final var pageNumber = component.remember(0);
                NextPreviousButtons navButtons = new NextPreviousButtons(pageNumber);
                LeaderboardPlayerStat playerStat = new LeaderboardPlayerStat(pageNumber);

                component.render(container -> {
                    navButtons.setContainer(container);
                    playerStat.setContainer(container);
                
                    
                    playerStat.invoke(1, 1);
                    
                    BackToMainMenuButton.invoke(container, 2, 5);
                    navButtons.invoke(2, 5);
                });

            })
            .build()
            .open(_player);
    }
}
