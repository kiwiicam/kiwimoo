
package randexample;

public class draw {
    private int drawnSuitInt;
    private int drawnFaceInt;
    private String drawnSuitString;
    private String drawnFaceString;

    public draw(int drawnSuitInt, int drawnFaceInt) {
        this.drawnSuitInt = drawnSuitInt;
        this.drawnFaceInt = drawnFaceInt;
        this.draw();
    }
        
    public void draw(){
        switch (drawnSuitInt) {
            case 0:
                drawnSuitString = "Hearts";
                break;
            case 1:
                drawnSuitString = "Spades";
                break;
            case 2:
                drawnSuitString = "Diamonds";
                break;
            case 3:
                drawnSuitString = "Clubs";
                break;
            default:
                drawnSuitString = "Did not run";
        }
        switch (drawnFaceInt) {
            case 0:
                drawnFaceString = "Ace";
                break;
            case 1:
                drawnFaceString = "Two";
                break;
            case 2:
                drawnFaceString = "Three";
                break;
            case 3:
                drawnFaceString = "Four";
                break;
            case 4:
                drawnFaceString = "Five";
                break;
            case 5:
                drawnFaceString = "Six";
                break;
            case 6:
                drawnFaceString = "Seven";
                break;
            case 7:
                drawnFaceString = "Eight";
                break;
            case 8:
                drawnFaceString = "Nine";
                break;
            case 9:
                drawnFaceString = "Ten";
                break;
            case 10:
                drawnFaceString = "Jack";
                break;
            case 11:
                drawnFaceString = "Queen";
                break;
            case 12:
                drawnFaceString = "King";
                break;
            default:
                drawnFaceString = "Joking";

        }
    }
}
