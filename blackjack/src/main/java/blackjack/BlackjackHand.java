package blackjack;
import poker.*;


//notes - unsure if we need to implement riskiness of hands. (probably do for ai)
//we need to ensure that aces are either be determined as 1 or 11.

public class BlackjackHand { //reference pokerhand.java in poker, references both dealer and player
    
    //static variables for cards etc
    public static final int NUMCARDS = 2; //initally gets 2 card to then hit on (the dealer gets 1 card initally)
    public static final int DEFAULT_RISK = 20; //default risk to determine play

    private Card[] hand;
    public BlackjackDeck deck;

    public BlackjackHand(Card[] hand, BlackjackDeck deck){
        this.hand = hand;
        this.deck = deck;
    }

    public BlackjackHand(BlackjackDeck deck){
        this.deck = deck;
        hand = new Card[NUMCARDS];

        //hand logic
        for (int i = 0; i < NUMCARDS; i++){
            setCard(i, deck.dealNext());
        }

        //unsure if we need to sort hand or not ------
    }

    public int getRiskWorthiness(){
        return DEFAULT_RISK;
    }

    public String toString(){ //display hand
        String desc = "";
        for (int i = 0; i < NUMCARDS; i++){
            desc = desc + "\n       " + i + ":  " + getCard(i).toString();
        }
        return desc + "\n";
    }


    //getters
    public Card getCard(int num){
        if (num >= 0 && num < NUMCARDS){
            return hand[num];
        } else {
            return null;
        }
    }

    public int getValue(){ //gets value of card
        return getCard(0).getValue();
    }

    public int getNumCards(){
        return hand.length;
    }

    public int getHandValue(){
        int handValue = 0;
        int numAces = 0;

        for (int i = 0; i < getNumCards(); i++){
            int cardValue = getCard(i).getValue();
            if (cardValue == 1){ //ace card
                numAces++;
                handValue += 11; //assuming that it is currently holding the value of 11
            } else if (cardValue >= 10 && cardValue <= 13){ //face card (king, queen, jack)
                handValue += 10;
            } else {
                handValue += cardValue;
            }
        }

        while (handValue > 21 && numAces > 0) {
            handValue -= 10; //convert ace value to 1 instead of 11
            numAces--; //remove any ace.
        }
        return handValue;
    }


    //setters
    public void setCard(int num, Card card){
        if (num >= 0 && num < NUMCARDS){
            hand[num] = card;
        }
    }


    //hand classifer
    public boolean isBlackjack(){
        if (getNumCards() != 2){
            return false;
        }
        int handValue = getHandValue();
        if (handValue == 21){
            return true;
        }
        return false;
    }
}