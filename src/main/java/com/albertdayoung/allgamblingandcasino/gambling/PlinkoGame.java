package com.albertdayoung.allgamblingandcasino.gambling;

import java.util.ArrayList;


public class PlinkoGame {
    ArrayList<Double> weights;
    ArrayList<Integer> pathList;
    
    Integer gameHeight;

    public PlinkoGame() {
        this.gameHeight = 5;
    }

    public double run(double ballWorth) {
        double profit = ballWorth;
        int location = 0;

        for (int i = 0;i < this.gameHeight; i++) {
            long result = Math.round(Math.random()*2)-1;
            location = location + (int) result;
        }

        double weight = this.weights.get((int) (Math.floor(this.weights.size()/2)+location));
        profit = profit * weight;

        return profit;
    }

    public ArrayList<Integer> getPath() {
        return
    }
}
