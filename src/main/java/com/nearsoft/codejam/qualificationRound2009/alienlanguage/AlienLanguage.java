package com.nearsoft.codejam.qualificationRound2009.alienlanguage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.sun.javaws.exceptions.InvalidArgumentException;

public class AlienLanguage {
    public String execute(Reader reader) throws InvalidArgumentException {
        int totalCases = 0;
        int testCasesCounter = 0;
        int wordLength = 0;
        int totalWords = 0;

        StringBuilder sb = new StringBuilder();
        StringBuilder wordList = new StringBuilder();

        try (BufferedReader br = new BufferedReader(reader)) {
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                if (testCasesCounter == 0 && totalCases == 0) {
                    Scanner scanner = new Scanner(sCurrentLine);
                    wordLength = scanner.nextInt();
                    totalWords = scanner.nextInt();
                    totalCases = scanner.nextInt();
                } else {
                    int wordIndex = 1;
                    do {
                        wordList.append(sCurrentLine).append(" ");
                        wordIndex++;
                        sCurrentLine = br.readLine();
                    } while (wordIndex <= totalWords);

                    testCasesCounter = 0;
                    do {
                        testCasesCounter++;
                        sb.append("Case #").append(testCasesCounter).append(": ");
                        sb.append(findNumberOfMatches(sCurrentLine, wordList.toString()));
                        sb.append("\n");
                        sCurrentLine = br.readLine();
                    } while (testCasesCounter <= totalCases && sCurrentLine != null);
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

    int findNumberOfMatches(String pattern, String input) {
        Pattern regex = Pattern.compile(convertToRegex(pattern));
        Matcher matcher = regex.matcher(input);
        int total = 0;

        while (matcher.find()) {
            total++;
        }

        return total;
    }

    String convertToRegex(String pattern) {
        return pattern.replaceAll("\\(", "[").replaceAll("\\)", "]");
    }
}
