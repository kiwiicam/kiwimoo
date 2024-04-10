package Blackjack.Game;

import java.util.*;
import java.util.Scanner;
import Blackjack.Game.Stats;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Blackjack {

    Stats stats;
    public static int tableAmount, bet;
    private final ArrayList<Integer> Ycard, Dcard;
    private int Dsum, sum, i, a;
    private String suit;
    private final Card myCard, suitCard;

    public Blackjack() throws FileNotFoundException {
        this.stats = new Stats();
        this.Ycard = new ArrayList<>();
        this.Dcard = new ArrayList<>();
        this.myCard = new Card(new int[]{0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10}, null);
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
        getPlayersBet();
        gameStart();
        Turn();
    }

    public void gameStart() throws IOException {
        DAceCheck();
        AceCheck();
        AceCheck();

    }

    public void Hit() throws IOException {
        AceCheck();
        if (sum > 21) {
            System.out.println("You have lost L bozo");
            endGame(0);
        } else if (sum == 21) {
            System.out.println("YOU HAVE WON WOOHOOOO KEEP GAMBLING!!!");
            endGame(1);
        }

    }

    public void Dhit() throws IOException {
        DAceCheck();
        if (Dsum == 21) {
            System.out.println("never gamble buddy");
            endGame(0);
        }

    }

    public void AceCheck() {
        Ycard.add(myCard.number());
        suit = suitCard.suit();

        if (Ycard.get(i) == 0) {
            if (sum + 11 > 21) {
                sum += 1;
                System.out.println("Your card is an ace of " + suit + " which brings your total to " + sum);
                i++;
            } else if (sum + 11 <= 21) {
                sum += 11;
                System.out.println("Your card is an ace of " + suit + " which brings your total to " + sum);
                i++;
            }
        } else if (Ycard.get(i) == 10) {
            String pictureCard = pictureCheck();
            if (pictureCard != null) {
                sum += Ycard.get(i);
                System.out.println("Your card is a " + pictureCard + " of " + suit + " which brings your total to " + sum);
                i++;

            } else {
                sum += Ycard.get(i);
                System.out.println("Your card is a " + Ycard.get(i) + " of " + suit + " which brings your total to " + sum);
                i++;
            }

        } else {

            sum += Ycard.get(i);
            System.out.println("Your card is a " + Ycard.get(i) + " of " + suit + " which brings your total to " + sum);
            i++;
        }

    }

    public void DAceCheck() {
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
        } 
        else if (Dcard.get(a) == 10) {
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
        Rand randGenerator = new Rand();
        int randIndex = randGenerator.randPicture();
        if (randIndex == 3) {
            return null;
        } 
        else if(randIndex == 0 || randIndex == 1 || randIndex == 2){
            String[] pictureList = {"King", "Queen", "Jack"};
            String picture = pictureList[randIndex];
            return picture;
        }
        return null;
    }

    public void Turn() throws IOException {
        FileEdit.increasePlays();
        boolean y = true;
        while (y) {
            System.out.println("Hit or Stand? (H/S)");
            Scanner input = new Scanner(System.in);
            String input1 = input.nextLine();
            if (input1.toUpperCase().equals("H") && sum < 21) {
                Hit();
                if (sum > 21) {
                    y = false;
                }
                if (sum == 21) {
                    y = false;
                }
            } else if (input1.toUpperCase().equals("S")) {
                System.out.println("Okay you have stood!");
                y = false;
                dTurn();
            }
        }
    }

    public void dTurn() throws IOException {
        while (Dsum < 17 && sum <= 21) {
            Dhit();
            if (Dsum >= 17 && Dsum < 21) {
                System.out.println("Dealer stands.");
//                if(Dsum == 21)
//                {
//                    endGame(false);
//                }
            } else if (Dsum > 21) {
                System.out.println("Dealer busts! You win!");
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
        Scanner inp = new Scanner(System.in);
        String input2 = inp.nextLine().trim();
        switch (input2.toUpperCase()) {
            case "Y":
                Blackjack game = new Blackjack();
                game.bjGame();
                PlayAgain();
                break;
            case "N":
                System.out.println("Thanks for playing!");
                Print.StatsDisplay();
                break;
            default:
                PlayAgain();
                break;
        }
    }

    public void getPlayersBet() {
        while (Stats.money < 50) {
            System.out.println("It looks like you do not meet the mininum buy in....");
            System.out.println("Maybe if you ask nicely?");
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            if (input.equals("pleasegivemoney")) {
                Stats.money = 50;
            }
        }

        Scanner betInput = new Scanner(System.in);
        while (true) {
            System.out.println("How many coins do you wish to bet?");
            System.out.println("Please bet no less than 50 and no more than your current balance of: " + Stats.money);
            while (true) {
                try {
                    bet = betInput.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Please input a valid integer!");
                }
                betInput.nextLine();
            }
            if (bet >= 50 && bet <= Stats.money) {
                Stats.money = Stats.money - bet; //betting before cards are dealt
                Stats.moneyBet = Stats.moneyBet + bet;
                break;
            }

        }
    }

    public void endGame(int result) throws IOException { //true for win

        if (result == 0) {
            Stats.moneyLost += bet;
        } else if(result == 1){
            int wonCoins = bet * 2;
            Stats.money += wonCoins;
            Stats.moneyWon += bet;
        }
        else if(result == 2)
        {
            Stats.money = bet;            
        }
        FileEdit.gameEndingUpdates();
    }

    public static void terminate() {
        System.out.println("Program closed");
        System.out.println("House always wins");
        System.out.println("You will not be refunded any bet money");
        System.exit(0);
    }
}
