package com.github.javabada.baccarat.game;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Player {

    private static final int SCALE = 2;
    private static final int ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;

    private BigDecimal balance;
    private Map<WagerType, BigDecimal> wagers = new HashMap<>();

    public Player(String amount) {
        balance = new BigDecimal(amount).setScale(SCALE, ROUNDING_MODE);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    /*
     * Balance is deducted as wagers are placed, calling clear() will undo all placed wagers and add them back.
     * If a potential wager is greater than the balance, this method will return false without any changes.
     */
    public boolean placeWager(WagerType type, String amount) {
        BigDecimal wagerAmount = new BigDecimal(amount).setScale(SCALE, ROUNDING_MODE);

        if (wagerAmount.compareTo(balance) > 0) {
            return false;
        }

        if (wagers.containsKey(type)) {
            BigDecimal totalAmount = wagers.get(type).add(wagerAmount);
            wagers.put(type, totalAmount);
        }
        else {
            wagers.put(type, wagerAmount);
        }

        balance = balance.subtract(wagerAmount);
        return true;
    }

    public BigDecimal checkWager(WagerType type) {
        if (wagers.containsKey(type)) {
            return wagers.get(type);
        }
        return BigDecimal.ZERO.setScale(SCALE, ROUNDING_MODE);
    }

    public void clear() {
        for (WagerType type : wagers.keySet()) {
            balance = balance.add(wagers.get(type));
        }
        wagers.clear();
    }

    // TODO
    // payout()

}
