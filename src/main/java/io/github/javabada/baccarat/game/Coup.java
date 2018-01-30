package io.github.javabada.baccarat.game;

import java.util.Map;

public class Coup {

  private static final Map<Integer, Integer> BANKER_MAP = Map.of(
    1, 4,
    2, 5,
    3, 5,
    4, 6,
    5, 6,
    6, 7,
    7, 7,
    8, 3,
    9, 4,
    0, 4
  );

  private final Shoe shoe;

  private final Hand playerHand = new Hand();
  private final Hand bankerHand = new Hand();

  public Coup(Shoe shoe) {
    this.shoe = shoe;
  }

  public Outcome play() {
    playerHand.add(shoe.draw());
    bankerHand.add(shoe.draw());
    playerHand.add(shoe.draw());
    bankerHand.add(shoe.draw());

    // Natural, coup ends
    if (playerHand.getValue() > 7 || bankerHand.getValue() > 7) {
      return getOutcome();
    }

    // Player's third card rule
    if (playerHand.getValue() < 6) {
      playerHand.add(shoe.draw());
    }

    // Banker's third card rule
    if (playerHand.count() == 2) {
      if (bankerHand.getValue() < 6) {
        bankerHand.add(shoe.draw());
      }
    } else if (bankerHand.getValue() < BANKER_MAP.get(playerHand.get(2).getValue())) {
      bankerHand.add(shoe.draw());
    }

    return getOutcome();
  }

  public Hand getPlayerHand() {
    return playerHand;
  }

  public Hand getBankerHand() {
    return bankerHand;
  }

  private Outcome getOutcome() {
    if (playerHand.getValue() > bankerHand.getValue()) {
      return Outcome.PLAYER;
    } else if (bankerHand.getValue() > playerHand.getValue()) {
      return Outcome.BANKER;
    } else {
      return Outcome.TIE;
    }
  }

}
