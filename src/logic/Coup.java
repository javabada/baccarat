package logic;

public class Coup {

    private final Shoe shoe;
    private int cardsDealt;
    private int playerScore;
    private int bankerScore;
    private boolean playerStanding;
    private boolean bankerStanding;
    private boolean coupOver;
    private int outcome; // Positive = player win, Negative = banker win, 0 = tie

    public Coup(Shoe shoe) {
        this.shoe = shoe;
        this.cardsDealt = 0;
        this.playerScore = 0;
        this.bankerScore = 0;
        this.playerStanding = false;
        this.bankerStanding = false;
        this.coupOver = false;
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

    public boolean isCoupOver() {
        return coupOver;
    }

    public int getOutcome() {
        return outcome;
    }

    public Card deal() {
        if (coupOver) {
            return null; // Should not happen if isCoupOver() is checked before calling deal()
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
                if (playerScore > 7 || bankerScore > 7) { // Natural, both hands stand
                    playerStanding = true;
                    bankerStanding = true;
                }
                else {
                    if (playerScore > 5) { // Player stands with 6 or 7
                        playerStanding = true;
                    }
                    if (playerStanding && bankerScore > 5) { // If player stands, banker stands with 6 or 7
                        bankerStanding = true;
                    }
                }
                break;
            case 5:
                if (!playerStanding) { // Player draws 3rd card
                    playerScore = (playerScore + card.getValue()) % 10;
                    playerStanding = true;
                    // Determine if banker should stand or draw 3rd card after player draws 3rd card
                    if (((card.getValue() == 2 || card.getValue() == 3) && bankerScore > 4) ||
                        ((card.getValue() == 4 || card.getValue() == 5) && bankerScore > 5) ||
                        ((card.getValue() == 6 || card.getValue() == 7) && bankerScore > 6) ||
                        (card.getValue() == 8 && bankerScore > 2) ||
                        ((card.getValue() == 9 || card.getValue() < 2) && bankerScore > 3)) {
                        bankerStanding = true;
                    }
                }
                else { // Player stood, banker draws 3rd card
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
            coupOver = true;
            outcome = playerScore - bankerScore;
        }

        return card;
    }

}
