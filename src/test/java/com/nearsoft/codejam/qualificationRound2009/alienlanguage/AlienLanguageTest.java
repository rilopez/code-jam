package com.nearsoft.codejam.qualificationRound2009.alienlanguage;

import java.io.FileReader;
import java.io.StringReader;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlienLanguageTest {

    @Test
    public void testconvertToRegex() {
        AlienLanguage alienLanguage = new AlienLanguage();
        assertEquals("[ab]b[aw]", alienLanguage.convertToRegex("(ab)b(aw)"));
    }

    @Test
    public void findNumberOfMatches() {
        AlienLanguage alienLanguage = new AlienLanguage();
        String wordList = "aba wiq wow";
        assertEquals(0, alienLanguage.findNumberOfMatches("(ab)aw", wordList));
        assertEquals(1, alienLanguage.findNumberOfMatches("(ab)b(aw)", wordList));
        assertEquals(1, alienLanguage.findNumberOfMatches("(wb)(bo)(aw)", wordList));
        assertEquals(0, alienLanguage.findNumberOfMatches("(wa)(tr)(xa)", wordList));
    }

    @Test
    public void testExecute() throws Exception {
        String sampleInput = "3 5 4\n" +
            "abc\n" +
            "bca\n" +
            "dac\n" +
            "dbc\n" +
            "cba\n" +
            "(ab)(bc)(ca)\n" +
            "abc\n" +
            "(abc)(abc)(abc)\n" +
            "(zyx)bc";
        String expectedOutput = "Case #1: 2\n" +
            "Case #2: 1\n" +
            "Case #3: 3\n" +
            "Case #4: 0\n";

        AlienLanguage alienLanguage = new AlienLanguage();
        Assert.assertEquals(expectedOutput, alienLanguage.execute(new StringReader(sampleInput)));
    }

    @Test
    public void testLargeInput() throws Exception {
        String largeInput = getClass().getResource("A-large-practice.in").getPath();
        AlienLanguage alienLanguage = new AlienLanguage();
        System.out.println(alienLanguage.execute(new FileReader(largeInput)));
    }

    @Test
    public void tesSmallInput() throws Exception {
        String smallInput = getClass().getResource("A-small-practice.in").getPath();
        AlienLanguage alienLanguage = new AlienLanguage();
        System.out.println(alienLanguage.execute(new FileReader(smallInput)));
    }
}
