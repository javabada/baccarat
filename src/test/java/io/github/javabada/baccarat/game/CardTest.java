package io.github.javabada.baccarat.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CardTest {

  @Test
  void cardShouldHave13PossibleRanks() {
    assertEquals(13, Rank.values().length);
  }

  @Test
  void cardShouldHave4PossibleSuits() {
    assertEquals(4, Suit.values().length);
  }

  @Test
  void cardShouldHaveAValueInBaccarat() {
    Card card = new Card(Rank.TEN, Suit.CLUBS);
    assertEquals(0, card.getValue());
  }

}
