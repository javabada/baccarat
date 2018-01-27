package io.github.javabada.baccarat.game;

public class Coup {

  private final Shoe shoe;

  private int playerScore = 0;
  private int bankerScore = 0;

  private Card playerCard1;
  private Card playerCard2;
  private Card bankerCard1;
  private Card bankerCard2;

  private boolean coupFinished = false;

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

    coupFinished = true;
  }

  public Outcome getOutcome() {
    checkState();
    if (playerScore > bankerScore) {
      return Outcome.PLAYER;
    } else if (bankerScore > playerScore) {
      return Outcome.BANKER;
    } else {
      return Outcome.TIE;
    }
  }

  public int getPlayerScore() {
    checkState();
    return playerScore;
  }

  public int getBankerScore() {
    checkState();
    return bankerScore;
  }

  public Card getPlayerCard1() {
    checkState();
    return playerCard1;
  }

  public Card getPlayerCard2() {
    checkState();
    return playerCard2;
  }

  public Card getBankerCard1() {
    checkState();
    return bankerCard1;
  }

  public Card getBankerCard2() {
    checkState();
    return bankerCard2;
  }

  private void checkState() {
    if (!coupFinished) {
      throw new IllegalStateException("Coup needs to be played, use play()");
    }
  }

}
