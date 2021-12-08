package dayone;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DayOne {

    public static void main(String[] args) {
        checkAgainstExample(); // answer should be 7 from example for testing

        // read full data (2000 items) to solve challenge
        // How many measurements are larger than the previous measurement?
        ArrayList<Integer> dataList = readData("src/dayone/input_data.txt");
        int answer = solveChallenge(dataList);
        System.out.println(answer);

        // part 2
        // How many sums are larger than the previous sum?
        partTwo_checkAgainstExample();

        int answer_partTwo = partTwo_solveChallenge(dataList);
        System.out.println(answer_partTwo);
    }

    private static ArrayList<Integer> readData(String file) {
        ArrayList<Integer> inputData = new ArrayList<>();
        String value = "";
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            while (in.ready()) {
                value = in.readLine();
                Integer number = Integer.valueOf(value);
                inputData.add(number);
            }
        } catch (Exception e) {
            System.out.println("Error reading data." + e);
        }
        return inputData;
    }

    private static int solveChallenge(ArrayList<Integer> measurementList) {
        // How many measurements are larger than the previous measurement?
        int totalMeasurementsLargerThanPrevious = 0;
        int previousValue = 0;
        for (int i = 0; i < measurementList.size(); i++) {
            int currentValue = measurementList.get(i);
            if (i > 0) {
                if (currentValue > previousValue) {
                    totalMeasurementsLargerThanPrevious += 1;
                }
            }
            previousValue = currentValue;
        }
        return totalMeasurementsLargerThanPrevious;
    }

    private static void checkAgainstExample() {
        // example data provided in instructions for verifying
        ArrayList<Integer> dataList = readData("src/dayone/test_data.txt");
        int answer = solveChallenge(dataList);
        System.out.println(answer);
    }

    private static int partTwo_solveChallenge(ArrayList<Integer> measurementList) {
        // How many sums are larger than the previous sum?
        int sumsLargerThanPrevious = 0;
        int previousSum = 0;
        int count = 0;
        for (int i = 0; i < measurementList.size(); i++) {
            if (i+1 < measurementList.size() && i+2 < measurementList.size()) {
                int currentValue = measurementList.get(i);
                int oneAbove = measurementList.get(i+1);
                int twoAbove = measurementList.get(i+2);
                int sum = currentValue + oneAbove + twoAbove;
                if (i > 0) {
                    if (sum > previousSum) {
                        sumsLargerThanPrevious += 1;
                    }
                }
                previousSum = sum;
            }
        }
        return sumsLargerThanPrevious;
    }

    private static void partTwo_checkAgainstExample() {
        // example data provided in instructions for verifying
        ArrayList<Integer> dataList = readData("src/dayone/test_data.txt");
        int answer = partTwo_solveChallenge(dataList);
        System.out.println(answer);
    }
}
