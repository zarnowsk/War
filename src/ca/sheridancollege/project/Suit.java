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
    HEARTS,
    DIAMONDS,
    SPADES,
    CLUBS;

    private Suit suit;
    
    public Suit getSuit() {
        return this.suit;
    }
}