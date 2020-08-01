package Cards;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private Deck deck;

    public Game() {}

    /**
     * Runs simulation
     */
    public void runGame() {
        // Except here while loop + switch statement for input on which method to run
        startGame();
        dealHandToPlayers(3);
        printHands();
        passCards();
        printHands();
        System.out.println(calculateWinner());
    }

    /**
     * Resets the game
     */
    private void startGame() {
        deck = new Deck();
        deck.reset();
        players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        players.add(new Player("Player 3"));
    }

    /**
     * Uses dealCardToPlayers() to deal a certain specified amount of cards to each player
     */
    private void dealHandToPlayers(int numCards) {
        for (int i = 0; i < numCards; i++) {
            dealCardToPlayers();
        }
    }

    /**
     * Simple one card deal to each player
     */
    private void dealCardToPlayers() {
        deck.shuffle();
        for (Player player : players) {
            player.receiveCard(deck.deal());
        }
    }

    /**
     * Simple go through each player and print
     */
    private void printHands() {
        for (Player player : players) {
            System.out.println(player);
        }
    }

    /**
     * Go through and find lowest card of each player
     * Then go around and pass the lowest card around
     */
    private void passCards() {
        List<Card> cardsToGive = new ArrayList<>(players.size());
        // Have to initialize cardsToGive with empty cards else will yell because can't set a position that's not there yet
        for (int i = 0; i < players.size(); i++) {
            cardsToGive.add(new Card());
        }
        // Here putting the lowest card of each player in the next player's index in cardsToGive so that easier addition later
        for (int i = 0; i < players.size(); i++) {
            cardsToGive.set((i+1)%players.size(), players.get(i).removeLowestCardFromHand());
        }
        for (int i = 0; i < players.size(); i++) {
            players.get(i).receiveCard(cardsToGive.get(i));
        }
        System.out.println("---------------------");
        System.out.println("Cards passed");
        System.out.println("---------------------");
    }

    /**
     * Fairly simple go through find maximum value algorithm
     */
    private String calculateWinner() {
        int currMax = -1;
        Player currMaxPlayer = new Player();
        for (Player player : players) {
            int calculatedHand = player.calculateHand();
            if (calculatedHand > currMax) {
                currMax = calculatedHand;
                currMaxPlayer = player;
            }
        }
        return currMaxPlayer.getName() + " wins with " + currMax;
    }
}
