package io.github.javabada.baccarat.card;

public class Card {

  private final Rank rank;
  private final Suit suit;

  public Card(Rank rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }

  public int getValue() {
    return rank.getValue();
  }

}
