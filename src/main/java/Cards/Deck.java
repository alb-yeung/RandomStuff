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

    public void reset() {
        deck = new ArrayList<>();
        for (Card.Suit suit : Card.LIST_OF_SUITS) {
            for (int i = Card.MIN_VALUE; i <= Card.MAX_VALUE; i++) {
                deck.add(new Card(suit, i));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card deal() {
        Card temp = deck.get(0);
        deck.remove(0);
        return temp;
    }
}
