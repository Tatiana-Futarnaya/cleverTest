package main.java.ru.clevertec.check.cards;



import main.java.ru.clevertec.check.checks.CSVReader;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class DiscountCardFactory {
    private static final  String DISCOUNT_CARDS_PATH="./src/main/resources/discountCards.csv";
    private static List<DiscountCard> cardList = new ArrayList<>();
    private static CSVReader csvReader;

    static {
        csvReader=new CSVReader();
        cardList=csvReader.readDiscountCardFromCsv(DISCOUNT_CARDS_PATH);
    }

    public static DiscountCard getCardList(String cardNumber) {
        try {
            return cardList.stream()
                              .filter(card -> card.getCardNumber().equals(cardNumber))
                              .findFirst()
                              .get();
        } catch (NullPointerException | NoSuchElementException e) {
            return null;
        }
    }

    public DiscountCard creatDiscountCard(String cardNumber){
        DiscountCard discountCard;
        if(getCardList(cardNumber)==null){
            discountCard=new DiscountCard(cardNumber, 2);
        }else{
            discountCard=new DiscountCard(cardNumber,getCardList(cardNumber).getDiscountAmount());
        }
        return discountCard;
    }
}
