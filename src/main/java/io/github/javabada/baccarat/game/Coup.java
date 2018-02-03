package io.github.javabada.baccarat.game;

import java.util.Map;

// A coup defines a round of play in baccarat

public class Coup {

  // A map to determine whether banker should receive a third card under the
  // condition that player has also received a third card
  //
  // Key: Value of player's third card
  // Value: Threshold value, banker receives a third card if its current hand is
  // less than the threshold value
  private static final Map<Integer, Integer> BANKER_RULE_MAP = Map.of(
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

  private boolean coupFinished = false;

  public Coup(Shoe shoe) {
    this.shoe = shoe;
  }

  public Outcome play() {
    if (coupFinished) {
      throw new IllegalStateException("Same coup cannot be played twice");
    }

    playerHand.add(shoe.draw());
    bankerHand.add(shoe.draw());
    playerHand.add(shoe.draw());
    bankerHand.add(shoe.draw());

    // Natural, coup ends
    if (playerHand.getValue() > 7 || bankerHand.getValue() > 7) {
      coupFinished = true;
      return getOutcome();
    }

    // Player's third card rule
    if (playerHand.getValue() < 6) {
      playerHand.add(shoe.draw());
    }

    // Banker's third card rule if player did not receive a third card
    if (playerHand.count() == 2) {
      if (bankerHand.getValue() < 6) {
        bankerHand.add(shoe.draw());
      }
    // Banker's third card rule if player did receive a third card
    } else if (bankerHand.getValue() <
        BANKER_RULE_MAP.get(playerHand.get(2).getValue())) {
      bankerHand.add(shoe.draw());
    }

    coupFinished = true;
    return getOutcome();
  }

  public Outcome getOutcome() {
    if (!coupFinished) {
      throw new IllegalStateException("Coup has not been played");
    }

    if (playerHand.getValue() > bankerHand.getValue()) {
      return Outcome.PLAYER;
    } else if (bankerHand.getValue() > playerHand.getValue()) {
      return Outcome.BANKER;
    } else {
      return Outcome.TIE;
    }
  }

  public Hand getPlayerHand() {
    return playerHand;
  }

  public Hand getBankerHand() {
    return bankerHand;
  }

}
