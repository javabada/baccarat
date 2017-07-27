package io.github.javabada.baccarat.game;

import io.github.javabada.baccarat.card.Card;
import io.github.javabada.baccarat.card.Shoe;

public class Coup {

  private final Shoe shoe;

  private Card playerCard1;
  private Card playerCard2;
  private Card playerCard3;
  private Card bankerCard1;
  private Card bankerCard2;
  private Card bankerCard3;

  private int playerScore = 0;
  private int bankerScore = 0;

  public Coup(Shoe shoe) {
    this.shoe = shoe;
  }

  public void play() {
    playerCard1 = shoe.draw();
    bankerCard1 = shoe.draw();
    playerCard2 = shoe.draw();
    bankerCard2 = shoe.draw();
    playerScore = (playerCard1.getValue() + playerCard2.getValue()) % 10;
    bankerScore = (bankerCard1.getValue() + bankerCard2.getValue()) % 10;

    if (playerScore > 7 || bankerScore > 7) {
      // natural
      return;
    }

    if (playerScore < 6) {
      // player draws third card
      playerCard3 = shoe.draw();
      playerScore = (playerScore + playerCard3.getValue()) % 10;
    }

    if ((playerCard3 == null && bankerScore < 6) ||
        ((playerCard3.getValue() == 2 || playerCard3.getValue() == 3) && bankerScore < 5) ||
        ((playerCard3.getValue() == 4 || playerCard3.getValue() == 5) && bankerScore < 6) ||
        ((playerCard3.getValue() == 6 || playerCard3.getValue() == 7) && bankerScore < 7) ||
         (playerCard3.getValue() == 8                                 && bankerScore < 3) ||
        ((playerCard3.getValue() == 9 || playerCard3.getValue() <  2) && bankerScore < 4)) {
      // banker draws third card
      bankerCard3 = shoe.draw();
      bankerScore = (bankerScore + bankerCard3.getValue()) % 10;
    }
  }

  public Card getPlayerCard1() {
    return playerCard1;
  }

  public Card getPlayerCard2() {
    return playerCard2;
  }

  public Card getPlayerCard3() {
    return playerCard3;
  }

  public Card getBankerCard1() {
    return bankerCard1;
  }

  public Card getBankerCard2() {
    return bankerCard2;
  }

  public Card getBankerCard3() {
    return bankerCard3;
  }

  public int getPlayerScore() {
    return playerScore;
  }

  public int getBankerScore() {
    return bankerScore;
  }

  public Outcome getOutcome() {
    if (playerScore > bankerScore) {
      return Outcome.PLAYER;
    } else if (bankerScore > playerScore) {
      return Outcome.BANKER;
    } else {
      return Outcome.TIE;
    }
  }

}
