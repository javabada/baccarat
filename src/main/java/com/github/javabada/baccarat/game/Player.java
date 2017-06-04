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
        return formatToString(balance);
    }

    public boolean placeWager(WagerType type, String amount) {
        BigDecimal wagerAmount = new BigDecimal(amount);

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

    public void clear() {
        for (WagerType type : wagers.keySet()) {
            balance = balance.add(wagers.get(type));
        }
        wagers.clear();
    }

    // TODO
    // payout()

    private String formatToString(BigDecimal amount) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(amount).replace(".00", "");
    }

}
