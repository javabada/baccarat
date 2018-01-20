package io.github.javabada.baccarat.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// https://en.wikipedia.org/wiki/Baccarat_(card_game)
// Behaviour required for testing:
// - Player win, banker win, or tie
// - How many cards used for the coup

class CoupTest {

  @Test
  @DisplayName("Player wins with natural 9")
  void coupTestCase1() {
    Shoe mockedShoe = mock(Shoe.class);
    when(mockedShoe.draw()).thenReturn(
      new Card(Rank.TEN, Suit.CLUBS),
      new Card(Rank.TWO, Suit.CLUBS),
      new Card(Rank.NINE, Suit.CLUBS),
      new Card(Rank.JACK, Suit.CLUBS),
      new Card(Rank.ACE, Suit.CLUBS),
      new Card(Rank.THREE, Suit.CLUBS)
    );

    Coup coup = new Coup(mockedShoe);
    coup.play();

    assertEquals(Outcome.PLAYER, coup.getOutcome());

    verify(mockedShoe, times(4)).draw();
  }

  @Test
  @DisplayName("Banker wins with natural 8")
  void coupTestCase2() {
    Shoe mockedShoe = mock(Shoe.class);
    when(mockedShoe.draw()).thenReturn(
      new Card(Rank.TEN, Suit.CLUBS),
      new Card(Rank.TWO, Suit.CLUBS),
      new Card(Rank.SIX, Suit.CLUBS),
      new Card(Rank.SIX, Suit.CLUBS),
      new Card(Rank.ACE, Suit.CLUBS),
      new Card(Rank.THREE, Suit.CLUBS)
    );

    Coup coup = new Coup(mockedShoe);
    coup.play();

    assertEquals(Outcome.BANKER, coup.getOutcome());

    verify(mockedShoe, times(4)).draw();
  }

  @Test
  @DisplayName("Natural tie")
  void coupTestCase3() {
    Shoe mockedShoe = mock(Shoe.class);
    when(mockedShoe.draw()).thenReturn(
      new Card(Rank.TEN, Suit.CLUBS),
      new Card(Rank.TWO, Suit.CLUBS),
      new Card(Rank.NINE, Suit.CLUBS),
      new Card(Rank.SEVEN, Suit.CLUBS),
      new Card(Rank.ACE, Suit.CLUBS),
      new Card(Rank.THREE, Suit.CLUBS)
    );

    Coup coup = new Coup(mockedShoe);
    coup.play();

    assertEquals(Outcome.TIE, coup.getOutcome());

    verify(mockedShoe, times(4)).draw();
  }

}
