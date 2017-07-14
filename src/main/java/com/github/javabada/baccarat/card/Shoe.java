package com.github.javabada.baccarat.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shoe {

  private final List<Card> shoe = new ArrayList<>();

  public Shoe() {}

  public void fill(int decks) {
    for (int i = 0; i < decks; i++) {
      for (Rank r : Rank.values()) {
        for (Suit s : Suit.values()) {
          shoe.add(new Card(r, s));
        }
      }
    }
  }

  public void shuffle() {
    Collections.shuffle(shoe);
  }

  public Card draw() {
    return shoe.remove(0);
  }

  public int cardsRemaining() {
    return shoe.size();
  }

  public void clear() {
    shoe.clear();
  }

  // only use when constructing non-random shoes for testing purposes
  public void add(Card card) {
    shoe.add(card);
  }

}
