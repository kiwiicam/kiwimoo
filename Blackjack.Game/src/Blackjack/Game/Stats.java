package Blackjack.Game;

import java.io.*;

public class Stats {

    public static int plays, money, moneyBet, moneyWon, moneyLost;
    
    //this searches the file to then be able to use and update the stats
    public Stats() throws FileNotFoundException {
        plays = FileEdit.searchFile("plays");
        money = FileEdit.searchFile("money");
        moneyBet = FileEdit.searchFile("moneyBet");
        moneyWon = FileEdit.searchFile("moneyWon");
        moneyLost = FileEdit.searchFile("moneyLost");
    }


}
