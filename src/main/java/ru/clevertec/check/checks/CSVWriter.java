package main.java.ru.clevertec.check.checks;


import main.java.ru.clevertec.check.exception.BadRequestException;
import main.java.ru.clevertec.check.exception.InternakServerErrorException;
import main.java.ru.clevertec.check.exception.NotEnoughMoneyException;
import main.java.ru.clevertec.check.products.Product;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Tatiana Futarnaya
 */
public class CSVWriter {

    private static final String RESULT_PATH="./src/main/result.csv";
    public void writeCheckToCsv(Check check) {
        try (FileWriter writer = new FileWriter(RESULT_PATH)) {

            writer.append("Data").append(";").append("Time\n");
            writer.append(check.getDate()).append(";").append(check.getTime()+"\n");
            writer.append("\n");

            writer.append("QTY").append(";")
                  .append("DESCRIPTION").append(";")
                    .append("PRICE").append(";")
                    .append("DISCOUNT").append(";")
                    .append("TOTAL").append('\n');
            for (Product product : check.getBasket().getProductList()) {
                writer.append(String.valueOf(product.getQuantity())).append(";")
                      .append(product.getName()).append(";")
                      .append(product.getPrice()+"$").append(";")
                        .append(String.format("%.2f$", check.getBasket().costDiscount(product))).append(";")
                        .append(product.calculateCost(product.getQuantity())+"$").append("\n");
            }

            double totalCost = check.getBasket().getTotalPrice();
            double discount = check.getBasket().getTotalDiscounts();
            double finalCost = totalCost - discount;

            writer.append("\n");
            writer.append("DISCOUNT CARD").append(";").append("DISCOUNT PERCENTAGE").append("\n");
            writer.append(check.getBasket().getDiscountCard().getCardNumber()).append(";")
                          .append(check.getBasket().getDiscountCard().getDiscountAmount()+"%").append("\n");
            writer.append("\n");

            writer.append("TOTAL PRICE").append(";")
                  .append("TOTAL DISCOUNT").append(";")
                  .append("TOTAL WITH DISCOUNT").append("\n");
            writer.append(totalCost+"$").append(";")
                          .append(String.format("%.2f",discount)+"$").append(";")
                          .append(finalCost+"$");


            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeExceptionToCsv(Exception e){
        try (FileWriter writer = new FileWriter(RESULT_PATH)) {

            if(e instanceof BadRequestException){
                writer.append("ERROR").append("\n");
                writer.append("BAD REQUEST");
            }

            if(e instanceof InternakServerErrorException){
                writer.append("ERROR").append("\n");
                writer.append("INTERNAL SERVER ERROR");
            }

            if(e instanceof NotEnoughMoneyException){
                writer.append("ERROR").append("\n");
                writer.append("NOT ENOUGH MONEY");
            }


            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
