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
    
    

    //constructor
    public BlackjackComputerPlayer(String name, int money){
        super(name, money);
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



    boolean shouldSplit(BlackjackDeck deck, int handIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shouldSplit'");
    }



    boolean shouldHit(BlackjackDeck deck, int handIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shouldHit'");
    }



    boolean shouldDouble(BlackjackDeck deck, int handIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shouldDouble'");
    }



    boolean shouldStand(BlackjackDeck deck, int handIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shouldStand'");
    }
}
