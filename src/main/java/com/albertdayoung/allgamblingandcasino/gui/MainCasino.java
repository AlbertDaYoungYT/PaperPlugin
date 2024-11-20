package com.albertdayoung.allgamblingandcasino.gui;

import org.bukkit.Material;

import com.albertdayoung.allgamblingandcasino.gui.components.FourRowCasinoBorder;

import dev.triumphteam.gui.paper.Gui;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class MainCasino {
    public static final Gui MAINCASINO_GUI = Gui.of(3)
        .title(Component.text("Casino"))
        .statelessComponent(container -> { // We use stateless since we don't need any updates for this example
            FourRowCasinoBorder.loopInvoke(container);
            container.setItem(2, 5, ItemBuilder.from(Material.PLAYER_HEAD)
                    .name(Component.text("Click me!"))
                    .asGuiItem((player, context) -> {
                        player.sendMessage("You have clicked on the diamond item!");
                    })
            );
        })
        .build();

}
