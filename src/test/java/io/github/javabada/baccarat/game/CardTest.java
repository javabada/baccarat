package io.github.javabada.baccarat.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CardTest {

  @Test
  void cardHas13PossibleRanks() {
    assertEquals(13, Rank.values().length);
  }

  @Test
  void cardHas4PossibleSuits() {
    assertEquals(4, Suit.values().length);
  }

  @Test
  void cardRepresentsAValueInBaccarat() {
    Card card = new Card(Rank.TEN, Suit.CLUBS);
    assertEquals(0, card.getValue());
  }

}
