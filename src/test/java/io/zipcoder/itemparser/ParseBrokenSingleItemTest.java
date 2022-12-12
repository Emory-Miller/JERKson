package io.zipcoder.itemparser;

import io.zipcoder.ItemParser;
import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;
import org.junit.Test;

public class ParseBrokenSingleItemTest {
    @Test(expected = ItemParseException.class)
    public void test1() throws ItemParseException {
        // given
        ItemParser itemParser = new ItemParser();
        String valueToParse = "naMe;price:3.23;type:Food;expiration:1/25/2016##";

        // when
        itemParser.parseSingleItem(valueToParse); // throws exception
    }

    @Test(expected = ItemParseException.class)
    public void test2() throws ItemParseException {
        // given
        ItemParser itemParser = new ItemParser();
        String valueToParse = "naMe:eggS;price;type:Food;expiration:1/25/2016##";

        // when
        itemParser.parseSingleItem(valueToParse); // throws exception
    }


    @Test(expected = ItemParseException.class)
    public void test3() throws ItemParseException {
        // given
        ItemParser itemParser = new ItemParser();
        String valueToParse = "naMe:teleVision;price:323.5;type;expiration:3/25/2019##";

        // when
        itemParser.parseSingleItem(valueToParse); // throws exception
    }



    @Test(expected = ItemParseException.class)
    public void test4() throws ItemParseException {
        // given
        ItemParser itemParser = new ItemParser();
        String valueToParse = "naMe:teleVision;price:323.5;type:electRoniCs;expiration";

        // when
        itemParser.parseSingleItem(valueToParse); // throws exception
    }

    @Test(expected = ItemParseException.class)
    public void test5() throws ItemParseException {
        // given
        ItemParser itemParser = new ItemParser();
        String valueToParse = "that damn brown fox jumping over the lazy dog again";

        // when
        itemParser.parseSingleItem(valueToParse); // throws exception
    }

    @Test(expected = ItemParseException.class)
    public void test6() throws ItemParseException {
        // given
        ItemParser itemParser = new ItemParser();
        String valueToParse = "naMe:;price:;type:Food;expiration:1/04/2016##";

        // when
        itemParser.parseSingleItem(valueToParse); // throws exception
    }


}
