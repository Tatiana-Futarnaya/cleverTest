package main.java.ru.clevertec.check.basket;

import main.java.ru.clevertec.check.cards.DiscountCard;
import main.java.ru.clevertec.check.discounts.Discount;
import main.java.ru.clevertec.check.discounts.DiscountForCard;
import main.java.ru.clevertec.check.discounts.WholesaleDiscount;
import main.java.ru.clevertec.check.exception.NotEnoughMoneyException;
import main.java.ru.clevertec.check.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private final List<Product> productList=new ArrayList<>();
    private String forBill = "";
    private double totalPrice = 0.0;
    private double totalDiscounts = 0.0;
    private DiscountCard discountCard;
    private double totalProduct=0.0;
    private double totalDiscount=0.0;


    public void addProduct(Product product) {
        productList.add(product);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public String printBill() {
        return forBill;
    }

    public Discount chooseDiscount(Product product){
        Discount discount;
        if (product.isWholesaleProduct() && product.getQuantity() > 5){
            discount = new WholesaleDiscount();
        }else{
            discount = new DiscountForCard(discountCard);
        }
        return discount;
    }

    public double costDiscount(Product product){
        Discount discount;
        discount=chooseDiscount(product);
        totalDiscount=discount.calculate(product.getQuantity(),product.getPrice());
        return totalDiscount;
    }

    public void costBasket(){
        for (Product product : productList) {
            int quantity=product.getQuantity();
            totalProduct = product.calculateCost(quantity);
            totalDiscount=costDiscount(product);
            this.forBill += String.format("%4d %-22s %7.2f %7.2f %7.2f\n",
                    quantity, product.getName(), product.getPrice(), totalDiscount, totalProduct);
            totalPrice+=totalProduct;
            totalDiscounts+=totalDiscount;
        }
    }




    public double getTotalProduct() {
        return totalProduct;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getTotalDiscounts() {
        return totalDiscounts;
    }

    public double getFinalTotal() {
        return totalPrice - totalDiscounts;
    }


    public void setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }
}

