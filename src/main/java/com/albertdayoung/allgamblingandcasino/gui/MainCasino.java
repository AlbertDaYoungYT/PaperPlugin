package com.albertdayoung.allgamblingandcasino.gui;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.gui.components.FourRowCasinoBorder;

import dev.triumphteam.gui.layout.GuiLayout;
import dev.triumphteam.gui.paper.Gui;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import dev.triumphteam.gui.slot.Slot;
import net.kyori.adventure.text.Component;

public class MainCasino {
    public static final Gui MAINCASINOMEN_GUI = Gui.of(3)
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
