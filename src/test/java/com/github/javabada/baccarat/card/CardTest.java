package com.github.javabada.baccarat.card;

import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void testCardEqualsAndHashCodeMethods() throws Exception {
        Card aceOfSpades = new Card(CardRank.ACE, CardSuit.SPADES);
        Card aceOfSpades2 = new Card(CardRank.ACE, CardSuit.SPADES);
        Card kingOfSpades = new Card(CardRank.KING, CardSuit.SPADES);

        assertTrue(aceOfSpades.equals(aceOfSpades2));
        assertFalse(aceOfSpades.equals(kingOfSpades));
        assertTrue(aceOfSpades.hashCode() == aceOfSpades2.hashCode());
        assertFalse(aceOfSpades.hashCode() == kingOfSpades.hashCode());
    }

}
