package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class representing a deck of cards. Multiple Decks are used for game play.
 * @author Paige Freeman-Cyopeck
 */
class Deck {
    
    private ArrayList<Card> deck = new ArrayList<>();
    
    /**
     * Default constructor
     */
    public Deck() {
        
    }
    
    /**
     * Method which generates the card deck with Enum Suit and Value values.
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

    /**
     * Method used to retrieve a Card from the Deck based on its position in the deck.
     * @param index Deck position of required Card
     * @return Required Card
     */
    public Card get(int index) {
        return deck.get(index);
    }
    
    /**
     * Method used to add a Card to the Deck
     * @param card to be added to the Deck
     */
    public void add(Card card) {
        deck.add(card);
    }
    
    /**
     * Method used to return the "top" card in the Deck
     * @return "top" Card
     */
    public Card pop() {
        if(deck.size() > 0) {
            Card card = deck.get(0);
            deck.remove(0);
            return card;
        } else {
            throw new NullPointerException("Deck is empty");
        }
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
     * Method currently checks the size of the deck as it stands.
     * @return the current size of the deck.
     */
    public int checkSize() {
        return deck.size();
    }
    
    /**
     * Method used to remove all Card in the Deck
     */
    public void clear() {
        deck.clear();
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

    
