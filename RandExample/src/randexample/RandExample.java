package randexample;
import java.util.Random;
public class RandExample {

    public static String card;
    public static int drawnSuitInt = -1;
    public static String drawnSuitString = "Dirt";
    public static int drawnFaceInt = -1;
    public static String drawnFaceString = "Joker";

    public static void main(String[] args) {
        Random rand = new Random();
        int upperSuits = 4; //four different suits to draw 0-3
        int upperFace = 13; //13 face cards 0-12

        drawnSuitInt = rand.nextInt(upperSuits);
        drawnFaceInt = rand.nextInt(upperFace);
        
    
        System.out.println(drawnSuitInt);
        System.out.println(drawnFaceInt);
        draw(drawnSuitInt, drawnFaceInt); //Draw hand1 = new draw(drawnSuitInt, drawnFaceInt, drawnSuitString, drawnSuitString);
        System.out.println(drawnSuitString);
        System.out.println(drawnFaceString);

    }
    
}

