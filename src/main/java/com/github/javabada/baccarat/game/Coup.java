package com.github.javabada.baccarat.game;

import com.github.javabada.baccarat.card.Card;

public class Coup {

    private int cardsDealt = 0;
    private boolean playerStanding = false;
    private boolean bankerStanding = false;

    private int playerScore = 0;
    private int bankerScore = 0;
    private boolean playerHand = true;
    private boolean finished = false;
    private Outcome winningBet;

    public int getPlayerScore() {
        return playerScore;
    }

    public int getBankerScore() {
        return bankerScore;
    }

    public boolean isPlayerHand() {
        return playerHand;
    }

    public boolean isFinished() {
        return finished;
    }

    public Outcome getWinningBet() {
        if (!finished) throw new IllegalStateException();
        return winningBet;
    }

    public void deal(Card card) {
        if (finished) throw new IllegalStateException();

        cardsDealt++;

        switch (cardsDealt) {
            case 1:
                playerScore = card.getValue();
                playerHand = true;
                break;

            case 2:
                bankerScore = card.getValue();
                playerHand = false;
                break;

            case 3:
                playerScore = (playerScore + card.getValue()) % 10;
                playerHand = true;
                break;

            case 4:
                bankerScore = (bankerScore + card.getValue()) % 10;
                playerHand = false;
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
                    playerHand = true;
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
                    playerHand = false;
                    bankerStanding = true;
                }
                break;

            case 6:
                bankerScore = (bankerScore + card.getValue()) % 10;
                playerHand = false;
                bankerStanding = true;
                break;
        }

        if (playerStanding && bankerStanding) {
            finished = true;

            if (playerScore > bankerScore) {
                winningBet = Outcome.PLAYER;
            }
            else if (bankerScore > playerScore) {
                winningBet = Outcome.BANKER;
            }
            else {
                winningBet = Outcome.TIE;
            }
        }
    }

}
