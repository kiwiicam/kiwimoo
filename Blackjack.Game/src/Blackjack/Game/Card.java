package Blackjack.Game;

import java.util.Random;

public class Card {
    private int[] cardNum;
    private String[] suits;
    public Card(int[] cardNum, String[] suits)
    {
        this.suits = suits;
        this.cardNum = cardNum;
    }
    public void setSuits(String[] suits)
    {
        this.suits = suits;
    }
    public void setCardNum(int[] cardNum) 
    {
    this.cardNum = cardNum;
    }
    
    public String[] getSuits()
    {
        return suits;
    }
    public int[] getCardNum()
    {
        return cardNum;
    }
    public String suit(){
        Random randSuitGenerator = new Random();
        int randSuitIndex = randSuitGenerator.randSuit();
        return suits[randSuitIndex];
        
    }
    public int number(){
        Random randGenerator = new Random();
        int randIndex = randGenerator.randNum();
        return cardNum[randIndex];        
        
    }

}
