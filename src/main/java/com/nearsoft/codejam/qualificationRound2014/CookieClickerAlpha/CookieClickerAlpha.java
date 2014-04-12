package com.nearsoft.codejam.qualificationRound2014.CookieClickerAlpha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class CookieClickerAlpha {
    public String execute(Reader reader) {
        int totalCases = 0;

        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(reader)) {
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {

                totalCases = Integer.parseInt(sCurrentLine);
                for (int testCasesCounter = 1; testCasesCounter <= totalCases; testCasesCounter++) {
                    Scanner scanner = new Scanner(br.readLine());

                    double cookieCostOfFactoryC = scanner.nextDouble();
                    double extraCookiesPerSecondF = scanner.nextDouble();
                    double cookiesToWinX = scanner.nextDouble();

                    sb.append("Case #").append(testCasesCounter).append(": ");
                    sb.append(getBestStrategyTime(cookieCostOfFactoryC, extraCookiesPerSecondF, cookiesToWinX));
                    sb.append("\n");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    String getBestStrategyTime(double cookieCostOfFactoryC, double extraCookiesPerSecondPerFactoryF, double cookiesToWinX) {

        //just waiting
        double timeToWinWaitingStrategy = cookiesToWinX / 2;

        //buying factories

        int factories = 0;

        double secondsToFactory;
        double secondsToWin;
        double totalTime = 0;
        double extraCookies = 0;
        double timeBuyingFactories = 0;

        while (timeBuyingFactories < timeToWinWaitingStrategy ) {

            secondsToFactory = cookieCostOfFactoryC / (2 + extraCookies);
            factories++;
            extraCookies = factories * extraCookiesPerSecondPerFactoryF;
            secondsToWin = cookiesToWinX / (2 + extraCookies);

            totalTime += secondsToFactory;

            if (totalTime + secondsToWin > timeBuyingFactories && timeBuyingFactories > 0) {
                break;
            }
            timeBuyingFactories = totalTime + secondsToWin;
        }

        if (timeToWinWaitingStrategy < timeBuyingFactories) {
            return formatSeconds(timeToWinWaitingStrategy);
        } else {
            return formatSeconds(timeBuyingFactories);
        }
    }

    public String formatSeconds(double seconds) {
        return String.format("%.7f", seconds);
    }
}
