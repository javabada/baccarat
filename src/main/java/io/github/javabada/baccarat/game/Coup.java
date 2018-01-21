package io.github.javabada.baccarat.game;

// A coup is a round of play in baccarat.

public class Coup {

  private final Shoe shoe;

  private int playerScore = 0;
  private int bankerScore = 0;

  private Card playerCard1;
  private Card playerCard2;
  //private Card playerCard3;
  private Card bankerCard1;
  private Card bankerCard2;
  //private Card bankerCard3;

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
