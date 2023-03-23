package blackjack;

import java.util.Arrays;

import poker.*;

abstract class BlackjackPlayer { //reference player.java, humanplayer.java and computerplayer.java
    
    
    private int bank = 0; //total money player has left (without stake)
    
    private BlackjackHand[] hand = new BlackjackHand[4]; //hands dealt to player
    private int[] stake = new int[4] ; //stakes of hands 
    private boolean[] stand = new boolean[4]; //flag for each hand
    private boolean[] bust = new boolean[4]; //flag for bust
    private boolean[] won = new boolean[4]; //flag for winning hand

    private int numOfHands = 1; 

    //DO WE NEED SURRENDER??
    private boolean surrendered = false; //essentially a fold system where players gets back half bet.


    private String name = ""; //UID for player name
    //private boolean win = false; //win condition check potentially?

    public BlackjackPlayer(String name, int money){
        this.name = name;
        this.bank = money;

        reset();
    }

    public void reset(){
        Arrays.fill(this.stake, 0);
        Arrays.fill(bust,  false);
        Arrays.fill(stand, false);
        
        hand =  new BlackjackHand[4];

        surrendered = false;
        //win = false;
    }

    public String toString(){ //handle events in game to display to user
        return "";
        //check for win/bust TODO
    }

    //getters
    public int getNumOfHands() {
        return numOfHands;
    }

    public BlackjackHand getHand(int handIndex){
        return hand[handIndex];
    }

    public Card getCard(int handIndex, int index){
        return hand[handIndex].getCard(index);
    }

    public int getBank(){
        return bank;
    }

    public int getStake(int handIndex){
        return stake[handIndex];
    }

    public String getName(){
        return name;
    }

    public boolean isBankrupt(){
        return bank == 0;
    }

    public boolean isBust(int handIndex){ //If go over 21
        return bust[handIndex];
    }

    public boolean hasSurrendered(){
        return surrendered;
    }

    public boolean isWon(int handIndex){ //condition for win.
        return won[handIndex];
    }

    
    //setters
    public void dealTo(BlackjackDeck deck, int originalStake){
        //Only Give 2 Cards when first dealt a hand

        if(originalStake>bank || isBankrupt()){
            //Need to do error handeling
        }

        hand[0] = deck.dealHand();
        stake[0] = originalStake;
    }
    //TODO

    // // player actions
    // // WHAT DOES SURRENDER DO
    // public void surrender(int handIndex){
    //     if (!surrendered){
    //         System.out.println("\n> " + getName() + " says: I surrender!\n");
    //         bank += (stake[handIndex] / 2);
    //         stake[handIndex] = 0; //shouldnt need to reset as reset() already init stake = 0
    //         surrendered = true;
    //     }
    // }


    public void hit(BlackjackDeck deck, int handIndex){ //TODO
        //implement hit functionality
        
        System.out.println("\n> " + getName() + " says: Hit!");
        hand[handIndex].addCard(deck.dealNext());
        
    }

    public void stand(int handIndex){ //TODO
        //nothing
        if(!stand[handIndex]){
            stand[handIndex] = true;
            System.out.println("\n> " + getName() + " says: They will Stand!");
        }
    }

    public void doubleDown(BlackjackDeck deck, int handIndex){
        if (bank < stake[handIndex]) {
            System.out.println("\n> " + getName() + " says: I can't afford to double down!");
            return;
        }

        bank -= stake[handIndex];
        stake[handIndex] *= 2;

        hit(deck, handIndex);
        stand(handIndex); //Check functionality
    
    }

    public boolean canSplit(int handIndex) {
        return hand[handIndex].isSplit() && bank >= stake[handIndex];
    }
   

    //split logic? -- unsure TODO
    public boolean split(BlackjackDeck deck, int handIndex){
        if (!canSplit(handIndex)){
            return false;
        }

        Card[] cards = hand[handIndex].getCards();
        
        hand[handIndex].setCard(1, null);   //Set second card of hirst hand to null
        hand[numOfHands++] = new BlackjackHand(new Card[]{cards[1]}, deck); //Add the card to new hand
        
        //hand[numOfHands].addCard(cards[0]);
        
        bank -= stake[handIndex];
        
        return true;
    }

    public void openBetting(int handIndex){ //Add player input for amount of the bet
        if (bank == 0){
            return;
        }

        stake[handIndex]++;    // Player Input
        bank--;     //Plyaer INput
        
        System.out.println("\n> " + getName() + " says: I open with one chip!\n");
    }
   
    abstract boolean shouldSplit(BlackjackDeck deck, int handIndex);
    abstract boolean shouldHit(BlackjackDeck deck, int handIndex);
    abstract boolean shouldDouble(BlackjackDeck deck, int handIndex);
    abstract boolean shouldStand(BlackjackDeck deck, int handIndex);

    //game decisions
    public void nextAction(BlackjackDeck deck, int handIndex){ //need to implement functions such as hit etc
        if (hasSurrendered()){
            return;
        }
        //CHANGE SURRENDER
        //TODO
        // if (isBankrupt()){
        //     System.out.println("\n> " + getName() + " says: I'm out!\n");
        //     surrender(handIndex);
        //     return;
        // }

        if (hand[handIndex].isBlackjack()){
            System.out.println("\n> " + getName() + " says: I have blackjack!\n");
            
            //CHECKING FOR BLACKJACK
            won[handIndex] = true;
            
            return;
        }

        if (hand[handIndex].isBust()) {
            System.out.println("\n> " + getName() + " says: I have bust!\n");
            bust[handIndex] = true;
            return;
        }
        //TODO see functioning
        else{
            if(shouldHit(deck, handIndex))
                hit(deck, handIndex);
            else if(shouldStand(deck, handIndex))
                stand(handIndex);
            else if(shouldDouble(deck, handIndex))
                doubleDown(deck, handIndex);
            else if(shouldSplit(deck, handIndex))
                split(deck, handIndex);
        }

    }


    public String addCount(int count, String singular, String plural) {
        if (count == 1 || count == -1)
            return count + " " + singular;
        else
            return count + " " + plural;
    }

    public static void main(String[] args) {
       

    }
}
