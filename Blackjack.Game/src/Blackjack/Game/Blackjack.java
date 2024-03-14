package Blackjack.Game;

import java.util.Scanner;
import java.util.ArrayList;

public class Blackjack {

    private ArrayList<Integer> Ycard;
    private ArrayList<Integer> Dcard;
    private int Dsum;
    private int sum;
    private int i;
    private int a;
    private String suit;
    private String dSuit;
    private Card myCard;
    private Card suitCard;
    private Card dealerCard;
    private Card dealerSuitCard;

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
    public void bjGame()
    {
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

    public void Turn() {
        System.out.println("Hit or stand? (H/S)");
        Scanner input = new Scanner(System.in);
        String input1 = input.nextLine();
        if (input1.toUpperCase().equals("H")) {
            Dcard.add(dealerCard.number());
            Ycard.add(myCard.number());
            sum += Ycard.get(i);
            Dsum += Dcard.get(a);
            suit = suitCard.suit();
            dSuit = dealerSuitCard.suit();
            System.out.println("Your next card is a " + Ycard.get(i) + " of " + suit + " which brings your total to " + sum);
            System.out.println("The dealers next card is a " + Dcard.get(a) + " of " + dSuit + " dealers sum: " + Dsum);
            a++;
            if (Dsum >= 17 && Dsum != 21) {
                System.out.println("The Dealer Stood!");
            } else {
                
            }
            
            i++;
            if (sum > 21) {
                System.out.println("Bust! You lose");
                
            } else if (Dsum > 21) {
                System.out.println("The dealer busted you win!");
            } else {
                Turn();
            }

        }

    }
    public void PlayAgain()
    {
        System.out.println("Would you like to play again? (Y/N)");
        Scanner inp = new Scanner(System.in);
        String input2 = inp.nextLine();
        if(input2.toUpperCase().equals("Y"))
        {
            bjGame();
        }
        else
        {
            System.out.println("Thanks for playing!");
        }
        
        
    }

}
