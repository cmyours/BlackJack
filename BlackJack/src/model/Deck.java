package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] suits = { "S", "H", "D", "C" };
        int[] ranks = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
        for (String suit : suits) {
            for (int rank : ranks) {
            	String imagePath = "resources/cards/" + rank + suit + ".png";
                cards.add(new Card(suit, rank, imagePath));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) return null;
        return cards.remove(0);
    }
}

