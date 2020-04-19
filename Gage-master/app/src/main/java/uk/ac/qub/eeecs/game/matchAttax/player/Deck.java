package uk.ac.qub.eeecs.game.matchAttax.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.game.matchAttax.cards.Card;
import uk.ac.qub.eeecs.game.matchAttax.cards.ManagerCard;
import uk.ac.qub.eeecs.game.matchAttax.cards.PlayerCard;


import uk.ac.qub.eeecs.game.matchAttax.screens.MatchGameScreen;

//Adam Kennedy
public class Deck {

    public static final int MAX_SIZE = 5;

    private ArrayList<Card> cardsInDeck;
    private MatchGameScreen gameScreen;

    public Deck(MatchGameScreen gameScreen){
        this.gameScreen = gameScreen;

        cardsInDeck = new ArrayList<Card>();

        PlayerCard newCard;

        for (int idx=0; idx<MAX_SIZE; idx++){
            do {
                newCard = gameScreen.getRandomPlayerCard();

            }
            while (cardAlreadyInDeck(newCard)==true);
            cardsInDeck.add(newCard);
        }
        cardsInDeck.add(gameScreen.getRandomManagerCard());
    }

    //copy constructor
    public Deck(Deck deck){
        gameScreen = deck.getGameScreen();
        cardsInDeck = deck.getCardsInDeck();
    }

    public ArrayList<Card> getCardsInDeck(){ return cardsInDeck; }

    public void setDeck(Deck deck)
    {
        cardsInDeck = deck.cardsInDeck;
    }

    public MatchGameScreen getGameScreen() { return gameScreen; }

    public void setGameScreen(MatchGameScreen gs) { gameScreen = gs;}

    //Checks card is not already been added to the deck
    public boolean cardAlreadyInDeck(Card card){
        if (cardsInDeck.contains(card)) return true;
        return false;
    }
}
