package com.albertdayoung.allgamblingandcasino.utils.dataclasses;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class BetOnPlayerDeathOptions {
    List<DeathOptionsData> causeOptions = new ArrayList<>();


    public BetOnPlayerDeathOptions() {
        causeOptions.add(new DeathOptionsData(DamageCause.BLOCK_EXPLOSION,  Material.TNT,                   "Death by Explosion",           "Deaths by Placed TNT"));
        causeOptions.add(new DeathOptionsData(DamageCause.DROWNING,         Material.WATER_BUCKET,          "Death by Drowning",            "Deaths by Player Drowning"));
        causeOptions.add(new DeathOptionsData(DamageCause.ENTITY_ATTACK,    Material.IRON_SWORD,            "Death by Entity Attack",       "Deaths by an Entity Attack"));
        causeOptions.add(new DeathOptionsData(DamageCause.ENTITY_EXPLOSION, Material.CREEPER_HEAD,          "Death by Entity Explosion",    "Deaths by an Entity Explosion (Creepers)"));
        causeOptions.add(new DeathOptionsData(DamageCause.CONTACT,          Material.CACTUS,                "Death by Contact",             "Deaths by Block Contact (Cactus, Dripstone, Berry Bushes)"));
        causeOptions.add(new DeathOptionsData(DamageCause.FALL,             Material.FEATHER,               "Death by Fall",                "Deaths by Fall Damage"));
        causeOptions.add(new DeathOptionsData(DamageCause.FIRE,             Material.FIRE_CHARGE,           "Death by Fire",                "Deaths by Standing in Fire"));
        causeOptions.add(new DeathOptionsData(DamageCause.FIRE_TICK,        Material.FLINT_AND_STEEL,       "Death by Fire Tick",           "Deaths by Fire Tick"));
        //causeOptions.add(new DeathOptionsData(DamageCause.FLY_INTO_WALL,    Material.ELYTRA,                "Death by Kinetic Energy",      "Deaths by Flying into a Wall"));
        causeOptions.add(new DeathOptionsData(DamageCause.HOT_FLOOR,        Material.MAGMA_BLOCK,           "Death by Hot Floor",           "Deaths by Magma Blocks"));
        causeOptions.add(new DeathOptionsData(DamageCause.LAVA,             Material.LAVA_BUCKET,           "Death by Lava",                "Deaths by Lava"));
        causeOptions.add(new DeathOptionsData(DamageCause.MAGIC,            Material.POTION,                "Death by Magic",               "Deaths by Potions and Magic"));
        causeOptions.add(new DeathOptionsData(DamageCause.PROJECTILE,       Material.ARROW,                 "Death by Projectiles",         "Deaths by Arrows and other Projectiles"));
        causeOptions.add(new DeathOptionsData(DamageCause.SONIC_BOOM,       Material.WARDEN_SPAWN_EGG,      "Death by Sonic Boom",          "Deaths by Warden Sonic Boom"));
        causeOptions.add(new DeathOptionsData(DamageCause.STARVATION,       Material.COOKED_BEEF,           "Death by Starvation",          "Deaths by not Eating"));
        causeOptions.add(new DeathOptionsData(DamageCause.SUFFOCATION,      Material.SAND,                  "Death by Suffocation",         "Deaths by Suffocating in a Block"));
        causeOptions.add(new DeathOptionsData(DamageCause.WITHER,           Material.WITHER_SKELETON_SKULL, "Death by Wither Effect",       "Deaths by Wither Skeletons and Withers"));
    }


    public List<DeathOptionsData> getCauseOptions() {
        return causeOptions;
    }

    public void setCauseOptions(List<DeathOptionsData> causeOptions) {
        this.causeOptions = causeOptions;
    }
}
