/**
 * SYST 17796 Project Winter 2019.
 * War card game.
 * Date created: 07-JUNE-2019
 * Owned by: Michal Zarnowski
 * Collaborators: Vinayak Pavate
 * Copyright: This work is the intellectual property of the owner and collaborators. Any further copying and
 * distribution must be within the Copyright law. Posting to commercial sites for profit is prohibited.
 * Citations: Base code designed by (github)alihassan-1
 */
package ca.sheridancollege.project;

/**
 * A class to be used as the base Card class for the project. Must be general
 * enough to be instantiated for any Card game. Students wishing to add to the
 * code should remember to add themselves as a modifier.
 *
 * @author dancye, 2018
 */
public class Card {
    
    private Value value;
    private Suit suit;
    
    /**
     * Default constructor.
     */
    Card() {
        
    }
    
    /**
     * Default constructor for the Card object. Creates a card using the Value/Suit
     * enum, and sets the card.
     * @param value(enum)
     * @param suit(enum)
     */
    Card(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }
    
    /**
     * Uses the Value enum getValue() method to return the correct number and not
     * the name of the card (Returns 1 instead of ACE)
     * @return the Value of the card.
     */
    public int getValue() {
        return value.getValue();
    }
    
    /**
     * Uses the Suit enum getSuit() method to return the suit of the current card.
     * @return The Suit of the card.
     */
    public String getSuit() {
        return suit.getSuit();
    }
    
    @Override
    public String toString() {
        String strValue = "";
        int value = this.getValue();
        if(value < 11) {
            strValue += value;
        } else if(value == 11) {
            strValue += "Jack";
        } else if(value == 12) {
            strValue += "Queen";
        } else if(value == 13) {
            strValue += "King";
        } else {
            strValue += "Ace";
        }
        
        String card = "" + strValue + " of " + this.getSuit();
        return card;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Card){
            Card inputCard = (Card)obj;
            
            if(this.suit == inputCard.suit && this.value == inputCard.value) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
        
    }

    
    
    
            
}
