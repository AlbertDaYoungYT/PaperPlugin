package com.albertdayoung.allgamblingandcasino.gambling;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.albertdayoung.allgamblingandcasino.utils.dataclasses.BetOnPlayerDeathData;




public class BetOnPlayerDeath {
    List<BetOnPlayerDeathData> bets;

    
    public BetOnPlayerDeath() {
        this.bets = Collections.emptyList();
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

    public OfflinePlayer getBetOwner(UUID playerUuid) {
        OfflinePlayer playerOwner = null;
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
}
