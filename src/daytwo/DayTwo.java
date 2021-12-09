package daytwo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DayTwo {
    public static void main(String[] args) {
        ArrayList<String> data = readData("src/daytwo/input_data2.txt");
        ArrayList<String> testData = readData("src/daytwo/test_data2.txt");

        int testAnswer = solveChallenge(testData);
        System.out.println("test: " + testAnswer);

        int answer = solveChallenge(data);
        System.out.println("answer: " + answer);

        int partTwo_testAnswer = partTwo_solveChallenge(testData);
        System.out.println("test part 2: " + partTwo_testAnswer);

        int partTwo_answer = partTwo_solveChallenge(data);
        System.out.println("answer part 2: " + partTwo_answer);
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

    private static int solveChallenge(ArrayList<String> list) {
        // What do you get if you multiply your final horizontal position by your final depth?
        int horizontalPosition = 0;
        int depth = 0;

        for (int i = 0; i < list.size(); i++) {
            String instruction = list.get(i);
            String[] instructionSplit = instruction.split(" ");
            String direction = instructionSplit[0];
            int number = Integer.parseInt(instructionSplit[1]);
            if (direction.equals("forward")) {
                horizontalPosition += number;
            } if (direction.equals("up")) {
                depth -= number;
            } else if (direction.equals("down")) {
                depth += number;
            }
        }
        return horizontalPosition * depth;
    }

    private static int partTwo_solveChallenge(ArrayList<String> list) {
        // What do you get if you multiply your final horizontal position by your final depth?
        int horizontalPosition = 0;
        int aim = 0;
        int depth = 0;

        for (int i = 0; i < list.size(); i++) {
            String instruction = list.get(i);
            String[] instructionSplit = instruction.split(" ");
            String direction = instructionSplit[0];
            int number = Integer.parseInt(instructionSplit[1]);
            if (direction.equals("forward")) {
                horizontalPosition += number;
                depth += (aim * number);
            } if (direction.equals("up")) {
                aim -= number;
            } else if (direction.equals("down")) {
                aim += number;
            }
        }
        return horizontalPosition * depth;
    }
}
