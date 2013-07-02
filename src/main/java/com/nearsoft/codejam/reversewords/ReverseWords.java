package com.nearsoft.codejam.reversewords;

import java.util.Scanner;
import java.util.Stack;

public class ReverseWords {
    public String execute(String input) {
        Scanner scanner = new Scanner(input);
        int totalCases = Integer.parseInt(scanner.nextLine());
        int i = 1;
        StringBuilder sb = new StringBuilder();
        while (i <= totalCases && scanner.hasNextLine()) {
            String testCaseLine = scanner.nextLine();
            Scanner worldLineScanner = new Scanner(testCaseLine).useDelimiter("\\s");
            Stack worldStack = new Stack();
            while (worldLineScanner.hasNext()) {
                worldStack.push(worldLineScanner.next());
            }

            sb.append("Case #" + i + ": ");
            while (!worldStack.empty()) {
                sb.append(worldStack.pop());
                if (!worldStack.empty()) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
            i++;
        }
        return sb.toString();
    }

}