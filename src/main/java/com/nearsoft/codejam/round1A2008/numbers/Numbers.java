package com.nearsoft.codejam.round1A2008.numbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import com.sun.javaws.exceptions.InvalidArgumentException;

public class Numbers {
    public String execute(Reader reader) throws InvalidArgumentException {
        int totalCases = 0;
        int testCasesCounter = 0;
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(reader)) {
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                if (testCasesCounter == 0 && totalCases == 0) {
                    totalCases = Integer.parseInt(sCurrentLine);
                } else {
                    testCasesCounter++;

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
}
