package com.albertdayoung.allgamblingandcasino.gui.components.buttons;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.gui.PlayerAccount;
import com.albertdayoung.allgamblingandcasino.utils.PlayerHead;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class PlayerAccountButton {
    

    public static void invoke(GuiContainer<Player, ItemStack> container, Player _player, int row, int column) {
        container.setItem(row, column, ItemBuilder.from(PlayerHead.getPlayerHead(_player.getUniqueId()))
                .name(Component.text("Casino Account"))
                .lore(Component.text("Check your account stats"))
                .asGuiItem((player, context) -> {
                    PlayerAccount.open(_player);
                })
        );
    }
}
