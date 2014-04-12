package com.nearsoft.codejam.qualificationRound2014.MinesweeperMaster;

import java.io.FileReader;
import java.io.StringReader;
import com.nearsoft.codejam.qualificationRound2014.MinesweeperMaster.MinesweeperMaster;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinesweeperMasterTest {



    @Test
    public void testExecute() throws Exception {
        String sampleInput = "5\n" +
            "5 5 23\n" +
            "3 1 1\n" +
            "2 2 1\n" +
            "4 7 3\n" +
            "10 10 82";
        String expectedOutput = "Case #1:\n" +
            "Impossible\n" +
            "Case #2:\n" +
            "c\n" +
            ".\n" +
            "*\n" +
            "Case #3:\n" +
            "Impossible\n" +
            "Case #4:\n" +
            "......*\n" +
            ".c....*\n" +
            ".......\n" +
            "..*....\n" +
            "Case #5:\n" +
            "**********\n" +
            "**********\n" +
            "**********\n" +
            "****....**\n" +
            "***.....**\n" +
            "***.c...**\n" +
            "***....***\n" +
            "**********\n" +
            "**********\n" +
            "**********";

        MinesweeperMaster minesweeperMaster = new MinesweeperMaster();
        Assert.assertEquals(expectedOutput, minesweeperMaster.execute(new StringReader(sampleInput)));
    }

    @Test
    public void testLargeInput() throws Exception {
        String largeInput = getClass().getResource("B-large.in").getPath();
        MinesweeperMaster minesweeperMaster = new MinesweeperMaster();
        System.out.println(minesweeperMaster.execute(new FileReader(largeInput)));
    }

    @Test
    public void tesSmallInput() throws Exception {
        String smallInput = getClass().getResource("B-small-attempt0.in").getPath();
        MinesweeperMaster minesweeperMaster = new MinesweeperMaster();
        System.out.println(minesweeperMaster.execute(new FileReader(smallInput)));
    }
}
