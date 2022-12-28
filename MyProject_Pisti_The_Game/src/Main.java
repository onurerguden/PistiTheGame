import java.io.*;
import  java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static int tour =0;
    public static boolean checkpoint;
    public static boolean checkpoin1;
    public static boolean checkpoin2;
    public static boolean checkGame = false;
    public static Hand player1 = new Hand(52);
    public static Hand AI = new Hand(52);
    public static Hand floor = new Hand(52);
    public static int scorePlayer1 = 0;
    public static int scoreAI = 0;
    public static int k = 0;

    public static int cardSelected;

    public static Deck deck1 = new Deck();
    public static void main(String[] args) throws IOException {
        String Stars="**************************";
        deck1.Shuffle();
        deck1.Cut();

        System.out.println("Do you want to see the deck ?\n(if yes press 1, if no press 2)");
        int answer=sc.nextInt();

        if (answer ==1){
            for (int i = 0; i < deck1.getCardNumber(); i++) {
              System.out.println(deck1.openCard(i).toString());
            }

        }
        else {
            System.out.println("You like privacy huh :)");
        }
        System.out.println(Stars);
        System.out.println("GAME IS STARTING!..");


       dealDeck();
       gameStart();
    }

    public static void dealDeck(){
        String Stars="**************************";
        System.out.println(Stars);
        for(int i=0;i<4;i++){

            player1.insertCard(deck1.getCard(k));

            k++;


            AI.insertCard(deck1.getCard(k));

            k++;


            floor.insertCard(deck1.getCard(k));

            k++;


        }
        System.out.println(Stars);
    }



    public static void dealDeckSecond(){
        String Stars="**************************";
        System.out.println(Stars);
        for(int i=0;i<4;i++){
            player1.insertCard(deck1.getCard(k));

            k++;

            AI.insertCard(deck1.getCard(k));

            k++;

        }

        System.out.println(Stars);

    }



    public static void gameStart() throws IOException {
        String Stars="**************************";
        while(true){


            while(player1.getHandCardNum()>0 || AI.getHandCardNum()>0){

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
            tour++;
            if(tour==6){
                endGame();
                break;
            }
            dealDeckSecond();

        }
    }
    public static void checkPlayer(int a){
        String Stars="**************************";
        checkpoin1 =false;
        floor.insertCard(deck1.getCard(player1.openCard(cardSelected).getNo()));


        if(floor.getHandCardNum()==0 && player1.openCard(cardSelected).getName().equals(floor.openCard(floor.getCardIndex()-1).getName())){
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
                    checkpoin1 = true;
                    scorePlayer1 += player1.openCard(cardSelected).getValue();

                }
            }

        }
        player1.eraseCard(cardSelected);

    }



    public static void checkPlayer2(){
        String Stars="**************************";
        checkpoint = false;




        for(int i=0;i<AI.getCardIndex();i++){

            if(AI.openCard(i)!=null &&checkpoin1==false){

                if(floor.getHandCardNum()==0 && AI.openCard(i).getName().equals(floor.openCard(floor.getHandCardNum()).getName())){
                    floor.eraseCard(floor.getCardIndex()-1);
                    AI.eraseCard(i);
                    checkpoint = true;
                    break;

                }
                else if(AI.openCard(i).getName().equals(floor.openCard(floor.getCardIndex()-1).getName())){
                    floor.insertCard(AI.openCard(i));
                    for(int j=0;j<floor.getCardIndex();j++){
                        if(floor.openCard(j)!=null){
                            scoreAI += floor.openCard(j).getValue();
                            floor.eraseCard(j);
                        }
                    }
                    scoreAI += AI.openCard(i).getValue();
                    AI.eraseCard(i);
                    checkpoint = true;
                    checkpoin2 =true;

                    break;


                }
            }
        }


        if(checkpoint == false){

            for(int i=0;i<AI.getCardIndex();i++){

                if(AI.openCard(i)!=null){

                    floor.insertCard(deck1.getCard(AI.openCard(i).getNo()));
                    AI.eraseCard(i);
                    break;
                }
            }

        }
    }




    public static void endGame() throws IOException {
        String Stars="**************************";

        System.out.println(Stars);
        System.out.println("Your Score: "+scorePlayer1);
        System.out.println("AI's Score: "+scoreAI);
        System.out.println(Stars);

        if(scorePlayer1>scoreAI){
            System.out.println("Congratulations you win the game");
            System.out.println(Stars);

        } else if (scoreAI>scorePlayer1) {
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


}
