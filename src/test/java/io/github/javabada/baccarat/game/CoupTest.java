package io.github.javabada.baccarat.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoupTest {

  Shoe shoe;
  Coup coup;

  @BeforeEach
  void init() {
    shoe = mock(Shoe.class);
    coup = new Coup(shoe);
  }

  @Test
  void playerWinWithNatural8() {
    when(shoe.draw()).thenReturn(
      new Card(Rank.TEN, Suit.CLUBS),
      new Card(Rank.TWO, Suit.CLUBS),
      new Card(Rank.EIGHT, Suit.CLUBS),
      new Card(Rank.QUEEN, Suit.CLUBS)
    );
    coup.play();
    assertEquals(Outcome.PLAYER, coup.getOutcome());
  }

  @Test
  void bankerWinWithNatural9() {
    when(shoe.draw()).thenReturn(
      new Card(Rank.TEN, Suit.CLUBS),
      new Card(Rank.TWO, Suit.CLUBS),
      new Card(Rank.EIGHT, Suit.CLUBS),
      new Card(Rank.SEVEN, Suit.CLUBS)
    );
    coup.play();
    assertEquals(Outcome.BANKER, coup.getOutcome());
  }

  @Test
  void tieWithNatural8() {
    when(shoe.draw()).thenReturn(
      new Card(Rank.TWO, Suit.CLUBS),
      new Card(Rank.TWO, Suit.CLUBS),
      new Card(Rank.SIX, Suit.CLUBS),
      new Card(Rank.SIX, Suit.CLUBS)
    );
    coup.play();
    assertEquals(Outcome.TIE, coup.getOutcome());
  }

  @Test
  void scoresSumCorrectly() {
    when(shoe.draw()).thenReturn(
      new Card(Rank.NINE, Suit.CLUBS),
      new Card(Rank.NINE, Suit.CLUBS),
      new Card(Rank.NINE, Suit.CLUBS),
      new Card(Rank.FIVE, Suit.CLUBS)
    );
    coup.play();
    assertEquals(8, coup.getPlayerScore());
    assertEquals(4, coup.getBankerScore());
  }

  @Test
  void getOutcomeBeforeCoupPlayThrowsException() {
    Throwable exception = assertThrows(IllegalStateException.class, () -> {
      coup.getOutcome();
    });
    assertEquals("Coup needs to be played, use play()", exception.getMessage());
  }

  @Test
  void getScoreBeforeCoupPlayThrowsException() {
    Throwable exception = assertThrows(IllegalStateException.class, () -> {
      coup.getPlayerScore();
      // Also for banker score
    });
  }

  @Test
  void getCardBeforeCoupPlayThrowsException() {
    Throwable exception = assertThrows(IllegalStateException.class, () -> {
      coup.getPlayerCard1();
      // Also for other cards
    });
  }

  @Test
  void naturalDrawsFourCards() {
    when(shoe.draw()).thenReturn(
      new Card(Rank.TEN, Suit.CLUBS),
      new Card(Rank.TWO, Suit.CLUBS),
      new Card(Rank.EIGHT, Suit.CLUBS),
      new Card(Rank.QUEEN, Suit.CLUBS)
    );
    coup.play();
    verify(shoe, times(4)).draw();
  }

  @Test
  void playerAndBankerStands() {
    when(shoe.draw()).thenReturn(
      new Card(Rank.TEN, Suit.CLUBS),
      new Card(Rank.ACE, Suit.CLUBS),
      new Card(Rank.SIX, Suit.CLUBS),
      new Card(Rank.SIX, Suit.CLUBS)
    );
    coup.play();
    verify(shoe, times(4)).draw();
  }

  @Test
  void playerStandsBankerDraws() {
    when(shoe.draw()).thenReturn(
      new Card(Rank.TEN, Suit.CLUBS),
      new Card(Rank.ACE, Suit.CLUBS),
      new Card(Rank.SIX, Suit.CLUBS),
      new Card(Rank.TWO, Suit.CLUBS),
      new Card(Rank.TWO, Suit.CLUBS)
    );
    coup.play();
    verify(shoe, times(5)).draw();
  }

}
