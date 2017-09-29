package io.github.javabada.baccarat.game;

import io.github.javabada.baccarat.card.Card;
import io.github.javabada.baccarat.card.Rank;
import io.github.javabada.baccarat.card.Suit;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShoeTest {

  @Test
  public void fillShouldGenerateCorrectAmountOfCards() throws Exception {
    Shoe s = new Shoe(8);
    s.fill();
    assertEquals(416, s.count());
  }

  @Test
  public void clearShouldRemoveAllCardsFromShoe() throws Exception {
    Shoe s = new Shoe(8);
    s.fill();
    s.clear();
    assertEquals(0, s.count());
  }

  @Test
  public void drawShouldRemoveOneCardFromShoe() throws Exception {
    Shoe s = new Shoe(6);
    s.fill();
    s.draw();
    assertEquals(311, s.count());
  }

  @Test
  public void burnShouldRemoveCorrectAmountOfCardsFromShoe() throws Exception {
    Shoe s = new Shoe(0);
    for (int i = 0; i < 100; i++) {
      s.add(new Card(Rank.EIGHT, Suit.CLUBS));
    }
    s.burn();
    assertEquals(92, s.count());
  }

  @Test
  public void addAndDrawShouldBeFifo() {
    Card c1 = new Card(Rank.ACE, Suit.HEARTS);
    Card c2 = new Card(Rank.TWO, Suit.CLUBS);
    Card c3 = new Card(Rank.TEN, Suit.DIAMONDS);
    Card c4 = new Card(Rank.SIX, Suit.SPADES);
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
