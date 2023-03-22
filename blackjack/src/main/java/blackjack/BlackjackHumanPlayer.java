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

    public boolean shouldOpen(PotOfMoney pot) {
        return true;
    }

    public boolean shouldSee(PotOfMoney pot) {
        if (getStake() == 0)
            return true;
        else
            return askQuestion("Do you want to see the bet of " +
                    addCount(pot.getCurrentStake() - getStake(), "chip", "chips"));
    }

    public boolean shouldDouble(PotOfMoney pot) { // TODO---------------
       return false;
    }

    public boolean shouldHit(PotOfMoney pot) { // TODO---------------
        /* total value of all cards
        if (total <= 11) {
            return true; // always hit if hand value is less than or equal to 11
        } else if (total == 12) {
            int dealerCardValue = pot.getDealerCardValue(); //require to implement dealer cards and their values
            if (dealerCardValue >= 4 && dealerCardValue <= 6) {
                return false; // stand if dealer has a 4, 5, or 6
            } else {
                return true; // hit otherwise
            }
        } else if (total >= 13 && total <= 16) {
            int dealerCardValue = pot.getDealerCardValue();
            if (dealerCardValue >= 2 && dealerCardValue <= 6) {
                return false; // stand if dealer has a 2, 3, 4, 5, or 6
            } else {
                return true; // hit otherwise
            }
        } else {
            return false; // stand if hand value is 17 or greater
        }*/
        return false;
    }
}
