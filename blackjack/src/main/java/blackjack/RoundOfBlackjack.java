package blackjack;
import poker.*;


// 
// Dealing hands changed
// original stake
// open round
// remove pot
// play method
// check hands bust or stand and play with next player
// dealer functionality (only hit < 17)
// win checking for each hand
// bankrupcy check
// no best player
// multiple winners
// working for the hole card (index 1) of the dealer
// 
// Displaying the information of the game
// 
// 
public class RoundOfBlackjack {
    public static int DELAY_BETWEEN_ACTIONS = 1000; //ms
    private BlackjackPlayer[] players;

    private BlackjackDeck deck;
    private int numPlayers;

    public RoundOfBlackjack(BlackjackDeck deck, BlackjackPlayer[] players){
        this.players = players;
        this.deck = deck;
        numPlayers = players.length;
        System.out.println("\n\nNew round:\n\n");
        deal();

        //conditions to deal
        //openRound();
        //check condition of game
    }

    public int getNumPlayers(){
        return numPlayers;
    }

    public BlackjackPlayer getPlayer(int num){
        if (num >= 0 && num <= numPlayers)
            return players[num];
        else
            return null;
    }

    public int getNumActivePlayers(){
        int count = 0;
        for (int i = 0; i < getNumPlayers(); i++){
            if (getPlayer(i) != null && !getPlayer(i).hasSurrendered() && !getPlayer(i).isBankrupt()){ //conditions for split>?
                count++;
            }
        }
        return count;
    }

    public void removePlayer(int num){
        if (num >= 0 && num < numPlayers){
            System.out.println("\n> " + players[num].getName() + " leaves the game.\n");
            players[num] = null;
        }
    }

    public int getPlayerScore(){
        int score = 0;
        //players and dealer
        for (int i = 0; i < getNumPlayers(); i++){
            BlackjackPlayer currentPlayer = getPlayer(i);
            if (currentPlayer == null || currentPlayer.hasSurrendered()){
                continue;
            }
            score = currentPlayer.getHand().getValue();
        }
        return score;
    }

    public void deal(){
        for (int i = 0; i < getNumPlayers(); i++){
            if (getPlayer(i) != null){
                if (getPlayer(i).isBankrupt()){
                    removePlayer(i);
                } else {
                    getPlayer(i).reset();
                    getPlayer(i).dealTo(deck);
                    System.out.println(getPlayer(i));
                }
            }
        }
        System.out.println("\n");
    }


    //allow players to hit/stand/split/surrender.


    public void openRound(){
        BlackjackPlayer player = null;
        System.out.println("");
        for (int i = 0; i < numPlayers; i++){
            player = getPlayer(i);
            if (player == null || player.isBankrupt()){
                continue;
            }
            //implement logic for blackjack and continue with current card. TODO
        }
    }

    public void play(){
        PotOfMoney pot = new PotOfMoney();
        int numActive = getNumActivePlayers();
        int stake = -1;
        BlackjackPlayer currentPlayer = null;
        deck.reset();

        while (stake < pot.getCurrentStake() && numActive > 0){
            stake = pot.getCurrentStake();
            for (int i = 0; i < getNumPlayers(); i++){
                currentPlayer = getPlayer(i);
                if (currentPlayer == null || currentPlayer.hasSurrendered()){ // if(player[i].getHand[j] !bust && player.getHand[j] !stood)
                    continue;
                }

                delay(DELAY_BETWEEN_ACTIONS);

                //check active players, if player wins takePot(pot) etc TODO


                if (currentPlayer.hasSurrendered()){
                    numActive--;
                }

            }
        }

        if (numActive == 0){
            System.out.println("\nNo players remaining in the game.\n");
            return;
        }

        //define winners? TODO
    }


    private void delay(int numMilliseconds) {
        try {
            Thread.sleep(numMilliseconds);
        } catch (Exception e) {
        }
    }

}
