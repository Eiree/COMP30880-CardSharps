package blackjack;
import poker.*;


//notes - unsure if we need to implement riskiness of hands. (probably do for ai)
//we need to ensure that aces are either be determined as 1 or 11.

public class BlackjackHand { //reference pokerhand.java in poker, references both dealer and player
    
    //static variables for cards etc
    private int numCards = 2;
    public static final int MAXCARDS = 11; //initally gets 2 card to then hit on (the dealer gets 1 card initally)
    
    
    private Card[] hand;
    public BlackjackDeck deck;
    private BlackjackPlayer player;

    private int stake;
    private boolean stand;
    private boolean bust;
    private boolean won;
    private boolean draw;

    public BlackjackHand(Card[] hand, BlackjackDeck deck, BlackjackPlayer player){
        this.hand = hand;
        this.deck = deck;
        this.player = player;
    }

    public BlackjackHand(BlackjackDeck blackjackDeck, BlackjackPlayer player){
        this.deck = blackjackDeck;
        hand = new Card[MAXCARDS]; //11 is max possible cards

        //hand logic
        for (int i = 0; i < numCards; i++){
            setCard(i, blackjackDeck.dealNext());
        }

        //unsure if we need to sort hand or not ------
    }

   

    public String toString(){ //display hand
        String desc = "";
        for (int i = 0; i < numCards; i++){
            desc = desc + "\n       " + i + ":  " + getCard(i).toString();
        }
        return desc + "\n";
    }


    //getters
    public Card getCard(int num){
        if (num >= 0 && num < numCards){
            return hand[num];
        } else {
            return null;
        }
    }

    public Card[] getCards(){ //might need to test this
        return hand;
    }

    public int getValue(){ //gets value of card
        return getCard(0).getValue(); //index 0?
    }

    public int getNumCards(){
        return numCards;
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
        if (num >= 0 && num < numCards){
            hand[num] = card;
        }
    }

    //TODO CHANGE??
    public void addCard(Card card){
        hand[numCards++] = card;
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

    public boolean isBust(){
        int handValue = getHandValue();
        if (handValue > 21){
            return true;
        } else {
            return false;
        }
    }

    public boolean isSplit(){
        if (getNumCards() != 2){
            return false;
        }
        int cardValue1 = getCard(0).getValue();
        int cardValue2 = getCard(1).getValue();

        if (cardValue1 == cardValue2){
            return true;
        }
        return false;
    }

    public boolean isStand(){
        return stand;
    }

    public boolean isWon(){ //condition for win.
        return won;
    }

    public boolean isDraw(){
        return draw;
    }

    public boolean hit(BlackjackDeck deck){ //TODO
        //implement hit functionality
        System.out.println("\n> " + player.getName() + " says: Hit!");
        addCard(deck.dealNext());
        System.out.println(hand.toString());
        return true;
    }

    public void stand(){ //TODO
        //nothing
        if(!stand){
            stand = true;
            System.out.println("\n> " + player.getName() + " says: They will Stand!");
        }
    }

    public void doubleDown(BlackjackDeck deck){
        if (player.getBank() < stake) {
            System.out.println("\n> " + player.getName() + " says: I can't afford to double down!");
            return;
        }

        player.addBank(-stake);
        stake *= 2;

        hit(deck);
        stand(); //Check functionality

    }

    public boolean canSplit() {
        return isSplit() && player.getBank() >= stake;
    }

//split logic? -- unsure TODO
    public boolean split(BlackjackDeck deck){
        if (!canSplit()){

            return false;
        }

        Card[] cards = getCards();

        setCard(1, null);   //Set second card of hirst hand to null
        new BlackjackHand(new Card[]{cards[1]}, deck, player); //Add the card to new hand

        //hand[numOfHands].addCard(cards[0]);
        player.addBank(-stake);

        return true;
    }

    public void setStake(int newStake) {
        stake = newStake;
    }

    public int getStake() {
        return stake;
    }

    public void setWon(boolean newWon) {
        won = newWon;
    }

    public void setBust(boolean newBust) {
        bust = newBust;
    }
}
