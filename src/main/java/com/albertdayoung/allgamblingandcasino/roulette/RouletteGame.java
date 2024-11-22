package com.albertdayoung.allgamblingandcasino.roulette;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.OfflinePlayer;

import com.albertdayoung.allgamblingandcasino.PaperPlugin;

import dev.triumphteam.nova.MutableState;

public class RouletteGame {
    OfflinePlayer player;

    ArrayList<String> bets;
    MutableState<Integer> betMultiplier;
    List<String> betOptions;
    boolean canBet = true;
    int betAmount;

    double rouletteGreenNumberPayoutAmount = PaperPlugin.mainConfig.getDouble("roulette.green-number-payout-amount");
    double rouletteSingleNumberPayoutAmount = PaperPlugin.mainConfig.getDouble("roulette.single-number-payout-amount");
    double rouletteTwoNumberPayoutAmount = PaperPlugin.mainConfig.getDouble("roulette.two-number-payout-amount");
    double rouletteThreeNumberPayoutAmount = PaperPlugin.mainConfig.getDouble("roulette.three-number-payout-amount");
    double rouletteFourNumberPayoutAmount = PaperPlugin.mainConfig.getDouble("roulette.four-number-payout-amount");
    double rouletteFiveNumberPayoutAmount = PaperPlugin.mainConfig.getDouble("roulette.five-number-payout-amount");
    double rouletteSixNumberPayoutAmount = PaperPlugin.mainConfig.getDouble("roulette.six-number-payout-amount");

    double rouletteGroupTwelvePayoutAmount = PaperPlugin.mainConfig.getDouble("roulette.group-twelve-payout-amount");
    double rouletteHalfsEightteenPayoutAmount = PaperPlugin.mainConfig.getDouble("roulette.halfs-eightteen-payout-amount");
    double rouletteRedBlackPayoutAmount = PaperPlugin.mainConfig.getDouble("roulette.red-black-eightteen-payout-amount");
    double rouletteOddsEvenPayoutAmount = PaperPlugin.mainConfig.getDouble("roulette.odds-even-eightteen-payout-amount");
    
    
    
    
        public RouletteGame(OfflinePlayer player, int betAmount) {
            this.player = player;
            this.bets = new ArrayList<>();
            this.betAmount = betAmount;
    
            this.betOptions = new ArrayList<>();
            this.betOptions.add("GREEN");
            this.betOptions.add("GROUP-1");
            this.betOptions.add("GROUP-2");
            this.betOptions.add("GROUP-3");
            this.betOptions.add("HALFS-1");
            this.betOptions.add("HALFS-2");
            this.betOptions.add("RED");
            this.betOptions.add("BLACK");
            this.betOptions.add("ODDS");
            this.betOptions.add("EVEN");
    }

    public final void roll() {
        int rand = new Random().nextInt(36 - 0 + 1) + 0;
        boolean isEven = rand % 2 == 0;
        int finalBetAmount = this.betAmount * this.betMultiplier.get();
        PaperPlugin.getEconomy().withdrawPlayer(player, finalBetAmount);

        finalBetAmount = calcBetAmount(finalBetAmount, rand);
        finalBetAmount = finalBetAmount - (this.betAmount * this.betMultiplier.get());

        PaperPlugin.getEconomy().depositPlayer(player, finalBetAmount);
        this.player.getPlayer().sendMessage(String.format("You won $%s", finalBetAmount));
    }

    public final int calcBetAmount(int f, int roll) {
        for (int i = 0; i < getBets().size(); i++) {
            switch (getBets().get(i)) {
                case "GREEN":
                    if (roll == 0) {
                        f = (int) (f * rouletteGreenNumberPayoutAmount);
                    }
                    break;

                case "GROUP-1":
                    if (roll <= 12 && roll >= 1) {
                        f = (int) (f * rouletteGroupTwelvePayoutAmount);
                    }
                    break;

                case "GROUP-2":
                    if (roll <= 24 && roll >= 12) {
                        f = (int) (f * rouletteGroupTwelvePayoutAmount);
                    }
                    break;

                case "GROUP-3":
                    if (roll <= 36 && roll >= 24) {
                        f = (int) (f * rouletteGroupTwelvePayoutAmount);
                    }
                    break;

                case "HALFS-1":
                    if (roll <= 18 && roll >= 1) {
                        f = (int) (f * rouletteHalfsEightteenPayoutAmount);
                    }
                    break;

                case "HALFS-2":
                    if (roll <= 36 && roll >= 18) {
                        f = (int) (f * rouletteHalfsEightteenPayoutAmount);
                    }
                    break;

                case "RED":
                    if (roll <= 18 && roll >= 1) {
                        f = (int) (f * rouletteRedBlackPayoutAmount);
                    }
                    break;

                case "BLACK":
                    if (roll <= 36 && roll >= 18) {
                        f = (int) (f * rouletteRedBlackPayoutAmount);
                    }
                    break;

                case "ODDS":
                    if (roll <= 18 && roll >= 1) {
                        f = (int) (f * rouletteRedBlackPayoutAmount);
                    }
                    break;

                case "EVEN":
                    if (roll <= 36 && roll >= 18) {
                        f = (int) (f * rouletteOddsEvenPayoutAmount);
                    }
                    break;
                
                default:
                    int intBet = Integer.valueOf(i);
                    switch (getBets().size()) {
                        case 1:
                            if (intBet == roll) {
                                f = (int) (f * rouletteSingleNumberPayoutAmount);
                            }
                            break;
                        case 2:
                            if (intBet == roll) {
                                f = (int) (f * rouletteTwoNumberPayoutAmount);
                            }
                            break;
                        case 3:
                            if (intBet == roll) {
                                f = (int) (f * rouletteThreeNumberPayoutAmount);
                            }
                            break;
                        case 4:
                            if (intBet == roll) {
                                f = (int) (f * rouletteFourNumberPayoutAmount);
                            }
                            break;
                        case 5:
                            if (intBet == roll) {
                                f = (int) (f * rouletteFiveNumberPayoutAmount);
                            }
                            break;
                        case 6:
                            if (intBet == roll) {
                                f = (int) (f * rouletteSixNumberPayoutAmount);
                            }
                            break;
                        default:
                            throw new AssertionError();
                    }
                    break;
            }
        }
        return f;
    }


    public final MutableState<Integer> getBetMultipliers() {
        return this.betMultiplier;
    }

    public final ArrayList<String> getBets() {
        return this.bets;
    }


    public final void setBetMultiplier(MutableState<Integer> i) {
        this.betMultiplier = i;
    }

    public final void incrementBetMultiplier(int amount) {
        this.betMultiplier.update(previous -> previous + amount);
    }

    public final void decrementBetMultiplier(int amount) {
        this.betMultiplier.update(previous -> previous - amount);
    }

    public final void addBet(String bet) {
        if (this.betOptions.contains(bet)) {
            this.canBet = false;
        } 
        if (!(getBets().size() == 6)) {
            this.bets.add(bet);
        }
    }

    public final void removeBet(String bet) {
        if (this.betOptions.contains(bet)) {
            this.canBet = true;
        }
        this.bets.remove(bet);
    }


}
