public class Hand {
    private Card cards[];
    private int totalCardNumber;
    private int handCardNumber;
    private int cardIndex;


    public Hand(int cardNumber){
        this.totalCardNumber=cardNumber;
        this.handCardNumber=0;
        this.cardIndex=0;
        cards=new Card[this.totalCardNumber];
    }

    public void insertCard(Card card){
        if(cardIndex == totalCardNumber){
            System.out.println("Cards are unable to give");
        }
        else{
            cards[cardIndex]=card;
            cardIndex++;
            handCardNumber++;
        }
    }
    public void eraseCard(int id){
        cards[id]=null;
        handCardNumber--;
    }
    public Card openCard(int id){
        return cards[id];
    }
    public int getCardIndex(){
        return cardIndex;
    }
    public int getHandCardNum(){
        return this.handCardNumber;
    }
    public int getTotalCardNum(){
        return this.totalCardNumber;
    }


}
