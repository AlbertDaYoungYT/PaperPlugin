package com.albertdayoung.allgamblingandcasino.gambling;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.albertdayoung.allgamblingandcasino.utils.dataclasses.BetOnPlayerDeathData;




public class BetOnPlayerDeath {
    List<BetOnPlayerDeathData> bets;

    
    public BetOnPlayerDeath() {
        this.bets = Collections.emptyList();
    }


    public void placeBet(UUID betOwner, UUID betTarget, DamageCause betDamageCause, double betAmount) {
        BetOnPlayerDeathData bet = new BetOnPlayerDeathData();
        bet.setBetOwner(betOwner);
        bet.setPlayerUUID(betTarget);
        bet.setDeathType(betDamageCause);
        bet.setBetAmount(betAmount);
        this.bets.add(bet);
    }

    public boolean isBetOnPlayer(UUID playerUuid) {
        boolean isBet = false;
        for (int i = 0; i < bets.size(); i++) {
            if (bets.get(i).getPlayerUUID().equals(playerUuid)) {
                isBet = true;
            }
        }
        return isBet;
    }

    public UUID getBetOwner(UUID playerUuid) {
        UUID playerOwner = null;
        for (int i = 0; i < bets.size(); i++) {
            if (bets.get(i).getPlayerUUID().equals(playerUuid)) {
                playerOwner = bets.get(i).getBetOwner();
            }
        }
        return playerOwner;
    }

    public DamageCause getBetDeathCause(UUID playerUuid) {
        DamageCause deathCause = null;
        for (int i = 0; i < bets.size(); i++) {
            if (bets.get(i).getPlayerUUID().equals(playerUuid)) {
                deathCause = bets.get(i).getDeathType();
            }
        }
        return deathCause;
    }


    public void save(YamlConfiguration deathBetsData) {
        for (int i = 0; i < this.bets.size(); i++) {
            deathBetsData.addDefault(String.format("data.%s.%s", String.valueOf(i), "playerBetOwner"), this.bets.get(i).toString());
            deathBetsData.addDefault(String.format("data.%s.%s", String.valueOf(i), "playerBetTarget"), this.bets.get(i).getPlayerUUID().toString());
            deathBetsData.addDefault(String.format("data.%s.%s", String.valueOf(i), "playerBetDeath"), this.bets.get(i).getDeathType().name());
            deathBetsData.addDefault(String.format("data.%s.%s", String.valueOf(i), "playerBetAmount"), String.valueOf(this.bets.get(i).getBetAmount()));
        }
    }
    public void load(YamlConfiguration deathBetsData) {
        
    }
}
