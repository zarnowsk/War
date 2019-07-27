package ca.sheridancollege.project;

/**
 *
 * @author acer
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

    public char getHotKey() {
        return hotKey;
    }

    public void setHotKey(char hotKey) {
        this.hotKey = hotKey;
    }

    public void setMachine(boolean machine) {
        this.machine = machine;
    }
//The next 3 getters are for getUser() 

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
