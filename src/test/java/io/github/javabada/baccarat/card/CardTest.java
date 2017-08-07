package io.github.javabada.baccarat.card;

import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

  @Test
  public void getValueShouldReturnCorrectValue() throws Exception {
    Card c1 = new Card(Rank.QUEEN, Suit.HEARTS);
    Card c2 = new Card(Rank.SIX, Suit.CLUBS);
    assertEquals(0, c1.getValue());
    assertEquals(6, c2.getValue());
  }

  @Test
  public void toStringShouldReturnCorrectString() throws Exception {
    Card c1 = new Card(Rank.TEN, Suit.HEARTS);
    Card c2 = new Card(Rank.JACK, Suit.SPADES);
    assertEquals("ten of hearts", c1.toString());
    assertEquals("jack of spades", c2.toString());
  }

}
