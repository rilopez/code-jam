package com.nearsoft.codejam.t9spelling;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class T9Spelling {
    static Map<String, String> keypadMapping = new HashMap<String, String>();
    private static HashMap<String, String> letterMapping;

    static {
        keypadMapping.put("2", "ABC");
        keypadMapping.put("3", "DEF");
        keypadMapping.put("4", "GHI");
        keypadMapping.put("5", "JKL");
        keypadMapping.put("6", "MNO");
        keypadMapping.put("7", "PQRS");
        keypadMapping.put("8", "TUV");
        keypadMapping.put("9", "WXYZ");
        keypadMapping.put("0", " ");

        letterMapping = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : keypadMapping.entrySet()) {
            for (int i = 0; i < entry.getValue().length(); i++) {
                String letter = String.valueOf(entry.getValue().charAt(i));
                String secuence = "";
                for (int scounter = 0; scounter <= i; scounter++) {
                    secuence += entry.getKey();
                }
                letterMapping.put(letter, secuence);
            }
        }
    }

    public String execute(String input) {
        Scanner scanner = new Scanner(input);
        int totalCases = Integer.parseInt(scanner.nextLine());
        int i = 1;
        StringBuilder sb = new StringBuilder();
        while (i <= totalCases && scanner.hasNextLine()) {
            sb.append("Case #").append(i).append(": ");
            sb.append(convert(scanner.nextLine())).append("\n");
            i++;
        }
        return sb.toString();
    }

    public String convert(String message) {
        String lastSecuence = null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            String letter = String.valueOf(message.charAt(i));
            String secuence = letterMapping.get(letter.toUpperCase());

            boolean usesTheSameNumberKey =
                lastSecuence != null && secuence != null && (lastSecuence.contains(secuence) || secuence.contains(lastSecuence));
            if (usesTheSameNumberKey) {
                sb.append(" ");
            }
            sb.append(secuence);
            lastSecuence = secuence;
        }

        return sb.toString();
    }
}
