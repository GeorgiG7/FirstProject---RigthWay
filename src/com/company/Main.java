package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    static int cows, bulls;
    static StringBuilder playerOneString = new StringBuilder();
    static String spaces = "                               ";
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        printEntering();

        int choice = input.nextInt();

        if (choice == 1) {
            playSinglePlayer();
        }else {
            playMultiPlayer();
        }

    }

    public static void playSinglePlayer() {
        int playerNumber;
        int[] playerNumberArray;
        int[] computerOneNumber = generateFourDigitNumberInArray();
        while (true) {
            cows = 0;
            bulls = 0;

            System.out.println("Въведете вашето число:");
            playerNumber = input.nextInt();
            playerNumberArray = putDigitsInArray(playerNumber);
            if (!isNumberValid(playerNumber, playerNumberArray)) {
                System.out.println("Невалидно число. Въведете " +
                        "четирицифрено число с четири различни цифри!");
                continue;
            }
            getBullsAndCows(computerOneNumber, playerNumberArray);

            printResult(playerNumber);
            checkForWinner();
        }
    }

    public static void playMultiPlayer(){
        int playerNumber;
        int[] playerNumberArray;
        int turn = 1;
        int[] computerTwoNumber = generateFourDigitNumberInArray();
        int[] computerOneNumber = generateFourDigitNumberInArray();
        int[] turnNumber;
        playerOneString.append("Играч 1:" + spaces + spaces + "| Играч 2:\n");
        while (true) {
            cows = 0;
            bulls = 0;

            System.out.println("\nНа ред е играч " + turn + ". Въведете следващото си число:");
            playerNumber = input.nextInt();
            if (playerNumber == 0) System.exit(0);
            playerNumberArray = putDigitsInArray(playerNumber);

            if (!isNumberValid(playerNumber, playerNumberArray)){
                System.out.println("Невалидно число. Въведете " +
                        "четирицифрено число с четири различни цифри!");
                continue;
            }
            turnNumber = turn==1?computerOneNumber:computerTwoNumber;
            getBullsAndCows(turnNumber, playerNumberArray);

            printResult(turn, playerNumber);
            checkForWinner(turn);
            turn = turn==1?2:1;

        }
    }

    static void checkForWinner(){
        if (bulls == 4) {
            System.out.println("Печелите!!!");
            System.exit(0);
        }
    }

    static void checkForWinner(int turn){
        if (bulls == 4) {
            System.out.println("Играч " + turn + " печели!!!");
            System.exit(0);
        }
    }

    public static int[] generateFourDigitNumberInArray() {
        Random rn = new Random();
        int firstDigit = (int) (rn.nextInt(9) + 1);
        int secondDigit = (int) rn.nextInt(10);
        int thirdDigit = (int) rn.nextInt(10);
        int forthDigit = (int) rn.nextInt(10);
        while (secondDigit == firstDigit) {
            secondDigit = (int) rn.nextInt(10);
        }
        while (thirdDigit == secondDigit || thirdDigit == firstDigit) {
            thirdDigit = (int) rn.nextInt(10);
        }
        while (forthDigit == thirdDigit ||
                forthDigit == secondDigit || forthDigit == firstDigit) {
            forthDigit = (int) rn.nextInt(10);
        }
        return new int[]{firstDigit, secondDigit, thirdDigit, forthDigit};
    }

    public static int[] putDigitsInArray(int playerNumberInt) {
        int[] playerNumberArray = new int[4];
        playerNumberArray[0] = playerNumberInt / 1000;
        playerNumberArray[1] = (playerNumberInt % 1000) / 100;
        playerNumberArray[2] = (playerNumberInt / 10) % 10;
        playerNumberArray[3] = playerNumberInt % 10;
        return playerNumberArray;
    }

    public static void printEntering() {
        System.out.println("Това е играта \"Бикове и крави\". Правилата са прости:" +
                "\nКомпютърът си намисля четирицифрено число с различни цифри," +
                " а ние трябва да го познаем,\nкато въвеждаме четирицифрени числа, " +
                "а той ни казва колко крави и колко бика имаме" +
                "\nКрава е цифра от нашето число, която я има в това на противника" +
                "(компютъра),\nно не е на същото място. Бикът е позната цифра и позицията ѝ. \nВъведете 0 за изход." +
                "\n\nИзберете брой играчи(1 или 2):");
    }

    public static boolean isNumberValid(int playerNumber, int[] playerNumberArray){
        for (int i = 0; i < playerNumberArray.length - 1; i++) {
            for (int j = i + 1; j < playerNumberArray.length; j++) {
                if (playerNumberArray[i] == playerNumberArray[j]) {
                    return false;
                }
            }
        }

        return playerNumber >= 1000 && playerNumber <= 9999;
    }

    public static void getBullsAndCows(int[] computerOneNumber, int[] playerNumberArray){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j && computerOneNumber[i] == playerNumberArray[j]) {
                    bulls++;
                } else if (computerOneNumber[i] == playerNumberArray[j]) {
                    cows++;
                }
            }
        }
    }

    public static void printResult(int playerNumber){
        String cowsString = cows > 1 || cows == 0 ? "крави" : "крава";
        String bullsString = bulls > 1 || bulls == 0 ? "бика" : "бик ";

        playerOneString.append("Числото " + playerNumber + " има " + cows + " " +
                cowsString + " и " + bulls + " " + bullsString + "\n");
        System.out.println(playerOneString.toString());
    }

    public static void printResult(int turn, int playerNumber){
        String cowsString = cows > 1 || cows == 0 ? "крави" : "крава";
        String bullsString = bulls > 1 || bulls == 0 ? "бика" : "бик ";

        if (turn == 1){
            playerOneString.append("Числото " + playerNumber + " има " + cows + " " +
                    cowsString + " и " + bulls + " " + bullsString + spaces + "      | ");
        } else {
            playerOneString.append("Числото " + playerNumber + " има " + cows + " " +
                    cowsString + " и " + bulls + " " + bullsString + "\n");
        }
        System.out.println(playerOneString.toString());

    }
}
