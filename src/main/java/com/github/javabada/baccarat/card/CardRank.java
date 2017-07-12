package com.github.javabada.baccarat.card;

public enum CardRank {

  ACE   (1),
  TWO   (2),
  THREE (3),
  FOUR  (4),
  FIVE  (5),
  SIX   (6),
  SEVEN (7),
  EIGHT (8),
  NINE  (9),
  TEN   (0),
  JACK  (0),
  QUEEN (0),
  KING  (0);

  private final int value;

  CardRank(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

}
