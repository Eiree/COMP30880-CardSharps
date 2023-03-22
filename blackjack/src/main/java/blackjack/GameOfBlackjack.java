package blackjack;
import poker.*;

public class GameOfBlackjack { //reference Gameofpoker.java in poker

    //logic etc here
    private BlackjackPlayer[] players;
    private BlackjackDeck deck;
    private int numPlayers;

    public GameOfBlackjack(String[] names, int bank){
        numPlayers = names.length;
        players = new BlackjackPlayer[numPlayers];
        for (int i = 0; i < numPlayers; i++){
            if (i == 0){
                players[i] = new BlackjackHumanPlayer(names[i].trim(), bank);
            } else {
                players[i] = new BlackjackComputerPlayer(names[i].trim(), bank);
            }
        }
        deck = new BlackjackDeck();
    }

    public int getNumPlayers(){
        return numPlayers;
    }

    public BlackjackPlayer getPlayer(int num){
        if (num >= 0 && num <= numPlayers){
            return players[num];
        } else {
            return null;
        }
    }

    public int getNumSolventPlayers(){
        int count = 0;
        for (int i = 0; i < getNumPlayers(); i++){
            if (getPlayer(i) != null && !getPlayer(i).isBankrupt()){
                count++;
            }
        }
        return count;
    }

    public void play(){
        while (getNumSolventPlayers() > 1){
            //RoundofBlackjack round = new RoundOfBlackjack(deck, players);
            //round.play();
            //check for another game
        }
    }

    public static void main(String[] args) {
    }
}
