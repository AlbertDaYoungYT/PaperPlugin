package com.albertdayoung.allgamblingandcasino.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class MenuHolder implements InventoryHolder {
    
    private final Player player;

    public MenuHolder(Player player) {
         this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Inventory getInventory() {
        return null;
    }
}