package com.cardsharps;

public class GameOfBlackjack {
    //variables
    private Player[] players;
    private DeckOfCards deck;
    private int numPlayers;

    //constructor
    public GameOfBlackjack(String[] names, int bank) {
		numPlayers = names.length;
		players = new Player[numPlayers];
		
		for (int i = 0; i < numPlayers; i++)
			if (i == 0)
				players[i] = new HumanPlayer(names[i].trim(), bank);
			else
				players[i] = new ComputerPlayer(names[i].trim(), bank);
		deck  = new DeckOfCards();
	}


    //getters
    public int getNumPlayers() {
        return numPlayers;

    }

    public Player getPlayer(int num) {
        if (num >= 0 && num <= numPlayers)
            return players[num];
        else
            return null;
    }

    public int getNumSolventPlayers() {
        // how many players still have money left?
        int count = 0;

        for (int i = 0; i < getNumPlayers(); i++)
            if (getPlayer(i) != null && !getPlayer(i).isBankrupt())
                count++;
        return count;
    }


    //logic
    public void play() {
        while (getNumSolventPlayers() > 1) {
            RoundOfPoker round = new RoundOfPoker(deck, players);
            round.play();
            
        }
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
        game.play(); //init game logic
    }
}
