package com.nearsoft.codejam.qualificationRound2014.magictrick;

import java.io.FileReader;
import java.io.StringReader;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MagicTrickTest {

    @Test
    public void guessCard() {
        MagicTrick magicTrick = new MagicTrick();

        String card = magicTrick.guessCard( //
            2, Arrays.asList( //
            Arrays.asList(1, 2, 3, 4), //
            Arrays.asList(5, 6, 7, 8), //
            Arrays.asList(9, 10, 11, 12), //
            Arrays.asList(13, 14, 15, 16)  //
        ),//
            3, Arrays.asList(//
            Arrays.asList(1, 2, 5, 4), //
            Arrays.asList(3, 11, 6, 15), //
            Arrays.asList(9, 10, 7, 12), //
            Arrays.asList(13, 14, 8, 16) //
        ));
        assertEquals("7", card);
    }

    @Test
    public void testExecute() throws Exception {
        String sampleInput = "3\n" +
            "2\n" +
            "1 2 3 4\n" +
            "5 6 7 8\n" +
            "9 10 11 12\n" +
            "13 14 15 16\n" +
            "3\n" +
            "1 2 5 4\n" +
            "3 11 6 15\n" +
            "9 10 7 12\n" +
            "13 14 8 16\n" +
            "2\n" +
            "1 2 3 4\n" +
            "5 6 7 8\n" +
            "9 10 11 12\n" +
            "13 14 15 16\n" +
            "2\n" +
            "1 2 3 4\n" +
            "5 6 7 8\n" +
            "9 10 11 12\n" +
            "13 14 15 16\n" +
            "2\n" +
            "1 2 3 4\n" +
            "5 6 7 8\n" +
            "9 10 11 12\n" +
            "13 14 15 16\n" +
            "3\n" +
            "1 2 3 4\n" +
            "5 6 7 8\n" +
            "9 10 11 12\n" +
            "13 14 15 16";
        String expectedOutput = "Case #1: 7\n" +
            "Case #2: Bad magician!\n" +
            "Case #3: Volunteer cheated!\n";

        MagicTrick MagicTrick = new MagicTrick();
        Assert.assertEquals(expectedOutput, MagicTrick.execute(new StringReader(sampleInput)));
    }

    @Test
    public void testLargeInput() throws Exception {
        String largeInput = getClass().getResource("A-small-attempt0.in").getPath();
        MagicTrick MagicTrick = new MagicTrick();
        System.out.println(MagicTrick.execute(new FileReader(largeInput)));
    }

    @Test
    public void tesSmallInput() throws Exception {
        String smallInput = getClass().getResource("A-small-attempt0.in").getPath();
        MagicTrick MagicTrick = new MagicTrick();
        System.out.println(MagicTrick.execute(new FileReader(smallInput)));
    }
}
