package Cards;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private Deck deck;

    public Game() {}

    public void runGame() {
        // Except here while loop + switch statement for input on which method to run
        startGame();
        dealHandToPlayers(3);
        printHands();
        passCards();
        printHands();
        System.out.println(calculateWinner());
    }

    private void startGame() {
        deck = new Deck();
        deck.reset();
        players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        players.add(new Player("Player 3"));
    }

    private void dealHandToPlayers(int numCards) {
        for (int i = 0; i < numCards; i++) {
            dealCardToPlayers();
        }
    }

    private void dealCardToPlayers() {
        deck.shuffle();
        for (Player player : players) {
            player.receiveCard(deck.deal());
        }
    }

    private void printHands() {
        for (Player player : players) {
            System.out.println(player);
        }
    }

    private void passCards() {
        List<Card> cardsToGive = new ArrayList<>(players.size());
        for (int i = 0; i < players.size(); i++) {
            cardsToGive.add(new Card());
        }
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
