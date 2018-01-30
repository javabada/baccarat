package io.github.javabada.baccarat.game;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
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
  void playerShouldWinWithNatural() {
    when(shoe.draw()).thenReturn(
        new Card(Rank.TEN, Suit.CLUBS),
        new Card(Rank.TWO, Suit.CLUBS),
        new Card(Rank.EIGHT, Suit.CLUBS),
        new Card(Rank.QUEEN, Suit.CLUBS)
    );
    assertEquals(Outcome.PLAYER, coup.play());
  }

  @Test
  void bankerShouldWinWithNatural() {
    when(shoe.draw()).thenReturn(
        new Card(Rank.TEN, Suit.CLUBS),
        new Card(Rank.TWO, Suit.CLUBS),
        new Card(Rank.EIGHT, Suit.CLUBS),
        new Card(Rank.SEVEN, Suit.CLUBS)
    );
    assertEquals(Outcome.BANKER, coup.play());
  }

  @Test
  void tieWithNatural() {
    when(shoe.draw()).thenReturn(
        new Card(Rank.TWO, Suit.CLUBS),
        new Card(Rank.TWO, Suit.CLUBS),
        new Card(Rank.SIX, Suit.CLUBS),
        new Card(Rank.SIX, Suit.CLUBS)
    );
    assertEquals(Outcome.TIE, coup.play());
  }

  @Test
  void bothShouldDraw2CardsEachWithNatural() {
    when(shoe.draw()).thenReturn(
        new Card(Rank.TEN, Suit.CLUBS),
        new Card(Rank.TWO, Suit.CLUBS),
        new Card(Rank.EIGHT, Suit.CLUBS),
        new Card(Rank.QUEEN, Suit.CLUBS),
        new Card(Rank.ACE, Suit.CLUBS),
        new Card(Rank.ACE, Suit.CLUBS)
    );
    coup.play();
    assertAll(
        () -> assertEquals(2, coup.getPlayerHand().count()),
        () -> assertEquals(2, coup.getBankerHand().count())
    );
  }

  @Test
  void bothShouldStandAndDraw2CardsEach() {
    when(shoe.draw()).thenReturn(
        new Card(Rank.TEN, Suit.CLUBS),
        new Card(Rank.ACE, Suit.CLUBS),
        new Card(Rank.SIX, Suit.CLUBS),
        new Card(Rank.SIX, Suit.CLUBS),
        new Card(Rank.ACE, Suit.CLUBS),
        new Card(Rank.ACE, Suit.CLUBS)
    );
    coup.play();
    assertAll(
        () -> assertEquals(2, coup.getPlayerHand().count()),
        () -> assertEquals(2, coup.getBankerHand().count())
    );
  }

  @Test
  void playerShouldStandAndBankerShouldDraw3Cards() {
    when(shoe.draw()).thenReturn(
        new Card(Rank.TEN, Suit.CLUBS),
        new Card(Rank.ACE, Suit.CLUBS),
        new Card(Rank.SIX, Suit.CLUBS),
        new Card(Rank.TWO, Suit.CLUBS),
        new Card(Rank.TWO, Suit.CLUBS),
        new Card(Rank.ACE, Suit.CLUBS)
    );
    coup.play();
    assertAll(
        () -> assertEquals(2, coup.getPlayerHand().count()),
        () -> assertEquals(3, coup.getBankerHand().count())
    );
  }

  // REVIEW: Test cases for banker's third card rule

  @Test
  void playerShouldDraw3CardsAndBankerShouldStand() {
    when(shoe.draw()).thenReturn(
        new Card(Rank.ACE, Suit.CLUBS),
        new Card(Rank.ACE, Suit.CLUBS),
        new Card(Rank.TWO, Suit.CLUBS),
        new Card(Rank.FOUR, Suit.CLUBS),
        new Card(Rank.TWO, Suit.CLUBS),
        new Card(Rank.ACE, Suit.CLUBS)
    );
    coup.play();
    assertAll(
        () -> assertEquals(3, coup.getPlayerHand().count()),
        () -> assertEquals(2, coup.getBankerHand().count())
    );
  }

  @Test
  void bothShouldDraw3CardsEach() {
    when(shoe.draw()).thenReturn(
        new Card(Rank.ACE, Suit.CLUBS),
        new Card(Rank.ACE, Suit.CLUBS),
        new Card(Rank.TWO, Suit.CLUBS),
        new Card(Rank.TWO, Suit.CLUBS),
        new Card(Rank.THREE, Suit.CLUBS),
        new Card(Rank.THREE, Suit.CLUBS)
    );
    coup.play();
    assertAll(
        () -> assertEquals(3, coup.getPlayerHand().count()),
        () -> assertEquals(3, coup.getBankerHand().count())
    );
  }

}
