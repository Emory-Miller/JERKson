package io.zipcoder;

import io.zipcoder.utils.FileReader;
import io.zipcoder.utils.Item;

import java.util.List;
import java.util.Set;

public class GroceryReporter {
    private final String originalFileText;

    public GroceryReporter(String jerksonFileName) {
        this.originalFileText = FileReader.readFile(jerksonFileName);
    }

    @Override
    public String toString() {
        ItemParser ip = new ItemParser();
        List<Item> itemList = ip.parseItemList(originalFileText);

        for (Item item : itemList){

        }

        return itemList.toString();
    }
}
