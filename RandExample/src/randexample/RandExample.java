/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package randexample;

import java.util.Random;

/**
 *
 * @author xndmo
 */
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
    public static void draw(int drawnSuitInt, int drawnFaceint) {
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

