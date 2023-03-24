package blackjack;

public class BlackjackDealer {
    private final int DEALER_MIN = 17;
    private int goalScore;
    private boolean dealerMinReached = false;
    private BlackjackHand hand;

    private BlackjackDealer(int goalScore, BlackjackHand hand)
    {
        this.goalScore = goalScore;
        this.hand = hand;
    }

    /*
    * TO DO:
    *   drawing cards behaviour (until goalScore or bust reached)
    *   flipping hole card (may be handled in poker game/round)
     */


}
