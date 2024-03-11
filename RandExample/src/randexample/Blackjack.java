/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package randexample;

/**
 *
 * @author Laptop
 */
public class Blackjack {
    public static void main(String [] args)
    {
        Card myCard = new Card(new int[]{0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10}, null);
        Card suitCard = new Card(null, new String[]{"Hearts", "Spades", "Clubs", "Diamonds"});
        suitCard.suit();
        myCard.number();
        
        
        
    }
    
}
