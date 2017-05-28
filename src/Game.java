import card.Shoe;

public class Game {

    private int minWager = 0;
    private int maxWager = 0;
    private Shoe shoe;
    private Player player;

    public Game() {
    }

    public boolean setMinWager(int amount) {
        if (amount <= 0 || amount >= maxWager) {
            return false;
        }
        else {
            minWager = amount;
            return true;
        }
    }

    public int getMinWager() {
        return minWager;
    }

    public boolean setMaxWager(int amount) {
        if (amount <= minWager) {
            return false;
        }
        else {
            maxWager = amount;
            return true;
        }
    }

    public int getMaxWager() {
        return maxWager;
    }

    /*
     * TODO
     * burn()
     * newShoe()
     *
     */


}
