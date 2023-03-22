package blackjack;
import poker.*;

abstract class BlackjackPlayer { //reference player.java, humanplayer.java and computerplayer.java
    private int bank = 0; //total money player has left (without stake)
    private int stake = 0; //players bet
    private String name = ""; //UID for player name
    private BlackjackHand hand = null; //hand dealth to player
    private boolean bust = false; //check for bust
    private boolean surrendered = false; //essentially a fold system where players gets back half bet.
    //private boolean win = false; //win condition check potentially?

    public BlackjackPlayer(String name, int money){
        this.name = name;
        this.bank = money;
        reset();
    }

    public void reset(){
        bust = false;
        //win = false;
        stake = 0;
    }

    public String toString(){ //handle events in game to display to user
        return "";
        //check for win/bust
    }

    //getters
    public BlackjackHand getHand(){
        return hand;
    }

    public int getBank(){
        return bank;
    }

    public int getStake(){
        return stake;
    }

    public String getName(){
        return name;
    }

    public boolean isBankrupt(){
        return bank == 0;
    }

    public boolean isBust(){ //condition for bust.
        return bust;
    }

    public boolean hasSurrendered(){
        return surrendered;
    }

    /*public boolean isWon(){ //condition for win.
        return win;
    }*/

    
    //setters
    public void dealTo(BlackjackDeck deck){
        hand = deck.dealHand();
    }

    public void takePot(PotOfMoney pot){
        bank += pot.takePot();

    }

    //player actions
    public void surrender(){
        if (!surrendered){
            System.out.println("\n> " + getName() + " says: I surrender!\n");
            surrendered = true;
        }
    }

    




}
