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

import java.util.Scanner;

public class Game {
    
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        Game game = new Game();
        
        //Create a new user
        User player = game.createUser();
        
        //Create a machine user
        User machine = new User("Machine", 'm');
        machine.setMachine(true);
        
        //Create a blank scoreboard with user details
        Scoreboard scoreboard = game.createScoreboard(player, machine);
        
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

    private Scoreboard createScoreboard(User player, User machine) {
        //Player's name
        String pName = player.getName();
        //Machine's name
        String mName = machine.getName();

        Scoreboard scoreboard = new Scoreboard(pName, mName);

        return scoreboard;
    }
}