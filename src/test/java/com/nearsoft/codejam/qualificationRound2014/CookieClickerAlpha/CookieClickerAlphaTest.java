package com.nearsoft.codejam.qualificationRound2014.CookieClickerAlpha;

import java.io.FileReader;
import java.io.StringReader;
import java.util.Arrays;
import com.nearsoft.codejam.qualificationRound2014.CookieClickerAlpha.CookieClickerAlpha;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CookieClickerAlphaTest {

    @Test
    public void getBestStrategyTime() {
        CookieClickerAlpha cookieClickerAlpha = new CookieClickerAlpha();


        assertEquals("1.0000000", cookieClickerAlpha.getBestStrategyTime(30.0, 1.0 ,2.0));
        assertEquals("39.1666667", cookieClickerAlpha.getBestStrategyTime(30.0, 2.0, 100.0));
        assertEquals("63.9680013", cookieClickerAlpha.getBestStrategyTime(30.50000, 3.14159, 1999.19990));
        assertEquals("526.1904762", cookieClickerAlpha.getBestStrategyTime(500.0, 4.0, 2000.0));
    }

    @Test
    public void testExecute() throws Exception {
        String sampleInput = "4\n" +
            "30.0 1.0 2.0\n" +
            "30.0 2.0 100.0\n" +
            "30.50000 3.14159 1999.19990\n" +
            "500.0 4.0 2000.0";
        String expectedOutput = "Case #1: 1.0000000\n" +
            "Case #2: 39.1666667\n" +
            "Case #3: 63.9680013\n" +
            "Case #4: 526.1904762\n";

        CookieClickerAlpha cookieClickerAlpha = new CookieClickerAlpha();
        Assert.assertEquals(expectedOutput, cookieClickerAlpha.execute(new StringReader(sampleInput)));
    }

    @Test
    public void testLargeInput() throws Exception {
        String largeInput = getClass().getResource("B-large.in").getPath();
        CookieClickerAlpha cookieClickerAlpha = new CookieClickerAlpha();
        System.out.println(cookieClickerAlpha.execute(new FileReader(largeInput)));
    }

    @Test
    public void tesSmallInput() throws Exception {
        String smallInput = getClass().getResource("B-small-attempt0.in").getPath();
        CookieClickerAlpha cookieClickerAlpha = new CookieClickerAlpha();
        System.out.println(cookieClickerAlpha.execute(new FileReader(smallInput)));
    }
}
