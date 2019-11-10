package org.mycode;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessGame {
    private static final String messageNameOfGame = "--- Try guess the number game ---";
    private static final String messageSetBounds = "Enter lower and upper bounds: ";
    private static final String messageGenerateNum = "--- New random number has generated ---";
    private static final String messageAttempt = "Attempt #: ";
    private static final String messageInputNotInt = "Enter integer number, please";
    private static final String messageNotGuess = "Give me # number";
    private static final String messageGuess = "You are right!";
    private static final String messageTrueNum = "True number is #";
    private static final String messageNumOfAttempts = "Number of your attempts is #";
    private static final String messageChooseMenu = "\n--- Сhoose menu ---";
    private static final String messageChooseMenuP1 = "1.Generate new random number";
    private static final String messageChooseMenuP2 = "2.Change bounds and generate new random number";
    private static final String messageChooseMenuP3 = "Or any symbol to exit game";
    
    private static Scanner scanner = new Scanner(System.in);
    private static int lbound, ubound;
    private static int attempt;
    private static int randomNum, num;

    public static void main(String[] args) {
        System.out.println(messageNameOfGame);
        setBounds();
        generateRandomNum();
        do{
            System.out.print(messageAttempt.replace("#", String.valueOf(++attempt)));
            if(!scanner.hasNextInt()) {
                scanner.next();
                System.out.println(messageInputNotInt);
                attempt--;
                continue;
            }
            num = scanner.nextInt();
            if(num>randomNum) System.out.println(messageNotGuess.replace("#", "lower"));
            else if(num<randomNum) System.out.println(messageNotGuess.replace("#", "greater"));
            else{
                System.out.println(messageGuess);
                System.out.println(messageTrueNum.replace("#", String.valueOf(randomNum)));
                System.out.println(messageNumOfAttempts.replace("#", String.valueOf(attempt)));
                System.out.println(messageChooseMenu);
                System.out.println(messageChooseMenuP1);
                System.out.println(messageChooseMenuP2);
                System.out.println(messageChooseMenuP3);
                switch (scanner.next()){
                    case "1":
                        generateRandomNum();
                        break;
                    case "2":
                        setBounds();
                        generateRandomNum();
                        break;
                    default:
                        System.exit(0);
                }
            }
        } while (true);
    }
    private static void generateRandomNum(){
        attempt = 0;
        if(lbound>ubound) {
            lbound += ubound;
            ubound = lbound-ubound;
            lbound -= ubound;
        }
        randomNum = lbound+(new Random()).nextInt(ubound==lbound ? 1 : ubound-lbound);
        System.out.println(messageGenerateNum);
    }
    private static void setBounds(){
        do {
            System.out.print(messageSetBounds);
            try {
                lbound = scanner.nextInt();
                ubound = scanner.nextInt();
                break;
            }catch(InputMismatchException e){
                scanner.next();
                System.out.println(messageInputNotInt);
            }
        }while (true);
    }
}
