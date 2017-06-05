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

    public CardRank getRank() {
        return rank;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (rank != card.rank) return false;
        return suit == card.suit;
    }

    @Override
    public int hashCode() {
        int result = rank.hashCode();
        result = 31 * result + suit.hashCode();
        return result;
    }

}
