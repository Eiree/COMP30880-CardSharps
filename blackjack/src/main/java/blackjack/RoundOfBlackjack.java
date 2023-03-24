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
    private BlackjackPlayer dealer;
    private static final int DEALERMIN = 17;
    public static final int GOALSCORE = 21;

    private BlackjackDeck deck;
    private int numPlayers;

    public RoundOfBlackjack(BlackjackDeck deck, BlackjackPlayer[] players, BlackjackPlayer dealer){
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
        numPlayers = players.length;
        System.out.println("\n\nNew round:\n\n");
        deal();
        openRound();

        //openbetting needs input for stake

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
            if (currentPlayer == null){
                continue;
            }
            //check for split.
            score = currentPlayer.getHand(i).getValue();
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
                    getPlayer(i).dealTo(deck, i);
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
        //1)Each player place bet
        for (int i = 0; i < numPlayers; i++) {
            BlackjackPlayer player = getPlayer(i);
            if (player == null || player.isBankrupt()){
                continue;
            }
            //player selects stake
            player.openBetting(); //every player puts an initial stake
        }

        //2)Each player gets 2 cards face up
        for (BlackjackPlayer player: players) {
            if (player == null || player.isBankrupt()){
                continue;
            }
            player.dealTo(deck, player.getStake(0));
            System.out.println(player.getName() + " has a " + player.getCard(0,0).toString()
                    + " & a " + player.getCard(0,1).toString());
        }

        //3)Dealer gets two cards

        dealer.dealTo(deck, 0);
        System.out.println("The dealers first card is a " + dealer.getCard(0, 0).getName());

        //4)a)Human player while not bust decides move
        /*while(!players[0].isBust()) {
            char move = players[0].askMove();
            if(move == 'h' || move == 'H'){
                players[0].hit(deck);
            } else if (move == 's' || move == 'S') {
                players[0].stand();
            } else if (move == 'd' || move == 'D') {
                players[0].doubleDown(deck,);
            } else if (move == 'p' || move == 'P') {
                players[0].split();
            }
        }*/

        int i = 0;
        if (i<players[0].getNumOfHands()){
            if(!players[0].isBust(0) || !players[0].isStand(0)) {      //need an isStand method
                players[0].nextAction(deck, i);
            } else {
                i++;
            }
        }

        //4)b)Computer player while not bust decides move from table
        for (i=1; i < numPlayers; i++) {    //start at 1 as human player is 0
            if (!players[i].isBust(0 ) || !players[i].isStand(i)){  //computer never splits
                players[i].nextAction(deck, 0);
            } else {
                System.out.println();
                i++;
            }
        }
        

        //5) Dealer turns over hole card
        System.out.println("Dealer's hole card is a" + dealer.getCard(0,1).toString());

        //6) Dealer must hit until >= 17
        while(dealer.getHand(0).getHandValue() < DEALERMIN && !dealer.isBust(0)){
            dealer.hit(deck, 0);
            dealer.getHand(0).toString();
        }

        //7) Winners calculated & winnings added to bank
        for (i=0; i<numPlayers; i++){
            for (int j=0; j<players[i].getNumOfHands(); j++){
                if(players[i].isWon(j)){
                    players[i].addBank(2*players[i].getStake(j));
                    System.out.println("Congratulations " + players[i].getName() + " you won " + 2*players[i].getStake(j));
                }
                else if(players[i].isDraw(j)){  //draw score dealer=player & not blackjack
                    players[i].addBank(players[i].getStake(j));
                    System.out.println("It's a draw " + players[i].getName() + " you get your stake of " + players[i].getStake(j) + "back");
                } else {
                    System.out.println(players[i].getName() + " you lost");
                }
            }
            System.out.println("Your total bank is " + players[i].getBank());
        }

        //define winners? TODO
    }
}
