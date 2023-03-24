package blackjack;
import poker.*;
import java.util.Random;



// TODO
// 
// Lookup table
// Should method ->checks lookup table
// 
// 
// 
// 
// 
// 
// 

public class BlackjackComputerPlayer extends BlackjackPlayer{ // error caused by abstract methods such as shouldSplit etc
    
    char[][] decisionMatrix;

    //constructor
    public BlackjackComputerPlayer(String name, int money){
        super(name, money);

        decisionMatrix = new char[][]{
                // Dealer's hand in range of values 2-A
                {'E','E','E','E','E','E','E','E','E','E'}, //computer's hand = 1
                {'E','E','E','E','E','E','E','E','E','E'}, //computer's hand = 2
                {'E','E','E','E','E','E','E','E','E','E'}, //computer's hand = 3
                {'H','H','H','H','H','H','H','H','H','H'}, //computer's hand = 4
                {'H','H','H','H','H','H','H','H','H','H'}, //computer's hand = 5
                {'H','H','H','H','H','H','H','H','H','H'}, //computer's hand = 6
                {'H','H','H','H','H','H','H','H','H','H'}, //computer's hand = 7
                {'H','H','H','H','H','H','H','H','H','H'}, //computer's hand = 8
                {'H','D','D','D','D','H','H','H','H','H'}, //computer's hand = 9
                {'D','D','D','D','D','D','D','D','H','H'}, //computer's hand = 10
                {'D','D','D','D','D','D','D','D','D','D'}, //computer's hand = 11
                {'H','H','S','S','S','H','H','H','H','H'}, //computer's hand = 12
                {'S','S','S','S','S','H','H','H','H','H'}, //computer's hand = 13
                {'S','S','S','S','S','H','H','H','H','H'}, //computer's hand = 14
                {'S','S','S','S','S','H','H','H','H','H'}, //computer's hand = 15
                {'S','S','S','S','S','H','H','H','H','H'}, //computer's hand = 16
                {'S','S','S','S','S','S','S','S','S','S'}, //computer's hand = 17
                {'S','S','S','S','S','S','S','S','S','S'}, //computer's hand = 18
                {'S','S','S','S','S','S','S','S','S','S'}, //computer's hand = 19
                {'S','S','S','S','S','S','S','S','S','S'}, //computer's hand = 20
        };
    }

    //always false computer never splits
    @Override
    boolean shouldSplit(BlackjackDeck deck, int handIndex) {
        return false;
    }

    //hit, double, stand according to table
    @Override
    boolean shouldHit(BlackjackDeck deck, int handIndex) {
        return false;
    }

    @Override
    boolean shouldDouble(BlackjackDeck deck, int handIndex) {
        return false;
    }

    @Override
    boolean shouldStand(BlackjackDeck deck, int handIndex) {
        return false;
    }


    //todo (verify) DECISIONS
    public boolean askQuestion(String question) {
        System.out.print("\n>> " + question + " (y/n)?  ");

        byte[] input = new byte[100];

        try {
            System.in.read(input);

            for (int i = 0; i < input.length; i++)
                if ((char) input[i] == 'y' || (char) input[i] == 'Y')
                    return true;
        } catch (Exception e) {
        };

        return false;
    }

    public boolean shouldSplit(PotOfMoney pot) { //TODO
        return false; // need to implement this someway
    }
}
