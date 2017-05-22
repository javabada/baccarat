import logic.Card;
import logic.Shoe;

public class Baccarat {

    public static void main(String[] args) {
        Shoe testShoe = new Shoe();
        for (int i = 0; i < 10; i++) {
            Card card = testShoe.draw();
            System.out.println(card);
        }
        System.out.println(testShoe.cardsRemaining());
    }

}
