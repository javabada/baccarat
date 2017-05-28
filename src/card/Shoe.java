package card;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Shoe {

    private final List<Card> shoe;

    public Shoe(int decks) {
        shoe = new ArrayList<>();
        fill(decks);
    }

    public int cardsRemaining() {
        return shoe.size();
    }

    public Card draw() {
        return shoe.remove(0);
    }

    private void fill(int decks) {
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
