package com.nearsoft.codejam.minimum_scalar_product;

import java.io.FileReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import com.google.common.base.Joiner;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MinimumScalarProductTest {
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
        assertEquals(5, msp.calculateScalarProduct(msp.parseVector("-2 4 1"),msp.parseVector("1 3 -5")));
        assertEquals(34, msp.calculateScalarProduct(msp.parseVector("12 4 2"),msp.parseVector("2 1 3")));

    }


    @Test
    public void findMinimunScalarProduct() {
        MinimumScalarProduct minimumScalarProduct = new MinimumScalarProduct();
        Collection<List<Integer>> firstVector = minimumScalarProduct.findVectorPermutations("1 3 -5");
        Collection<List<Integer>> secondVector = minimumScalarProduct.findVectorPermutations("-2 4 1");
        assertEquals(-25, minimumScalarProduct.findMinimunScalarProduct(firstVector, secondVector));;

    }
    @Test
    public void findVectorPermutations() {
        MinimumScalarProduct minimumScalarProduct = new MinimumScalarProduct();
        String expectedVectorPermutations = "1,2,3\n1,3,2\n2,1,3\n2,3,1\n3,1,2\n3,2,1\n" ;
        Collection<List<Integer>> vectorPermutations = minimumScalarProduct.findVectorPermutations("1 2 3");

        for (List<Integer> vectorPermutation : vectorPermutations) {

            assertThat(expectedVectorPermutations, containsString( Joiner.on(",").join(vectorPermutation) +"\n"));
        }

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