package dayfour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class DayFour {

    private static ArrayList<Integer> drawList = new ArrayList<>();
    private static ArrayList<Integer[][]> boards = new ArrayList<>();
    private static ArrayList<Integer[][]> scoreSheets = new ArrayList<>();
    private static int currentDrawnNumber;
    private static int winningBoardUnmarkedSum;


    public static void main(String[] args) {
//        prepareData("src/dayfour/test_data4.txt");
        prepareData("src/dayfour/input_data4.txt");

        setupScoreSheets();

        playGame();
        int answer = solveChallenge();
        System.out.println("answer: " + answer);
    }

    private static ArrayList<String> readData(String file) {
        ArrayList<String> inputData = new ArrayList<>();
        String value = "";
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            while (in.ready()) {
                value = in.readLine();
                inputData.add(value);
            }
        } catch (Exception e) {
            System.out.println("Error reading data." + e);
        }
        return inputData;
    }

    private static void prepareData(String file) {
        ArrayList<String> inputData = readData(file);
        createDrawList(inputData);

        ArrayList<ArrayList<String>> boardsStrings = new ArrayList<>();
        int numberOfLines = inputData.size();

        int count = 0;
        while (count < numberOfLines) {
            ArrayList<String> currentBoard = new ArrayList<>();
            inputData.remove(0);
            count += 1;
            for (int i = 0; i < 5; i++) {
                String data = inputData.remove(0);
                currentBoard.add(i, data);
                count += 1;
            }
            boardsStrings.add(currentBoard);
        }

        formatBoardsToIntegers(boardsStrings);
    }

    private static void formatBoardsToIntegers(ArrayList<ArrayList<String>> boardsStrings) {
        for (ArrayList<String> board : boardsStrings) {
            Integer[][] currentBoard = new Integer[5][5];
            for (int line = 0; line < board.size(); line++) {
                String[] list = board.get(line).trim().split("\\s+");
                for (int no = 0; no < list.length; no++) {
                    String number = list[no];
                    int value = Integer.parseInt(number);
                    currentBoard[line][no] = value;
                }
            }
            boards.add(currentBoard);
        }
    }

    private static void createDrawList(ArrayList<String> inputData) {
        String drawLine = inputData.remove(0);
        String[] list = drawLine.split(",");
        for (int i = 0; i < list.length; i++) {
            String number = list[i];
            int value = Integer.parseInt(number);
            drawList.add(i, value);
        }
    }

    private static void setupScoreSheets() {
        for (Integer[][] board : boards) {
            Integer[][] scoreSheet = {{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
            scoreSheets.add(scoreSheet);
        }
    }

    private static void playGame() {
        for (int drawnNumber : drawList) {
            currentDrawnNumber = drawnNumber;
            System.out.println("Drawn number is: " + drawnNumber);
            // players check their boards
            for (int i = 0; i < boards.size(); i++) {
                Integer[][] currentBoard = boards.get(i);
                System.out.println("\n\n****** Player with board " + i + " is checking their score...");
                // check each number in the board
                for (int j = 0; j < currentBoard.length; j++) {
                    for (int k = 0; k < currentBoard[0].length; k++) {
                        int currentNumber = currentBoard[j][k];
                        System.out.println("Does " + currentNumber + " match " + drawnNumber + "?");
                        if (currentNumber == drawnNumber) {
                            System.out.println("Yes! It matches.");
                            // so update the number on my scoresheet
                            Integer[][] currentScoreSheet = scoreSheets.get(i);
                            currentScoreSheet[j][k] = 1;
                            System.out.println(Arrays.deepToString(currentScoreSheet));
                            if (checkIfWon(currentScoreSheet, i)) {
                                System.out.println("BINGO!!!");
                                System.out.println("The winning board is: " + i);
                                return;
                            }
                        } else {
                            System.out.println("Nope! :(");
                            System.out.println(Arrays.deepToString(scoreSheets.get(i)));
                        }
                    }
                }
            }
        }
    }

    private static Boolean checkIfWon(Integer[][] scoreSheet, int boardNumber) {
        // tally up score
        for (int i = 0; i < scoreSheet.length; i++) {
            int rowTotal = 0;
            int colTotal = 0;
            for (int j = 0; j < scoreSheet[0].length; j++) {
                rowTotal += scoreSheet[i][j];
                colTotal += scoreSheet[j][i];
            }
            // check if won
            if (rowTotal == 5 || colTotal == 5) {

                for (int m = 0; m < scoreSheet.length; m++) {
                    for (int n = 0; n < scoreSheet[0].length; n++) {
                        if (scoreSheet[m][n] == 0) {
                            winningBoardUnmarkedSum += boards.get(boardNumber)[m][n];
                        }
                    }
                }
                    return true;
            }
        }
        return false;
    }

    private static int solveChallenge() {
        int answer = currentDrawnNumber * winningBoardUnmarkedSum;
        return answer;
    }
}
