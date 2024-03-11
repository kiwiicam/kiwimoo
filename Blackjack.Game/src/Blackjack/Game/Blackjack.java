package Blackjack.Game;
public class Blackjack {
    public static void main(String [] args)
    {
        Card myCard = new Card(new int[]{0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10}, null);
        Card suitCard = new Card(null, new String[]{"Hearts", "Spades", "Clubs", "Diamonds"});
        suitCard.suit();
        myCard.number();
        System.out.print("this shit rated mfing porn");
        
        
        
    }
    
}
