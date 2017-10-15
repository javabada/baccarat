package io.github.javabada.baccarat.game;

import io.github.javabada.baccarat.card.Card;
import io.github.javabada.baccarat.card.Rank;
import io.github.javabada.baccarat.card.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shoe {

  private final List<Card> shoe = new ArrayList<>();
  private final int decks;

  public Shoe(int decks) {
    this.decks = decks;
  }

  public void fill() {
    for (int i = 0; i < decks; i++) {
      for (Rank r : Rank.values()) {
        for (Suit s : Suit.values()) {
          shoe.add(new Card(r, s));
        }
      }
    }
    Collections.shuffle(shoe);
  }

  public void clear() {
    shoe.clear();
  }

  public Card draw() {
    return shoe.remove(0);
  }

  // draw one card and burn a further amount of cards based on the card value
  public int burn() {
    Card topCard = draw();
    int burnAmount = topCard.getValue() == 0 ? 10 : topCard.getValue();
    for (int i = 0; i < burnAmount - 1; i++) {
      draw();
    }
    return burnAmount;
  }

  public int count() {
    return shoe.size();
  }

  // only use when constructing non-random shoes for testing purposes
  public void add(Card card) {
    shoe.add(card);
  }

}
