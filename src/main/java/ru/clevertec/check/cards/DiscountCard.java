package main.java.ru.clevertec.check.cards;



/**
 * @author Tatiana Futarnaya
 */
public class DiscountCard {
    private String cardNumber;
    private int discountAmount;

    public DiscountCard() {
    }

    public DiscountCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public DiscountCard(String cardNumber, int discountAmount) {
        this.cardNumber = cardNumber;
        this.discountAmount = discountAmount;
    }


    public String getCardNumber() {
        return cardNumber;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", discount_amount=" + discountAmount +
                '}';
    }
}
