package io.github.javabada.baccarat.game;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Player {

  private static final int SCALE = 2;
  private static final int ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;

  private BigDecimal balance;
  private Map<Outcome, BigDecimal> wagers = new HashMap<>();

  public Player(String amount) {
    balance = new BigDecimal(amount).setScale(SCALE, ROUNDING_MODE);
  }

  public BigDecimal getBalance() {
    return balance;
  }

  // balance is deducted as wagers are placed, clear() will undo all placed wagers
  // returns false without any changes if potential wager is greater than balance
  public boolean placeWager(Outcome outcome, String amount) {
    BigDecimal wagerAmount = new BigDecimal(amount).setScale(SCALE, ROUNDING_MODE);

    if (wagerAmount.compareTo(balance) > 0) {
      return false;
    }

    if (wagers.containsKey(outcome)) {
      BigDecimal totalAmount = wagers.get(outcome).add(wagerAmount);
      wagers.put(outcome, totalAmount);
    } else {
      wagers.put(outcome, wagerAmount);
    }

    balance = balance.subtract(wagerAmount);
    return true;
  }

  public BigDecimal checkWager(Outcome outcome) {
    if (wagers.containsKey(outcome)) {
      return wagers.get(outcome);
    }
    return BigDecimal.ZERO.setScale(SCALE);
  }

  public void clearWagers() {
    for (Outcome o : wagers.keySet()) {
      balance = balance.add(wagers.get(o));
    }
    wagers.clear();
  }

  public BigDecimal settleWagers(Outcome winner) {
    BigDecimal winnings = BigDecimal.ZERO.setScale(SCALE);

    if (winner == Outcome.TIE) {
      if (wagers.containsKey(Outcome.TIE)) {
        winnings = wagers.get(Outcome.TIE).multiply(Outcome.TIE.getOdds());
      }
      for (Outcome o : wagers.keySet()) {
        balance = balance.add(wagers.get(o));
      }
    } else if (wagers.containsKey(winner)) {
      winnings = wagers.get(winner).multiply(winner.getOdds()).setScale(SCALE, ROUNDING_MODE);
      balance = balance.add(wagers.get(winner));
    }

    wagers.clear();
    balance = balance.add(winnings);
    return winnings;
  }

}
