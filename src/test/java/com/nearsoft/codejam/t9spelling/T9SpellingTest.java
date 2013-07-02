package com.nearsoft.codejam.t9spelling;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class T9SpellingTest {

    private static final String BASE_PATH = "/home/ilopez/ril/docs/google-code-jam/store-credit/src/test/resources/com/nearsoft/codejam/";

    @Test
    public void testLargeInput() throws Exception {

        String largeInput = FileUtils.readFileToString(new File(BASE_PATH + "t9spelling/C-large-practice.in"));
        T9Spelling sc = new T9Spelling();
        System.out.println(sc.execute(largeInput));
    }

    @Test
    public void tesSmallInput() throws Exception {

        String smallInput = FileUtils.readFileToString(new File(BASE_PATH + "t9spelling/C-small-practice.in"));
        T9Spelling sc = new T9Spelling();

        System.out.println(sc.execute(smallInput));
    }

    @Test
    public void testExecute() throws Exception {
        String sampleInput ="4\n" +
            "hi\n" +
            "yes\n" +
            "foo  bar\n" +
            "hello world";
        String expectedOutput ="Case #1: 44 444\n" +
            "Case #2: 999337777\n" +
            "Case #3: 333666 6660 022 2777\n" +
            "Case #4: 4433555 555666096667775553\n";

        T9Spelling spelling = new T9Spelling();
        assertEquals(expectedOutput,spelling.execute(sampleInput));
    }

    @Test
    public void convert() throws Exception {
        T9Spelling spelling = new T9Spelling();
        assertEquals("44 444",spelling.convert("hi"));
        assertEquals("333666 6660 022 2777", spelling.convert("foo  bar"));
    }
}
