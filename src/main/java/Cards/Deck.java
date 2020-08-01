package Cards;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class Deck {
    private List<Card> deck;

    /**
     * Resets the deck to a state with every card in it, fairly ordered
     */
    public void reset() {
        deck = new ArrayList<>();
        for (Card.Suit suit : Card.LIST_OF_SUITS) {
            for (int i = Card.MIN_VALUE; i <= Card.MAX_VALUE; i++) {
                deck.add(new Card(suit, i));
            }
        }
    }

    /**
     * Simple built in Java shuffle
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * Pop off top of the deck and return it
     */
    public Card deal() {
        Card temp = deck.get(0);
        deck.remove(0);
        return temp;
    }
}
