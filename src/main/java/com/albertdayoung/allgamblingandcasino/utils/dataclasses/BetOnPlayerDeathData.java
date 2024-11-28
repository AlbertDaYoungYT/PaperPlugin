package com.albertdayoung.allgamblingandcasino.utils.dataclasses;

import java.util.UUID;

import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.google.gson.annotations.SerializedName;

public class BetOnPlayerDeathData {
    @SerializedName("betOwner")
    private UUID betOwner;

    @SerializedName("playerUuid")
    private UUID playerUuid;
    
    @SerializedName("deathType")
    private DamageCause deathType;
    
    @SerializedName("betAmount")
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

    public UUID getBetOwner() {
        return betOwner;
    }

    public void setBetOwner(UUID betOwner) {
        this.betOwner = betOwner;
    }

    public double getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(double betAmount) {
        this.betAmount = betAmount;
    }
}
