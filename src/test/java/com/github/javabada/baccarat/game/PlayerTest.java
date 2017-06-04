package com.github.javabada.baccarat.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testGetBalanceString() throws Exception {
        Player player;

        player = new Player("0");
        assertEquals("0", player.getBalanceString());

        player = new Player("100");
        assertEquals("100", player.getBalanceString());

        player = new Player("1000000");
        assertEquals("1,000,000", player.getBalanceString());

        player = new Player("100.55");
        assertEquals("100.55", player.getBalanceString());

        player = new Player("100.5");
        assertEquals("100.50", player.getBalanceString());

        player = new Player("1000000.5");
        assertEquals("1,000,000.50", player.getBalanceString());
    }

    @Test
    public void testBalanceRounding() throws Exception {
        Player player;

        player = new Player("0.004");
        assertEquals("0", player.getBalanceString());

        player = new Player("0.005");
        assertEquals("0.01", player.getBalanceString());
    }

    @Test
    public void testPlaceWager() throws Exception {
        Player player = new Player("1000");

        player.placeWager(WagerType.PLAYER, "100");
        player.placeWager(WagerType.PLAYER, "100");
        assertEquals("800", player.getBalanceString());

        player.placeWager(WagerType.BANKER, "99.75");
        player.placeWager(WagerType.BANKER, "100.25");
        assertEquals("600", player.getBalanceString());

        player.placeWager(WagerType.TIE, "50");
        player.placeWager(WagerType.TIE, "150");
        assertEquals("400", player.getBalanceString());

        // test if wagers greater than balance can be placed
        boolean wagerPlaced;

        wagerPlaced = player.placeWager(WagerType.PLAYER, "500");
        assertFalse(wagerPlaced);
        assertEquals("400", player.getBalanceString());

        wagerPlaced = player.placeWager(WagerType.PLAYER, "400");
        assertTrue(wagerPlaced);
        assertEquals("0", player.getBalanceString());

        wagerPlaced = player.placeWager(WagerType.PLAYER, "100");
        assertFalse(wagerPlaced);
        assertEquals("0", player.getBalanceString());
    }

    @Test
    public void testClear() throws Exception {
        Player player = new Player("1000");

        player.placeWager(WagerType.PLAYER, "100");
        player.placeWager(WagerType.PLAYER, "200");
        player.clear();
        assertEquals("1,000", player.getBalanceString());

        // test if previous wagers are cleared
        player.placeWager(WagerType.PLAYER, "200");
        player.placeWager(WagerType.PLAYER, "400");
        player.clear();
        assertEquals("1,000", player.getBalanceString());
    }

}
