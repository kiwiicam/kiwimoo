//https://github.com/kiwiicam/kiwimoo
package Blackjack.Game;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Blackjack {
    public boolean Dprev = false;
    public boolean prev = false;
    Stats stats; //DO NOT DELETE THIS
    public static int tableAmount, bet;
    private final ArrayList<Integer> Ycard, Dcard;
    private int Dsum, sum, i, a;
    private String suit;
    private final Card myCard, suitCard;

    public Blackjack() throws FileNotFoundException {
        
        this.stats = new Stats();
        this.Ycard = new ArrayList<>();
        this.Dcard = new ArrayList<>();
        //2 lists to hold the cards for each player
        this.myCard = new Card(new int[]{0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10}, null);
        //2 card arrays to be randomly selected for drawing a card
        this.suitCard = new Card(null, new String[]{"Hearts", "Spades", "Clubs", "Diamonds"});
        this.suit = suitCard.suit();
        this.i = 0;
        this.a = 0;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        //main logic of the game from starting it to playing it again
        Blackjack game = new Blackjack();
        game.bjGame();
        game.PlayAgain();
    }

    public void bjGame() throws IOException {
        //all the main methods compiled into one game method to make it easier to manage
        Print.StatsDisplay();
        Print.StartArt();
        Print.controls();
        Scan.putMoneyInTable();
        Scan.Bet();
        gameStart();
        Turn();
    }

    public void gameStart() throws IOException {
        //starts the game off with 1 dealer card and 2 player cards like a real blackjack game
        Dhit();
        Hit();
        Hit();
    }

    public void Hit() {
        //this function contains the main logic for receiving a card
        //this line below the comment is bassicly "drawing" a card as it calls from the card class which calls from the rand class to select a card randomly
        Ycard.add(myCard.number());
        suit = suitCard.suit();
        //then all this code below sorts through to find what type of card it is, whether it has special properties like an ace or a picture card

        if (null == Ycard.get(i)) {
            sum += Ycard.get(i);
            System.out.println("Your card is a " + Ycard.get(i) + " of " + suit + " which brings your total to " + sum);
            i++;
        } else {
            switch (Ycard.get(i)) {
                case 0:
                    
                    if (sum + 11 <= 21) {
                        sum += 11;
                        System.out.println("Your card is an ace of " + suit + " which brings your total to " + sum);
                    } else {
                        sum += 1;
                        System.out.println("Your card is an ace of " + suit + " which brings your total to " + sum);
                    }
                    i++;
                    break;
                case 10:
                    String pictureCard = pictureCheck();
                    if (pictureCard != null) {
                        sum += Ycard.get(i);
                        System.out.println("Your card is a " + pictureCard + " of " + suit + " which brings your total to " + sum);
                        i++;
                        prev = false;
                    } else {
                        sum += Ycard.get(i);
                        System.out.println("Your card is a " + Ycard.get(i) + " of " + suit + " which brings your total to " + sum);
                        i++;
                        prev = false;
                    }
                    break;
                default:
                    sum += Ycard.get(i);
                    if (prev && sum > 21 && Ycard.contains(0)) {
                        sum -= 10;                       
                        System.out.println("Your card is a " + Ycard.get(i) + " of " + suit + " which brings your total to " + sum + " since your previous card was an ace!");
                        prev = false;
                        i++;
                        break;
                    } else {
                        System.out.println("Your card is a " + Ycard.get(i) + " of " + suit + " which brings your total to " + sum);
                        i++;
                    }
                    break;
            }
        }
    }

    public void Dhit() {
        //this is the same as hit but for the dealer as to keep track of the dealers sum and cards
        Dcard.add(myCard.number());
        suit = suitCard.suit();

        if (null == Dcard.get(a)) {

            Dsum += Dcard.get(a);
            System.out.println("Dealers card is a " + Dcard.get(a) + " of " + suit + " which brings their total to " + Dsum);
            a++;
        } else {
            switch (Dcard.get(a)) {
                case 0:
                    Dprev = true;
                    if (Dsum + 11 > 21) {
                        Dsum += 1;
                        System.out.println("Dealers card is an ace of " + suit + " which brings their total to " + Dsum);
                        a++;
                    } else if (Dsum + 11 <= 21) {
                        Dsum += 11;
                        System.out.println("Dealers card is an ace of " + suit + " which brings their total to " + Dsum);
                        a++;
                    }
                    break;
                case 10:
                    String pictureCard = pictureCheck();
                    if (pictureCard != null) {
                        Dsum += Dcard.get(a);
                        System.out.println("Dealers card is a " + pictureCard + " of " + suit + " which brings their total to " + Dsum);
                        a++;
                        Dprev = false;
                    } else {
                        Dsum += Dcard.get(a);
                        System.out.println("Dealers card is a " + Dcard.get(a) + " of " + suit + " which brings their total to " + Dsum);
                        a++;
                        Dprev = false;
                    }
                    break;
                default:
                    Dsum += Dcard.get(a);
                    if (Dprev && Dsum > 21 && Dcard.contains(0)) {
                        Dsum -= 10;                        
                        System.out.println("Dealers card is a " + Dcard.get(a) + " of " + suit + " which brings their total to " + Dsum + " since your previous card was an ace!");
                        Dprev = false;
                        a++;
                        break;
                        } 
                    else {
                        System.out.println("Dealers card is a " + Dcard.get(a) + " of " + suit + " which brings their total to " + Dsum);
                        a++;
                        Dprev = false;
                        break;
                    }
            }
        }
    }

    public String pictureCheck() {
        //this method is used to check if a 10 is drawn change it to a picture card
        int randIndex = rand.randPicture();
        if (randIndex == 3) {
            return null;
        } else if (randIndex == 0 || randIndex == 1 || randIndex == 2) {
            String[] pictureList = {"King", "Queen", "Jack"};
            String picture = pictureList[randIndex];
            return picture;
        }
        return null;
    }

    public void Turn() throws IOException {
        //this is the main turn function for the player and asks if they want to hit or stand and based of the players decisions and cards draw
        //will decide if they win or not and has checks for this
        if (sum != 21) {
            FileEdit.increasePlays();
            while (true) {
                System.out.println("Hit or stand? (H/S)");
                String str = Scan.newInput().toUpperCase();
                if (str.equals("H") && sum < 21) {
                    Hit();
                    if (sum == 21) {
                        break;
                    }
                    if (sum > 21) {
                        break;
                    }
                } else if (str.equals("S")) {
                    System.out.println("----------------------------------------");
                    System.out.println("Okay you have stood!");
                    System.out.println("----------------------------------------");
                    dTurn();
                    break;
                }
            }
        }
        if (sum == 21) {
            System.out.println("you hit 21 !!! you WIN!!!!");
            endGame(1);

        } else if (sum > 21) {
            System.out.println("you bust you lose ):");
            endGame(0);

        }

    }

    public void dTurn() throws IOException {
        //simular as turn but for the dealer, but since the dealer goes last at the end of this method it compares scores to decide the 
        //outcome if neither have busted or hit blackjack(21)
        while (Dsum < 17 && sum <= 21) {
            Dhit();
            if (Dsum >= 17 && Dsum < 21) {
                System.out.println("Dealer stands.");
            } else if (Dsum > 21) {
                System.out.println("----------------------------------------");
                System.out.println("Dealer busts!");
                System.out.println("----------------------------------------");
                endGame(1);
            }
        }

        if (sum <= 21 && Dsum <= 21) {
            if (sum > Dsum) {
                System.out.println("You win!");
                endGame(1);
            } else if (sum < Dsum) {
                System.out.println("Dealer wins!");
                endGame(0);
            } else {
                System.out.println("It's a tie!");
                endGame(2);
            }
        }
    }

    public void PlayAgain() throws FileNotFoundException, IOException {
        //this method is called when the game has ended to update the stats and ask whether they would like another round
        
        a = 0;
        i = 0;
        Ycard.clear();
        Dcard.clear();
        System.out.println("Would you like to play again? (Y/N)");
        String str = Scan.newInput();
        switch (str) {
            case "Y":
                Blackjack game = new Blackjack();
                game.bjGame();
                PlayAgain();
                break;
            case "N":
                System.out.println("----------------------------------------");

                System.out.println("Thanks for playing!");
                Stats.money += tableAmount;
                System.out.println("You cash out " + tableAmount + "!");
                tableAmount = 0;
                System.out.println("----------------------------------------");

                FileEdit.gameEndingUpdates();
                Print.StatsDisplay();
                break;
            default:
                PlayAgain();
                break;
        }
    }
    //this next method is for updating the stats at the end of the game
    public void endGame(int result) throws IOException { //true for win
        int wonCoins = bet * 2;
        switch (result) {
            case 0:
                //lose. no need to change Stats.money as subbed when bet
                Stats.moneyLost += bet;
                break;
            case 1:
                //win
                tableAmount += wonCoins;
                Stats.moneyWon += bet;
                break;
            case 2:
                //tie refunds
                tableAmount += bet;
                break;
            default:
                break;
        }
        System.out.println("You now have " + tableAmount + " chips on the table");
        System.out.println("You also have " + Stats.money + " chips in the bank.");
        FileEdit.gameEndingUpdates();
    }

    public static void terminate() {
        //this is for when the player wants to quit, at any time if the player presses x it will allow them to 
        System.out.println("Program closed.");
        System.out.println("House always wins..");
        System.out.println("You will not be refunded any bet money!");
        System.out.println("But you cash out your table chips of " + tableAmount);
        System.out.println("+======================================+");
        System.out.println();
        Stats.money += tableAmount;
        System.out.println("You know have a total amount of " + Stats.money + " chips.");
        System.out.println();
        System.out.println("+======================================+");

        tableAmount = 0;
        System.exit(0);
    }
}
