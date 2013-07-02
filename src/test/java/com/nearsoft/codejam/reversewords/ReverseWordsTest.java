package com.nearsoft.codejam.reversewords;

import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReverseWordsTest {



    @Test
    public void testSmall() throws Exception {
        String smallInput = getClass().getResource("B-small-practice.in").getPath();

        ReverseWords rw = new ReverseWords();

        System.out.println(rw.execute(new FileReader(smallInput)));
    }
    @Test
    public void testLarge() throws Exception {
        String smallInput = getClass().getResource("B-large-practice.in").getPath();

        ReverseWords rw = new ReverseWords();

        System.out.println(rw.execute(new FileReader(smallInput)));
    }


    @Test
    public void testExecute() throws Exception {

        ReverseWords rw = new ReverseWords();
        String input = "3\n" +
            "this is a test\n" +
            "foobar\n" +
            "all your base";
        String expectedOutput = "Case #1: test a is this\n" +
            "Case #2: foobar\n" +
            "Case #3: base your all\n";
        String output = rw.execute(new StringReader(input));
        assertEquals(expectedOutput, output);
    }
}
