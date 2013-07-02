package com.nearsoft.codejam.storecredit;

import java.io.File;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StoreCreditTest {
    private static final String BASE_PATH = "/home/ilopez/ril/docs/google-code-jam/store-credit/src/test/resources/com/nearsoft/codejam/";

    @Test
    public void testInputs() throws InvalidArgumentException {
        String sample = "3\n" +
            "100\n" +
            "3\n" +
            "5 75 25\n" +
            "200\n" +
            "7\n" +
            "150 24 79 50 88 345 3\n" +
            "8\n" +
            "8\n" +
            "2 1 9 4 4 56 90 3";
        String expectedOutput = "Case #1: 2 3\n" +
            "Case #2: 1 4\n" +
            "Case #3: 4 5\n";

        StoreCredit sc = new StoreCredit();
        assertEquals(expectedOutput, sc.execute(sample));
    }

    @Test
    public void testLargeInput() throws Exception {
        String largeInput = FileUtils.readFileToString(new File(BASE_PATH + "scorecredit/A-large-practice.in"));
        StoreCredit sc = new StoreCredit();
        System.out.println(sc.execute(largeInput));
    }

    @Test
    public void tesSmallInput() throws Exception {
        String smallInput = FileUtils.readFileToString(new File(BASE_PATH + "scorecredit/A-small-practice.in"));
        StoreCredit sc = new StoreCredit();
        System.out.println(sc.execute(smallInput));
    }

    @Test
    public void testSolver() throws Exception {
        StoreCredit sc = new StoreCredit();
        assertEquals("2 3", sc.solve(new InputStoreCredit(100, 3, "5 75 25")));
        assertEquals("1 4", sc.solve(new InputStoreCredit(200, 7, "150 24 79 50 88 345 3")));
        assertEquals("4 5", sc.solve(new InputStoreCredit(8, 8, "2 1 9 4 4 56 90 3")));
    }
}
