package io.zipcoder;

import io.zipcoder.utils.FileReader;
import io.zipcoder.utils.Item;

import java.util.HashSet;
import java.util.LinkedHashSet;
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

        Set<String> set = new LinkedHashSet<>();

        for (Item item : itemList){
            if (!item.getName().equals("")) {
                set.add(item.getName());
            }
        }

        StringBuilder sb = new StringBuilder();

        for (String str : set){
            sb.append(String.format("name:%8s\t\t seen: %d times\n", str, getSeenCount(str)));
            sb.append("============= 	 	 =============" + "\n");
            sb.append("Price:" + "\n");
        }

        System.out.println(sb.toString());

        return itemList.toString();
    }

    public int getSeenCount(String str){
        ItemParser ip = new ItemParser();
        List<Item> itemList = ip.parseItemList(originalFileText);
        int count = 0;
        for (Item item : itemList){
            if(item.getName().equals(str)){
                count++;
            }
        }
        return count;
    }
}
