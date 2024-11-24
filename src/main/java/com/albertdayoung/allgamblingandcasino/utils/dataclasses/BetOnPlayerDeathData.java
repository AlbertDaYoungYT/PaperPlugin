package com.albertdayoung.allgamblingandcasino.utils.dataclasses;

import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class BetOnPlayerDeathData {
    private OfflinePlayer betOwner;
    private UUID playerUuid;
    private DamageCause deathType;
    private double betAmount;

    public BetOnPlayerDeathData(){}

    public UUID getPlayerUUID(){
        return playerUuid;
    }
    public void setPlayerUUID(UUID playerUuid){
        this.playerUuid = playerUuid;
    }
    public DamageCause getDeathType(){
        return deathType;
    }
    public void setDeathType(DamageCause deathType){
        this.deathType = deathType;
    }

    public OfflinePlayer getBetOwner() {
        return betOwner;
    }

    public void setBetOwner(OfflinePlayer betOwner) {
        this.betOwner = betOwner;
    }

    public double getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(double betAmount) {
        this.betAmount = betAmount;
    }
}
