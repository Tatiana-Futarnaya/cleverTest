package main.java.ru.clevertec.check.discounts;

/**
 * @author Tatiana Futarnaya
 */
public class WholesaleDiscount extends Discount {
    private static final int DISCOUNT_PERCENTAGE = 10;

    public double calculate(int quantity, double price) {
        return  (price * quantity * DISCOUNT_PERCENTAGE / 100);
    }


}
