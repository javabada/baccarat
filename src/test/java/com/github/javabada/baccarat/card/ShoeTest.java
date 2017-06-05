package com.github.javabada.baccarat.card;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShoeTest {

    @Test
    public void testAmountOfCardsAfterConstruction() throws Exception {
        Shoe shoe;

        shoe = new Shoe(8);
        assertEquals(416, shoe.cardsRemaining());

        shoe = new Shoe(6);
        assertEquals(312, shoe.cardsRemaining());
    }

    @Test
    public void testDraw() throws Exception {
        Shoe shoe = new Shoe(8);

        shoe.draw();
        assertEquals(415, shoe.cardsRemaining());

        shoe.draw();
        shoe.draw();
        assertEquals(413, shoe.cardsRemaining());
    }

}
