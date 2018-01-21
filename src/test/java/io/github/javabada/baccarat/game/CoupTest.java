package io.github.javabada.baccarat.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// Following behaviours are tested for each coup:
// - Outcome
// - Amount of cards dealt

class CoupTest {

  Shoe shoe;
  Coup coup;

  @BeforeEach
  void init() {
    shoe = mock(Shoe.class);
    coup = new Coup(shoe);
  }

  @Test
  @DisplayName("Natural, player win")
  void coupTest1() {
    when(shoe.draw()).thenReturn(
      new Card(Rank.TEN, Suit.CLUBS),
      new Card(Rank.TWO, Suit.CLUBS),
      new Card(Rank.NINE, Suit.CLUBS),
      new Card(Rank.JACK, Suit.CLUBS),
      new Card(Rank.ACE, Suit.CLUBS),
      new Card(Rank.ACE, Suit.CLUBS)
    );

    coup.play();

    assertEquals(Outcome.PLAYER, coup.getOutcome());
    verify(shoe, times(4)).draw();
  }

  @Test
  @DisplayName("Natural, banker win")
  void coupTest2() {
    when(shoe.draw()).thenReturn(
      new Card(Rank.TEN, Suit.CLUBS),
      new Card(Rank.TWO, Suit.CLUBS),
      new Card(Rank.SIX, Suit.CLUBS),
      new Card(Rank.SIX, Suit.CLUBS),
      new Card(Rank.ACE, Suit.CLUBS),
      new Card(Rank.ACE, Suit.CLUBS)
    );

    coup.play();

    assertEquals(Outcome.BANKER, coup.getOutcome());
    verify(shoe, times(4)).draw();
  }

  @Test
  @DisplayName("Natural, tie")
  void coupTest3() {
    when(shoe.draw()).thenReturn(
      new Card(Rank.TEN, Suit.CLUBS),
      new Card(Rank.TWO, Suit.CLUBS),
      new Card(Rank.NINE, Suit.CLUBS),
      new Card(Rank.SEVEN, Suit.CLUBS),
      new Card(Rank.ACE, Suit.CLUBS),
      new Card(Rank.ACE, Suit.CLUBS)
    );

    coup.play();

    assertEquals(Outcome.TIE, coup.getOutcome());
    verify(shoe, times(4)).draw();
  }

}
