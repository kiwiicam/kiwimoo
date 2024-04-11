/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Blackjack.Game;

/**
 *
 * @author xndmo
 */
public class Print {

    public static void StartArt() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.print("88          88                       88        88                       88         \n"
                + "88          88                       88        \"\"                       88         \n"
                + "88          88                       88                                 88         \n"
                + "88,dPPYba,  88 ,adPPYYba,  ,adPPYba, 88   ,d8  88 ,adPPYYba,  ,adPPYba, 88   ,d8   \n"
                + "88P'    \"8a 88 \"\"     `Y8 a8\"     \"\" 88 ,a8\"   88 \"\"     `Y8 a8\"     \"\" 88 ,a8\"    \n"
                + "88       d8 88 ,adPPPPP88 8b         8888[     88 ,adPPPPP88 8b         8888[      \n"
                + "88b,   ,a8\" 88 88,    ,88 \"8a,   ,aa 88`\"Yba,  88 88,    ,88 \"8a,   ,aa 88`\"Yba,   \n"
                + "8Y\"Ybbd8\"'  88 `\"8bbdP\"Y8  `\"Ybbd8\"' 88   `Y8a 88 `\"8bbdP\"Y8  `\"Ybbd8\"' 88   `Y8a  \n"
                + "                                              ,88                                  \n"
                + "                                            888P\"                                  ");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

    }

    public static void StatsDisplay() {
        System.out.println("---------------------");
        System.out.println("Games played: " + Stats.plays);
        System.out.println("Current money: " + Stats.money);
        System.out.println("Total money bet: " + Stats.moneyBet);
        System.out.println("Total money won: " + Stats.moneyWon);
        System.out.println("Total money lost: " + Stats.moneyLost);
        System.out.println("Total profit: " + (Stats.moneyWon - Stats.moneyLost));
        System.out.println("----------------------");
    }

    public static void checkMoney() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("You currently have a total of " + Stats.money + " chips");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("You also currently have " + Blackjack.tableAmount + " chips on the table");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();
    }



    public static void tableInfo() {
        System.out.println("+======================================+");
        System.out.println("You cannot play with more than 500 chips at this table.");
        System.out.println("----------------------------------------");
        System.out.println("Minimum buy in is 50 chips.");
        System.out.println("----------------------------------------");
        System.out.println("You currently have a total of " + Stats.money + " chips.");
        System.out.println("----------------------------------------");
        System.out.println("How many chips do you wish to play with?");
        System.out.println("+======================================+");
    }
    
    public static void controls(){
        System.out.println("-----------------------");
         System.out.println("Input \"x\" at any time to close program!");
        System.out.println("You will not be refunded your hand buy you will cash out your table chips");
        System.out.println("\"checkstats\" at any time to display your stats.");
        System.out.println("\"cat\" at any time to display cat!");
        System.out.println("-----------------------");
    }
}
