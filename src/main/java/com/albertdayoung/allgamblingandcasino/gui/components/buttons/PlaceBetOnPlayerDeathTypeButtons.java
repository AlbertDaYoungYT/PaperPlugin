package com.albertdayoung.allgamblingandcasino.gui.components.buttons;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.albertdayoung.allgamblingandcasino.utils.dataclasses.DeathOptionsData;

import dev.triumphteam.gui.container.GuiContainer;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import dev.triumphteam.nova.MutableState;
import net.kyori.adventure.text.Component;

public class PlaceBetOnPlayerDeathTypeButtons {
    DeathOptionsData deathBet;
    MutableState<DeathOptionsData> deathChosenBet;
    GuiContainer<Player, ItemStack> container;

    public PlaceBetOnPlayerDeathTypeButtons(GuiContainer<Player, ItemStack> container, MutableState<DeathOptionsData> deathChosenBet, DeathOptionsData deathBet) {
        this.deathBet = deathBet;
        this.container = container;
        this.deathChosenBet = deathChosenBet;
    }


    public void invoke(int row, int column) {

        Material usedMaterial = this.deathBet.getCauseOptionsIcons();
        if (deathChosenBet.get().getCauseOptions().equals(deathBet.getCauseOptions())) {
            usedMaterial = Material.GRAY_WOOL;
        }

        this.container.setItem(row, column, ItemBuilder.from(usedMaterial)
                                                    .name(Component.text(this.deathBet.getCauseOptionsTitle()))
                                                    .lore(Component.text(this.deathBet.getCauseOptionsLore()))
                                                    .asGuiItem((player, context) -> {
                                                        this.deathChosenBet.update(prevous -> deathBet);
                                                    })
                                        );
    }
}
