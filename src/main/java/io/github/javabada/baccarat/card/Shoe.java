package io.github.javabada.baccarat.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shoe {

  private final List<Card> shoe = new ArrayList<>();
  private final int decks;

  public Shoe(int decks) {
    this.decks = decks;
  }

  public void prepare() {
    for (int i = 0; i < decks; i++) {
      for (Rank r : Rank.values()) {
        for (Suit s : Suit.values()) {
          shoe.add(new Card(r, s));
        }
      }
    }
    Collections.shuffle(shoe);
  }

  public Card draw() {
    return shoe.remove(0);
  }

  // the cut card is placed 16 cards from the bottom, appearance of the cut card
  // indicates that the next coup is the final coup of the shoe
  public boolean cutCardDrawn() {
    if (shoe.size() < 17) {
      return true;
    }
    return false;
  }

  // only use when constructing non-random shoes for testing purposes
  public void add(Card card) {
    shoe.add(card);
  }

}
