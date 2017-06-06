package com.github.javabada.baccarat.game;

import com.github.javabada.baccarat.card.Card;
import com.github.javabada.baccarat.card.CardRank;
import com.github.javabada.baccarat.card.CardSuit;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoupTest {

    @Test
    public void testNaturalCase() throws Exception {
        Coup coup = new Coup();

        coup.deal(new Card(CardRank.FIVE, CardSuit.DIAMONDS));
        assertEquals(5, coup.getPlayerScore());
        assertEquals(0, coup.getBankerScore());
        assertTrue(coup.isPlayerHand());
        assertFalse(coup.isCoupFinished());

        coup.deal(new Card(CardRank.FIVE, CardSuit.DIAMONDS));
        assertEquals(5, coup.getPlayerScore());
        assertEquals(5, coup.getBankerScore());
        assertFalse(coup.isPlayerHand());
        assertFalse(coup.isCoupFinished());

        coup.deal(new Card(CardRank.FOUR, CardSuit.DIAMONDS));
        assertEquals(9, coup.getPlayerScore());
        assertEquals(5, coup.getBankerScore());
        assertTrue(coup.isPlayerHand());
        assertFalse(coup.isCoupFinished());

        coup.deal(new Card(CardRank.EIGHT, CardSuit.DIAMONDS));
        assertEquals(9, coup.getPlayerScore());
        assertEquals(3, coup.getBankerScore());
        assertFalse(coup.isPlayerHand());
        assertTrue(coup.isCoupFinished());

        assertTrue(coup.getWinningOutcomes().contains(Outcome.PLAYER));
        assertFalse(coup.getWinningOutcomes().contains(Outcome.BANKER));
        assertFalse(coup.getWinningOutcomes().contains(Outcome.TIE));
    }

    @Test
    public void testTwoCardsEachCase() throws Exception {
        Coup coup = new Coup();

        coup.deal(new Card(CardRank.FIVE, CardSuit.DIAMONDS));
        assertEquals(5, coup.getPlayerScore());
        assertEquals(0, coup.getBankerScore());
        assertTrue(coup.isPlayerHand());
        assertFalse(coup.isCoupFinished());

        coup.deal(new Card(CardRank.FIVE, CardSuit.DIAMONDS));
        assertEquals(5, coup.getPlayerScore());
        assertEquals(5, coup.getBankerScore());
        assertFalse(coup.isPlayerHand());
        assertFalse(coup.isCoupFinished());

        coup.deal(new Card(CardRank.ACE, CardSuit.DIAMONDS));
        assertEquals(6, coup.getPlayerScore());
        assertEquals(5, coup.getBankerScore());
        assertTrue(coup.isPlayerHand());
        assertFalse(coup.isCoupFinished());

        coup.deal(new Card(CardRank.TWO, CardSuit.DIAMONDS));
        assertEquals(6, coup.getPlayerScore());
        assertEquals(7, coup.getBankerScore());
        assertFalse(coup.isPlayerHand());
        assertTrue(coup.isCoupFinished());

        assertFalse(coup.getWinningOutcomes().contains(Outcome.PLAYER));
        assertTrue(coup.getWinningOutcomes().contains(Outcome.BANKER));
        assertFalse(coup.getWinningOutcomes().contains(Outcome.TIE));
    }

    @Test
    public void testPlayerThreeCardsBankerTwoCardsCase() throws Exception {
        Coup coup = new Coup();

        coup.deal(new Card(CardRank.FIVE, CardSuit.DIAMONDS));
        assertEquals(5, coup.getPlayerScore());
        assertEquals(0, coup.getBankerScore());
        assertTrue(coup.isPlayerHand());
        assertFalse(coup.isCoupFinished());

        coup.deal(new Card(CardRank.FIVE, CardSuit.DIAMONDS));
        assertEquals(5, coup.getPlayerScore());
        assertEquals(5, coup.getBankerScore());
        assertFalse(coup.isPlayerHand());
        assertFalse(coup.isCoupFinished());

        coup.deal(new Card(CardRank.KING, CardSuit.DIAMONDS));
        assertEquals(5, coup.getPlayerScore());
        assertEquals(5, coup.getBankerScore());
        assertTrue(coup.isPlayerHand());
        assertFalse(coup.isCoupFinished());

        coup.deal(new Card(CardRank.TWO, CardSuit.DIAMONDS));
        assertEquals(5, coup.getPlayerScore());
        assertEquals(7, coup.getBankerScore());
        assertFalse(coup.isPlayerHand());
        assertFalse(coup.isCoupFinished());

        coup.deal(new Card(CardRank.ACE, CardSuit.DIAMONDS));
        assertEquals(6, coup.getPlayerScore());
        assertEquals(7, coup.getBankerScore());
        assertTrue(coup.isPlayerHand());
        assertTrue(coup.isCoupFinished());

        assertFalse(coup.getWinningOutcomes().contains(Outcome.PLAYER));
        assertTrue(coup.getWinningOutcomes().contains(Outcome.BANKER));
        assertFalse(coup.getWinningOutcomes().contains(Outcome.TIE));
    }

    @Test
    public void testPlayerTwoCardsBankerThreeCardsCase() throws Exception {
        Coup coup = new Coup();

        coup.deal(new Card(CardRank.FIVE, CardSuit.DIAMONDS));
        assertEquals(5, coup.getPlayerScore());
        assertEquals(0, coup.getBankerScore());
        assertTrue(coup.isPlayerHand());
        assertFalse(coup.isCoupFinished());

        coup.deal(new Card(CardRank.FIVE, CardSuit.DIAMONDS));
        assertEquals(5, coup.getPlayerScore());
        assertEquals(5, coup.getBankerScore());
        assertFalse(coup.isPlayerHand());
        assertFalse(coup.isCoupFinished());

        coup.deal(new Card(CardRank.ACE, CardSuit.DIAMONDS));
        assertEquals(6, coup.getPlayerScore());
        assertEquals(5, coup.getBankerScore());
        assertTrue(coup.isPlayerHand());
        assertFalse(coup.isCoupFinished());

        coup.deal(new Card(CardRank.KING, CardSuit.DIAMONDS));
        assertEquals(6, coup.getPlayerScore());
        assertEquals(5, coup.getBankerScore());
        assertFalse(coup.isPlayerHand());
        assertFalse(coup.isCoupFinished());

        coup.deal(new Card(CardRank.ACE, CardSuit.DIAMONDS));
        assertEquals(6, coup.getPlayerScore());
        assertEquals(6, coup.getBankerScore());
        assertFalse(coup.isPlayerHand());
        assertTrue(coup.isCoupFinished());

        assertFalse(coup.getWinningOutcomes().contains(Outcome.PLAYER));
        assertFalse(coup.getWinningOutcomes().contains(Outcome.BANKER));
        assertTrue(coup.getWinningOutcomes().contains(Outcome.TIE));
    }

    @Test
    public void testPlayerThreeCardsBankerThreeCardsCase() throws Exception {
        Coup coup = new Coup();

        coup.deal(new Card(CardRank.FIVE, CardSuit.DIAMONDS));
        assertEquals(5, coup.getPlayerScore());
        assertEquals(0, coup.getBankerScore());
        assertTrue(coup.isPlayerHand());
        assertFalse(coup.isCoupFinished());

        coup.deal(new Card(CardRank.FIVE, CardSuit.DIAMONDS));
        assertEquals(5, coup.getPlayerScore());
        assertEquals(5, coup.getBankerScore());
        assertFalse(coup.isPlayerHand());
        assertFalse(coup.isCoupFinished());

        coup.deal(new Card(CardRank.KING, CardSuit.DIAMONDS));
        assertEquals(5, coup.getPlayerScore());
        assertEquals(5, coup.getBankerScore());
        assertTrue(coup.isPlayerHand());
        assertFalse(coup.isCoupFinished());

        coup.deal(new Card(CardRank.KING, CardSuit.DIAMONDS));
        assertEquals(5, coup.getPlayerScore());
        assertEquals(5, coup.getBankerScore());
        assertFalse(coup.isPlayerHand());
        assertFalse(coup.isCoupFinished());

        coup.deal(new Card(CardRank.FOUR, CardSuit.DIAMONDS));
        assertEquals(9, coup.getPlayerScore());
        assertEquals(5, coup.getBankerScore());
        assertTrue(coup.isPlayerHand());
        assertFalse(coup.isCoupFinished());

        coup.deal(new Card(CardRank.KING, CardSuit.DIAMONDS));
        assertEquals(9, coup.getPlayerScore());
        assertEquals(5, coup.getBankerScore());
        assertFalse(coup.isPlayerHand());
        assertTrue(coup.isCoupFinished());

        assertTrue(coup.getWinningOutcomes().contains(Outcome.PLAYER));
        assertFalse(coup.getWinningOutcomes().contains(Outcome.BANKER));
        assertFalse(coup.getWinningOutcomes().contains(Outcome.TIE));
    }

    @Test (expected = IllegalStateException.class)
    public void testGetWinningOutcomesIllegalStateException() throws Exception {
        Coup coup = new Coup();
        coup.getWinningOutcomes();
    }

    @Test (expected = IllegalStateException.class)
    public void testDealIllegalStateException() throws Exception {
        Coup coup = new Coup();
        coup.deal(new Card(CardRank.NINE, CardSuit.DIAMONDS));
        coup.deal(new Card(CardRank.NINE, CardSuit.DIAMONDS));
        coup.deal(new Card(CardRank.KING, CardSuit.DIAMONDS));
        coup.deal(new Card(CardRank.KING, CardSuit.DIAMONDS));
        coup.deal(new Card(CardRank.KING, CardSuit.DIAMONDS));
    }

}
