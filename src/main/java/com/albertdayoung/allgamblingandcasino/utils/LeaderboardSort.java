package com.albertdayoung.allgamblingandcasino.utils;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.bukkit.OfflinePlayer;

import com.albertdayoung.allgamblingandcasino.PeakGambling;

public class LeaderboardSort {
    OfflinePlayer[] players;
    List<Double> playerBalances;


    public LeaderboardSort(OfflinePlayer[] players) {
        this.players = players;
        this.playerBalances = new ArrayList<>();
    }


    public final List<String> sort() {
        for (int playerIndex = 0; playerIndex < this.players.length; playerIndex++) {
            this.playerBalances.add(PeakGambling.getEconomy().getBalance(players[playerIndex]));
        }

        // Combine names and balances into a list of pairs
        List<Map.Entry<String, Double>> entries = new ArrayList<>();
        for (int i = 0; i < this.players.length; i++) {
            entries.add(new AbstractMap.SimpleEntry<>(this.players[i].getUniqueId().toString(), this.playerBalances.get(i)));
        }

        // Sort by balance
        entries.sort(Comparator.comparing(Map.Entry::getValue));

        // Extract sorted names
        List<String> sortedNames = new ArrayList<>();
        for (Map.Entry<String, Double> entry : entries) {
            sortedNames.add(entry.getKey());
        }
        Collections.reverse(sortedNames);

        
        return sortedNames;
    }
}
