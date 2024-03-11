package Blackjack.Game;
import java.util.Scanner;
public class Blackjack {
    public static void main(String [] args)
    {
        
        Card myCard = new Card(new int[]{0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10}, null);
        Card suitCard = new Card(null, new String[]{"Hearts", "Spades", "Clubs", "Diamonds"});
        Card dealerCard = new Card(new int[]{0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10}, null);
        Card dealerSuitCard = new Card(null, new String[]{"Hearts", "Spades", "Clubs", "Diamonds"});
        suitCard.suit();
        dealerSuitCard.suit();
        int Dsum = dealerCard.number();
        int sum = myCard.number();
        System.out.println("Your card " + sum);
        System.out.println("Hit or stand? (H/S");
        Scanner input = new Scanner(System.in);
        String input1 = input.nextLine();
        if(input1.toUpperCase().equals("H"))
        {
            sum += myCard.number();
            System.out.println(sum);
        }
        
        
        
        
        
        
        
        
    }
    
}
