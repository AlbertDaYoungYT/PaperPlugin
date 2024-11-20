package com.albertdayoung.allgamblingandcasino.roulette;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.OfflinePlayer;

import com.albertdayoung.allgamblingandcasino.PaperPlugin;

public class RouletteGame {
    OfflinePlayer player;

    ArrayList<String> bets;
    Double betMultiplier;
    int betAmount;

    int rouletteSingleNumberPayoutAmount = PaperPlugin.mainConfig.getInt("roulette.single-payout-amount");
    int rouletteTwoNumberPayoutAmount = PaperPlugin.mainConfig.getInt("roulette.two-payout-amount");
    int rouletteThreeNumberPayoutAmount = PaperPlugin.mainConfig.getInt("roulette.three-payout-amount");
    int rouletteFourNumberPayoutAmount = PaperPlugin.mainConfig.getInt("roulette.four-payout-amount");
    int rouletteFiveNumberPayoutAmount = PaperPlugin.mainConfig.getInt("roulette.five-payout-amount");
    int rouletteSixNumberPayoutAmount = PaperPlugin.mainConfig.getInt("roulette.six-payout-amount");


    RouletteGame(OfflinePlayer player, int betAmount) {
        this.player = player;
        this.bets = new ArrayList<>();
        this.betMultiplier = 1.0;
        this.betAmount = betAmount;
    }

    public final void roll() {
        int rand = new Random().nextInt(37 - 1 + 1) + 1;
        boolean isEven = rand % 2 == 0;
        Double finalBetAmount = this.betAmount * this.betMultiplier;

        if (getBets().contains(String.valueOf(rand))) {
            finalBetAmount = finalBetAmount;
        }
    }


    public final Double getBetMultipliers() {
        return this.betMultiplier;
    }

    public final ArrayList<String> getBets() {
        return this.bets;
    }


    public final void setBetMultiplier(Double i) {
        this.betMultiplier = i;
    }

    public final void incrementBetMultiplier(Double amount) {
        this.betMultiplier += amount;
    }

    public final void decrementBetMultiplier(Double amount) {
        this.betMultiplier -= amount;
    }

    public final void addBet(String bet) {
        this.bets.add(bet);
    }

    public final void removeBet(String bet) {
        this.bets.remove(bet);
    }


}
