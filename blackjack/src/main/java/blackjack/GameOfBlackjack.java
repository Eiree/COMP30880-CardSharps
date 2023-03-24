package blackjack;
import poker.*;

public class GameOfBlackjack { //reference Gameofpoker.java in poker
    private BlackjackPlayer[] players;
    private BlackjackPlayer dealer;
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
        dealer = new BlackjackHumanPlayer("Dealer", bank);
        deck = new BlackjackDeck();
    }



    //TODO
    // DEALER HANDLED HERE
    // 
    // Handle Bankrupcy
    // Initialize players
    // Initialize deck and pass it to the round
    // counter for num of players
    // input for name
    // contructor to take names and bank(??)
    // getters and setters
    // play method handle rounds
    // check for termination


    public int getNumPlayers(){
        return numPlayers;
    }

    public void play(){
        RoundOfBlackjack round = new RoundOfBlackjack(deck, players, dealer);
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



    public static void main(String[] args) {
        
         String[] names = { "Human", "Tom", "Dick", "Harry" }; //define names
         System.out.println("\nWelcome to the Blackjack ...\n\n");
         System.out.print("\nWhat is your name?  ");
         byte[] input = new byte[100]; //get user input for name
         try {
         System.in.read(input);
         names[0] = new String(input);
          } catch (Exception e) {
          };
          
          int startingBank = 10;
          System.out.println("\nLet's play Blackjack ...\n\n");
          
          GameOfBlackjack game = new GameOfBlackjack(names, startingBank);
          game.play();
        // init game logic
    }
}
