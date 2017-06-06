package com.github.javabada.baccarat.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testBalanceRounding() throws Exception {
        Player player;

        player = new Player("0.004");
        assertEquals("0.00", player.getBalance().toPlainString());

        player = new Player("0.005");
        assertEquals("0.01", player.getBalance().toPlainString());
    }

    @Test
    public void testPlaceWagerAndCheckWager() throws Exception {
        Player player = new Player("1000");
        boolean wagerPlaced;

        wagerPlaced = player.placeWager(Outcome.PLAYER, "100");
        assertTrue(wagerPlaced);
        assertEquals("100.00", player.checkWager(Outcome.PLAYER).toPlainString());
        assertEquals("900.00", player.getBalance().toPlainString());

        wagerPlaced = player.placeWager(Outcome.PLAYER, "0.25");
        assertTrue(wagerPlaced);
        assertEquals("100.25", player.checkWager(Outcome.PLAYER).toPlainString());
        assertEquals("899.75", player.getBalance().toPlainString());
    }

    @Test
    public void testPlaceWagerGreaterThanBalance() throws Exception {
        Player player = new Player("1000");
        boolean wagerPlaced;

        wagerPlaced = player.placeWager(Outcome.PLAYER, "1100");
        assertFalse(wagerPlaced);
        assertEquals("0.00", player.checkWager(Outcome.PLAYER).toPlainString());
        assertEquals("1000.00", player.getBalance().toPlainString());

        wagerPlaced = player.placeWager(Outcome.PLAYER, "1000");
        assertTrue(wagerPlaced);
        assertEquals("1000.00", player.checkWager(Outcome.PLAYER).toPlainString());
        assertEquals("0.00", player.getBalance().toPlainString());

        wagerPlaced = player.placeWager(Outcome.PLAYER, "100");
        assertFalse(wagerPlaced);
        assertEquals("1000.00", player.checkWager(Outcome.PLAYER).toPlainString());
        assertEquals("0.00", player.getBalance().toPlainString());
    }

    @Test
    public void testClear() throws Exception {
        Player player = new Player("1000");

        player.placeWager(Outcome.PLAYER, "100");
        player.clear();
        assertEquals("0.00", player.checkWager(Outcome.PLAYER).toPlainString());
        assertEquals("1000.00", player.getBalance().toPlainString());
    }

}
