package uk.ac.qub.eeecs.game.matchAttax.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uk.ac.qub.eeecs.game.matchAttax.cards.Card;
import uk.ac.qub.eeecs.game.matchAttax.cards.PlayerCard;
import uk.ac.qub.eeecs.game.matchAttax.cards.ManagerCard;
import uk.ac.qub.eeecs.game.matchAttax.screens.MatchGameScreen;

//By Adam Kennedy
public class Deck {

    public static final int MAX_SIZE = 5;

    private ArrayList<Card> cardsInDeck;
    private MatchGameScreen gameScreen;

    public Deck(MatchGameScreen gameScreen){
        this.gameScreen = gameScreen;

        cardsInDeck = new ArrayList<Card>();
        PlayerCard newCard;
        for (int idx=0; idx<MAX_SIZE-1; idx++){
            do {
                newCard = gameScreen.getRandomPlayerCard();
            }
            while (cardAlreadyInDeck(newCard)==true);
            cardsInDeck.add(newCard);
        }
        //newCard = gameScreen.getRandomManagerCard();
        //cardsInDeck.add(newCard);
    }

    public List<Card> getCardsInDeck(){ return cardsInDeck; }

    public void setDeck(Deck deck)
    {
        cardsInDeck = deck.cardsInDeck;
    }

    public boolean cardAlreadyInDeck(Card card){
        if (cardsInDeck.contains(card)) return true;
        return false;
    }


}
