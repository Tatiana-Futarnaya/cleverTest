package main.java.ru.clevertec.check.basket;

import main.java.ru.clevertec.check.cards.DiscountCard;
import main.java.ru.clevertec.check.cards.DiscountCardFactory;
import main.java.ru.clevertec.check.products.Product;


import java.util.List;


public class BasketBuilder implements Builder{
    private Basket basket = new Basket();

    @Override
    public void addProductIntoBasket(List<Product> productList) {
        for (Product product: productList) {
            if (product != null) {
                basket.addProduct(product);
            }
        }
    }

    @Override
    public Basket getBasket() {
        return this.basket;
    }

    @Override
    public void addDiscountCard(String numberDiscountCard) {
        if (numberDiscountCard !=null) {
            DiscountCard discountCard = DiscountCardFactory.getCardList(numberDiscountCard);
            basket.setDiscountCard(discountCard);
        }
    }
}
