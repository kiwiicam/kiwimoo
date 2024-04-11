package Blackjack.Game;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Blackjack {

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
        this.myCard = new Card(new int[]{0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7, 7}, null);
        this.suitCard = new Card(null, new String[]{"Hearts", "Spades", "Clubs", "Diamonds"});
        this.suit = suitCard.suit();
        this.i = 0;
        this.a = 0;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Blackjack game = new Blackjack();
        game.bjGame();
        game.PlayAgain();
    }

    public void bjGame() throws IOException {
        Print.StatsDisplay();
        Print.StartArt();
        Print.controls();
        Scan.putMoneyInTable();
        Scan.Bet();
        gameStart();
        Turn();
    }

    public void gameStart() throws IOException {
        Dhit();
        Hit();
        Hit();
    }

    public void Hit() {
        Ycard.add(myCard.number());
        suit = suitCard.suit();

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
                    prev = true;
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
                        sum -= Ycard.get(i);
                        sum+=1;
                        System.out.println("Your card is a " + Ycard.get(i) + " of " + suit + " which brings your total to " + sum + " since your previous card was an ace!");
                        prev = false;
                    } else {
                        System.out.println("Your card is a " + Ycard.get(i) + " of " + suit + " which brings your total to " + sum);
                        i++;
                    }
                    break;
            }
        }
    }

    public void Dhit() {
        Dcard.add(myCard.number());
        suit = suitCard.suit();

        if (Dcard.get(a) == 0) {
            if (Dsum + 11 > 21) {
                Dsum += 1;
                System.out.println("Dealers card is an ace of " + suit + " which brings their total to " + Dsum);
                a++;
            } else if (Dsum + 11 <= 21) {
                Dsum += 11;
                System.out.println("Dealers card is an ace of " + suit + " which brings their total to " + Dsum);
                a++;
            }
        } else if (Dcard.get(a) == 10) {
            String pictureCard = pictureCheck();
            if (pictureCard != null) {
                Dsum += Dcard.get(a);
                System.out.println("Dealers card is a " + pictureCard + " of " + suit + " which brings their total to " + Dsum);
                a++;

            } else {
                Dsum += Dcard.get(a);
                System.out.println("Dealers card is a " + Dcard.get(a) + " of " + suit + " which brings their total to " + Dsum);
                a++;
            }

        } else {

            Dsum += Dcard.get(a);
            System.out.println("Dealers card is a " + Dcard.get(a) + " of " + suit + " which brings their total to " + Dsum);
            a++;
        }
    }

    public String pictureCheck() {
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
