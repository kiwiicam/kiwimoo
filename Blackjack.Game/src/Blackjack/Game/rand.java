package Blackjack.Game;
public class rand {
    public int randSuit(){
        int max = 3;
        int min = 0;
        int randS = (int)(Math.random() * (max - min + 1)) + min;
        return randS;
                
    }
    public int randNum(){
        int max = 12;
        int min = 0;
        int rand = (int)(Math.random() * (max - min + 1)) + min;
        return rand;
    }
    public int randPicture()
    {
        int max = 3;
        int min = 0;
        int rand = (int)(Math.random() * (max - min + 1)) + min;
        return rand;
        
                
    }
    
}
