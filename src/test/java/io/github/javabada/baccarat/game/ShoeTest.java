package io.github.javabada.baccarat.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ShoeTest {

  @Test
  void drawShouldReturnACard() {
    Shoe shoe = new Shoe();
    shoe.fill(8);
    assertTrue(shoe.draw() instanceof Card);
  }

  @Test
  void shoeWith8DecksShouldFillWith416Cards() {
    Shoe shoe = new Shoe();
    shoe.fill(8);
    assertEquals(416, shoe.count());
  }

  @Test
  void shoeWith6DecksShouldFillWith312Cards() {
    Shoe shoe = new Shoe();
    shoe.fill(6);
    assertEquals(312, shoe.count());
  }

  @Test
  void drawShouldRemoveACard() {
    Shoe shoe = new Shoe();
    shoe.fill(8);
    shoe.draw();
    assertEquals(415, shoe.count());
  }

  @Test
  void refillingShoeShouldClearOldCards() {
    Shoe shoe = new Shoe();
    shoe.fill(6);
    shoe.fill(8);
    assertEquals(416, shoe.count());
  }

  @Test
  void drawShouldThrowExceptionWhenShoeIsEmpty() {
    Throwable exception = assertThrows(IllegalStateException.class, () -> {
      Shoe shoe = new Shoe();
      shoe.draw();
    });
    assertEquals("Shoe is empty", exception.getMessage());
  }

}
