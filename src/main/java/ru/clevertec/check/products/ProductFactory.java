package main.java.ru.clevertec.check.products;


import main.java.ru.clevertec.check.checks.CSVReader;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Tatiana Futarnaya
 */
public  class ProductFactory {
    private static final  String PRODUCT_PATH="./src/main/resources/products.csv";
    public static final List<Product> productList;
    private static final CSVReader csvReader;

    static {
        csvReader=new CSVReader();
        productList=csvReader.readProductsFromCsv(PRODUCT_PATH);
    }

    public static Product getProduct(int id) {
        try {
            return productList.stream()
                    .filter(product -> product.getId() == id)
                    .findFirst()
                    .get();
        } catch (NullPointerException | NoSuchElementException e) {
            return null;
        }
    }



    public Product creatProduct(int id, int quantity){
        return new Product(id,
                getProduct(id).getName(),
                getProduct(id).getPrice(),
                quantity,
                getProduct(id).isWholesaleProduct());
    }
}
