package blackjack;

import poker.*;

abstract class BlackjackPlayer { //reference player.java, humanplayer.java and computerplayer.java
    private int bank = 0; //total money player has left (without stake)
    private int stake = 0; //players bet
    private String name = ""; //UID for player name
    private BlackjackHand hand = null; //hand dealth to player
    //private BlackjackHand secondHand = null; //might need if a player splits
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
        surrendered = false;
        //win = false;
        stake = 0;
        hand = null;
    }

    public String toString(){ //handle events in game to display to user
        return "";
        //check for win/bust TODO
    }

    //getters
    public BlackjackHand getHand(){
        return hand;
    }

    public Card getCard(int index){
        return hand.getCard(index);
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
        System.out.println("\n> " + getName() + "says: I WIN " + addCount(pot.getTotal(), "chip", "chips") + "!\n");
        bank += pot.takePot();
    }

    //player actions
    public void surrender(){
        if (!surrendered){
            System.out.println("\n> " + getName() + " says: I surrender!\n");
            bank += (stake / 2);
            stake = 0; //shouldnt need to reset as reset() already init stake = 0
            surrendered = true;
        }
    }

    public void hit(BlackjackDeck deck){ //TODO
        //implement hit functionality
    }

    public void stand(){ //TODO
        //nothing
    }

    public void doubleDown(BlackjackDeck deck, PotOfMoney pot){
        if (bank < stake) {
            System.out.println("\n> " + getName() + " says: I can't afford to double down!");
            return;
        }
        bank -= stake;
        stake *= 2;
        pot.raiseStake(stake);
        hit(deck);
        stand();
    }

    public boolean canSplit() {
        return hand.isSplit() && bank >= stake;
    }

    //split logic? -- unsure TODO
    /*public BlackjackHand split(BlackjackDeck deck, PotOfMoney pot){
        if (!canSplit()){
            return null;
        }
        Card[] cards = hand.getCards();
        hand = new BlackjackHand(deck);
        //hand.addCard(cards[0]);
        bank -= stake;
        pot.raiseStake(stake);
        return new BlackjackHand(new Card[]{cards[1]}, deck);
    }*/

    public void openBetting(PotOfMoney pot){
        if (bank == 0){
            return;
        }

        stake++;
        bank--;

        pot.raiseStake(1);
        System.out.println("\n> " + getName() + " says: I open with one chip!\n");
    }

    public void seeBet(PotOfMoney pot){
        int needed = pot.getCurrentStake() - getStake();
        if (needed == 0 || needed > getBank()){
            return;
        }
        stake += needed;
        bank -= needed;

        pot.addToPot(needed);
        System.out.println("\n> " + getName() + " says: I see that " + addCount(needed, "chip", "chips") + "!\n");
    }

    //Key decisions
    abstract boolean shouldOpen(PotOfMoney pot);
    abstract boolean shouldSee(PotOfMoney pot);

    abstract boolean shouldSplit(PotOfMoney pot);
    abstract boolean shouldHit(PotOfMoney pot);
    abstract boolean shouldDouble(PotOfMoney pot);


    //game decisions
    public void nextAction(PotOfMoney pot){ //need to implement functions such as hit etc
        if (hasSurrendered()){
            return;
        }

        if (isBankrupt() || pot.getCurrentStake() - getStake() > getBank()){
            System.out.println("\n> " + getName() + " says: I'm out!\n");
            surrender();
            return;
        }

        if (hand.isBlackjack()){
            System.out.println("\n> " + getName() + " says: I have blackjack!\n");
            return;
        }

        if (hand.isBust()) {
            System.out.println("\n> " + getName() + " says: I have bust!\n");
            return;
        }

        //requires logic for hit, stand, double TODO
    }


    public String addCount(int count, String singular, String plural) {
        if (count == 1 || count == -1)
            return count + " " + singular;
        else
            return count + " " + plural;
    }
}
