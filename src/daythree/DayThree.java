package daythree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DayThree {
    public static void main(String[] args) {
        ArrayList<String> data = readData("src/daythree/input_data3.txt");
        ArrayList<String> testData = readData("src/daythree/test_data3.txt");

        int testAnswer = solveChallenge(testData);
        System.out.println("test: " + testAnswer);

        int answer = solveChallenge(data);
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

    private static int solveChallenge(ArrayList<String> list) {
        // What is the power consumption of the submarine?
        // Power consumption is gamma rate multiplied by epsilon rate.

        ArrayList<Integer> gammaRateBinary = calculateGammaRate(list);
        ArrayList<Integer> epsilonRateBinary = calculateEpsilonRate(gammaRateBinary);

        String gammaRateBinaryString = "";
        for (Integer x : gammaRateBinary) {
            gammaRateBinaryString += x.toString();
        }

        String epsilonRateBinaryString = "";
        for (Integer x : epsilonRateBinary) {
            epsilonRateBinaryString += x.toString();
        }

        int gammaRateDecimal = Integer.parseInt(gammaRateBinaryString, 2);
        int epsilonRateDecimal = Integer.parseInt(epsilonRateBinaryString, 2);

        return gammaRateDecimal * epsilonRateDecimal;
    }

    private static ArrayList<Integer> calculateGammaRate(ArrayList<String> list) {

        ArrayList<Integer> gammaRateBinary = new ArrayList<>();
        int numberOf1s = 0;
        int numberOf0s = 0;
        int count = 0;

        // the test data and input data are different lengths
        while (count < list.get(0).length()) {
            for (int i = 0; i < list.size(); i++) {
                String binaryString = list.get(i);
                for (int j = count; j < count + 1; j++) {
                    int value = Character.getNumericValue(binaryString.charAt(j));
                    if (value == 1) {
                        numberOf1s += 1;
                    } else if (value == 0) {
                        numberOf0s += 1;
                    }
                }
            }
            if (numberOf1s > numberOf0s) {
                gammaRateBinary.add(count, 1);
            } else if (numberOf0s > numberOf1s) {
                gammaRateBinary.add(count, 0);
            }
            numberOf0s = 0;
            numberOf1s = 0;
            count += 1;
        }
        return gammaRateBinary;
    }

    private static ArrayList<Integer> calculateEpsilonRate(ArrayList<Integer> gammaRateBinary) {
        ArrayList<Integer> epsilonRateBinary = new ArrayList<>();
        for (int i = 0; i < gammaRateBinary.size(); i++) {
            if (gammaRateBinary.get(i) == 0) {
                epsilonRateBinary.add(i, 1);
            } else if (gammaRateBinary.get(i) == 1) {
                epsilonRateBinary.add(i, 0);
            }
        }
        return epsilonRateBinary;
    }
}
