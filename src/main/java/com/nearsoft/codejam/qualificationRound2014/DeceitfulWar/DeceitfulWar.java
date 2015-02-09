package com.nearsoft.codejam.qualificationRound2014.DeceitfulWar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DeceitfulWar {
    public String execute(Reader reader) {
        int totalCases = 0;
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(reader)) {
            String sCurrentLine;

            sCurrentLine = br.readLine();

            totalCases = Integer.parseInt(sCurrentLine);

            for (int testCasesCounter = 1; testCasesCounter <= totalCases; testCasesCounter++) {
                int numberOfBlocks = Integer.parseInt(br.readLine());
                Scanner scannerForNaomi = new Scanner(br.readLine());
                Scanner scannerForKen = new Scanner(br.readLine());
                List<Double> naomisBlocks = new ArrayList<>();
                List<Double> kensBlocks = new ArrayList<>();
                for (int j = 0; j < numberOfBlocks; j++) {
                    naomisBlocks.add(scannerForNaomi.nextDouble());
                    kensBlocks.add(scannerForKen.nextDouble());
                }
                sb.append("Case #").append(testCasesCounter).append(": ");
                sb.append(getNaomisScorePlayingDecitfulWar(naomisBlocks, kensBlocks));
                sb.append(" ");
                sb.append(getNaomisScorePlayingWar(naomisBlocks, kensBlocks));

                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    String getNaomisScorePlayingWar(List<Double> naomisBlockList, List<Double> kensBlocks) {
        int score = 0;
        List<Double> kensBlocksOrdered = new ArrayList<>(kensBlocks);
        Collections.sort(kensBlocksOrdered);
        for (Double naomisBlock : naomisBlockList) {
            int kenUsedBlockIndex = 0;
            while (kenUsedBlockIndex < kensBlocksOrdered.size() && //
                kensBlocksOrdered.get(kenUsedBlockIndex) < naomisBlock) {
                kenUsedBlockIndex++;
            }
            if (kenUsedBlockIndex < kensBlocksOrdered.size()) {
                kensBlocksOrdered.remove(kenUsedBlockIndex);
            } else {
                score++;
            }
        }

        return Integer.toString(score);
    }

    String getNaomisScorePlayingDecitfulWar(List<Double> naomisBlocks, List<Double> kensBlocks) {
        int score = 0;
        List<Double> kensBlocksOrdered = new ArrayList<>(kensBlocks);
        List<Double> naomisBlocksOrdered = new ArrayList<>(naomisBlocks);
        Collections.sort(kensBlocksOrdered);
        Collections.sort(naomisBlocksOrdered);

        for (int i = 0; i < naomisBlocks.size(); i++) {
            int naomiChooseIndex = 0;
            Double naomisChoose = naomisBlocksOrdered.get(naomiChooseIndex);
            Double kensChoose = kensBlocksOrdered.get(kensBlocksOrdered.size() - 1 - i);
            boolean needsFixIndex = false;
            while (kensChoose > naomisChoose && naomiChooseIndex < naomisBlocksOrdered.size()) {
                naomisChoose = naomisBlocksOrdered.get(naomiChooseIndex);
                naomiChooseIndex++;
                needsFixIndex = true;
            }
            if (naomisChoose > kensChoose ) {
                naomisBlocksOrdered.remove(needsFixIndex? naomiChooseIndex - 1: naomiChooseIndex);
                score++;
            }
        }

        return Integer.toString(score);
    }
}
