package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ItemParser {
    public List<Item> parseItemList(String valueToParse)  {
        String lowerCase = valueToParse.toLowerCase();
        String[] delimString = lowerCase.split("##");
        List<Item> itemList = new ArrayList<>();


        for (String str : delimString) {
            try {
                Item item = parseSingleItem(str);
                itemList.add(item);
            } catch (ItemParseException e){}
        }
        return itemList;
    }

    public Item parseSingleItem(String singleItem) throws ItemParseException {

        if( singleItem.contains(" ")) throw new ItemParseException();

        String lowerCase = singleItem.toLowerCase();
        String[] str = lowerCase.split("[:;@\\^%#*!]");

        HashMap<String, String> hash = new HashMap<>();

        for (int i = 0; i <str.length; i++){
            str[i] = cookieMonster(str[i]);
        }

        try {
            for (int i = 0; i < str.length; i++) {

                if (str[i].equals("name") && !str[i + 1].equals("price")) {
                    hash.put("name", str[i + 1]);
                } else if (str[i].equals("name") && str[i + 1].equals("price")) {
                    hash.put("name", " ");
                    throw new ItemParseException();

                } else if (str[i].equals("price") && str[i + 1].equals("type")) {
                    hash.put("price", "0.0");
                    throw new ItemParseException();
                } else if (str[i].equals("price") && !str[i + 1].equals("type") && str[i+1].equals("")) {
                    hash.put("price", "0.0");
                    throw new ItemParseException();
                } else if (str[i].equals("price") && !str[i + 1].equals("type")) {
                    hash.put("price", str[i + 1]);

                } else if (str[i].equals("type") && !str[i + 1].equals("expiration")) {
                    hash.put("type", str[i + 1]);
                } else if (str[i].equals("type") && str[i + 1].equals("expiration")) {
                    hash.put("type", " ");
                    throw new ItemParseException();

                } else if (str[i].equals("expiration") && i + 1 != str.length) {
                    hash.put("expiration", str[i + 1]);
                } else if (str[i].equals("expiration") && i + 1 == str.length) {
                    hash.put("expiration", " ");
                    throw new ItemParseException();
                }
            }
        } catch (IndexOutOfBoundsException e) {}
        return new Item (hash.get("name"), Double.parseDouble(hash.get("price")), hash.get("type"), hash.get("expiration"));
    }

    public String cookieMonster (String str){
        if (str.equals("co0kies"))
            return "cookies";
        else return str;
    }
}
