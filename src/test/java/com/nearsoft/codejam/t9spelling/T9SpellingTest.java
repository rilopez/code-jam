package com.nearsoft.codejam.t9spelling;

import java.io.FileReader;
import java.io.StringReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class T9SpellingTest {
    @Test
    public void testExecute() throws Exception {
        String sampleInput = "4\n" +
            "hi\n" +
            "yes\n" +
            "foo  bar\n" +
            "hello world";
        String expectedOutput = "Case #1: 44 444\n" +
            "Case #2: 999337777\n" +
            "Case #3: 333666 6660 022 2777\n" +
            "Case #4: 4433555 555666096667775553\n";

        T9Spelling spelling = new T9Spelling();
        assertEquals(expectedOutput, spelling.execute(new StringReader(sampleInput)));
    }

    @Test
    public void convert() throws Exception {
        T9Spelling spelling = new T9Spelling();
        assertEquals("44 444", spelling.convert("hi"));
        assertEquals("333666 6660 022 2777", spelling.convert("foo  bar"));
    }

    @Test
    public void testLargeInput() throws Exception {
        String largeInput = getClass().getResource("C-large-practice.in").getPath();
        T9Spelling sc = new T9Spelling();
        System.out.println(sc.execute(new FileReader(largeInput)));
    }

    @Test
    public void tesSmallInput() throws Exception {
        String smallInput = getClass().getResource("C-small-practice.in").getPath();
        T9Spelling sc = new T9Spelling();
        System.out.println(sc.execute(new FileReader(smallInput)));
    }
}
