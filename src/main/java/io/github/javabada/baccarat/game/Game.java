package io.github.javabada.baccarat.game;

import io.github.javabada.baccarat.card.Card;
import io.github.javabada.baccarat.card.Shoe;

public class Game {

  private Shoe shoe;

  public Game() {}

  public void newShoe(int decks) {
    shoe = new Shoe(decks);
    shoe.prepare();
  }

  public Card burnShoe() {
    Card topCard = shoe.draw();
    int cardsToBurn = (topCard.getValue() == 0 ? 10 : topCard.getValue()) - 1;
    for (int i = 0; i < cardsToBurn; i++) {
      shoe.draw();
    }
    return topCard;
  }

  public Coup playNewCoup() {
    Coup coup = new Coup(shoe);
    coup.play();
    return coup;
  }

}
