package Blackjack.Game;

import java.util.Scanner;
import java.util.ArrayList;

public class Blackjack {

    private final ArrayList<Integer> Ycard;
    private final ArrayList<Integer> Dcard;
    private int Dsum;
    private int sum;
    private int i;
    private int a;
    private String suit;
    private String dSuit;
    private final Card myCard;
    private final Card suitCard;
    private final Card dealerCard;
    private final Card dealerSuitCard;

    public Blackjack() {
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

    public static void main(String[] args) {
        Blackjack game = new Blackjack();
        game.bjGame();
        game.PlayAgain();
    }

    public void bjGame() {
        StartArt();
        gameStart();
        Turn();

    }

    public void gameStart() {
        Dcard.add(dealerCard.number());
        Ycard.add(myCard.number());
        System.out.println("The dealers first card is a " + Dcard.get(a) + " of " + dSuit);
        System.out.println("Your first card is a " + Ycard.get(i) + " of " + suit);
        sum += Ycard.get(i);
        Dsum += Dcard.get(a);
        a++;
        i++;
        suit = suitCard.suit();
        Ycard.add(myCard.number());
        sum += Ycard.get(i);
        System.out.println("Your second card is a " + Ycard.get(i) + " of " + suit + " which brings your total count to " + sum);
        i++;
    }

    public void Hit() {
        Ycard.add(myCard.number());
        sum += Ycard.get(i);
        suit = suitCard.suit();
        System.out.println("Your next card is a " + Ycard.get(i) + " of " + suit + " which brings your total to " + sum);
        i++;
        if (sum > 21) {
            System.out.println("You have lost L bozo");
        } else if (sum == 21) {
            System.out.println("YOU HAVE WON WOOHOOOO KEEP GAMBLING!!!");
        }
    }

    public void Dhit() {
        Dcard.add(dealerCard.number());
        Dsum += Dcard.get(a);
        dSuit = dealerSuitCard.suit();
        System.out.println("Dealer's next card is a " + Dcard.get(a) + " of " + dSuit + " which brings the dealer's total to " + Dsum);
        a++;
    }

    public boolean hasAce(ArrayList<Integer> hand) {
        for (int card : hand) {
            if (card == 11) {
                return true;
            }
        }
        return false;
    }

    public void Turn() {
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

    public void dTurn() {
        while (Dsum < 17 && sum <= 21) {
            Dhit();
            if (Dsum >= 17 && Dsum < 21) {
                System.out.println("Dealer stands.");
            } else if (Dsum > 21) {
                System.out.println("Dealer busts! You win!");
            }
        }

        if (sum <= 21 && Dsum <= 21) {
            if (sum > Dsum) {
                System.out.println("You win!");
            } else if (sum < Dsum) {
                System.out.println("Dealer wins!");
            } else {
                System.out.println("It's a tie!");
            }
        }
    }

    public void PlayAgain() {
        System.out.println("Would you like to play again? (Y/N)");
        Scanner inp = new Scanner(System.in);
        String input2 = inp.nextLine();
        if (input2.toUpperCase().equals("Y")) {
            Blackjack game = new Blackjack();
            game.bjGame();
            PlayAgain();
        } else if (input2.toUpperCase().equals("N")) {
            System.out.println("Thanks for playing!");
        } else {
            PlayAgain();
        }
    }

    public void StartArt() {
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

}
