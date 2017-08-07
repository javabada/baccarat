package io.github.javabada.baccarat.game;

import java.math.BigDecimal;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

  @Test
  public void balanceShouldRoundDown() throws Exception {
    Player p = new Player("10.524");
    assertEquals("10.52", p.getBalance().toString());
  }

  @Test
  public void balanceShouldRoundUp() throws Exception {
    Player p = new Player("12.565");
    assertEquals("12.57", p.getBalance().toString());
  }

  @Test
  public void singleWagerShouldBePlaced() throws Exception {
    Player p = new Player("1000");
    boolean wagerPlaced = p.placeWager(Outcome.PLAYER, "400");
    assertTrue(wagerPlaced);
    assertEquals("400.00", p.checkWager(Outcome.PLAYER).toString());
    assertEquals("600.00", p.getBalance().toString());
  }

  @Test
  public void multipleSameOutcomeWagersShouldAggregate() throws Exception {
    Player p = new Player("1000");
    boolean wager1Placed = p.placeWager(Outcome.PLAYER, "400");
    boolean wager2Placed = p.placeWager(Outcome.PLAYER, "200");
    assertTrue(wager1Placed);
    assertTrue(wager2Placed);
    assertEquals("600.00", p.checkWager(Outcome.PLAYER).toString());
    assertEquals("400.00", p.getBalance().toString());
  }

  @Test
  public void multipleDifferentOutcomeWagersShouldBePlaced() throws Exception {
    Player p = new Player("1000");
    boolean wager1Placed = p.placeWager(Outcome.PLAYER, "100");
    boolean wager2Placed = p.placeWager(Outcome.BANKER, "200");
    boolean wager3Placed = p.placeWager(Outcome.TIE, "300");
    assertTrue(wager1Placed);
    assertTrue(wager2Placed);
    assertTrue(wager3Placed);
    assertEquals("100.00", p.checkWager(Outcome.PLAYER).toString());
    assertEquals("200.00", p.checkWager(Outcome.BANKER).toString());
    assertEquals("300.00", p.checkWager(Outcome.TIE).toString());
    assertEquals("400.00", p.getBalance().toString());
  }

  @Test
  public void wagerEqualToBalanceShouldBePlaced() throws Exception {
    Player p = new Player("1000");
    boolean wagerPlaced = p.placeWager(Outcome.PLAYER, "1000");
    assertTrue(wagerPlaced);
    assertEquals("1000.00", p.checkWager(Outcome.PLAYER).toString());
    assertEquals("0.00", p.getBalance().toString());
  }

  @Test
  public void wagerGreaterThanBalanceShouldBeDenied() throws Exception {
    Player p = new Player("1000");
    boolean wagerPlaced = p.placeWager(Outcome.BANKER, "1001");
    assertFalse(wagerPlaced);
    assertEquals("0.00", p.checkWager(Outcome.BANKER).toString());
    assertEquals("1000.00", p.getBalance().toString());
  }

  @Test
  public void wagersShouldBeCleared() throws Exception {
    Player p = new Player("1000");
    p.placeWager(Outcome.PLAYER, "300");
    p.placeWager(Outcome.BANKER, "200");
    p.placeWager(Outcome.TIE, "150");
    p.clearWagers();
    assertEquals("1000.00", p.getBalance().toString());
    assertEquals("0.00", p.checkWager(Outcome.PLAYER).toString());
    assertEquals("0.00", p.checkWager(Outcome.BANKER).toString());
    assertEquals("0.00", p.checkWager(Outcome.TIE).toString());
  }

  @Test
  public void settleWagersShouldClearPlacedWagersAfter() throws Exception {
    Player p = new Player("1000");
    p.placeWager(Outcome.PLAYER, "100");
    p.placeWager(Outcome.BANKER, "200");
    p.placeWager(Outcome.TIE, "150");
    p.settleWagers(Outcome.TIE);
    assertEquals("0.00", p.checkWager(Outcome.PLAYER).toString());
    assertEquals("0.00", p.checkWager(Outcome.BANKER).toString());
    assertEquals("0.00", p.checkWager(Outcome.TIE).toString());
  }

  @Test
  public void payoutOnPlayerWinShouldBeCorrectAmount() throws Exception {
    Player p = new Player("1000");
    p.placeWager(Outcome.PLAYER, "500");
    BigDecimal winnings = p.settleWagers(Outcome.PLAYER);
    assertEquals("1500.00", p.getBalance().toString());
    assertEquals("500.00", winnings.toString());
  }

  @Test
  public void payoutOnBankerWinShouldBeCorrectAmount() throws Exception {
    Player p = new Player("1000");
    p.placeWager(Outcome.BANKER, "100");
    BigDecimal winnings = p.settleWagers(Outcome.BANKER);
    assertEquals("1095.00", p.getBalance().toString());
    assertEquals("95.00", winnings.toString());
  }

  @Test
  public void payoutOnTieShouldBeCorrectAmount() throws Exception {
    Player p = new Player("1000");
    p.placeWager(Outcome.TIE, "100");
    BigDecimal winnings = p.settleWagers(Outcome.TIE);
    assertEquals("1800.00", p.getBalance().toString());
    assertEquals("800.00", winnings.toString());
  }

  @Test
  public void wagersShouldBeCollectedOnLoss() throws Exception {
    Player p = new Player("1000");
    p.placeWager(Outcome.PLAYER, "100");
    BigDecimal winnings1 = p.settleWagers(Outcome.BANKER);
    p.placeWager(Outcome.BANKER, "200");
    BigDecimal winnings2 = p.settleWagers(Outcome.PLAYER);
    assertEquals("700.00", p.getBalance().toString());
    assertEquals("0.00", winnings1.toString());
    assertEquals("0.00", winnings2.toString());
  }

  @Test
  public void wagersShouldBeReturnedOnTie() throws Exception {
    Player p = new Player("1000");
    p.placeWager(Outcome.PLAYER, "200");
    p.placeWager(Outcome.BANKER, "500");
    BigDecimal winnings = p.settleWagers(Outcome.TIE);
    assertEquals("1000.00", p.getBalance().toString());
    assertEquals("0.00", winnings.toString());
  }

  @Test
  public void payoutForMultipleWagersOnPlayerWinShouldBeCorrectAmount() throws Exception {
    Player p = new Player("1000");
    p.placeWager(Outcome.PLAYER, "200");
    p.placeWager(Outcome.BANKER, "300");
    p.placeWager(Outcome.TIE, "100");
    BigDecimal winnings = p.settleWagers(Outcome.PLAYER);
    assertEquals("800.00", p.getBalance().toString());
    assertEquals("200.00", winnings.toString());
  }

  @Test
  public void payoutForMultipleWagersOnBankerWinShouldBeCorrectAmount() throws Exception {
    Player p = new Player("1000");
    p.placeWager(Outcome.PLAYER, "300");
    p.placeWager(Outcome.BANKER, "100");
    p.placeWager(Outcome.TIE, "200");
    BigDecimal winnings = p.settleWagers(Outcome.BANKER);
    assertEquals("595.00", p.getBalance().toString());
    assertEquals("95.00", winnings.toString());
  }

  @Test
  public void payoutForMultipleWagersOnTieShouldBeCorrectAmount() throws Exception {
    Player p = new Player("1000");
    p.placeWager(Outcome.PLAYER, "100");
    p.placeWager(Outcome.BANKER, "200");
    p.placeWager(Outcome.TIE, "300");
    BigDecimal winnings = p.settleWagers(Outcome.TIE);
    assertEquals("3400.00", p.getBalance().toString());
    assertEquals("2400.00", winnings.toString());
  }

}
