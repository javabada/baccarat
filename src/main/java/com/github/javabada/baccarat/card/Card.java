package com.github.javabada.baccarat.card;

public class Card {

  private final CardRank rank;
  private final CardSuit suit;

  public Card(CardRank rank, CardSuit suit) {
    this.rank = rank;
    this.suit = suit;
  }

  public int getValue() {
    return rank.getValue();
  }

  @Override
  public String toString() {
    return (rank + " of " + suit).toLowerCase();
  }

}
