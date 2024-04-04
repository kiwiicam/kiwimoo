package Blackjack.Game;

import java.util.*;
import java.util.Scanner;
import Blackjack.Game.Stats;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Blackjack {

    Stats stats;
    public int newBet;
    private final ArrayList<Integer> Ycard, Dcard;
    //private final ArrayList<Integer> Ycard;
    //private final ArrayList<Integer> Dcard;
    private int Dsum, sum, i, a;
    //private int Dsum;
    //private int sum;
    //private int i;
    //private int a;
    private String suit, dSuit;
    // private String suit;
    //private String dSuit;
    private final Card myCard, suitCard, dealerCard, dealerSuitCard;
    // private final Card myCard;
    //private final Card suitCard;
    //private final Card dealerCard;
    //private final Card dealerSuitCard;

    public Blackjack() throws FileNotFoundException {
        this.stats = new Stats();
        this.Ycard = new ArrayList<>();
        this.Dcard = new ArrayList<>();
        this.myCard = new Card(new int[]{0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10}, null);
        this.suitCard = new Card(null, new String[]{"Hearts", "Spades", "Clubs", "Diamonds"});
        this.dealerCard = new Card(new int[]{0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10}, null);
        this.dealerSuitCard = new Card(null, new String[]{"Hearts", "Spades", "Clubs", "Diamonds"});
        this.suit = suitCard.suit();
        this.dSuit = dealerSuitCard.suit();
        this.i = 0;
        this.a = 0;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Blackjack game = new Blackjack();
        game.bjGame();
        game.PlayAgain();
    }

    public void bjGame() throws IOException {
        printStats();
        StartArt();
        getPlayersBet();

        // Stats.updateStatsFile("newBet", newBet);
        gameStart();
        Turn();

    }

    public void gameStart() throws IOException {
        Dcard.add(dealerCard.number());
        Ycard.add(myCard.number());
        dSuit = dealerSuitCard.suit();
        if (DAceCheck() == 1) {
            Dsum += 11;
            System.out.println("The dealers first card is an ace of " + dSuit);
        } else {
            Dsum += Dcard.get(a);
            System.out.println("The dealers first card is a " + Dcard.get(a) + " of " + dSuit);
        }
        a++;

        switch (AceCheck()) {
            case 2:
                sum += 1;
                System.out.println("Your first card is an ace of " + suit);
                i++;
                break;
            case 1:
                sum += 11;
                System.out.println("Your first card is an ace of " + suit + " which brings your total count to " + sum);
                i++;
                break;
            default:
                System.out.println("Your first card is a " + Ycard.get(i) + " of " + suit);
                sum += Ycard.get(i);
                i++;
                Hit();
        }

    }

    public void Hit() throws IOException {
        Ycard.add(myCard.number());
        switch (AceCheck()) {
            case 2:
                sum += 1;
                System.out.println("Your next card is an ace of " + suit + " which brings your total to " + sum);
                if (sum > 21); else if (sum == 21) {
                    System.out.println("YOU HAVE WON WOOHOOOO KEEP GAMBLING!!!");
                    endGame(true);
                }
                i++;
                break;
            case 1:
                sum += 11;
                System.out.println("Your next card is an ace of " + suit + " which brings your total to " + sum);
                if (sum > 21); else if (sum == 21) {
                    System.out.println("YOU HAVE WON WOOHOOOO KEEP GAMBLING!!!");
                    endGame(true);
                }
                i++;
                break;
            default:
                sum += Ycard.get(i);
                suit = suitCard.suit();
                System.out.println("Your next card is a " + Ycard.get(i) + " of " + suit + " which brings your total to " + sum);
                i++;
                if (sum > 21) {
                    System.out.println("You have lost L bozo");
                    endGame(false);
                } else if (sum == 21) {
                    System.out.println("YOU HAVE WON WOOHOOOO KEEP GAMBLING!!!");
                    endGame(true);
                }
                break;
        }
    }

    public void Dhit() throws IOException {
        Dcard.add(dealerCard.number());
        switch (DAceCheck()) {
            case 2:
                Dsum += 1;
                System.out.println("Dealer's next card is a ace of " + dSuit + " which brings the dealer's total to " + Dsum);
                if (Dsum > 21) {
                    System.out.println("You have lost L bozo");
                    endGame(false);
                } else if (Dsum == 21) {
                    System.out.println("YOU HAVE LOST DONT KEEP GAMBLING");
                    endGame(false);
                }
                a++;
                break;
            case 1:
                Dsum += 11;
                System.out.println("Dealer's next card is a ace of " + dSuit + " which brings the dealer's total to " + Dsum);
                if (Dsum > 21) {
                    System.out.println("You have lost L bozo");
                    endGame(false);
                } else if (Dsum == 21) {
                    System.out.println("THE DEALER WON U SUCK");
                    endGame(false);
                }
                a++;
                break;
            default:

                Dsum += Dcard.get(a);
                dSuit = dealerSuitCard.suit();
                System.out.println("Dealer's next card is a " + Dcard.get(a) + " of " + dSuit + " which brings the dealer's total to " + Dsum);
                a++;
        }
    }

    public int AceCheck() {
        if (Ycard.get(i) == 0) {
            if (sum + 11 > 21) {
                return 2;
            } else if (sum + 11 <= 21) {
                return 1;
            }
        }
        return 0;
    }

    public int DAceCheck() {
        if (Dcard.get(a) == 0) {
            if (Dsum + 11 > 21) {
                return 2;
            } else if (Dsum + 11 <= 21) {
                return 1;
            }
        }
        return 0;
    }

    public void Turn() throws IOException {
        Stats.increasePlays();
        // newBet = getPlayersBet();
        boolean y = true;
        while (y) {
            System.out.println("Hit or stand? (H/S)");
            Scanner input = new Scanner(System.in);
            String input1 = input.nextLine();
            if (input1.toUpperCase().equals("H") && sum < 21) {
                Hit();
                if (sum > 21) {
                    y = false;
                }
                if (sum == 21) {
                    y = false;
                }
            } else if (input1.toUpperCase().equals("S")) {
                System.out.println("Okay you have stood!");
                y = false;
                dTurn();
            }
        }
    }

    public void dTurn() throws IOException {
        while (Dsum < 17 && sum <= 21) {
            Dhit();
            if (Dsum >= 17 && Dsum < 21) {
                System.out.println("Dealer stands.");
            } else if (Dsum > 21) {
                System.out.println("Dealer busts! You win!");
                endGame(true);
            }
        }

        if (sum <= 21 && Dsum <= 21) {
            if (sum > Dsum) {
                System.out.println("You win!");
                endGame(true);
            } else if (sum < Dsum) {
                System.out.println("Dealer wins!");
                endGame(true);
            } else {
                System.out.println("It's a tie!");
                endGame(false);
            }
        }
    }

    public void PlayAgain() throws FileNotFoundException, IOException {
        System.out.println("Would you like to play again? (Y/N)");
        Scanner inp = new Scanner(System.in);
        String input2 = inp.nextLine().trim();
        switch (input2.toUpperCase()) {
            case "Y":
                Blackjack game = new Blackjack();
                game.bjGame();
                PlayAgain();
                break;
            case "N":
                System.out.println("Thanks for playing!");
                printStats();
                break;
            default:
                PlayAgain();
                break;
        }
    }

    public void StartArt() {
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

    public void printStats() {
        System.out.println("---------------------");
        System.out.println("Games played: " + Stats.plays);
        System.out.println("Current money: " + Stats.money);
        System.out.println("Total money bet: " + Stats.moneyBet);
        System.out.println("Total money won: " + Stats.moneyWon);
        System.out.println("Total money lost: " + Stats.moneyLost);
        System.out.println("Total profit: " + (Stats.moneyWon - Stats.moneyLost));
        System.out.println("----------------------");
    }

    public static void gameEndUpdateCalls() throws IOException {
        //there is no need to make it update plays as it updates 
        //everytime the game runs 
        //as it was the og coding to test fileIO
        Stats.updateStatsFile("money", Stats.money);
        Stats.updateStatsFile("moneyBet", Stats.moneyBet);
        Stats.updateStatsFile("moneyWon", Stats.moneyWon);
        Stats.updateStatsFile("moneyLost", Stats.moneyLost);
        // Stats.updateStatsFile("profit", (Stats.moneyWon - Stats.moneyLost));
    }

    public void getPlayersBet() {
        while (Stats.money < 50) {
            System.out.println("It looks like you do not meet the mininum buyin?");
            System.out.println("Maybe if you ask nicely?");
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            if (input.equals("pleasegivemoney")) {
                Stats.money = 50;
            }
        }

        Scanner betInput = new Scanner(System.in);
        while (true) {
            System.out.println("How many coins do you wish to bet?");
            System.out.println("Please bet no less than 50 and no more than your current balance of: " + Stats.money);
            while (true) {
                try {
                    newBet = betInput.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Please input a valid integer!");
                }
                betInput.nextLine();
            }
            if (newBet >= 50 && newBet <= Stats.money) {
                Stats.money = Stats.money - newBet; //betting before cards are dealt
                Stats.moneyBet = Stats.moneyBet + newBet;
                break;
            }

        }
    }

    public void endGame(boolean win) throws IOException { //true for win

        if (!win) {
            Stats.moneyLost += newBet;
        } else {
            int wonCoins = newBet * 2;
            Stats.money += wonCoins;
            Stats.moneyWon += newBet;

        }

        gameEndUpdateCalls();
    }

}
