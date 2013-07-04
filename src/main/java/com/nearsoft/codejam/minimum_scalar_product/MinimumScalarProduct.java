package com.nearsoft.codejam.minimum_scalar_product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import com.sun.javaws.exceptions.InvalidArgumentException;

public class MinimumScalarProduct {
    public String execute(Reader reader) throws InvalidArgumentException {
        int totalCases = 0;
        int testCasesCounter = 0;
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(reader)) {
            String sCurrentLine;
            int vectorSize = 0;
            Collection<List<Integer>> firstVectorPermutations = new ArrayList<>();
            Collection<List<Integer>> secondVectorPermutations = new ArrayList<>();
            int testCaseLineIndex = 1;
            while ((sCurrentLine = br.readLine()) != null) {
                if (testCasesCounter == 0 && totalCases == 0) {
                    totalCases = Integer.parseInt(sCurrentLine);
                } else {

                    if (testCaseLineIndex == 1) {
                        vectorSize = Integer.parseInt(sCurrentLine);
                    } else if (testCaseLineIndex == 2) {
                        firstVectorPermutations = findVectorPermutations(sCurrentLine);
                    } else if (testCaseLineIndex == 3) {
                        testCasesCounter++;
                        secondVectorPermutations = findVectorPermutations(sCurrentLine);
                        int miniumnScalarProduct = findMinimunScalarProduct(firstVectorPermutations, secondVectorPermutations);
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

    int findMinimunScalarProduct(Collection<List<Integer>> firstVectorPermutations, Collection<List<Integer>> secondVectorPermutations) {
        boolean isTheFirstCalculation = true;
        int minimunScalarProduct = 0;
        for (List<Integer> firstVectorPermutation : firstVectorPermutations) {
            for (List<Integer> secondVectorPermutation : secondVectorPermutations) {
                int scalarProduct = calculateScalarProduct(firstVectorPermutation, secondVectorPermutation);
                if (isTheFirstCalculation) {
                    isTheFirstCalculation = false;
                    minimunScalarProduct = scalarProduct;
                } else if (minimunScalarProduct > scalarProduct) {
                    minimunScalarProduct = scalarProduct;
                }
            }
        }
        return minimunScalarProduct;
    }

    int calculateScalarProduct(List<Integer> firstVectorPermutation, List<Integer> secondVectorPermutation) {
        int scalarProduct = 0;
        for (int i = 0; i < firstVectorPermutation.size(); i++) {
            Integer xi = firstVectorPermutation.get(i);
            Integer yi = secondVectorPermutation.get(i);
            scalarProduct += xi * yi;
        }
        return scalarProduct;
    }

    public Collection<List<Integer>> permute(Collection<Integer> input) {
        Collection<List<Integer>> output = new ArrayList<>();
        if (input.isEmpty()) {
            output.add(new ArrayList<Integer>());
            return output;
        }
        List<Integer> list = new ArrayList<>(input);
        Integer head = list.get(0);
        List<Integer> rest = list.subList(1, list.size());
        for (List<Integer> permutations : permute(rest)) {
            List<List<Integer>> subLists = new ArrayList<>();
            for (int i = 0; i <= permutations.size(); i++) {
                List<Integer> subList = new ArrayList<>();
                subList.addAll(permutations);
                subList.add(i, head);
                subLists.add(subList);
            }
            output.addAll(subLists);
        }
        return output;
    }

    Collection<List<Integer>> findVectorPermutations(String vectorString) {
        List<Integer> numericVector = parseVector(vectorString);
        return permute(numericVector);  //To change body of created methods use File | Settings | File Templates.
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
