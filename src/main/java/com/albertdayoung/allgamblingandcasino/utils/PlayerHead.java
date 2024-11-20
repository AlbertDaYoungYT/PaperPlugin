package com.albertdayoung.allgamblingandcasino.utils;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerHead {
    public static ItemStack getPlayerHead(UUID uuid) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        headMeta.setOwningPlayer(Bukkit.getOfflinePlayer(uuid)); 
        head.setItemMeta(headMeta);
        return head;
    }
}
