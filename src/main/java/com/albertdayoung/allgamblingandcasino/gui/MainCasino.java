package com.albertdayoung.allgamblingandcasino.gui;

import org.bukkit.Material;

import dev.triumphteam.gui.paper.Gui;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class MainCasino {
    public static final Gui MAINCASINOMEN_GUI = Gui.of(1)
        .title(Component.text("My Simple GUI!"))
        .statelessComponent(container -> { // We use stateless since we don't need any updates for this example
            container.setItem(1, 5, ItemBuilder.from(Material.DIAMOND)
                    .name(Component.text("Click me!"))
                    .asGuiItem((player, context) -> {
                        player.sendMessage("You have clicked on the diamond item!");
                    })
            );
        })
        .build();
}
