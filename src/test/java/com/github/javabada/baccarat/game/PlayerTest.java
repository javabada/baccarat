package com.github.javabada.baccarat.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testBalanceStringOutput() throws Exception {
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

}
