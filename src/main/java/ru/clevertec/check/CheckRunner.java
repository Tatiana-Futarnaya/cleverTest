package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.basket.Basket;
import main.java.ru.clevertec.check.basket.Client;
import main.java.ru.clevertec.check.cards.DiscountCard;
import main.java.ru.clevertec.check.checks.CSVWriter;
import main.java.ru.clevertec.check.checks.Check;
import main.java.ru.clevertec.check.exception.BadRequestException;
import main.java.ru.clevertec.check.exception.InternakServerErrorException;
import main.java.ru.clevertec.check.exception.NotEnoughMoneyException;

/**
 * @author Tatiana Futarnaya
 */


public class CheckRunner {
    public static void main(String[] args) {
        CSVWriter writer = new CSVWriter();
       ArgLine argLine =new ArgLine();
        try {
            argLine.parsingArgs(args);
            DiscountCard discountCard= argLine.getDiscountCard();

            Client customer=new Client();
            customer.addNumberDiscountCard(discountCard.getCardNumber());
            customer.addProduct(argLine.getProductList());
            Basket basket = customer.formationBasket();


            Check check = new Check(basket);
            check.setDebitBalance(argLine.getDebitBalance());
            check.printCheck();
            writer.writeCheckToCsv(check);
        } catch ( NotEnoughMoneyException | BadRequestException e) {
            writer.writeExceptionToCsv(e);
        }

    }
}
