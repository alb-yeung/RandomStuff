package Cards;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Card {
    public final static List<Suit> LIST_OF_SUITS = new ArrayList<>(Arrays.asList(new Suit[]{Suit.DIAMOND, Suit.CLUB, Suit.HEART, Suit.SPADE}));
    public final static int MIN_VALUE = 0;
    public final static int MAX_VALUE = 12;

    private Suit suit;
    private int value;

    public Card() {}

    public Card(Suit suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public String toString() {
        return suit.name() + " " + value;
    }

    public enum Suit {
        DIAMOND, CLUB, HEART, SPADE
    }
}
