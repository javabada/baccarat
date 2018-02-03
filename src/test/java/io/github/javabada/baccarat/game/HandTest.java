package io.github.javabada.baccarat.game;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class HandTest {

  @Test
  void handShouldHoldAndReturnCards() {
    Card card1 = new Card(Rank.ACE, Suit.CLUBS);
    Card card2 = new Card(Rank.TWO, Suit.CLUBS);
    Hand hand = new Hand();
    hand.add(card1);
    hand.add(card2);
    assertAll(
      () -> assertEquals(card1, hand.get(0)),
      () -> assertEquals(card2, hand.get(1))
    );
  }

  @Test
  void handShouldKeepCardCount() {
    Hand hand = new Hand();
    hand.add(new Card(Rank.ACE, Suit.CLUBS));
    assertEquals(1, hand.count());
  }

  @Test
  void handShouldNotHoldMoreThan3Cards() {
    Hand hand = new Hand();
    hand.add(new Card(Rank.ACE, Suit.CLUBS));
    hand.add(new Card(Rank.TWO, Suit.CLUBS));
    hand.add(new Card(Rank.SIX, Suit.CLUBS));
    Throwable exception = assertThrows(IllegalStateException.class,
        () -> hand.add(new Card(Rank.TEN, Suit.CLUBS)));
    assertEquals("Hand cannot hold more than 3 cards", exception.getMessage());
  }

  @Test
  void handShouldReturnItsBaccaratValueOf4() {
    Hand hand = new Hand();
    hand.add(new Card(Rank.FIVE, Suit.CLUBS));
    hand.add(new Card(Rank.NINE, Suit.CLUBS));
    hand.add(new Card(Rank.JACK, Suit.CLUBS));
    assertEquals(4, hand.getValue());
  }

  @Test
  void handShouldReturnItsBaccaratValueOf7() {
    Hand hand = new Hand();
    hand.add(new Card(Rank.NINE, Suit.CLUBS));
    hand.add(new Card(Rank.FOUR, Suit.CLUBS));
    hand.add(new Card(Rank.FOUR, Suit.CLUBS));
    assertEquals(7, hand.getValue());
  }

}
