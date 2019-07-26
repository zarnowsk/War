package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Paige Freeman-Cyopeck
 */
class Deck {
    
    private ArrayList<Card> deck = new ArrayList<>();
    
    public Deck() {
        
    }
    
    /**
     * Method which generates the card deck.
     * @return Returns an ArrayList<Card> which has been generated according to
     * a standard card deck (52 cards, 13 cards for each suit.)
     */
    public ArrayList<Card> generateDeck() {
        for(Suit s : Suit.values()) {
            for(Value v : Value.values()) {
                Card tempCard = new Card(v, s);
                deck.add(tempCard);     
            }
        }
        return deck;
    }

    public Card get(int index) {
        return deck.get(index);
    }
    
    public void add(Card card) {
        deck.add(card);
    }
    
    /**
     * Utilizes the collections import to shuffle the deck on the ArrayList<Card>
     * @param deck
     * @return The deck after it has been shuffled.
     */
    public ArrayList<Card> shuffleDeck() {
        Collections.shuffle(deck);
        return deck;
    }
    
    /**
     * Currently checks the size of the deck as it stands.
     * @param deck
     * @return the current size of the deck.
     */
    public int checkSize() {
        return deck.size();
    }
    
    @Override
    public String toString() {
        String string = "";
        for(int i = 0; i < deck.size(); i++) {
            string += deck.get(i) + "\n";
        }
        return string;
    }
    

}

    
