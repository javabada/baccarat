package io.github.javabada.baccarat.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CardTest {

  @Test
  void cardRepresentsAValueBetween0And9() {
    Card card = new Card(Rank.TWO, Suit.CLUBS);
    assertTrue(card.getValue() >= 0 && card.getValue() <= 9);
  }

  @Test
  void cardDerivesItsValueFromCardRank() {
    Card card = new Card(Rank.FIVE, Suit.SPADES);
    assertEquals(5, card.getValue());
  }

  @Test
  void cardSuitDoesNotAffectCardValue() {
    Card card1 = new Card(Rank.JACK, Suit.SPADES);
    Card card2 = new Card(Rank.KING, Suit.DIAMONDS);
    assertTrue(card1.getValue() == card2.getValue());
  }

}
