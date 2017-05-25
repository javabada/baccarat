import logic.Card;
import logic.Shoe;
import logic.Coup;

public class Baccarat {

    public static void main(String[] args) {
        Shoe shoe = new Shoe();
        for (int i = 0; i < 5; i++) {
            Coup coup = new Coup(shoe);
            while (!coup.isCoupOver()) {
                Card card = coup.deal();
                System.out.println(card);
            }
            System.out.println(coup.getOutcome());
        }
        System.out.println(shoe.cardsRemaining());
    }

}
