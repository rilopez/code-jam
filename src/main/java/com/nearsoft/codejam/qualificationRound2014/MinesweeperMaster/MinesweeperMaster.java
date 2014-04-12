package com.nearsoft.codejam.qualificationRound2014.MinesweeperMaster;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MinesweeperMaster {
    public String execute(Reader reader) {
        int totalCases = 0;

        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(reader)) {
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {

                totalCases = Integer.parseInt(sCurrentLine);
                for (int testCasesCounter = 1; testCasesCounter <= totalCases; testCasesCounter++) {
                    Scanner scanner = new Scanner(br.readLine());

                    int rows = scanner.nextInt();
                    int columns = scanner.nextInt();
                    int numberOfMines = scanner.nextInt();

                    sb.append("Case #").append(testCasesCounter).append(": ");
                    sb.append(findConfigurationForOneClick(rows, columns, numberOfMines));
                    sb.append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private String findConfigurationForOneClick(int rows, int columns, int numberOfMines) {
        if (numberOfMines >= rows * columns) {
            return "Impossible";
        }
        char[][] board = new char[rows][columns];
        //set the mines
        int mineR = rows - 1;
        int mineC = columns - 1;
        for (int i = 0; i < numberOfMines; i++) {
            board[mineR][mineC] = '*';
            mineC--;
            if (mineC < 0) {
                mineC = rows - 1;
                mineR--;
            }
        }

        //set cells without mines
        Map<Character, List<List<Integer>>> positionIndex = new HashMap<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                char currentCell = board[r][c];
                if (currentCell != '*') {
                    char neighborMines = countNeighborMines(r, c, board, rows, columns);
                    board[r][c] = neighborMines;
                    if (!positionIndex.containsKey(neighborMines)) {
                        positionIndex.put(neighborMines, new ArrayList<List<Integer>>());
                    }
                    positionIndex.get(neighborMines).add(Arrays.asList(r, c));
                }
            }
        }

        int rFastWinnerClick = -1;
        int cFastWinnerClick = -1;
        for (int i = 0; i <= 8 && rFastWinnerClick < 0 && cFastWinnerClick < 0; i++) {
            char iAsChar = Integer.toString(i).charAt(0);
            if (positionIndex.containsKey(iAsChar)) {
                List<List<Integer>> listOfPositionForChar = positionIndex.get(iAsChar);
                for (List<Integer> position : listOfPositionForChar) {
                    //try to solve with one click
                    if (remainHiddenCellsAfterClickOn(position.get(0),position.get(1),board) == 0 ){
                        rFastWinnerClick = position.get(0);
                        cFastWinnerClick = position.get(1);
                        break;
                    }

                }
            }
        }

        return null;
    }

    private int remainHiddenCellsAfterClickOn(int r, int c, char[][] board) {

        return 0;  //To change body of created methods use File | Settings | File Templates.
    }

    private char countNeighborMines(int r, int c, char[][] board, int rows, int columns) {
        int mines = 0;
        //6 o'clock
        if (r + 1 < rows && board[r + 1][c] == '*') {
            mines++;
        }
        //4:30
        if (r + 1 < rows && c + 1 < columns && board[r + 1][c + 1] == '*') {
            mines++;
        }

        // 3 o'clock
        if (c + 1 < columns && board[r][c + 1] == '*') {
            mines++;
        }

        //1:30
        if (r - 1 >= 0 && c + 1 < columns && board[r - 1][c + 1] == '*') {
            mines++;
        }

        //12 o'clock
        if (r - 1 >= 0 && board[r - 1][c] == '*') {
            mines++;
        }

        //10:30
        if (r - 1 >= 0 && c - 1 >= 0 && board[r - 1][c - 1] == '*') {
            mines++;
        }

        //9 o'clock
        if (c - 1 >= 0 && board[r][c - 1] == '*') {
            mines++;
        }

        //7:30
        if (r + 1 < rows && c - 1 >= 0 && board[r + 1][c - 1] == '*') {
            mines++;
        }
        return Integer.toString(mines).charAt(0);
    }
}
