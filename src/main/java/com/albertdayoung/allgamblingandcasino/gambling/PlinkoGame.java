package com.albertdayoung.allgamblingandcasino.gambling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class PlinkoGame {
    ArrayList<Double> weights;
    ArrayList<Integer> pathList;
    
    Integer gameHeight;

    public PlinkoGame() {
        List<Double> unmodifiableList = Collections.unmodifiableList(Arrays.asList( 40.0d, 10.0d, 5.0d, 1.0d, 0.5d, 0.2d, 0.5d, 1.0d, 5.0d, 10.0d, 40.0d));
        this.weights = new ArrayList<>(unmodifiableList);
        this.gameHeight = 5;
    }

    public double run(double ballWorth) {
        double profit = ballWorth;
        int location = 0;

        this.pathList = new ArrayList<>();

        for (int i = 0;i < this.gameHeight; i++) {
            long result = Math.round(Math.random()*2)-1;
            location = location + (int) result;
            this.pathList.add(location);
        }

        double weight = this.weights.get((int) (Math.floor(this.weights.size()/2)+location));
        profit = profit * weight;

        return profit;
    }

    public ArrayList<Integer> getPath() {
        return this.pathList;
    }
}
