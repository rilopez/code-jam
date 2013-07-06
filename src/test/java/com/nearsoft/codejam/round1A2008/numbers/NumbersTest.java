package com.nearsoft.codejam.round1A2008.numbers;

import java.io.FileReader;
import java.io.StringReader;
import org.junit.Assert;
import org.junit.Test;

public class NumbersTest {
    @Test
    public void testExecute() throws Exception {
        String sampleInput = "2\n" +
            "5\n" +
            "2";
        String expectedOutput = "Case #1: 935\n" + "Case #2: 027";

        Numbers numbers = new Numbers();
        Assert.assertEquals(expectedOutput, numbers.execute(new StringReader(sampleInput)));
    }

    @Test
    public void testLargeInput() throws Exception {
        String largeInput = getClass().getResource("C-large-practice.in").getPath();
        Numbers numbers = new Numbers();
        System.out.println(numbers.execute(new FileReader(largeInput)));
    }

    @Test
    public void tesSmallInput() throws Exception {
        String smallInput = getClass().getResource("C-small-practice.in").getPath();
        Numbers numbers = new Numbers();
        System.out.println(numbers.execute(new FileReader(smallInput)));
    }
}