package com.albertdayoung.allgamblingandcasino.gambling;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.albertdayoung.allgamblingandcasino.PeakGambling;
import com.albertdayoung.allgamblingandcasino.utils.dataclasses.BetOnPlayerDeathData;
import com.albertdayoung.allgamblingandcasino.utils.dataclasses.BetOnPlayerDeathDataList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;




public class BetOnPlayerDeath {
    @SerializedName("bets")
    BetOnPlayerDeathDataList bets;
    File deathBetsDataFile;

    
    public BetOnPlayerDeath(File deathBetsDataFile) {
        this.bets = new BetOnPlayerDeathDataList();
        this.deathBetsDataFile = deathBetsDataFile;

        this.load();
    }


    public void placeBet(UUID betOwner, UUID betTarget, DamageCause betDamageCause, double betAmount) {
        BetOnPlayerDeathData bet = new BetOnPlayerDeathData();
        bet.setBetOwner(betOwner);
        bet.setPlayerUUID(betTarget);
        bet.setDeathType(betDamageCause);
        bet.setBetAmount(betAmount);
        this.bets.add(bet);

        this.save();
    }

    public void removeBet(UUID betOwner, UUID betTarget) {
        BetOnPlayerDeathData bet = this.getBet(betTarget);
        if (bet != null) {
            this.bets.remove(bet);
            this.save();
        }
    }

    public BetOnPlayerDeathDataList getBets() {
        return bets;
    }

    public boolean isBetOnPlayer(UUID playerUuid) {
        for (BetOnPlayerDeathData bet : this.bets.getBets()) {
            if (bet.getPlayerUUID().equals(playerUuid)) {
                return true;
            }
        }
        return false;
    }

    public BetOnPlayerDeathData getBet(UUID playerUuid) {
        for (BetOnPlayerDeathData bet : this.bets.getBets()) {
            if (bet.getPlayerUUID().equals(playerUuid)) {
                return bet;
            }
        }
        return null;
    }

    public UUID getBetOwner(UUID playerUuid) {
        BetOnPlayerDeathData bet = this.getBet(playerUuid);
        return bet != null ? bet.getBetOwner() : null;
    }

    public DamageCause getBetDeathCause(UUID playerUuid) {
        BetOnPlayerDeathData bet = this.getBet(playerUuid);
        return bet != null ? bet.getDeathType() : null;
    }


    public void save() {
        Gson gson = new GsonBuilder().create();

        try (FileWriter writer = new FileWriter(deathBetsDataFile)) {
            gson.toJson(this.bets, writer);
        } catch (IOException e) {
            PeakGambling.LOGGER.warning("Error saving data file: " + e.getMessage());
        }
    }

    public void load() {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(deathBetsDataFile)) {
            ArrayList<BetOnPlayerDeathData> betData = gson.fromJson(reader, new TypeToken<ArrayList<BetOnPlayerDeathData>>() {}.getType());

            if (betData != null) {
                this.bets.addAll(betData);
                for (BetOnPlayerDeathData data : betData) {
                    PeakGambling.LOGGER.info(data.toString());
                }
            } else {
                // Handle the case where there's no data
            }
        } catch (IOException e) {
            PeakGambling.LOGGER.warning("Error loading data file: " + e.getMessage());
        }
    }
}
