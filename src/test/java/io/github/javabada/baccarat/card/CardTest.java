package io.github.javabada.baccarat.card;

import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

  @Test
  public void getValueShouldReturnCorrectValue() throws Exception {
    Card card1 = new Card(Rank.TEN, Suit.HEARTS);
    assertEquals(0, card1.getValue());

    Card card2 = new Card(Rank.JACK, Suit.SPADES);
    assertEquals(0, card2.getValue());

    Card card3 = new Card(Rank.SIX, Suit.CLUBS);
    assertEquals(6, card3.getValue());

    Card card4 = new Card(Rank.EIGHT, Suit.DIAMONDS);
    assertEquals(8, card4.getValue());
  }

  @Test
  public void toStringShouldReturnCorrectString() throws Exception {
    Card card1 = new Card(Rank.TEN, Suit.HEARTS);
    assertEquals("ten of hearts", card1.toString());

    Card card2 = new Card(Rank.JACK, Suit.SPADES);
    assertEquals("jack of spades", card2.toString());

    Card card3 = new Card(Rank.SIX, Suit.CLUBS);
    assertEquals("six of clubs", card3.toString());

    Card card4 = new Card(Rank.EIGHT, Suit.DIAMONDS);
    assertEquals("eight of diamonds", card4.toString());
  }

}
