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

public class War {
    
    private static Scanner input = new Scanner(System.in);
    private static char reservedStatsChar = 's';
    private static Scoreboard scoreboard = new Scoreboard();
    private static boolean nextGame = false;
    private static boolean gameOver = false;
    
    public static void main(String[] args) {
        
        // ***** INITIALIZE GAME VARIABLES *****
        War game = new War();
        
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
        
        //Assign names to scoreboard
        scoreboard.setUserName(player.getName());
        scoreboard.setOpponentName(machine.getName());
        
        // ****** GAMEPLAY *****
        
        String userInput = "";
        
        do {
            //Play next round if the game is alive
            while(!gameOver) {
                //Allow the user to hit their hot key to play next card or hit "stat" key to display game stats
                System.out.println("\n" + player.getName() + " hit your hot key to play card. Hit 's' to display game stats");
                userInput = input.nextLine();
                if(userInput.length() > 0) {
                    if(Character.getNumericValue(userInput.charAt(0)) == Character.getNumericValue(player.getHotKey())) {
                        game.playTopCards(player, machine);
                    } else if(Character.getNumericValue(userInput.charAt(0)) == Character.getNumericValue(reservedStatsChar)) {
                        game.displayGameStats(player, machine);
                    }
                }
            }
            //Check if user would like to play another game
            game.playNextGame(player, machine);
        }while(nextGame);
        
    }
    
    /**
     * Method creates and returns a new User entity from validated user input.
     * @return A new User entity
     */
    private User createUser() {
        
        String userName = "";
        String key = "";
        boolean valid = false;
        
        //Validate user input for player name. Must not be empty String
        do {
            System.out.println("Please enter your name: ");
            userName = input.nextLine();
        }while(userName.length() == 0);
        
        //Validate user input for hot key. Must be of length 1. Cannot be 's' as its reserved.
        do {
            System.out.println("Select your default 'play card' key: ");
            key = input.nextLine();
            if(key.length() != 1) {
                System.out.println("Your play key must be a single character");
            } else if(key.charAt(0) == reservedStatsChar) {
                System.out.println("Please choose a different key, this one is reserved");
            } else {
                valid = true;
            }
            
        }while(!valid);
        
        char hotKey = key.charAt(0);
        
        User player = new User(userName, hotKey);
        return player;
   } 
    
    /**
     * Method displays each player's top card, evaluates them and displays the winner of the round if there is one.
     * In case of a draw, war() method is called, if there is a winner in the round, both cards get added to their next
     * deck. In case of either player running out of cards, terminateGame() method is called.
     * @param player User object of player 1
     * @param machine User object of player 2(PC)
     */
    private void playTopCards(User player, User machine) {
        try {
            //Get top deck cards via getTopCard() method
            Card playerCard = getTopCard(player);
            Card machineCard = getTopCard(machine);
            
            //If top cards exist(players had cards in their decks), display and compare both cards
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
                System.out.println("WAR!");
                war(player, playerCard, machine, machineCard);
            }
        } catch(Exception e) {
            //If any of the player's ran out of cards, call terminateGame()
            terminateGame(player);
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
                System.out.println(user.getName() + " ran out of cards!!!");
                throw new NullPointerException();
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
    
    /**
     * Method simulates the game action of War. Players draw their next top card, place it upside down on their decks. 
     * Next players draw their next topmost card to evaluate who will win the entire war stack of cards. If the deciding
     * cards are of same value, the war continues with same procedure.
     * @param player User object of the player
     * @param playerCardOne Player's card which initiated the war
     * @param machine User object of the machine
     * @param machineCardOne Machine's card which initiated the war
     */
    private void war(User player, Card playerCardOne, User machine, Card machineCardOne) {
        //Initialize variables utilized in this function
        Deck winningPool = new Deck();
        User winner = player;
        boolean warResolved = true;
        Card decidingPlayerCard = new Card();
        Card decidingMachineCard = new Card();
        
        //Add the cards that caused the war to the war winning pool
        winningPool.add(playerCardOne);
        winningPool.add(machineCardOne);
        
        //Repeat the following process until war is resolved (war continues if deciding (third) cards are of same value:
        // - Add second card of each player to winning pool (simulates cards facing down)
        // - Assign next top card from each user as their deciding cards, also add to winning pool
        // - Display deciding cards and compare them, if values differ, announce the winner, assign the winner and
        //   declare warResolved variable as true
        // - If values of deciding cards are the same, repeat the process until war can be resolved
        do {
            winningPool.add(getTopCard(player));
            winningPool.add(getTopCard(machine));
            
            decidingPlayerCard = getTopCard(player);
            decidingMachineCard = getTopCard(machine);
            winningPool.add(decidingPlayerCard);
            winningPool.add(decidingMachineCard);
            
            System.out.println("Placing each player's next card upside down on each deck.\nNext card will determine the winner!");
            String playerRound = player.getName() + "'s next card: " + decidingPlayerCard;
            String machineRound = machine.getName() + "'s next card: " + decidingMachineCard;
            System.out.println(playerRound);
            System.out.println(machineRound);
            
            if(decidingPlayerCard.getValue() > decidingMachineCard.getValue()){
                System.out.println(player.getName() + " wins the war and takes the winning pool!");
                winner = player;
                warResolved = true;
            } else if(decidingPlayerCard.getValue() < decidingMachineCard.getValue()) {
                System.out.println(machine.getName() + " wins the war and takes the winning pool!");
                winner = machine;
                warResolved = true;
            } else {
                warResolved = false;
                System.out.println("The War continues!");
            }
            
        }while(!warResolved);
        
        //Add all cards from winning pool to winners next deck
        for(int i = 0; i < winningPool.checkSize(); i++) {
            winner.getNextDeck().add(winningPool.get(i));
        }
        
        //Display the winnings
        System.out.println("WINNING POOL:");
        System.out.println(winningPool);
        
    }
    
    /**
     * Method displays the game statistics including player's current and next deck and the machines card count. 
     * Machine's deck does not get displayed as it would be considered cheating.
     * @param player User object of the player
     * @param machine User object of the machine
     */
    private void displayGameStats(User player, User machine) {
        System.out.println("");
        System.out.println("***********************");
        System.out.println("GAME STATS");
        
        System.out.println("PLAYER'S CURRENT DECK");
        System.out.println(player.getCurrentDeck());
        System.out.println("");
        
        System.out.println("PLAYER'S NEXT DECK");
        System.out.println(player.getNextDeck());
        System.out.println("");
        
        int machineDeckCount = machine.getCurrentDeck().checkSize() + machine.getNextDeck().checkSize();
        System.out.println("MACHINES CARD COUNT: " + machineDeckCount);
        System.out.println("***********************");
        System.out.println("");
    }

    /**
     * Method is called when a game is over. Global gameOver variable is set to true. Scoreboard is adjusted according
     * to the round winner and then displayed. 
     * @param player User object, used for checking who won the game
     */
    private void terminateGame(User player) {
        //Set global var gameOver to true
        gameOver = true;
        System.out.println("GAME OVER");

        //Adjust scoreboard based on user deck count. If total cards left in user's decks is 0, he lost the game,
        //otherwise machine lost the game.
        int playerDeckCount = player.getCurrentDeck().checkSize() + player.getNextDeck().checkSize();
        if(playerDeckCount == 0) {
            scoreboard.incrementOpponentScore();
        } else {
            scoreboard.incrementUserScore();
        }
        
        //Displaye scoreboard to user
        System.out.println(scoreboard);
    }
    
    /**
     * Method gives the user a choice of starting a new game
     * @param player User object of the player
     * @param machine User object of the machine
     */
    private void playNextGame(User player, User machine) {
        String answer = "";
        boolean validAnswer = false;
        
        //Get user input and validate to be Y/y/N/n only
        do {
            System.out.println("Would you like to play another game? (Y/N)");
            answer = input.nextLine();
            if(answer.length() > 0) {
                if(answer.charAt(0) == 'y' || answer.charAt(0) == 'Y' ||
                        answer.charAt(0) == 'n' || answer.charAt(0) == 'N') {
                    validAnswer = true;
                }
            }
        }while(!validAnswer);
        
        //If user answered Y/y, reset decks
        if(answer.charAt(0) == 'Y' || answer.charAt(0) == 'y') {
            nextGame = true;
            gameOver = false;
            //Clear all remaining cards from the round
            player.getCurrentDeck().clear();
            player.getNextDeck().clear();
            machine.getCurrentDeck().clear();
            machine.getNextDeck().clear();
            
            //Create new temporay deck and shuffle
            Deck newDeck = new Deck();
            newDeck.generateDeck();
            newDeck.shuffleDeck();
            
            //For every card in the deck, add nth card to player's deck, add nth+1 card to machine's deck
            for(int i = 0; i < newDeck.checkSize(); i += 2) {
                player.getCurrentDeck().add(newDeck.get(i));
                machine.getCurrentDeck().add(newDeck.get(i + 1));
            }
        }
    }
}