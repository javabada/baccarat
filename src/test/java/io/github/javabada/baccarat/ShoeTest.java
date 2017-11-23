package io.github.javabada.baccarat;

// import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.javabada.baccarat.card.Card;
import org.junit.jupiter.api.Test;

class ShoeTest {

  @Test
  void drawFromShoeReturnsACard() {
    Shoe shoe = new Shoe();
    assertTrue(shoe.draw() instanceof Card);
  }

}
