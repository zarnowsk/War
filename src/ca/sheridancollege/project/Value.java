
package ca.sheridancollege.project;

/**
 * Enumaration class representing card values.
 * @author pfree
 */
public enum Value {
    ACE (14), 
    TWO (2), 
    THREE (3), 
    FOUR (4), 
    FIVE (5), 
    SIX (6), 
    SEVEN (7), 
    EIGHT (8), 
    NINE (9), 
    TEN (10), 
    JACK (11),
    QUEEN (12), 
    KING (13);
    
    private int value;
    
    /**
     * Basic constructor for the Enum allowing the values to be set from an int
     * but still retain their correct user friendly name.
     * @param value 
     */
    private Value(int value) {
        this.value = value;
    }
    
    /**
     * 
     * @return The current value of the Card. 
     */
    public int getValue() {
        return value;
    }
    
    
}
