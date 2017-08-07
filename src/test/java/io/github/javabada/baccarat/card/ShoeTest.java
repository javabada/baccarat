package io.github.javabada.baccarat.card;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShoeTest {

  @Test
  public void prepareShouldGenerateCorrectNumberOfCards() throws Exception {
    Shoe s = new Shoe(8);
    s.prepare();
    assertEquals(416, s.cardsRemaining());
  }

  @Test
  public void drawShouldRemoveSingleCardFromShoe() throws Exception {
    Shoe s = new Shoe(6);
    s.prepare();
    s.draw();
    assertEquals(311, s.cardsRemaining());
  }

  @Test
  public void addAndDrawCardsShouldBeInCorrectOrder() throws Exception {
    Card c1 = new Card(Rank.ACE, Suit.HEARTS);
    Card c2 = new Card(Rank.SIX, Suit.SPADES);
    Card c3 = new Card(Rank.TWO, Suit.CLUBS);
    Card c4 = new Card(Rank.TEN, Suit.SPADES);
    Shoe s = new Shoe(0);
    s.add(c1);
    s.add(c2);
    s.add(c3);
    s.add(c4);
    assertEquals(c1, s.draw());
    assertEquals(c2, s.draw());
    assertEquals(c3, s.draw());
    assertEquals(c4, s.draw());
  }

}
