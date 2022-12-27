import java.io.*;
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

    public static Deck deck1 = new Deck();
    public static void main(String[] args) throws IOException {
        String Stars="**************************";
        deck1.Shuffle();
        deck1.Cut();
        //for (int i = 0; i < deck1.getCardNumber(); i++) {
          //  System.out.println(deck1.openCard(i).toString());

        //}
        System.out.println(Stars);

       dealDeck();
       gameStart();
    }
    public static void endGame() throws IOException {
        String Stars="**************************";

        System.out.println(Stars);
        System.out.println("Your Score: "+scorePlayer1);
        System.out.println("AI's Score: "+scorePlayer2);
        System.out.println(Stars);

        if(scorePlayer1>scorePlayer2){
            System.out.println("Congratulations you win the game");
            System.out.println(Stars);

        } else if (scorePlayer2>scorePlayer1) {
            System.out.println("You lose the game.Good luck text time :)");
            System.out.println(Stars);

        }



        File file = new File("Top_Score_List.txt");

        if (!file.exists()){
            file.createNewFile();
        }

        Scanner sc = new Scanner(System.in);



        System.out.println("What it your nickname?");
        String newPlayer= sc.nextLine();
        System.out.println(Stars);


        FileWriter fWriter = new FileWriter(file,true);
        BufferedWriter bWriter = new BufferedWriter(fWriter);

        FileReader fReader= new FileReader(file);
        String line;

        BufferedReader bReader = new BufferedReader(fReader);

        while ((line=bReader.readLine())!= null){
           // System.out.println("taradiğim sey ="+line);

            if(newPlayer.equals(line)){
                System.out.println("SELECT A NEW NİCK...");
                String TryNick =sc.nextLine();
                bWriter.write("\n"+TryNick + " = "+scorePlayer1);

                break;

            }


            else bWriter.write("\n"+newPlayer + " = "+scorePlayer1);
            break;

        }

        if ((line=bReader.readLine())== null){
            bWriter.write("SCORE LİST");
            bWriter.write("\n"+newPlayer + " = "+scorePlayer1);
        }


        System.out.println("Thanks for playing the game!..");


        bWriter.close();



        fReader.close();



    }
    public static void gameStart() throws IOException {
        String Stars="**************************";
        while(true){


            while(player1.getHandCardNum()>0 || player2.getHandCardNum()>0){

                System.out.println("Your deck: ");
                for(int i=player1.getCardIndex()-4;i<player1.getCardIndex();i++){
                    System.out.println(i+1+"." +player1.openCard(i));
                }
                System.out.println(Stars);
                /*System.out.println("Opennent Deck: ");
                for(int i=player2.getCardIndex()-4;i<player2.getCardIndex();i++){
                    System.out.println(i+1+"."+player2.openCard(i));
                }
                System.out.println("------------------");
                System.out.println("Floor Deck: ");
                for(int i=0;i<floor.getCardIndex();i++){
                    System.out.println(i+1+"."+floor.openCard(i));
                }*/
                System.out.println(Stars);
                System.out.println("Last card on floor: "+floor.openCard(floor.getCardIndex()-1));
                System.out.println(Stars);
                System.out.println("Number of the cards that are on the floor: "+ floor.getHandCardNum());
                System.out.println(Stars);
                System.out.print("Please draw a card: ");
                cardSelected = sc.nextInt()-1;

                checkPlayer(cardSelected);
                checkPlayer2();

            }
            round++;
            if(round==6){
                endGame();
                break;
            }

            dealDeckSecond();




        }
    }
    public static void checkPlayer(int a){
        String Stars="**************************";
        check1 =false;
        floor.insertCard(deck1.getCard(player1.openCard(cardSelected).getNo()));


        if(floor.getHandCardNum()==0 && player1.openCard(cardSelected).getName().equals(floor.openCard(floor.getCardIndex()-1).getName())){
            System.out.println("You made pisti ! 10 Points added");
            scorePlayer1 += 10;
            floor.eraseCard(floor.getCardIndex()-1);


        }

        for(int c=0;c<floor.getCardIndex();c++){
            if(floor.openCard(floor.getCardIndex()-1)!=null&&floor.openCard(floor.getCardIndex()-2)!=null){

                if(player1.openCard(cardSelected).getName()==floor.openCard(floor.getCardIndex()-2).getName()&&player1.openCard(cardSelected).getNo()!=floor.openCard(floor.getCardIndex()-2).getNo()){
                    for(int i=0;i<floor.getCardIndex();i++){
                        if(floor.openCard(i)!=null){
                            scorePlayer1 += floor.openCard(i).getValue();
                            floor.eraseCard(i);
                        }
                    }
                    check1 = true;
                    scorePlayer1 += player1.openCard(cardSelected).getValue();




                }
            }

        }






        player1.eraseCard(cardSelected);




    }
    public static void checkPlayer2(){
        String Stars="**************************";
        check = false;




        for(int i=0;i<player2.getCardIndex();i++){




            if(player2.openCard(i)!=null &&check1==false){



                if(floor.getHandCardNum()==0 && player2.openCard(i).getName().equals(floor.openCard(floor.getHandCardNum()).getName())){
                    System.out.println("Oppenent made a pisti! 10 Points added to oppenent.");
                    floor.eraseCard(floor.getCardIndex()-1);
                    player2.eraseCard(i);
                    check = true;
                    break;

                }
                else if(player2.openCard(i).getName().equals(floor.openCard(floor.getCardIndex()-1).getName())){
                    floor.insertCard(player2.openCard(i));
                    for(int j=0;j<floor.getCardIndex();j++){
                        if(floor.openCard(j)!=null){
                            scorePlayer2 += floor.openCard(j).getValue();
                            floor.eraseCard(j);
                        }
                    }
                    scorePlayer2 += player2.openCard(i).getValue();
                    player2.eraseCard(i);
                    check = true;
                    check2 =true;

                    break;


                }




            }







        }


        if(check == false){


            for(int i=0;i<player2.getCardIndex();i++){



                if(player2.openCard(i)!=null){

                    floor.insertCard(deck1.getCard(player2.openCard(i).getNo()));
                    player2.eraseCard(i);
                    break;


                }








            }






        }












    }


    public static void dealDeck(){
        String Stars="**************************";
        System.out.println(Stars);
        for(int i=0;i<4;i++){

            player1.insertCard(deck1.getCard(k));

            k++;


            player2.insertCard(deck1.getCard(k));

            k++;


            floor.insertCard(deck1.getCard(k));

            k++;


        }
        System.out.println("-----------------");
    }
    public static void dealDeckSecond(){
        String Stars="**************************";
        System.out.println(Stars);
        for(int i=0;i<4;i++){
            player1.insertCard(deck1.getCard(k));

            k++;

            player2.insertCard(deck1.getCard(k));

            k++;

        }

        System.out.println(Stars);

    }


}
