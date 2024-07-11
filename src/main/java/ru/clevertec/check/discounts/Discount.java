package main.java.ru.clevertec.check.discounts;

import main.java.ru.clevertec.check.products.Product;

/**
 * @author Tatiana Futarnaya
 */
public abstract class Discount extends Product {
    public abstract double calculate(int quantity, double price);
}
