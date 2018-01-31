package io.github.javabada.baccarat.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// A shoe holds multiple number of decks shuffled together.

public class Shoe {

  private final List<Card> shoe = new ArrayList<>();

  public Shoe() {}

  public void fill(int decks) {
    // Clear shoe if not empty
    if (!shoe.isEmpty()) {
      shoe.clear();
    }

    // Fill shoe with given number of decks
    for (int i = 0; i < decks; i++) {
      for (Rank rank : Rank.values()) {
        for (Suit suit : Suit.values()) {
          shoe.add(new Card(rank, suit));
        }
      }
    }

    // Shuffle shoe
    Collections.shuffle(shoe);
  }

  public Card draw() {
    if (shoe.isEmpty()) {
      throw new IllegalStateException("Shoe is empty");
    }
    return shoe.remove(0);
  }

  public int count() {
    return shoe.size();
  }

}
