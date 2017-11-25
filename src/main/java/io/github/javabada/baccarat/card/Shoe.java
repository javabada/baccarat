package io.github.javabada.baccarat.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shoe {

  private final List<Card> shoe = new ArrayList<>();

  public Shoe() {
  }

  public void fill(int decks) {
    for (int i = 0; i < decks; i++) {
      for (Rank rank : Rank.values()) {
        for (Suit suit : Suit.values()) {
          shoe.add(new Card(rank, suit));
        }
      }
    }
    
    Collections.shuffle(shoe);
  }

  public Card draw() {
    return shoe.remove(0);
  }

  public int count() {
    return shoe.size();
  }

}
