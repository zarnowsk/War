/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

/**
 *
 * @author pfree
 */
public enum Suit {
    HEARTS ("Hearts"),
    DIAMONDS ("Diamonds"),
    SPADES ("Spades"),
    CLUBS ("Clubs");

    private String suit;
    
    private Suit(String suit) {
        this.suit = suit;
    }
    
    public String getSuit() {
        return suit;
    }
}
