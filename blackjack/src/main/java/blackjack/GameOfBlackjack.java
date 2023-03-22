package blackjack;
import poker.*;

public class GameOfBlackjack { //reference Gameofpoker.java in poker

    //logic etc here
    private BlackjackPlayer[] players;
    private BlackjackDeck deck;
    private int numPlayers;


    //constructor
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


    //getters
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


    //init game
    public void play(){
        while (getNumSolventPlayers() > 1){
            RoundOfBlackjack round = new RoundOfBlackjack(deck, players);
            round.play();
            try {
                System.out.print("\n\nPlay another round? Press 'q' to terminate this game ... ");

                byte[] input = new byte[100];

                System.in.read(input);

                for (int i = 0; i < input.length; i++)
                    if ((char) input[i] == 'q' || (char) input[i] == 'Q')
                        return;
            } catch (Exception e) {
            };
        }
    }

    public static void main(String[] args) { //game structure
        String[] names = { "Human", "Tom", "Dick", "Harry" };
        System.out.println("\nWelcome to Blackjack ...\n\n");
        System.out.print("\nWhat is your name?  ");

        byte[] input = new byte[100];
        try {
            System.in.read(input);

            names[0] = new String(input);
        } catch (Exception e) {
        };

        int startingBank = 10;
        System.out.println("\nLet's play BLACKJACK ...\n\n");
        GameOfBlackjack game = new GameOfBlackjack(names, startingBank);
        game.play();
    }
}
