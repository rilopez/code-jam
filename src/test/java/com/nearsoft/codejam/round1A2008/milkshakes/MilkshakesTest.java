package com.nearsoft.codejam.round1A2008.milkshakes;

import java.io.FileReader;
import java.io.StringReader;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MilkshakesTest {
    @Test
    public void testExecute() throws Exception {
        String sampleInput = "2\n" +
            "5\n" +
            "3\n" +
            "1 1 1\n" +
            "2 1 0 2 0\n" +
            "1 5 0\n" +
            "1\n" +
            "2\n" +
            "1 1 0\n" +
            "1 1 1";
        String expectedOutput = "Case #1: 1 0 0 0 0\n" + "Case #2: IMPOSSIBLE";

        Milkshakes milkshakes = new Milkshakes();
        Assert.assertEquals(expectedOutput, milkshakes.execute(new StringReader(sampleInput)));
    }

    @Test
    public void parseCustomerOrder() {
        Milkshakes milkshakes = new Milkshakes();
        Map<Integer, Integer> customerOrder = milkshakes.parseCustomerOrder("3 4 0 1 1 2 0");
        assertEquals(3,customerOrder.size());
        assertEquals(Integer.valueOf(0),customerOrder.get(4));
        assertEquals(Integer.valueOf(1),customerOrder.get(1));
        assertEquals(Integer.valueOf(0),customerOrder.get(2));

    }

    @Test
    public void testLargeInput() throws Exception {
        String largeInput = getClass().getResource("C-large-practice.in").getPath();
        Milkshakes milkshakes = new Milkshakes();
        System.out.println(milkshakes.execute(new FileReader(largeInput)));
    }

    @Test
    public void tesSmallInput() throws Exception {
        String smallInput = getClass().getResource("C-small-practice.in").getPath();
        Milkshakes milkshakes = new Milkshakes();
        System.out.println(milkshakes.execute(new FileReader(smallInput)));
    }
}