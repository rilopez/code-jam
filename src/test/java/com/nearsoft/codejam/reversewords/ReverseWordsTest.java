package com.nearsoft.codejam.reversewords;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReverseWordsTest {

    private static final String BASE_PATH = "/home/ilopez/ril/docs/google-code-jam/store-credit/src/test/resources/com/nearsoft/codejam/";



    @Test
    public void testSmall() throws Exception {
        String smallInput = FileUtils.readFileToString(
            new File(BASE_PATH + "reversewords/B-small-practice.in"));

        ReverseWords rw = new ReverseWords();

        System.out.println(rw.execute(smallInput));
    }
    @Test
    public void testLarge() throws Exception {
        String smallInput = FileUtils.readFileToString(
            new File(BASE_PATH + "reversewords/B-large-practice.in"));

        ReverseWords rw = new ReverseWords();

        System.out.println(rw.execute(smallInput));
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
        String output = rw.execute(input);
        assertEquals(expectedOutput, output);
    }
}
