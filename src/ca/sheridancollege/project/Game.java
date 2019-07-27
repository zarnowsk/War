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

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    
    private static Scanner input = new Scanner(System.in);
    private static boolean gameOver = false;
    
    public static void main(String[] args) {
        
        // ***** INITIALIZE GAME VARIABLES *****
        Game game = new Game();
        
        //Create a new user
        User player = game.createUser();
        
        //Create a machine user
        User machine = new User("Machine", 'm');
        machine.setMachine(true);
        
        //Create a deck of cards, shuffle it and distribute evenly 
        //between both players
        Deck mainDeck = new Deck();
        mainDeck.generateDeck();
        mainDeck.shuffleDeck();
        
        Deck playerCurrentDeck = new Deck();
        Deck machineCurrentDeck = new Deck();
        //For every card in the deck, add nth card to player's deck, add nth+1 card to machine's deck
        for(int i = 0; i < mainDeck.checkSize(); i += 2) {
            playerCurrentDeck.add(mainDeck.get(i));
            machineCurrentDeck.add(mainDeck.get(i + 1));
        }
        
        //Set decks for both players
        player.setCurrentDeck(playerCurrentDeck);
        machine.setCurrentDeck(machineCurrentDeck);
        
        //Create next round decks for players
        Deck playerNextDeck = new Deck();
        Deck machineNextDeck = new Deck();
        player.setNextDeck(playerNextDeck);
        machine.setNextDeck(machineNextDeck);
        
        //Create a blank scoreboard with user details
        Scoreboard scoreboard = game.createScoreboard(player, machine);
        
        // ****** GAMEPLAY *****
        while(!gameOver) {
            game.playTopCards(player, machine);
        }
        
    }
    
    /**
     * Method creates and returns a new User entity from validated user input.
     * @return A new User entity
     */
    private User createUser() {
        
        String userName = "";
        String key = "";
        
        //Validate user input for player name. Must not be empty String
        do {
            System.out.println("Please enter your name: ");
            userName = input.nextLine();
        }while(userName.length() == 0);
        
        //Validate user input for hot key. Must be of length 1.
        do {
            System.out.println("Select your default 'play card' key: ");
            key = input.nextLine();
            if(key.length() != 1) {
                System.out.println("Your play key must be a single character");
            }
        }while(key.length() != 1);
        
        char hotKey = key.charAt(0);
        
        User player = new User(userName, hotKey);
        return player;
   } 

    /**
     * Method creates a blank scoreboard with names of supplied users.
     * @param player Human user.
     * @param machine Machine user.
     * @return Blank scoreboard initialized with user's and machine's names.
     */
    private Scoreboard createScoreboard(User player, User machine) {
        //Player's name
        String pName = player.getName();
        //Machine's name
        String mName = machine.getName();

        Scoreboard scoreboard = new Scoreboard(pName, mName);

        return scoreboard;
    }
    
    /**
     * Method displays each player's top card, evaluates them and displays the winner of the round if there is one.
     * In case of a draw, war() method is called, if there is a winner in the round, both cards get added to their next
     * deck. In case of either player running out of cards, terminateGame() method is called.
     * @param player User object of player 1
     * @param machine User object of player 2(PC)
     */
    private void playTopCards(User player, User machine) {
        //Get top deck cards via getTopCard() method
        Card playerCard = getTopCard(player);
        Card machineCard = getTopCard(machine);
        
        //If top cards exist(players had cards in their decks), display and compare both cards
        try {
            String playerRound = player.getName() + "'s card: " + playerCard;
            String machineRound = machine.getName() + "'s card: " + machineCard;
            System.out.println(playerRound);
            System.out.println(machineRound);
            
            //If cards have different values, compare and add to winner's stack, otherwise initiate war()
            if(playerCard.getValue() > machineCard.getValue()) {
                player.getNextDeck().add(playerCard);
                player.getNextDeck().add(machineCard);
                System.out.println(player.getName() + " wins this round");
            } else if (playerCard.getValue() < machineCard.getValue()) {
                machine.getNextDeck().add(playerCard);
                machine.getNextDeck().add(machineCard);
                System.out.println(machine.getName() + " wins this round");
            } else {
                war();
            }
        } catch(Exception e) {
            //If any of the player's ran out of cards, call terminateGame()
            terminateGame();
        }
        
    }
    
    /**
     * Method returns a Card object based on the user's stack. If user has no cards left, null card object is returned.
     * @param user User object of a player
     * @return Card object
     */
    private Card getTopCard(User user) {
        Card card = new Card();
        //Return first Card in the current Deck if deck not empty
        try {
            card = user.getCurrentDeck().pop();
        } catch(Exception e) {
            //If current deck empty, set next deck as current deck via setNextDeck() method. Return first Card in 
            //new current Deck if not empty
            try {
                setNextDeck(user);
                card = user.getCurrentDeck().pop();
            } catch(Exception ee) {

            }
        }
        return card;
    }
    
    /**
     * Method sets user's next deck as their current deck and shuffles it. Players next deck is cleared for future use.
     * @param user User object
     */
    private void setNextDeck(User user) {
        //Create a temporary deck from player's next deck
        Deck tempDeck = user.getNextDeck();
        //Transfer all cards from temp deck to player's current deck
        for(int i = 0; i < tempDeck.checkSize(); i++) {
            user.getCurrentDeck().add(tempDeck.get(i));
        }
        //Clear next deck for future use
        user.getNextDeck().clear();
        //Shuffle current deck
        user.getCurrentDeck().shuffleDeck();
    }
    
    private void war() {
        //TO BE IMPLEMENTED
    }
    
    private void terminateGame() {
        //TO BE IMPLEMENTED
        gameOver = true;
        System.out.println("GAME OVER");
    }
}