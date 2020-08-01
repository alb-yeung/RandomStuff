package Cards;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Player {
    private List<Card> hand;
    private String name;

    public Player() {
        name = "";
        hand = new ArrayList<>();
    }

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    private Card calculateLowest() {
        int curMinIndex = 0;
        for (int index = 0; index < hand.size(); index++) {
            if (hand.get(index).getValue() < hand.get(curMinIndex).getValue()) {
                curMinIndex = index;
            }
        }
        return hand.get(curMinIndex);
    }

    public Card removeLowestCardFromHand() {
        Card lowestCard = calculateLowest();
        hand.remove(lowestCard);
        return lowestCard;
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public int calculateHand() {
        int total = 0;
        for (Card card : hand) {
            total += card.getValue();
        }
        return total;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(name).append('\n');
        for (Card card : hand) {
            str.append(card).append(", ");
        }
        return str.toString();
    }
}
