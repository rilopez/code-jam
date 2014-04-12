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

    public static final String IMPOSSIBLE = "Impossible";

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

                    sb.append("Case #").append(testCasesCounter).append(":\n");
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
            return IMPOSSIBLE;
        }
        char[][] board = new char[rows][columns];

        //set the mines
        int mineR = rows - 1;
        int mineC = columns - 1;
        for (int i = 0; i < numberOfMines; i++) {
            board[mineR][mineC] = '*';
            mineC--;
            if (mineC < 0) {
                mineC = columns - 1;
                mineR--;
            }
        }

        //set cells without mines
        Map<Character, List<List<Integer>>> positionIndex = new HashMap<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                char currentCell = board[r][c];
                if (currentCell != '*') {
                    char neighborMines = Integer.toString(countNeighborChars(r, c, board, '*')).charAt(0);
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
        if (positionIndex.containsKey('0')) {
            List<List<Integer>> positionsWithZero = positionIndex.get('0');
            boolean allZerosAreConnected = true;
            if (positionsWithZero.size() > 1) {
                for (List<Integer> position : positionsWithZero) {
                    if (countNeighborChars(position.get(0), position.get(1), board, '0') == 0) {
                        allZerosAreConnected = false;
                        break;
                    }
                }
            }
            if (allZerosAreConnected && allOtherNumbersHaveAZeroNeighbor(board)) {
                List<Integer> firstPosition = positionsWithZero.get(0);
                rFastWinnerClick = firstPosition.get(0);
                cFastWinnerClick = firstPosition.get(1);
            }
        } else if (numberOfMines + 1 == rows * columns && positionIndex.values().size() > 0) {
            List<Integer> anyPosition = positionIndex.values().iterator().next().get(0);
            rFastWinnerClick = anyPosition.get(0);
            cFastWinnerClick = anyPosition.get(1);
        }

        if (rFastWinnerClick == -1 || cFastWinnerClick == -1) {
            return IMPOSSIBLE  + '\n' + printSolvedBoard(rFastWinnerClick, cFastWinnerClick, board);
        } else {
            return printSolvedBoard(rFastWinnerClick, cFastWinnerClick, board);
        }
    }

    private boolean allOtherNumbersHaveAZeroNeighbor(char[][] board) {
        boolean allHaveAZeroNeighbor = true;
        for (int r = 0; r < board.length && allHaveAZeroNeighbor; r++) {
            for (int c = 0; c < board[r].length; c++) {
                char cell = board[r][c];
                if (cell != '0' && cell != '*' && countNeighborChars(r, c, board, '0') == 0) {
                    allHaveAZeroNeighbor = false;
                    break;
                }
            }
        }
        return allHaveAZeroNeighbor;
    }

    private String printSolvedBoard(int rFastWinnerClick, int cFastWinnerClick, char[][] board) {
        StringBuilder sb = new StringBuilder();

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == '*') {
                    sb.append('*');
                } else if (r == rFastWinnerClick && c == cFastWinnerClick) {
                    sb.append('c');
                } else {
                    //sb.append('.');
                    sb.append(board[r][c]);
                }
            }
            if (r + 1 < board.length) {
                sb.append('\n');
            }
        }
        return sb.toString();
    }

    private int countNeighborChars(int r, int c, char[][] board, char character) {
        int rows = board.length;
        int columns = board[0].length;
        int mines = 0;
        //6 o'clock
        if (r + 1 < rows && board[r + 1][c] == character) {
            mines++;
        }
        //4:30
        if (r + 1 < rows && c + 1 < columns && board[r + 1][c + 1] == character) {
            mines++;
        }

        // 3 o'clock
        if (c + 1 < columns && board[r][c + 1] == character) {
            mines++;
        }

        //1:30
        if (r - 1 >= 0 && c + 1 < columns && board[r - 1][c + 1] == character) {
            mines++;
        }

        //12 o'clock
        if (r - 1 >= 0 && board[r - 1][c] == character) {
            mines++;
        }

        //10:30
        if (r - 1 >= 0 && c - 1 >= 0 && board[r - 1][c - 1] == character) {
            mines++;
        }

        //9 o'clock
        if (c - 1 >= 0 && board[r][c - 1] == character) {
            mines++;
        }

        //7:30
        if (r + 1 < rows && c - 1 >= 0 && board[r + 1][c - 1] == character) {
            mines++;
        }
        return mines;
    }
}
