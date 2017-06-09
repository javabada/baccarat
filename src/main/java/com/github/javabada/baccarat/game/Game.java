package com.github.javabada.baccarat.game;

import com.github.javabada.baccarat.card.Shoe;

public class Game {

    private static final int SHOE_SIZE = 8;

    private Shoe shoe = new Shoe(SHOE_SIZE);

    public Shoe getShoe() {
        return shoe;
    }

    // TODO
    // burn()
    // change shoe / cut card

}
