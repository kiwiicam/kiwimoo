package Blackjack.Game;
public class Card {
    private int[] cardNum = {0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
    private String[] suits = {"Hearts", "Spades", "Clubs", "Diamonds"};
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
        rand randSuitGenerator = new rand();
        int randSuitIndex = randSuitGenerator.randSuit();
        return suits[randSuitIndex];
        
    }
    public int number(){
        rand randGenerator = new rand();
        int randIndex = randGenerator.randNum();
        return cardNum[randIndex];        
        
    }

}
