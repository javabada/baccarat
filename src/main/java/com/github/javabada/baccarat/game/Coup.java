package com.github.javabada.baccarat.game;

import com.github.javabada.baccarat.card.Card;
import com.github.javabada.baccarat.card.Shoe;

public class Coup {

    public enum Hand {
        PLAYER,
        BANKER
    }

    private int playerScore = 0;
    private int bankerScore = 0;
    private int cardNumber = 0;
    private Hand currentHand = Hand.PLAYER;
    private boolean finished = false;
    private Outcome winner;

    private final Shoe shoe;
    private boolean playerStanding = false;
    private boolean bankerStanding = false;

    public Coup(Shoe shoe) {
        this.shoe = shoe;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getBankerScore() {
        return bankerScore;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public Hand getCurrentHand() {
        return currentHand;
    }

    public boolean isFinished() {
        return finished;
    }

    public Outcome getWinner() {
        if (!finished) {
            throw new IllegalStateException();
        }
        return winner;
    }

    public Card deal() {
        if (finished) {
            throw new IllegalStateException();
        }

        Card card = shoe.draw();
        cardNumber++;

        switch (cardNumber) {
            case 1:
                playerScore = card.getValue();
                currentHand = Hand.PLAYER;
                break;
            case 2:
                bankerScore = card.getValue();
                currentHand = Hand.BANKER;
                break;
            case 3:
                playerScore = (playerScore + card.getValue()) % 10;
                currentHand = Hand.PLAYER;
                break;
            case 4:
                bankerScore = (bankerScore + card.getValue()) % 10;
                currentHand = Hand.BANKER;
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
                    currentHand = Hand.PLAYER;
                    playerStanding = true;
                    if (((card.getValue() == 2 || card.getValue() == 3) && bankerScore > 4) ||
                        ((card.getValue() == 4 || card.getValue() == 5) && bankerScore > 5) ||
                        ((card.getValue() == 6 || card.getValue() == 7) && bankerScore > 6) ||
                         (card.getValue() == 8                          && bankerScore > 2) ||
                        ((card.getValue() == 9 || card.getValue() <  2) && bankerScore > 3)) {
                        bankerStanding = true;
                    }
                }
                else {
                    bankerScore = (bankerScore + card.getValue()) % 10;
                    currentHand = Hand.BANKER;
                    bankerStanding = true;
                }
                break;
            case 6:
                bankerScore = (bankerScore + card.getValue()) % 10;
                currentHand = Hand.BANKER;
                bankerStanding = true;
                break;
        }

        if (playerStanding && bankerStanding) {
            finished = true;
            if (playerScore > bankerScore) {
                winner = Outcome.PLAYER;
            }
            else if (bankerScore > playerScore) {
                winner = Outcome.BANKER;
            }
            else {
                winner = Outcome.TIE;
            }
        }

        return card;
    }

}
