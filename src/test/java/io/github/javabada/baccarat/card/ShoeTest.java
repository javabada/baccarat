package io.github.javabada.baccarat.card;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShoeTest {

  @Test
  public void prepareGeneratesCorrectNumberOfCards() throws Exception {
    Shoe shoe1 = new Shoe(8);
    shoe1.prepare();
    assertEquals(416, shoe1.cardsRemaining());

    Shoe shoe2 = new Shoe(6);
    shoe2.prepare();
    assertEquals(312, shoe2.cardsRemaining());
  }

  @Test
  public void addAndDrawCardsShouldBeInCorrectOrder() throws Exception {
    Card card1 = new Card(Rank.ACE, Suit.HEARTS);
    Card card2 = new Card(Rank.SIX, Suit.HEARTS);
    Card card3 = new Card(Rank.TWO, Suit.HEARTS);
    Card card4 = new Card(Rank.TEN, Suit.HEARTS);

    Shoe shoe = new Shoe(0);
    shoe.add(card1);
    shoe.add(card2);
    shoe.add(card3);
    shoe.add(card4);

    assertEquals(card1, shoe.draw());
    assertEquals(card2, shoe.draw());
    assertEquals(card3, shoe.draw());
    assertEquals(card4, shoe.draw());
  }

}
