package main.java.ru.clevertec.check.basket;

import main.java.ru.clevertec.check.products.Product;

import java.util.List;

public interface Builder {
    void addProductIntoBasket(List<Product> productList);
    Basket getBasket();
    void addDiscountCard(String numberDiscontCard);
}
