
package ca.sheridancollege.project;

/**
 * Enumaration class representing the card's suit
 * @author pfree
 */
public enum Suit {
    HEARTS ("Hearts"),
    DIAMONDS ("Diamonds"),
    SPADES ("Spades"),
    CLUBS ("Clubs");

    private String suit;
    
    /**
     * Constructer for the Suit
     * @param suit 
     */
    private Suit(String suit) {
        this.suit = suit;
    }
    
    /**
     * Class accessor
     * @return 
     */
    public String getSuit() {
        return suit;
    }
}
