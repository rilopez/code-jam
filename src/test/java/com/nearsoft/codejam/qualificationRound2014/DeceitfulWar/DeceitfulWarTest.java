package com.nearsoft.codejam.qualificationRound2014.DeceitfulWar;

import java.io.FileReader;
import java.io.StringReader;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeceitfulWarTest {

    @Test
    public void getNaomisScorePlayingWar() {
        DeceitfulWar deceitfulWar = new DeceitfulWar();
        assertEquals("0", deceitfulWar.getNaomisScorePlayingWar(Arrays.asList(0.5), Arrays.asList(0.6))) ;
        assertEquals("0", deceitfulWar.getNaomisScorePlayingWar(Arrays.asList(0.7,0.2), Arrays.asList(0.8,0.3))) ;
        assertEquals("1", deceitfulWar.getNaomisScorePlayingWar(Arrays.asList(0.5,0.1,0.9), Arrays.asList(0.6,0.4,0.3))) ;
    }

    @Test
    public void testExecute() throws Exception {
        String sampleInput = "4\n" +
            "1\n" +
            "0.5\n" +
            "0.6\n" +
            "2\n" +
            "0.7 0.2\n" +
            "0.8 0.3\n" +
            "3\n" +
            "0.5 0.1 0.9\n" +
            "0.6 0.4 0.3\n" +
            "9\n" +
            "0.186 0.389 0.907 0.832 0.959 0.557 0.300 0.992 0.899\n" +
            "0.916 0.728 0.271 0.520 0.700 0.521 0.215 0.341 0.458";
        String expectedOutput = "Case #1: 0 0\n" +
            "Case #2: 1 0\n" +
            "Case #3: 2 1\n" +
            "Case #4: 8 4\n";

        DeceitfulWar deceitfulWar = new DeceitfulWar();
        assertEquals(expectedOutput, deceitfulWar.execute(new StringReader(sampleInput)));
    }

    @Test
    public void testLargeInput() throws Exception {
        String largeInput = getClass().getResource("D-large.in").getPath();
        DeceitfulWar deceitfulWar = new DeceitfulWar();
        System.out.println(deceitfulWar.execute(new FileReader(largeInput)));
    }

    @Test
    public void tesSmallInput() throws Exception {
        String smallInput = getClass().getResource("D-small-attempt1.in").getPath();
        DeceitfulWar deceitfulWar = new DeceitfulWar();
        System.out.println(deceitfulWar.execute(new FileReader(smallInput)));
    }
}
