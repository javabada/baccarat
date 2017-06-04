package com.github.javabada.baccarat.game;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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

    public String getBalanceString() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(balance).replace(".00", "");
    }

    public void placeWager(WagerType type, String amount) {
        BigDecimal wagerAmount = new BigDecimal(amount);

        if (wagers.containsKey(type)) {
            BigDecimal totalAmount = wagers.get(type).add(wagerAmount);
            wagers.put(type, totalAmount);
        }
        else {
            wagers.put(type, wagerAmount);
        }

        balance = balance.subtract(wagerAmount);
    }

    // TODO
    // reset()
    // payout()

}
