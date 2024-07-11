package main.java.ru.clevertec.check.basket;

import main.java.ru.clevertec.check.exception.BadRequestException;
import main.java.ru.clevertec.check.products.Product;
import main.java.ru.clevertec.check.products.ProductFactory;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private List<Product> productList=new ArrayList<>();
    private Builder builder;
    private String numberDiscountCard;


    public Client() {
        builder = new BasketBuilder();
    }



    public void addProduct(List<Product> productList) throws BadRequestException {
        for (Product product: productList) {
            for(int i=0; i<ProductFactory.productList.size();i++){
                if(product.getId() == ProductFactory.productList.get(i).getId()
                && product.getQuantity()>ProductFactory.productList.get(i).getQuantity()){
                    throw new BadRequestException();
                }
            }
        }
        this.productList.addAll(productList);
    }

    public void addNumberDiscountCard(String number){
        this.numberDiscountCard = number;
    }




    public Basket formationBasket() {
        builder.addProductIntoBasket(productList);
        builder.addDiscountCard(numberDiscountCard);
        return builder.getBasket();
    }
}
