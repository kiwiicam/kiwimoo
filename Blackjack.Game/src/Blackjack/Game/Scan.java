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
        String str = scan.nextLine().trim();
        checkForInputKey(str);
        return str;
    }

    public static void Bet() throws IOException {
        if (Blackjack.tableAmount >= 50) {
            while (true) {
                System.out.println("Place your bet from this hand!");
                System.out.println("You have " + Blackjack.tableAmount + " chips on the table.");

                String str = newInput();

                try {
                    Blackjack.bet = Integer.parseInt(str);
                    if (Blackjack.bet >= 50 && Blackjack.bet <= Blackjack.tableAmount) {
                        Stats.money = Stats.money - Blackjack.bet; //betting before cards are dealt
                        Stats.moneyBet = Stats.moneyBet + Blackjack.bet;
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

    public static void putMoneyInTable() throws IOException {
        if (Stats.money < 50) {
            begForMoney();
        }
        if (Blackjack.tableAmount >= 50) {

            askToChangeTableAmount();
        }
        while (true) {
            System.out.println("How many chips do you wish to play with?");
            System.out.println("You currently have a total of " + Stats.money + " chips.");
            System.out.println("You cannot play with more than 500 chips at this table.");
            System.out.println("Minimum buy in is 50 chips.");
            String str = newInput();

            try {
                Blackjack.tableAmount = Integer.parseInt(str);
                if (Blackjack.tableAmount >= 50 && Blackjack.tableAmount <= Stats.money && Blackjack.tableAmount <= 500) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please input an integer!");
            }
        }
    }

    public static void askToChangeTableAmount() throws IOException {
        boolean change = false;
        boolean y = true;
        while (y) {
            System.out.println("You currently have " + Blackjack.tableAmount + " chips on the table.");
            System.out.println("Do you wish to increase or change this?");
            System.out.println("Y/N");
            String str = newInput().toUpperCase();

            switch (str) {
                case "Y":
                    System.out.println("What is the new amount to put in the table?");
                    change = true;
                    y = false;
                    break;
                case "N":
                    change = false;
                    y = false;
                    break;
                default:
                    System.out.println("Unknown input");
            }
        }
        while (change) {
            String str = newInput();
            try {
                Blackjack.tableAmount = Integer.parseInt(str);
                if (Blackjack.tableAmount >= 50 && Blackjack.tableAmount <= Stats.money && Blackjack.tableAmount <= 500) {
                    change = true;
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
            String str = newInput();
            if (str.equals("pleasegivemoney")) {
                Stats.money = 50;
            }
        }
    }
}
