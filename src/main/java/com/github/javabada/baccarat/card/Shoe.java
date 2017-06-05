package com.github.javabada.baccarat.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shoe {

    private final List<Card> shoe = new ArrayList<>();

    public Shoe(int decks) {
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
            for (CardRank rank : CardRank.values()) {
                for (CardSuit suit : CardSuit.values()) {
                    shoe.add(new Card(rank, suit));
                }
            }
        }
        Collections.shuffle(shoe);
    }

}
