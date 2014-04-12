package com.nearsoft.codejam.qualificationRound2014.magictrick;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import com.google.common.collect.Sets;

public class MagicTrick {
    public String execute(Reader reader) {
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
                    int firstAnswer;
                    int secondAnswer;

                    List<List<Integer>> firstArrangement = new ArrayList<>();
                    List<List<Integer>> secondArrangement = new ArrayList<>();

                    firstAnswer = Integer.valueOf(sCurrentLine);



                    for (int i = 0; i < 4; i++) {
                        Scanner scanner = new Scanner(br.readLine());
                        List<Integer> columns = new ArrayList<>();
                        firstArrangement.add(columns);
                        columns.add(scanner.nextInt());
                        columns.add(scanner.nextInt());
                        columns.add(scanner.nextInt());
                        columns.add(scanner.nextInt());
                    }

                    secondAnswer = Integer.valueOf(br.readLine());

                    for (int i = 0; i < 4; i++) {
                        Scanner scanner = new Scanner(br.readLine());
                        List<Integer> columns = new ArrayList<>();
                        secondArrangement.add(columns);
                        columns.add(scanner.nextInt());
                        columns.add(scanner.nextInt());
                        columns.add(scanner.nextInt());
                        columns.add(scanner.nextInt());
                    }

                    sb.append("Case #").append(testCasesCounter).append(": ");
                    sb.append(guessCard(firstAnswer, firstArrangement, secondAnswer, secondArrangement));

                    sb.append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    String guessCard(int firstAnswer, List<List<Integer>> firstArrangement, int secondAnswer, List<List<Integer>> secondArrangement) {

        Set<Integer> rowFirstAnswer = Sets.newHashSet(firstArrangement.get(firstAnswer - 1));
        Set<Integer> rowSecondAnswer = Sets.newHashSet(secondArrangement.get(secondAnswer - 1));
        Set<Integer> intersection = Sets.intersection(rowFirstAnswer, rowSecondAnswer);

        String guess;
        if (intersection.size() == 1) {
            guess = intersection.iterator().next().toString();
        } else if (intersection.size() > 1) {
            guess = "Bad magician!";
        } else {
            guess = "Volunteer cheated!";
        }
        return guess;
    }
}
