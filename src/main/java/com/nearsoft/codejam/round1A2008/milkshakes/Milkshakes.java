package com.nearsoft.codejam.round1A2008.milkshakes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.sun.javaws.exceptions.InvalidArgumentException;

public class Milkshakes {
    public String execute(Reader reader) throws InvalidArgumentException {
        int totalCases = 0;
        int testCasesCounter = 0;
        StringBuilder sb = new StringBuilder();
        int testCaseLineIndex = 0;
        int mCustomers = 0;
        int nShakesTypes = 0;
        try (BufferedReader br = new BufferedReader(reader)) {
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                if (testCasesCounter == 0 && totalCases == 0) {
                    totalCases = Integer.parseInt(sCurrentLine);
                } else {


                    if (testCaseLineIndex == 0) {

                        nShakesTypes = Integer.valueOf(sCurrentLine);
                    } else if (testCaseLineIndex == 1) {
                        mCustomers = Integer.valueOf(sCurrentLine);
                    } else {
                        testCasesCounter++;

                        List<Map<Integer,Integer>> customerOrders = new ArrayList<>();
                        int iCustomer = 0;
                        do {
                            customerOrders.add(parseCustomerOrder(sCurrentLine));
                            sCurrentLine = br.readLine();
                            iCustomer++;

                        } while (iCustomer < mCustomers && sCurrentLine != null);
                        List shakesBatches = new ArrayList();

                           shakesBatches.addAll( prepareOrders(nShakesTypes, customerOrders));

                        sb.append("Case #").append(testCasesCounter).append(": ");
                        if (shakesBatches.isEmpty()) {
                            sb.append("IMPOSSIBLE");
                        } else {
                            for (int i = 0; i < nShakesTypes; i++) {
                                sb.append(shakesBatches.get(i));
                            }
                        }
                        sb.append("\n");
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

    private List prepareOrders(int nShakesTypes, List<Map<Integer, Integer>> customerOrders) {

        Map<Integer,Integer> batch = new LinkedHashMap<>();

        for (int shakeType = 0; shakeType < nShakesTypes; shakeType++) {
            int melted = findIfSHouldBeMelted(shakeType, customerOrders);
            boolean containsKey = batch.containsKey(shakeType);
            if (containsKey &&  batch.get(shakeType) != melted){
               break;  // impossible
            }else if (!containsKey){
                batch.put(shakeType,melted);
            }
        }

        List preparedOrders = new ArrayList();
        if (batch.size() == nShakesTypes){
           preparedOrders.addAll(batch.values());
        }

        return preparedOrders;
    }

    private int findIfSHouldBeMelted(int shakeType, List<Map<Integer, Integer>> customerOrders) {
        int melted =-1;
        boolean shakeTypeWasOrdered = false;
        for (int i = 0; i < customerOrders.size(); i++) {
             if(customerOrders.get(i).containsKey(shakeType)){
                 shakeTypeWasOrdered = true;
                 Integer shakeTypeMeltedPreference = customerOrders.get(i).get(shakeType);
                 if (melted == -1){
                     melted = shakeTypeMeltedPreference;
                 }else if(melted != shakeTypeMeltedPreference){
                     melted =-1; //impossible, two customers have different preferences for this shakeType
                     break;
                 }
             }
        }

        if(!shakeTypeWasOrdered && melted== -1){
            melted = 0;
        }
        return melted;  //To change body of created methods use File | Settings | File Templates.
    }

    Map<Integer, Integer> parseCustomerOrder(String sCurrentLine) {
        Map<Integer, Integer> parsedOrder = new HashMap<>();
        Scanner scanner = new Scanner(sCurrentLine);
        int orderSize = scanner.nextInt();
        for (int i = 0; i < orderSize; i++) {
            int shakeType = scanner.nextInt();
            int melted = scanner.nextInt();
            parsedOrder.put(shakeType, melted);
        }
        return parsedOrder;
    }
}
