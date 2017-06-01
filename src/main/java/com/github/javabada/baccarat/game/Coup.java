package com.github.javabada.baccarat.game;

import com.github.javabada.baccarat.card.Card;
import com.github.javabada.baccarat.card.Shoe;

public class Coup {

    private final Shoe shoe;
    private int cardsDealt;
    private int playerScore;
    private int bankerScore;
    private boolean playerStanding;
    private boolean bankerStanding;
    private boolean coupFinished;
    private int outcome;

    public Coup(Shoe shoe) {
        this.shoe = shoe;
        this.cardsDealt = 0;
        this.playerScore = 0;
        this.bankerScore = 0;
        this.playerStanding = false;
        this.bankerStanding = false;
        this.coupFinished = false;
        this.outcome = 0;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getBankerScore() {
        return bankerScore;
    }

    public boolean isPlayerStanding() {
        return playerStanding;
    }

    public boolean isBankerStanding() {
        return bankerStanding;
    }

    public boolean isCoupFinished() {
        return coupFinished;
    }

    /*
     * outcome = final player score - final banker score
     * positive = player win, negative = banker win, 0 = tie
     */
    public int getOutcome() {
        return outcome;
    }

    /*
     * Every time deal() is called a card is drawn from the shoe, whether the card is for the player hand
     * or the banker hand is determined in the method. This is done to make the game more interactive,
     * e.g. the user clicks a button to receive another card.
     *
     * Note: deal() should only be called while isCoupFinished() == false
     */
    public Card deal() {
        if (coupFinished) {
            return null; // should not happen
        }

        Card card = shoe.draw();
        cardsDealt++;

        switch (cardsDealt) {
            case 1:
                playerScore = card.getValue();
                break;
            case 2:
                bankerScore = card.getValue();
                break;
            case 3:
                playerScore = (playerScore + card.getValue()) % 10;
                break;
            case 4:
                bankerScore = (bankerScore + card.getValue()) % 10;
                if (playerScore > 7 || bankerScore > 7) {
                    playerStanding = true;
                    bankerStanding = true;
                }
                else {
                    if (playerScore > 5) {
                        playerStanding = true;
                    }
                    if (playerStanding && bankerScore > 5) {
                        bankerStanding = true;
                    }
                }
                break;
            case 5:
                if (!playerStanding) {
                    playerScore = (playerScore + card.getValue()) % 10;
                    playerStanding = true;
                    if (((card.getValue() == 2 || card.getValue() == 3) && bankerScore > 4) ||
                        ((card.getValue() == 4 || card.getValue() == 5) && bankerScore > 5) ||
                        ((card.getValue() == 6 || card.getValue() == 7) && bankerScore > 6) ||
                        (card.getValue() == 8 && bankerScore > 2) ||
                        ((card.getValue() == 9 || card.getValue() < 2) && bankerScore > 3)) {
                        bankerStanding = true;
                    }
                }
                else {
                    bankerScore = (bankerScore + card.getValue()) % 10;
                    bankerStanding = true;
                }
                break;
            case 6:
                bankerScore = (bankerScore + card.getValue()) % 10;
                bankerStanding = true;
                break;
        }

        if (playerStanding && bankerStanding) {
            coupFinished = true;
            outcome = playerScore - bankerScore;
        }

        return card;
    }

}
