package com.github.javabada.baccarat.game;

public class Game {

    private static final int WAGER_LOW_MIN  = 5;
    private static final int WAGER_LOW_MAX  = 500;
    private static final int WAGER_MED_MIN  = 100;
    private static final int WAGER_MED_MAX  = 10000;
    private static final int WAGER_HIGH_MIN = 1000;
    private static final int WAGER_HIGH_MAX = 150000;

    private int minWager;
    private int maxWager;
    private Player player;

    public Game() {
    }

    public void setLowWager() {
        minWager = WAGER_LOW_MIN;
        maxWager = WAGER_LOW_MAX;
    }

    public void setMedWager() {
        minWager = WAGER_MED_MIN;
        maxWager = WAGER_MED_MAX;
    }

    public void setHighWager() {
        minWager = WAGER_HIGH_MIN;
        maxWager = WAGER_HIGH_MAX;
    }

    public int getMinWager() {
        return minWager;
    }

    public int getMaxWager() {
        return maxWager;
    }




    /*
     * TODO
     * burn()
     * newShoe()
     *
     */


}
