package com.github.javabada.baccarat.card;

public class Card {

    private final CardRank rank;
    private final CardSuit suit;
    private final int value;

    public Card(CardRank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
        this.value = rank.getValue();
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return (rank + " of " + suit).toLowerCase();
    }

}
