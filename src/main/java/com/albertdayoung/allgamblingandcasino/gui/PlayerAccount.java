package com.albertdayoung.allgamblingandcasino.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import com.albertdayoung.allgamblingandcasino.gui.components.BlankingFill;
import com.albertdayoung.allgamblingandcasino.gui.components.TwoItemCasinoFill;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.BackToMainMenuButton;
import com.albertdayoung.allgamblingandcasino.gui.components.stat.PlayerCasinoBalanceStat;

import dev.triumphteam.gui.paper.Gui;
import net.kyori.adventure.text.Component;

public class PlayerAccount implements Listener {
    public static void open(Player _player) {
        Gui.of(3)
            .title(Component.text(_player.getName() + "'s Account"))
            .statelessComponent(container -> {
                TwoItemCasinoFill.loopInvoke(container, 1, 9, 1, 3);
                BlankingFill.invoke(container, 2, 8, 2, 3);

                BackToMainMenuButton.invoke(container, 3, 5);
                PlayerCasinoBalanceStat.invoke(container, _player, 2, 5);
            })
            .build()
            .open(_player);
    }

    @EventHandler
    public void inventoryClose(InventoryCloseEvent event) {
        if (event.getInventory().equals(this)) {
            MainCasino.open((Player) event.getPlayer());
        }
    }
}
