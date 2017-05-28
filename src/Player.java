import java.math.BigDecimal;

public class Player {

    private static final int SCALE = 2;
    private static final int ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;

    private BigDecimal balance;

    public Player() {
        balance = BigDecimal.ZERO;
        balance = balance.setScale(SCALE, ROUNDING_MODE);
    }

    public boolean setInitialBalance(int amount) {
        if (amount <= 0) {
            return false;
        }
        else {
            balance = new BigDecimal(amount);
            balance = balance.setScale(SCALE, ROUNDING_MODE);
            return true;
        }
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public boolean bet(int amount) {
        BigDecimal wager = new BigDecimal(amount);
        wager = wager.setScale(SCALE, ROUNDING_MODE);
        if (balance.compareTo(wager) < 0) {
            return false;
        }
        else {
            balance = balance.subtract(wager);
            balance = balance.setScale(SCALE, ROUNDING_MODE);
            return true;
        }
    }

}
