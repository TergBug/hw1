package org.mycode;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static int lbound, ubound;
    private static int attempt;
    private static int randomNum, num;
    public static void main(String[] args) {
        System.out.println("--- Try guess the number game ---");
        setBounds();
        generateRandomNum();
        do{
            System.out.print("Attempt " + (++attempt) + ": ");
            if(!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Enter integer number, please");
                attempt--;
                continue;
            }
            num = scanner.nextInt();
            if(num>randomNum) System.out.println("Wrong! Give me lower number");
            else if(num<randomNum) System.out.println("Wrong! Give me greater number");
            else{
                System.out.println("You are right!");
                System.out.println("True number is " + randomNum + "\nNumber of your attempts is " + attempt);
                System.out.println("\nWhat would you like to choose?");
                System.out.println("1.Generate new random number");
                System.out.println("2.Change bounds and generate new random number");
                System.out.println("Or any symbol to exit game");
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
        System.out.println("--- New random number has generated ---");
    }
    private static void setBounds(){
        do{
            System.out.print("Enter lower and upper bounds: ");
            String s = scanner.nextLine();
            if(s.equals("")) s = scanner.nextLine();
            String[] ss = s.split("\\s+", 2);
            if(ss.length==2 && ss[0].matches("-*\\d+") && ss[1].matches("-*\\d+")) {
                lbound = Integer.parseInt(ss[0]);
                ubound = Integer.parseInt(ss[1]);
                break;
            }
            System.out.println("Wrong input! Try again...");
        } while (true);
    }
}
