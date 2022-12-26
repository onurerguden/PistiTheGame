import java.io.*;
import java.util.Random;
import java.util.Scanner;


public class Deck {
    Random r = new Random();
    Scanner sc = new Scanner(System.in);
    String Stars="**************************";



    public Card[]newCards =new Card[52];

    private String[] Type = {"♠", "♣", "♥", "♦"};
    private String[] Name = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private int cardNumber;
    private  Card[] cards;


    public Deck() {
        cards = new Card[52];

        cardNumber=0;

        for (int t=0;t< Type.length; t++){
            for (int n =0; n< Name.length; n++){
                int Value=1;
                if (t==1 && n==1){
                    Value=2;
                }
                if (t==3 && n ==9){
                    Value=3;
                }

                cards[cardNumber] = new Card(cardNumber,Value,Type[t],Name[n]);

                cardNumber++;
            }
        }
        System.out.println(Stars);
        System.out.println("The Game has been started");
        System.out.println(Stars);


    }
    public Card getCard(int No) {
        cardNumber--;
        return cards[No];
    }

    public Card openCard(int No){
        return cards[No];

    }
    public int getCardNumber(){
        return cardNumber;
    }

    public void Shuffle(){
        System.out.println(Stars);
        System.out.println("Shuffling the cards...");
        System.out.println(Stars);
        for(int c=0; c< cards.length;c++){
            int index=(int)(Math.random()*cards.length);
            Card Temp;
            Temp=cards[c];
            cards[c]=cards[index];
            cards[index]=Temp;
        }
        cardNumber=52;

    }

    public void Cut(){



        Card[]bottomHalf = new Card[26];
        Card[]topHalf = new Card[26];

        System.out.println(Stars);
        System.out.println("Cutting the Deck");
        System.out.println(Stars);

        for(int i = 0; i<26;i++){
            bottomHalf[i]= (cards[i+26]);
        }
        for(int i = 0; i <26;i++){
            topHalf[i]=(cards[i]);
        }
        for(int i = 0;i<26;i++){
            newCards[i]=bottomHalf[i];
        }
        for(int i = 0;i<26;i++){
            newCards[i+26]=topHalf[i];

        }
        for(int i = 0;i<52;i++){
            cards[i]=newCards[i];
        }
        for(int k=0;k<cards.length;k++) {
            cards[k].setNo(k);
        }

    }


    }

