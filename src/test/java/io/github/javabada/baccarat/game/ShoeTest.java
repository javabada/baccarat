package io.github.javabada.baccarat.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ShoeTest {

  @Test
  void drawFromShoeReturnsACard() {
    Shoe shoe = new Shoe();
    shoe.fill(8);
    assertTrue(shoe.draw() instanceof Card);
  }

  @Test
  void shoeWith8DecksStartWith416Cards() {
    Shoe shoe = new Shoe();
    shoe.fill(8);
    assertEquals(416, shoe.count());
  }

  @Test
  void shoeWith6DecksStartWith312Cards() {
    Shoe shoe = new Shoe();
    shoe.fill(6);
    assertEquals(312, shoe.count());
  }

  @Test
  void drawRemovesACardFromShoe() {
    Shoe shoe = new Shoe();
    shoe.fill(8);
    shoe.draw();
    assertEquals(415, shoe.count());
  }

  @Test
  void newShoeClearsRemainingCardsFromOldShoe() {
    Shoe shoe = new Shoe();
    shoe.fill(6);
    shoe.fill(8);
    assertEquals(416, shoe.count());
  }

  @Test
  void drawWhenShoeIsEmptyThrowsException() {
    Throwable exception = assertThrows(IllegalStateException.class, () -> {
      Shoe shoe = new Shoe();
      shoe.draw();
    });
    assertEquals("Shoe is empty", exception.getMessage());
  }

}
