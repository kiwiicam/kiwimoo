/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Blackjack.Game;

import java.io.IOException;
import java.util.*;

/**
 *
 * @author xndmo
 */
public class Scan {

    public static Integer i;
    public static String str;

    public static void checkForX(String str) throws IOException {
        if (str.toUpperCase().equals("X")) {
            Blackjack.terminate();
        }
    }

    public static void checkForCat(String str) throws IOException {
        if (str.toUpperCase().equals("CAT")) {
            Print.cat();
        }
    }

    public static void checkForInputKey(String str) throws IOException {
        str = str.toUpperCase();
        switch (str) {
            case "X":
                Blackjack.terminate();
                break;
            case "CAT":
                Print.cat();
                break;
            case "CHECKSTATS":
                Print.StatsDisplay();
                break;
            default:
                break;

        }
    }

    public static String newInput() throws IOException { //having the string makes the string codes run here
        Scanner scan = new Scanner(System.in);
        str = scan.nextLine().trim().toUpperCase();
        checkForInputKey(str);
        return str;
    }

    public static void Bet() throws IOException {
        if (Blackjack.tableAmount >= 50) {
            while (true) {
                System.out.println("Place your bet for this hand!");
                System.out.println("You have " + Blackjack.tableAmount + " chips on the table.");

                str = newInput();
                try {
                    Blackjack.bet = Integer.parseInt(str);
                    if (Blackjack.bet >= 50 && Blackjack.bet <= Blackjack.tableAmount) {
                        Blackjack.tableAmount -= Blackjack.bet; //betting before cards are dealt //removing from table not Stats.money!
                        Stats.moneyBet = +Blackjack.bet;
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please input an integer!");
                }
            }
        } else {
            System.out.println("There is no money in the table.");
            putMoneyInTable();
        }
    }

    public static void putMoneyInTable() throws IOException { //this is the first scan of the program
        if (Blackjack.tableAmount < 50) {
            begForMoney();
        }
        if (Blackjack.tableAmount >= 50) {
            askToChangeTableAmount();
        } else {
            while (true) {
                Print.tableInfo();
                str = newInput();

                try {
                    int i = Integer.parseInt(str);
                    if (i >= 50 && i <= Stats.money && i <= 500) {
                        Blackjack.tableAmount = i;
                        Stats.money -= i;
                        break;
                    }
                    else{
                        System.out.println("Please input a value between 50 and "+Math.min(Stats.money, 500)+".");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please input an integer!");
                }
            }
        }
    }

    public static void askToChangeTableAmount() throws IOException {
        boolean running = true;
        while (running) {
            System.out.println("You currently have " + Blackjack.tableAmount + " chips on the table.");
            System.out.println("Do you wish to change this?");
            System.out.println("Y/N");
            str = newInput().toUpperCase();

            switch (str) {
                case "Y":
                    changeTableAmount();
                    running = false;
                    break;
                case "N":
                    running = false;
                    break;
                default:
                    System.out.println("Unknown input");
            }

        }
    }

    public static void changeTableAmount() throws IOException {
        System.out.println("Input the amount of chips to place on the table.");
        System.out.println("Minimum buy in is 50 chips. ");
        System.out.println("Max table amount is 500 chips");
        System.out.println("You currently have " + Stats.money + " chips.");
        while (true) {
            str = newInput();
            Stats.money += Blackjack.tableAmount;
            Blackjack.tableAmount = 0;
            try {
                if (Blackjack.tableAmount >= 50 && Blackjack.tableAmount <= Stats.money && Blackjack.tableAmount <= 500) {
                    Blackjack.tableAmount = Integer.parseInt(str);
                    break;
                } else {
                    System.out.print("Please input at least 50 chips and no more than ");
                    if (Stats.money < 500) {
                        System.out.println("your total of " + Stats.money + " chips.");
                    } else {
                        System.out.println("the table limit of 500");
                    }
                    System.out.println("----------------------------------------");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please input an integer!");
            }
        }
    }

    public static void begForMoney() throws IOException {
        while (Stats.money < 50) {
            System.out.println("It looks like you do not meet the mininum buy in....");
            System.out.println("Maybe if you ask nicely?");
            System.out.println("type \"pleasegivemoney\"");
            str = newInput();
            if (str.equals("pleasegivemoney")) {
                Stats.money = 50;
            }
        }
    }
}
