package com.nearsoft.codejam.t9spelling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import com.sun.javaws.exceptions.InvalidArgumentException;

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

    public String execute(Reader reader) throws InvalidArgumentException {

        int totalCases = 0;
        int testCasesCounter = 0;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(reader)) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                if (totalCases == 0 && testCasesCounter == 0) {
                    totalCases = Integer.parseInt(sCurrentLine);
                } else {
                    testCasesCounter++;
                    sb.append("Case #").append(testCasesCounter).append(": ");
                    sb.append(convert(sCurrentLine)).append("\n");
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
