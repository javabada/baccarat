package io.github.javabada.baccarat;

import io.github.javabada.baccarat.card.Card;
import io.github.javabada.baccarat.card.Rank;
import io.github.javabada.baccarat.card.Suit;

public class Shoe {

  public Shoe() {
  }

  public Card draw() {
    return new Card(Rank.FIVE, Suit.DIAMONDS);
  }

}
