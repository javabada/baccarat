package logic;

public class Card {

    private final Suit suit;
    private final Rank rank;
    private final int value;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.value = rank.getValue();
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + " - " + rank + " of " + suit;
    }

}
