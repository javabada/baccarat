package io.github.javabada.baccarat.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CardTest {

  @Test
  void cardHas13DifferentRanks() {
    assertEquals(13, Rank.values().length);
  }

  @Test
  void cardHas4DifferentSuits() {
    assertEquals(4, Suit.values().length);
  }

  @Test
  void cardDerivesItsValueInBaccaratFromCardRank() {
    Card card = new Card(Rank.JACK, Suit.SPADES);
    assertEquals(0, card.getValue());
  }

}
