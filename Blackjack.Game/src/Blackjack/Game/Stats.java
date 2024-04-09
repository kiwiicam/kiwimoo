package Blackjack.Game;

import java.io.*;

public class Stats {

    public static int plays, money, moneyBet, moneyWon, moneyLost, profit;

    //public static final String statsFile = "resources/stats.txt";
    //int newBet;

    public Stats() throws FileNotFoundException {
        plays = FileEdit.searchFile("plays");
        money = FileEdit.searchFile("money");
        moneyBet = FileEdit.searchFile("moneyBet");
        moneyWon = FileEdit.searchFile("moneyWon");
        moneyLost = FileEdit.searchFile("moneyLost");
      //  Stats.profit = searchFile("profit");
       // Stats.newBet = searchFile("newBet");
    }


}
