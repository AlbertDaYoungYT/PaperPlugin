package com.albertdayoung.allgamblingandcasino.utils.dataclasses;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.google.gson.annotations.SerializedName;

public class BetOnPlayerDeathDataList {
    @SerializedName("bets")
    private ArrayList<BetOnPlayerDeathData> bets;


    public BetOnPlayerDeathDataList(){
        this.bets = new ArrayList<>();
    }


    public ArrayList<BetOnPlayerDeathData> getBets() {
        return bets;
    }

    public void setBets(ArrayList<BetOnPlayerDeathData> bets) {
        this.bets = bets;
    }

    public void remove(int i) {
        this.bets.remove(i);
    }

    public void remove(BetOnPlayerDeathData bet) {
        this.bets.remove(bet);
    }

    public void add(BetOnPlayerDeathData bet) {
        this.bets.add(bet);
    }

    public void addAll(List<BetOnPlayerDeathData> bets) {
        this.bets.addAll(bets);
    }
}
