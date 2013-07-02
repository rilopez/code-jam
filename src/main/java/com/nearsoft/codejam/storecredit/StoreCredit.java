package com.nearsoft.codejam.storecredit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.sun.javaws.exceptions.InvalidArgumentException;

public class StoreCredit {

    public String execute(Reader reader) throws InvalidArgumentException {
        StringBuilder sb = new StringBuilder();
        int expectedTestCases = 0;
        int readerIndex = 0;
        int testCaseLineIndex = 0;
        int testCaseCounter = 0;

        try (BufferedReader br = new BufferedReader(reader)) {
            String sCurrentLine;
            int credit = 0;
            int totalItems = 0;
            while ((sCurrentLine = br.readLine()) != null) {
                if (readerIndex == 0) {
                    expectedTestCases = Integer.parseInt(sCurrentLine);
                } else {
                    if (testCaseLineIndex < 3) {
                        if (testCaseLineIndex == 0) {
                            credit = Integer.parseInt(sCurrentLine);
                            testCaseLineIndex++;
                        } else if (testCaseLineIndex == 1) {
                            totalItems = Integer.parseInt(sCurrentLine);
                            testCaseLineIndex++;
                        } else {
                            testCaseCounter++;
                            String solution = solve(credit, totalItems, sCurrentLine);
                            sb.append(String.format("Case #%d: %s", testCaseCounter, solution)).append("\n");
                            testCaseLineIndex = 0;
                        }
                    }
                }
                readerIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (expectedTestCases != testCaseCounter) {
            throw new InvalidArgumentException(new String[]{"expectedTestCases != testCasesFound"});
        }
        return sb.toString();
    }

    public String solve(int credit, int totalItems, String items) {
        String solution = null;
        int priceIndex = 1;
        Scanner scanner = new Scanner(items).useDelimiter("\\s");

        List<Map.Entry<Integer, Integer>> pricesList = new ArrayList<Map.Entry<Integer, Integer>>();
        while (priceIndex <= totalItems && scanner.hasNext()) {
            int price = scanner.nextInt();
            if (price < credit) {
                pricesList.add(new AbstractMap.SimpleEntry<Integer, Integer>(priceIndex, price));
            }
            priceIndex++;
        }

        for (int i = 0; i < pricesList.size() - 1; i++) {
            for (int j = i + 1; j < pricesList.size(); j++) {
                Map.Entry<Integer, Integer> itemA = pricesList.get(i);
                Integer priceA = itemA.getValue();
                Map.Entry<Integer, Integer> itemB = pricesList.get(j);
                Integer priceB = itemB.getValue();
                if (priceA + priceB == credit) {
                    solution = String.format("%d %d", Math.min(itemA.getKey(), itemB.getKey()), Math.max(itemA.getKey(), itemB.getKey()));
                    break;
                }
            }
            if (solution != null) {
                break;
            }
        }

        return solution;
    }
}
