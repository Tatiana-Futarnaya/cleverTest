package main.java.ru.clevertec.check.discounts;

import main.java.ru.clevertec.check.cards.DiscountCard;

/**
 * @author Tatiana Futarnaya
 */
public class DiscountForCard extends Discount {
    private DiscountCard discountCard;

    public DiscountForCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
    }

    public double calculate(int quantity, double price) {
        return  (price * quantity * discountCard.getDiscountAmount()/ 100);
    }

}
