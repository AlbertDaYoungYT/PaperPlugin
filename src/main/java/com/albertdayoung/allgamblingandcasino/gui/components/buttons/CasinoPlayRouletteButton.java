package com.albertdayoung.allgamblingandcasino.gui.components.buttons;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.PaperPlugin;
import com.albertdayoung.allgamblingandcasino.gui.PlayRouletteMain;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import net.kyori.adventure.text.Component;

public class CasinoPlayRouletteButton {
    

    public static final void invoke(GuiContainer<Player, ItemStack> container, Player _player, int row, int column) {
        container.setItem(row, column, ItemBuilder.from(Material.MUSIC_DISC_CHIRP)
                .name(Component.text(PaperPlugin.mainConfig.getString("translation.gui.casino-play-roulette-button-title")))
                .lore(Component.text(PaperPlugin.mainConfig.getString("translation.gui.casino-play-roulette-button-lore")))
                .asGuiItem((player, context) -> {
                    PlayRouletteMain.open(_player);
                })
        );
    }
}
