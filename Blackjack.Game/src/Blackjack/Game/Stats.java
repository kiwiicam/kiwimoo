package Blackjack.Game;

import java.io.*;

public class Stats {

    public static int plays, money, moneyBet, moneyWon, moneyLost;
    

    public Stats() throws FileNotFoundException {
        plays = FileEdit.searchFile("plays");
        money = FileEdit.searchFile("money");
        moneyBet = FileEdit.searchFile("moneyBet");
        moneyWon = FileEdit.searchFile("moneyWon");
        moneyLost = FileEdit.searchFile("moneyLost");
    }


}
