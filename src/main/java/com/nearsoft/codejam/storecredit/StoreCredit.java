package com.nearsoft.codejam.storecredit;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.google.common.base.Splitter;
import com.sun.javaws.exceptions.InvalidArgumentException;

public class StoreCredit {

    public String execute(String input) throws InvalidArgumentException {
        Iterable<String> inputList = Splitter.on("\n").trimResults().omitEmptyStrings().split(input);
        StringBuilder sb = new StringBuilder();

        int expectedTestCases = 0;
        int readerIndex = 0;
        int testCaseLineIndex = 0;
        List<InputStoreCredit> storeCreditSamples = new ArrayList<InputStoreCredit>();
        for (String line : inputList) {
            if (readerIndex == 0) {
                expectedTestCases = Integer.parseInt(line);
            } else {
                if (testCaseLineIndex < 3) {
                    if (testCaseLineIndex == 0) {
                        storeCreditSamples.add(new InputStoreCredit());
                        storeCreditSamples.get(storeCreditSamples.size() - 1).setCredit(Integer.parseInt(line));
                        testCaseLineIndex++;
                    } else if (testCaseLineIndex == 1) {
                        storeCreditSamples.get(storeCreditSamples.size() - 1).setTotalItems(Integer.parseInt(line));
                        testCaseLineIndex++;
                    } else {
                        storeCreditSamples.get(storeCreditSamples.size() - 1).setItems(line);
                        String solution = solve(storeCreditSamples.get(storeCreditSamples.size() - 1));
                        sb.append(String.format("Case #%d: %s", storeCreditSamples.size(), solution)).append("\n");
                        testCaseLineIndex = 0;
                    }
                }
            }
            readerIndex++;
        }

        if (expectedTestCases != storeCreditSamples.size()) {
            throw new InvalidArgumentException(new String[]{"expectedTestCases != testCasesFound"});
        }
        return sb.toString();
    }

    public String solve(InputStoreCredit inputStoreCredit) {
        String solution = null;
        int priceIndex = 1;
        Scanner scanner = new Scanner(inputStoreCredit.getItems()).useDelimiter("\\s");

        List<Map.Entry<Integer, Integer>> pricesList = new ArrayList<Map.Entry<Integer, Integer>>();
        while (priceIndex <= inputStoreCredit.getTotalItems() && scanner.hasNext()) {
            int price = scanner.nextInt();
            if (price < inputStoreCredit.getCredit()) {
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
                if (priceA + priceB == inputStoreCredit.getCredit()) {
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
