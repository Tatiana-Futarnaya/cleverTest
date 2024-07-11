package main.java.ru.clevertec.check.checks;


import main.java.ru.clevertec.check.basket.Basket;
import main.java.ru.clevertec.check.cards.DiscountCard;
import main.java.ru.clevertec.check.exception.NotEnoughMoneyException;
import main.java.ru.clevertec.check.products.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Tatiana Futarnaya
 */
public class Check {
    private List<Product> productList;
    private DiscountCard discountCard;
    private Basket basket;
    private double debitBalance;
    private static Date date = new Date();
    private String bill = "              CASH RECEIPT\n";

    public Check(Basket basket) {
        productList = new ArrayList<>();
        this.basket=basket;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Product product : productList) {
            total += product.calculateCost(product.getQuantity());
        }
        return total;
    }



    public String getDate() {
        SimpleDateFormat formatterForDate = new SimpleDateFormat("dd.MM.yyyy");
        return formatterForDate.format(date);
    }

    public String getTime() {
        SimpleDateFormat formatterForTime = new SimpleDateFormat("HH:mm:ss");
        return formatterForTime.format(date);
    }

    public String printCheck() throws NotEnoughMoneyException {
        bill += "   DATE: " + getDate() + "      TIME: " + getTime() + "\n\n";
        bill += String.format("%4s %-22s %7s %7s %7s\n\n", "QTY", "DESCRIPTION", "PRICE","DISCOUNT", "TOTAL");
        basket.costBasket();
        checkEnoughMoney(debitBalance,basket);
        bill += basket.printBill();
        for (int i = 0; i < 43; i++)
            bill += "-";
        bill += String.format("\n  Taxable: %32.2f\n", basket.getTotalPrice());
        bill += String.format("  Tax:     %32.2f\n", basket.getTotalDiscounts());
        bill += String.format("  TOTAL:   %32.2f", basket.getFinalTotal());
        System.out.println(bill);
        return bill;
    }

    public void checkEnoughMoney(double debit, Basket basket) throws NotEnoughMoneyException {
        if(debit< basket.getFinalTotal()){
            throw new NotEnoughMoneyException("BAD REQUEST");
        }
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public void setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
    }

    public double getDebitBalance() {
        return debitBalance;
    }

    public void setDebitBalance(double debitBalance) {
        this.debitBalance = debitBalance;
    }

    public Basket getBasket() {
        return basket;
    }
}


