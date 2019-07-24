package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author acer
 */
class Deck {
    
    private ArrayList<Card> deck = new ArrayList<>();
    
    
    /**
     * Method which generates the card deck.
     * @return Returns an ArrayList<Card> which has been generated according to
     * a standard card deck (52 cards, 13 cards for each suit.)
     */
    private ArrayList<Card> generateDeck() {
        for(Suit s : Suit.values()) {
            for(Value v : Value.values()) {
                Card tempCard = new Card(v, s);
                deck.add(tempCard);     
            }
        }
        return deck;
    }
    
    /**
     * Utilizes the collections import to shuffle the deck on the ArrayList<Card>
     * @param deck
     * @return The deck after it has been shuffled.
     */
    private ArrayList<Deck> shuffleDeck(ArrayList<Deck> deck) {
        Collections.shuffle(deck);
        return deck;
    }
    
    /**
     * Currently checks the size of the deck as it stands.
     * @param deck
     * @return the current size of the deck.
     */
    private int checkSize(ArrayList<Deck> deck) {
        return deck.size();
    }
    
    

}

    
