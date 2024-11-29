package com.albertdayoung.allgamblingandcasino.gui.components.theme;

import org.bukkit.Material;

import com.albertdayoung.allgamblingandcasino.PeakGambling;

import net.kyori.adventure.text.Component;

public class Theme {
    public static final Material PRIMARY_BUTTON_MATERIAL      = Material.GOLD_BLOCK;
    public static final Material SECONDARY_BUTTON_MATERIAL    = Material.IRON_BLOCK;

    public static final Material BACK_BUTTON_MATERIAL         = Material.ARROW;
    public static final Material NEXT_BUTTON_MATERIAL         = Material.ARROW;
    
    public static final Material BACK_TO_HOME_BUTTON_MATERIAL = Material.RED_BED;

    public static final Material SELECTED_ITEM_MATERIAL       = Material.GRAY_WOOL;
    public static final Material ACTIVE_ITEM_MATERIAL         = Material.GREEN_WOOL;
    public static final Material INACTIVE_ITEM_MATERIAL       = Material.GRAY_WOOL;


    public static Component defaultChatMessage(Object arg) {
        return PeakGambling.miniMessage.deserialize(String.format("[<dark_green>%s<white>]: <dark_green>%s", PeakGambling.PLUGIN, arg));
    }
}
