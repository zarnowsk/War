package ca.sheridancollege.project;

/**
 * Class representing the players in the game
 * @author Vinayak Pavate
 */
public class User {

    private final String name;
    private char hotKey;
    private boolean machine;
    private Deck currentDeck;
    private Deck nextDeck;

    //Constructor to create a User
    public User(String name, char hotKey) {
        this.name = name;
        this.hotKey = hotKey;
    }

    //Getters and setters for the class
    public char getHotKey() {
        return hotKey;
    }

    public void setHotKey(char hotKey) {
        this.hotKey = hotKey;
    }

    public void setMachine(boolean machine) {
        this.machine = machine;
    }

    public String getName() {
        return name;
    }

    public boolean isMachine() {
        return machine;
    }

    public void setCurrentDeck(Deck deck) {
        this.currentDeck = deck;
    }
    
    public Deck getCurrentDeck() {
        return currentDeck;
    }
    
    public void setNextDeck(Deck deck) {
        this.nextDeck = deck;
    }
    
    public Deck getNextDeck() {
        return nextDeck;
    }
    

}
