/**
 * SYST 17796 Project Winter 2019.
 * War card game.
 * Date created: 07-JUNE-2019
 * Owned by: Michal Zarnowski
 * Collaborators: Paige Freeman-Cyopeck, Vinayak Pavate
 * Copyright: This work is the intellectual property of the owner and collaborators. Any further copying and
 * distribution must be within the Copyright law. Posting to commercial sites for profit is prohibited.
 * Citations: Base code designed by (github)alihassan-1
 */
package ca.sheridancollege.project;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 * @author dancye, 2018
 */
public abstract class Player 
{
    private String playerID; //the unique ID for this player
    
    /**
     * A constructor that allows you to set the player's unique ID
     * @param name the unique ID to assign to this player.
     */
    public Player(String name)
    {
        playerID= name;
    }
    
    /**
     * @return the playerID
     */
    public String getPlayerID() 
    {
        return playerID;
    }

    /**
     * Ensure that the playerID is unique
     * @param givenID the playerID to set
     */
    public void setPlayerID(String givenID) 
    {
        playerID = givenID;
    }
    
    /**
     * The method to be instantiated when you subclass the Player class
     * with your specific type of Player and filled in with logic to play your game.
     */
    public abstract void play();
    
}
