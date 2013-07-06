package com.nearsoft.codejam.round1A2008.minimum_scalar_product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import com.sun.javaws.exceptions.InvalidArgumentException;

public class MinimumScalarProduct {
    public String execute(Reader reader) throws InvalidArgumentException {
        int totalCases = 0;
        int testCasesCounter = 0;
        StringBuilder sb = new StringBuilder();
        String firstVectorString = null;
        try (BufferedReader br = new BufferedReader(reader)) {
            String sCurrentLine;
            int vectorSize = 0;
            List<Integer> firstVector = new ArrayList<>();
            List<Integer> secondVector = new ArrayList<>();
            int testCaseLineIndex = 1;
            while ((sCurrentLine = br.readLine()) != null) {
                if (testCasesCounter == 0 && totalCases == 0) {
                    totalCases = Integer.parseInt(sCurrentLine);
                } else {

                    if (testCaseLineIndex == 1) {
                        testCasesCounter++;

                        vectorSize = Integer.parseInt(sCurrentLine);
                    } else if (testCaseLineIndex == 2) {
                        firstVectorString = sCurrentLine;

                        firstVector = parseVector(firstVectorString);
                    } else if (testCaseLineIndex == 3) {
                        secondVector = parseVector(sCurrentLine);
                        BigInteger miniumnScalarProduct = findMinimunScalarProduct(firstVector, secondVector);
                        sb.append("Case #").append(testCasesCounter).append(": ").append(miniumnScalarProduct).append("\n");
                        testCaseLineIndex = 0;
                    }
                    testCaseLineIndex++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (testCasesCounter != totalCases) {
            throw new InvalidArgumentException(new String[]{"totalCases", String.valueOf(totalCases), "found", String.valueOf(testCasesCounter)});
        }

        return sb.toString();
    }

    //Woow, for large sets we need to use BigInteger, because the data on the large set input, produces larger  values than the int can handle
    BigInteger findMinimunScalarProduct(List<Integer> firstVector, List<Integer> secondVector) {

        BigInteger minimunScalarProduct = new BigInteger("0");

        Collections.sort(firstVector);
        Collections.sort(secondVector);

        for (int i = 0; i < firstVector.size(); i++) {

            int xi = firstVector.get(i);
            int yi = secondVector.get(firstVector.size() - 1 - i);
            BigInteger multiply = BigInteger.valueOf(xi).multiply(BigInteger.valueOf(yi));
            minimunScalarProduct = minimunScalarProduct.add(multiply);
        }

        return minimunScalarProduct;
    }

    List<Integer> parseVector(String vectorString) {
        Scanner scanner = new Scanner(vectorString).useDelimiter("\\s+");
        List<Integer> vectorList = new ArrayList<>();
        while (scanner.hasNextInt()) {
            vectorList.add(scanner.nextInt());
        }
        return vectorList;
    }
}
