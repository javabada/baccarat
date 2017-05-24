package logic;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Shoe {

    private final int decks; // Amount of standard 52-card decks being used (default = 8)
    private final List<Card> shoe;

    public Shoe(int decks) {
        this.decks = decks;
        this.shoe = new ArrayList<Card>();
        fill();
    }

    public Shoe() {
        this(8);
    }

    public int cardsRemaining() {
        return shoe.size();
    }

    public Card draw() {
        Card card = shoe.get(0);
        shoe.remove(0);
        return card;
    }

    private void fill() {
        for (int i = 0; i < decks; i++) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    shoe.add( new Card(suit, rank) );
                }
            }
        }

        Collections.shuffle(shoe);
    }

}
