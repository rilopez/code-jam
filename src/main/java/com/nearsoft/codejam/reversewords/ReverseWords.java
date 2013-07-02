package com.nearsoft.codejam.reversewords;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.Stack;
import com.sun.javaws.exceptions.InvalidArgumentException;

public class ReverseWords {
    public String execute(Reader inputReader) throws InvalidArgumentException {

        int totalCases = 0;
        int testCasesCounter = 0;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(inputReader)) {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {

                if (testCasesCounter == 0 && totalCases == 0) {
                    totalCases = Integer.parseInt(sCurrentLine);
                } else {
                    testCasesCounter++;
                    String testCaseLine = sCurrentLine;
                    Scanner worldLineScanner = new Scanner(testCaseLine).useDelimiter("\\s");
                    Stack worldStack = new Stack();
                    while (worldLineScanner.hasNext()) {
                        worldStack.push(worldLineScanner.next());
                    }

                    sb.append("Case #" + testCasesCounter + ": ");
                    while (!worldStack.empty()) {
                        sb.append(worldStack.pop());
                        if (!worldStack.empty()) {
                            sb.append(" ");
                        }
                    }
                    sb.append("\n");
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