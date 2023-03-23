package blackjack;
import poker.*;

public abstract class BlackjackHumanPlayer extends BlackjackPlayer { //error caused by abstract methods such as shouldSplit etc
    //constructor
    public BlackjackHumanPlayer(String name, int money){
        super(name, money);
    }


    // todo (verify) DECISIONS
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

    public boolean shouldDouble(BlackjackDeck deck, int handIndex) { // TODO---------------
        if(getBank() >= getStake(handIndex)*2){
            if(askQuestion("Do you want to Double the Stakes and Get a Card? Current Stakes: " + getStake(handIndex)))
                doubleDown(deck, handIndex);
                return true;
        }
        else{
            return false;
        }
    }
    public boolean shouldSplit(BlackjackDeck deck, int handIndex){
        if(getBank() >= getStake(handIndex)*2)
            return askQuestion("Do you want to Split the Hands in 2? Current Stakes: " + getStake(handIndex));

        else
            return false;
    }

    public boolean shouldHit(BlackjackDeck deck, int handIndex) { // TODO---------------
         if(askQuestion("Do you want to Hit?")){
            hit(deck, handIndex);
            return true;
         }
         else{
            return false;
         }
    }

    public boolean shouldStand(BlackjackDeck deck, int handIndex){
        if(askQuestion("Do you want to Stand?")){
            return true;
        }
        else{
            return false;
        }
    }


}
