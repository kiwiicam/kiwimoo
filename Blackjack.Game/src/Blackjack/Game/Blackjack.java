package Blackjack.Game;
import java.util.Scanner;
import java.util.ArrayList;
public class Blackjack {
    public static void main(String [] args)
    {
        ArrayList<Integer> Ycard = new ArrayList<>();
        ArrayList<Integer> Dcard = new ArrayList<>();
        Card myCard = new Card(new int[]{0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10}, null);
        Card suitCard = new Card(null, new String[]{"Hearts", "Spades", "Clubs", "Diamonds"});
        Card dealerCard = new Card(new int[]{0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10}, null);
        Card dealerSuitCard = new Card(null, new String[]{"Hearts", "Spades", "Clubs", "Diamonds"});
        String suit = suitCard.suit();
        String dSuit = dealerSuitCard.suit();
        Dcard.add(dealerCard.number());
        Ycard.add(myCard.number());    
        int sum = 0;
        int i = 0;
        System.out.println("Your first card is a " + Ycard.get(i) + " of " + suit);
        sum += Ycard.get(i);
        i++;
        suit = suitCard.suit();
        Ycard.add(myCard.number());
        sum += Ycard.get(i);
        System.out.println("Your second card is a " + Ycard.get(i) + " of " + suit + " which brings your total count to " + sum);
        i++;
        System.out.println("Hit or stand? (H/S");
        Scanner input = new Scanner(System.in);
        String input1 = input.nextLine();
        if(input1.toUpperCase().equals("H"))
        {
            Ycard.add(myCard.number());
            sum += Ycard.get(i);
            suit = suitCard.suit();
            System.out.println("Your next card is a " + Ycard.get(i) + " of " + suit + " which brings your total to " + sum);
            i++;
        }
        
        
        
        
        
        
        
        
    }
    
}
