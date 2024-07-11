package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.cards.DiscountCard;
import main.java.ru.clevertec.check.cards.DiscountCardFactory;
import main.java.ru.clevertec.check.exception.BadRequestException;
import main.java.ru.clevertec.check.exception.InternakServerErrorException;
import main.java.ru.clevertec.check.products.Product;
import main.java.ru.clevertec.check.products.ProductFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ArgLine {
    private List<String> argList ;
    private List<Product> productList ;
    private DiscountCard discountCard;
    private double debitBalance = 0.0;

    public ArgLine() {
        productList=new ArrayList<>();
    }

    public void parsingArgs(String[] args) throws BadRequestException {
        argList=Arrays.asList(args);
        try {
            for (String arg : argList) {
                if (arg.startsWith("discountCard=")) {
                    String cardNumber = arg.substring(13);
                    DiscountCardFactory discountCardFactory = new DiscountCardFactory();
                    discountCard = discountCardFactory.creatDiscountCard(cardNumber);
                } else if (arg.startsWith("balanceDebitCard=")) {
                    debitBalance = Double.parseDouble(arg.substring(17));
                } else {
                    String[] fields = arg.split("-");
                    int id = Integer.parseInt(fields[0]);
                    int quantity = Integer.parseInt(fields[1]);
                    ProductFactory factory = new ProductFactory();
                    Product product = factory.creatProduct(id, quantity);
                    Optional<Product> optionalProduct = productList.stream()
                                                                   .filter(item -> item.getId() == id)
                                                                   .findFirst();

                    if (optionalProduct.isPresent()) {
                        Product existingProduct = optionalProduct.get();
                        existingProduct.setQuantity(existingProduct.getQuantity()+quantity);
                    } else {
                        productList.add(product);
                    }
                }
            }
        }catch (Exception e){
            throw new BadRequestException();
        }
    }

    public List<String> getArgList() {
        return argList;
    }

    public void setArgList(List<String> argList) {
        this.argList = argList;
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
}
