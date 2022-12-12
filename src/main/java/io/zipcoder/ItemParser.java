package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemParser {
    public List<Item> parseItemList(String valueToParse) throws ItemParseException {
        String lowerCase = valueToParse.toLowerCase();
        String[] delimString = lowerCase.split("##");
        List<Item> itemList = new ArrayList<>();
        try {
            for (String str : delimString) {
                Item item = parseSingleItem(str);
                itemList.add(item);
            }
        } catch ( ItemParseException e){
            System.out.println("Error");
        }
        return itemList;
    }

    public Item parseSingleItem(String singleItem) throws ItemParseException {
        String lowerCase = singleItem.toLowerCase();
        String[] delimString = lowerCase.split("[:;@\\^%#]");

        Item item = new Item(delimString[1], Double.parseDouble(delimString[3]), delimString[5], delimString[7]);

        return item;
    }
}
