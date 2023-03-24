package blackjack;
import poker.*;

public class BlackjackHumanPlayer extends BlackjackPlayer { //error caused by abstract methods such as shouldSplit etc
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

    public boolean shouldDouble(BlackjackDeck deck, BlackjackHand hand) { // TODO---------------
        if(getBank() >= hand.getStake()*2){
            if(askQuestion("Do you want to Double the Stakes and Get a Card? Current Stakes: " + hand.getStake())){
                return true;
            }
        }
        return false;
    }


    public boolean shouldSplit(BlackjackDeck deck, BlackjackHand hand){
        if(getBank() >= hand.getStake()*2)
            if (askQuestion("Do you want to Split the Hands in 2? Current Stakes: " + hand.getStake())){
                return true;
            } else {
                return false;
            }
        else
            return false;
    }

    public boolean shouldHit(BlackjackDeck deck, BlackjackHand hand) { // TODO---------------
         if(askQuestion("Do you want to Hit?")){
            return true;
         }
         else{
            return false;
         }
    }

    public boolean shouldStand(BlackjackDeck deck, BlackjackHand hand){
        if(askQuestion("Do you want to Stand?")){
            return true;
        }
        else{
            return false;
        }
    }


}
