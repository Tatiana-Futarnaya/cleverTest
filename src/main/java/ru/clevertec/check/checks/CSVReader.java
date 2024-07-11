package main.java.ru.clevertec.check.checks;


import main.java.ru.clevertec.check.cards.DiscountCard;
import main.java.ru.clevertec.check.products.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tatiana Futarnaya
 */
public class CSVReader {
    public List<Product> readProductsFromCsv(String filename) {
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                int quantity = Integer.parseInt(parts[3]);
                boolean wholesaleProduct=Boolean.parseBoolean(parts[4]);
                Product product = new Product(id, name, price,quantity,wholesaleProduct);
                products.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }

    public List<DiscountCard> readDiscountCardFromCsv(String filename) {
        List<DiscountCard> cardList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String number = parts[1];
                int discountAmount= Integer.parseInt(parts[2]);
                DiscountCard discountCard=new DiscountCard(number,discountAmount);
                cardList.add(discountCard);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cardList;
    }
}
