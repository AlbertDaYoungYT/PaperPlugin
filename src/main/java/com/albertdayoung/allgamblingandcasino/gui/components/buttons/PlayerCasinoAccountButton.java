package com.albertdayoung.allgamblingandcasino.gui.components.buttons;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.PaperPlugin;
import com.albertdayoung.allgamblingandcasino.gui.PlayerAccount;
import com.albertdayoung.allgamblingandcasino.utils.PlayerHead;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class PlayerCasinoAccountButton {
    

    public static final void invoke(GuiContainer<Player, ItemStack> container, Player _player, int row, int column) {
        container.setItem(row, column, ItemBuilder.from(PlayerHead.getPlayerHead(_player.getUniqueId()))
                .name(Component.text(PaperPlugin.mainConfig.getString("translation.gui.casino-player-button-title")))
                .lore(Component.text(PaperPlugin.mainConfig.getString("translation.gui.casino-player-button-lore")))
                .asGuiItem((player, context) -> {
                    PlayerAccount.open(_player);
                })
        );
    }
}
