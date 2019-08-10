
package ca.sheridancollege.project;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *JUnit test class for all methods used in War class
 * @author Michal Zarnowski
 */
public class WarTest {
    
    /**
     * Good test for playTopCards() method.
     * To set up a good test we are giving each player sufficient amount of cards. The method 
     * should succeed and print out first player's name and their card, and second player's
     * name and their card.
     */
    @Test
    public void testPlayTopCards_Good(){
        //Setting up the good test where each players deck has more than one card left
        User testUserOne = new User("testA", 'a');
        User testUserTwo = new User("testB", 'b');
        
        Deck testDeckA = new Deck();
        Deck testDeckB = new Deck();
        testDeckA.add(new Card(Value.ACE, Suit.HEARTS));
        testDeckA.add(new Card(Value.KING, Suit.HEARTS));
        testDeckA.add(new Card(Value.QUEEN, Suit.HEARTS));
        testDeckB.add(new Card(Value.ACE, Suit.SPADES));
        testDeckB.add(new Card(Value.KING, Suit.SPADES));
        testDeckB.add(new Card(Value.QUEEN, Suit.SPADES));
        
        testUserOne.setCurrentDeck(testDeckA);
        testUserOne.setNextDeck(new Deck());
        testUserTwo.setCurrentDeck(testDeckB);
        testUserTwo.setNextDeck(new Deck());
        
        //Converting console message to byte stream
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        War war = new War();
        
        //Comparing expected outcome to the substring of the console out stream as we
        //are only checking if the method can play top cards from each player
        war.playTopCards(testUserOne, testUserTwo);
        assertEquals("testA's card: Ace of Hearts" + System.getProperty("line.separator") + 
                "testB's card: Ace of Spades" + System.getProperty("line.separator"), 
                outContent.toString().substring(0, 58));
        
    }
    
    /**
     * Boundary test for playTopCards() method.
     * To set up a boundary test we are giving each player one card left. The method 
     * should succeed and print out first player's name and their card, and second player's
     * name and their card.
     */
    @Test
    public void testPlayTopCards_Boundary(){
        //Setting up the boundary test where each players deck has only one card left
        User testUserOne = new User("testA", 'a');
        User testUserTwo = new User("testB", 'b');
        
        Deck testDeckA = new Deck();
        Deck testDeckB = new Deck();
        testDeckA.add(new Card(Value.ACE, Suit.HEARTS));
        testDeckB.add(new Card(Value.KING, Suit.HEARTS));
        
        testUserOne.setCurrentDeck(testDeckA);
        testUserOne.setNextDeck(new Deck());
        testUserTwo.setCurrentDeck(testDeckB);
        testUserTwo.setNextDeck(new Deck());
        
        //Converting console message to byte stream
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        War war = new War();
        
        //Comparing expected outcome to the substring of the console out stream as we
        //are only checking if the method can play top cards from each player
        war.playTopCards(testUserOne, testUserTwo);
        assertEquals("testA's card: Ace of Hearts" + System.getProperty("line.separator") + 
                "testB's card: King of Hearts" + System.getProperty("line.separator"), 
                outContent.toString().substring(0, 59));
        
    }
    
    /**
     * Bad test for playTopCards() method.
     * To set up a bad test we initialize current decks for each player but not their
     * next decks. This will cause the program to throw an error because when the terminateGame()
     * method is called from inside playTopCards() method, both decks are required.
     */
    @Test (expected = Exception.class)
    public void testPlayTopCards_Bad() {
        //Setting up the bad test with only current decks for each player
        User testUserOne = new User("testA", 'a');
        User testUserTwo = new User("testB", 'b');
        
        testUserOne.setCurrentDeck(new Deck());
        testUserTwo.setCurrentDeck(new Deck());
        
        War war = new War();
        war.playTopCards(testUserOne, testUserTwo);
    }
    
    /**
     * Good test for getTopCard() method.
     * This method method should return the top card in the deck of supplied User object.
     * To set up the test, we are giving our test User a Deck with one Card. If successful,
     * method should return this card.
     */
    @Test
    public void testGetTopCard_Good() {
        //Setting up the good test with a card in players current deck
        User testUser = new User("test", 'a');
        Deck testDeck = new Deck();
        testDeck.add(new Card(Value.ACE, Suit.HEARTS));
        testUser.setCurrentDeck(testDeck);
        testUser.setNextDeck(new Deck());
        
        War war = new War();
        Card testCard = war.getTopCard(testUser);
        Card expectedCard = (new Card(Value.ACE, Suit.HEARTS));
        
        //Comparing the card from the method to an expected card
        assertTrue(testCard.equals(expectedCard));
    }
    
    /**
     * Boundary test for getTopCard() method.
     * To set up the boundary test we are giving our test user one card in their next deck.
     * The method should still successfully return the last remaining card in player's last
     * remaining deck.
     */
    @Test
    public void testGetTopCard_Boundary() {
        //Setting up the boundary test with a card in players next deck
        User testUser = new User("test", 'a');
        Deck testDeck = new Deck();
        testDeck.add(new Card(Value.ACE, Suit.HEARTS));
        testUser.setCurrentDeck(new Deck());
        testUser.setNextDeck(testDeck);
        
        War war = new War();
        Card testCard = war.getTopCard(testUser);
        Card expectedCard = (new Card(Value.ACE, Suit.HEARTS));
        
        //Comparing the card from the method to an expected card
        assertTrue(testCard.equals(expectedCard));
    }
    
    /**
     * Bad test for getTopCard() method.
     * To set up a bad test we supply the method with a User object who's current and next
     * Decks are empty. In this case, the method should throw a NullPointerException.
     */
    @Test (expected = Exception.class)
    public void testGetTopCard_Bad() {
        //Setting up the bad test with both decks empty
        User testUser = new User("test", 'a');
        testUser.setCurrentDeck(new Deck());
        testUser.setNextDeck(new Deck());
        
        War war = new War();
        //Next line should cause the method to throw an exception
        Card testCard = war.getTopCard(testUser);
    }
    
    /**
     * Good test for setNextDeck() method.
     * This method should set player's next deck as their current deck and then clear their
     * next deck. In order to test this, we set up a test User with an empty current deck and
     * next deck containing one Card. To test successfulness of this method we need to check
     * two things, if cards in player's current deck are equal to cards that were in their next 
     * deck and that their next deck contains no cards once the method is called.
     */
    @Test
    public void testSetNextDeck_Good() {
        //Setting up the good test with a test user, empty current deck and next
        //deck containing one card
        User testUser = new User("test", 'a');
        testUser.setCurrentDeck(new Deck());
        
        Deck testDeck = new Deck();
        testDeck.add(new Card(Value.ACE, Suit.HEARTS));
        testUser.setNextDeck(testDeck);
        
        War war = new War();
        war.setNextDeck(testUser);
        
        //Testing if current deck contains the card from next deck after method call
        assertEquals("Ace of Hearts", testUser.getCurrentDeck().toString().substring(0, 13));
        //Testing if next deck is clear after method call
        assertTrue(testUser.getNextDeck().checkSize() == 0);
    }
    /**
     * Boundary test for setNextDeck() method.
     * In order to test the boundary criteria we set both decks to be empty. The method
     * should still run successfully, but both deck will be empty.
     */
    @Test
    public void testSetNextDeck_Boundary() {
        //Setting up boundary test with both decks empty
        User testUser = new User("test", 'a');
        testUser.setCurrentDeck(new Deck());
        testUser.setNextDeck(new Deck());
        
        War war = new War();
        war.setNextDeck(testUser);
        
        //Testing if both decks are still empty once the method is called
        assertTrue(testUser.getCurrentDeck().checkSize() == 0 &&
                testUser.getNextDeck().checkSize() == 0);
    }
    
    /* ********** BAD TEST ************
       In this situation, there is no way to do a bad test for method setNextDeck().
       This method would only fail if a current Deck or next Deck object didn't exist
       however those objects are instantiated when the program is run therefore there 
       is no way the method would be called with no Deck in place.
    */
    
    /**
     * Good test for war() method.
     * This method simulates the war game play. If ran successfully, it should produce a string output
     * of who won the war and their winning pool of Cards. To test this method, we supply it with two
     * test users and their decks containing three Cards each. We expect to see a String in the 
     * console as a result of running this method, for the purpose of this test, we use a substring
     * of console output in order to compare to expected result as this will be a sufficient test.
     */
    @Test
    public void testWar_Good() {
        //Setting up the good test with 2 Users and their Decks containing 3 Cards each
        User testUserA = new User("testA", 'a');
        User testUserB = new User("testB", 'b');
        
        Card warCardA = new Card(Value.ACE, Suit.HEARTS);
        Card warCardB = new Card(Value.ACE, Suit.SPADES);
        
        Deck testDeckA = new Deck();
        testDeckA.add(new Card(Value.KING, Suit.HEARTS));
        testDeckA.add(new Card(Value.QUEEN, Suit.HEARTS));
        
        Deck testDeckB = new Deck();
        testDeckB.add(new Card(Value.KING, Suit.SPADES));
        testDeckB.add(new Card(Value.JACK, Suit.SPADES));
        
        testUserA.setCurrentDeck(testDeckA);
        testUserA.setNextDeck(new Deck());
        testUserB.setCurrentDeck(testDeckB);
        testUserB.setNextDeck(new Deck());
        
        //Converting console message to byte stream
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        War war = new War();
        war.war(testUserA, warCardA, testUserB, warCardB);
        
        //Expected result from calling the method
        String expectedString = "WINNING POOL:" + System.getProperty("line.separator") + 
                "Ace of Hearts\nAce of Spades\nKing of Hearts\nKing of Spades\nQueen of Hearts\nJack of Spades";
                
        String returnedString = outContent.toString();
        
        //Comparing expected result to console output
        assertEquals(expectedString, returnedString.substring(215,318));
    }
    
    /**
     * Design of the terminateGame() method prohibts from running a boundary test
     */
    
    /**
     * Bad test for war() method.
     * In order to run a bad test, we call the method with 2 users with no Decks. This should result 
     * with the method throwing an exception when the method tries to access user's Decks to see next
     * cards for the war.
     */
    @Test (expected = Exception.class)
    public void testWar_Bad() {
        //Setting up the bad test with two Users with no Decks
        User testUserA = new User("testA", 'a');
        User testUserB = new User("testB", 'b');
        
        Card warCardA = new Card(Value.ACE, Suit.HEARTS);
        Card warCardB = new Card(Value.ACE, Suit.SPADES);
        
        War war = new War();
        //The following method call will result in an exception being thrown
        war.war(testUserA, warCardA, testUserB, warCardB);
    }
    
    /**
     * Good test for terminateGame() method.
     * This method should print out the scoreboard with scores incremented based on the
     * deck size of each player. To set up the good test, we initialize a User object with
     * empty Decks. This should result in the method incrementing opponent's score and
     * printing out the scoreboard with appropriate results.
     */
    @Test
    public void testTerminateGame_Good() {
        //Setting up the good test with player's decks empty
        User testUserA = new User("testA", 'a');
        testUserA.setCurrentDeck(new Deck());
        testUserA.setNextDeck(new Deck());
        
        //Converting console message to byte stream
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        War war = new War();
        war.terminateGame(testUserA);
        
        //Method should produce the string below if run successfully
        String expectedString = "'s score: 0 \n's score: 1" + System.getProperty("line.separator");
        
        //Comparing expected output with console output
        assertEquals(expectedString, outContent.toString().substring(11));
    }
    
    /**
     * Design of the terminateGame() method prohibts from running a boundary test
     */
    
    /**
     * Bad test for terminateGame() method.
     * To set up the bad test for this method, we supply the method a User object with
     * no Decks set.This will result in the method throwing a NullPointerException.
     */
    @Test (expected = Exception.class)
    public void testTerminateGame_Bad() {
        //Setting up the good test with player's decks empty
        User testUserA = new User("testA", 'a');
        
        War war = new War();
        war.terminateGame(testUserA);
    }
    
}
