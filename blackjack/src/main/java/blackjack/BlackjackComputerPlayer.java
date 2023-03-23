package blackjack;
import poker.*;
import java.util.Random;

public abstract class BlackjackComputerPlayer extends BlackjackPlayer{ // error caused by abstract methods such as shouldSplit etc
    
    

    //constructor
    public BlackjackComputerPlayer(String name, int money){
        super(name, money);
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



    // public boolean shouldOpen(PotOfMoney pot) {
    //     return true;
    // }

    // public boolean shouldSee(PotOfMoney pot) {
    //     if (getStake() == 0)
    //         return true;
    //     else
    //         return askQuestion("Do you want to see the bet of " +
    //                 addCount(pot.getCurrentStake() - getStake(), "chip", "chips"));
    // }

    public boolean shouldSplit(PotOfMoney pot) { //TODO
        return false; // need to implement this someway
    }
}
