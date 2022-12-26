import  java.util.Scanner;
import java.util.Random;
public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static Random r = new Random();
    public static Hand player1 = new Hand(52);
    public static Hand player2 = new Hand(52);
    public static Hand floor = new Hand(52);
    public static int scorePlayer1 = 0;
    public static int scorePlayer2 = 0;
    public static int k = 0;
    public static int round =0;
    public static boolean check;
    public static boolean check1;
    public static boolean check2;
    public static boolean checkGame = false;
    public static int cardSelected;
    public static int cardSelectedPlayer2;
    public static Deck deck1 = new Deck();
    public static void main(String[] args) {
        deck1.Shuffle();
        deck1.Cut();
        for (int i = 0; i < deck1.getCardNumber(); i++) {
            System.out.println(deck1.openCard(i).Anc());

        }
    }
}
