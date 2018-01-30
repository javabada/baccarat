package io.github.javabada.baccarat.game;

import java.util.ArrayList;
import java.util.List;

public class Hand {

  private final List<Card> hand = new ArrayList<>();

  public Hand() {}

  public void add(Card card) {
    if (hand.size() == 3) {
      throw new IllegalStateException("Hand cannot hold more than 3 cards");
    }
    hand.add(card);
  }

  public Card get(int i) {
    return hand.get(i);
  }

  public int count() {
    return hand.size();
  }

  public int getValue() {
    return hand.stream().mapToInt(Card::getValue).sum() % 10;
  }

}
