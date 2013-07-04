package com.nearsoft.codejam.minimum_scalar_product;

import java.io.FileReader;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import com.google.common.base.Joiner;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MinimumScalarProductTest {

    @Test
    public void slowExecute() throws InvalidArgumentException {

            String sampleInput = "1\n" +
                "8\n" +
                "-986 -926 -600 918 988 649 712 998\n" +
                "888 901 871 703 711 514 924 -901\n"
            ;
            String expectedOutput = "Case #1: -952691\n";

            MinimumScalarProduct minimumScalarProduct = new MinimumScalarProduct();
            Assert.assertEquals(expectedOutput, minimumScalarProduct.execute(new StringReader(sampleInput)));



    }

    @Test
    public void testExecute() throws Exception {
        String sampleInput = "2\n" +
            "3\n" +
            "1 3 -5\n" +
            "-2 4 1\n" +
            "5\n" +
            "1 2 3 4 5\n" +
            "1 0 1 0 1\n";
        String expectedOutput = "Case #1: -25\n" + "Case #2: 6\n";

        MinimumScalarProduct minimumScalarProduct = new MinimumScalarProduct();
        Assert.assertEquals(expectedOutput, minimumScalarProduct.execute(new StringReader(sampleInput)));
    }

    @Test
    public void calculateScalarProduct() {
        MinimumScalarProduct msp = new MinimumScalarProduct();
        List<Integer> firstVectorPermutation1 = msp.parseVector("-2 4 1");
        int scalarProduct1 = 0;
        for (int i1 = 0; i1 < firstVectorPermutation1.size(); i1++) {
            Integer xi1 = firstVectorPermutation1.get(i1);
            Integer yi1 = msp.parseVector("1 3 -5").get(i1);
            scalarProduct1 += xi1 * yi1;
        }
        assertEquals(5, scalarProduct1);
        List<Integer> firstVectorPermutation = msp.parseVector("12 4 2");
        int scalarProduct = 0;
        for (int i = 0; i < firstVectorPermutation.size(); i++) {
            Integer xi = firstVectorPermutation.get(i);
            Integer yi = msp.parseVector("2 1 3").get(i);
            scalarProduct += xi * yi;
        }
        assertEquals(34, scalarProduct);

    }


    @Test
    public void findMinimunScalarProduct() {
        MinimumScalarProduct minimumScalarProduct = new MinimumScalarProduct();
        List<Integer> firstVector = minimumScalarProduct.parseVector("1 3 -5");
        List<Integer> secondVector = minimumScalarProduct.parseVector("-2 4 1");
        assertEquals(BigInteger.valueOf(-25), minimumScalarProduct.findMinimunScalarProduct(firstVector, secondVector));;

    }


    @Test
    public void parseVector() {
        MinimumScalarProduct minimumScalarProduct = new MinimumScalarProduct();
        List<Integer> vectorList = minimumScalarProduct.parseVector("3 43 2");
        List<Integer> expectedVector = Arrays.asList(3, 43, 2);
        assertArrayEquals(vectorList.toArray(), expectedVector.toArray());
    }

    @Test
    public void testLargeInput() throws Exception {
        String largeInput = getClass().getResource("A-large-practice.in").getPath();
        MinimumScalarProduct minimumScalarProduct = new MinimumScalarProduct();
        System.out.println(minimumScalarProduct.execute(new FileReader(largeInput)));
    }

    @Test
    public void tesSmallInput() throws Exception {
        String smallInput = getClass().getResource("A-small-practice.in").getPath();
        MinimumScalarProduct minimumScalarProduct = new MinimumScalarProduct();
        System.out.println(minimumScalarProduct.execute(new FileReader(smallInput)));
    }
}