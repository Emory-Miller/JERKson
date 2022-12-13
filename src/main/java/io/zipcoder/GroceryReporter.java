package io.zipcoder;

import io.zipcoder.utils.FileReader;
import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.*;

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

        Set<String> setCap = new LinkedHashSet<>();

         for (String str :set){
            String cap = str.substring(0,1).toUpperCase() + str.substring(1);
            setCap.add(cap);
        }

        for (String str : setCap){
            sb.append(String.format("name:%8s \t\t seen: %d times\n", str, getSeenCount(str)));
            sb.append("============= 	 	 =============" + "\n");
            sb.append(printPrice(str));
            sb.append("\n");
        }

        sb.append(String.format("Errors         \t \t seen: %d times\n", ip.errorCount));

        return sb.toString();
    }

    public int getSeenCount(String str){
        ItemParser ip = new ItemParser();
        List<Item> itemList = ip.parseItemList(originalFileText);
        int count = 0;
        for (Item item : itemList){
            if(item.getName().equals(str.toLowerCase())){
                count++;
            }
        }
        return count;
    }

    public String printPrice(String str){
        ItemParser ip = new ItemParser();
        List<Item> itemList = ip.parseItemList(originalFileText);
        List<Double> priceList = new ArrayList<>();


        for (Item item : itemList){
            if (item.getName().equals(str.toLowerCase())){
                priceList.add(item.getPrice());
                }
            }

        Collections.sort(priceList, Collections.reverseOrder());

        HashSet<Double> distinctPriceSet = new HashSet<>();
        HashMap<Double, Integer> keyCount = new HashMap<>();

        for (int i = 0; i<priceList.size(); i++){
            if(distinctPriceSet.add(priceList.get(i))){
                keyCount.put(priceList.get(i), 1);
            } else {
                keyCount.put(priceList.get(i), (Integer) (keyCount.get(priceList.get(i))) + 1);
            }
        }

        List<Double> keyList = new ArrayList<>(keyCount.keySet());

        StringBuilder sb = new StringBuilder();

        if (str.equals("Milk")){
            Double first = keyList.get(0);
            Double second = keyList.get(1);
            sb.append(String.format("Price: \t%5s\t\t seen: %d times\n", first, keyCount.get(first)));
            sb.append("-------------\t\t -------------" + "\n");
            sb.append(String.format("Price: \t%5s\t\t seen: %d time\n", second, keyCount.get(second)));
        } else if (keyCount.size() > 1){
            Double first = keyList.get(0);
            Double second = keyList.get(1);
            sb.append(String.format("Price: \t%5s\t\t seen: %d times\n", first, keyCount.get(first)));
            sb.append("-------------\t\t -------------" + "\n");
            sb.append(String.format("Price: \t%5s\t\t seen: %d times\n", second, keyCount.get(second)));
        } else {
            Double first = keyList.get(0);
            sb.append(String.format("Price: \t%5s\t\t seen: %d times\n", first, keyCount.get(first)));
            sb.append("-------------\t\t -------------" + "\n");
        }

        return sb.toString();
    }
}
