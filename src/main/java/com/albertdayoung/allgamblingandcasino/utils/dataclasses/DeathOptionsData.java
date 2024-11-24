package com.albertdayoung.allgamblingandcasino.utils.dataclasses;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class DeathOptionsData {
    DamageCause causeOptions;
    Material causeOptionsIcons;
    String causeOptionsTitle;
    String causeOptionsLore;

    public DeathOptionsData(DamageCause causeOptions, Material causeOptionsIcons, String causeOptionsTitle, String causeOptionsLore) {
        this.causeOptions = causeOptions;
        this.causeOptionsIcons = causeOptionsIcons;
        this.causeOptionsTitle = causeOptionsTitle;
        this.causeOptionsLore = causeOptionsLore;
    }

    public DamageCause getCauseOptions() {
        return causeOptions;
    }

    public void setCauseOptions(DamageCause causeOptions) {
        this.causeOptions = causeOptions;
    }

    public Material getCauseOptionsIcons() {
        return causeOptionsIcons;
    }

    public void setCauseOptionsIcons(Material causeOptionsIcons) {
        this.causeOptionsIcons = causeOptionsIcons;
    }

    public String getCauseOptionsTitle() {
        return causeOptionsTitle;
    }

    public void setCauseOptionsTitle(String causeOptionsTitle) {
        this.causeOptionsTitle = causeOptionsTitle;
    }

    public String getCauseOptionsLore() {
        return causeOptionsLore;
    }

    public void setCauseOptionsLore(String causeOptionsLore) {
        this.causeOptionsLore = causeOptionsLore;
    }
}
