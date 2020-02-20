package uk.ac.qub.eeecs.game.matchAttax.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uk.ac.qub.eeecs.game.matchAttax.cards.Card;
import uk.ac.qub.eeecs.game.matchAttax.screens.MatchGameScreen;

//By Adam Kennedy
public class Deck {

    public static final int MAX_SIZE = 5;

    private List<Card> cardsInDeck;
    private MatchGameScreen gameScreen;

    Random random = new Random();

    public Deck(MatchGameScreen gameScreen){
        this.gameScreen = gameScreen;

        cardsInDeck = new ArrayList<>();
        Card newCard;
        for (int idx=0; idx<MAX_SIZE-1; idx++){
            while(true) {
                newCard = gameScreen.getRandomPlayerCard();
                if(cardAlreadyInDeck(newCard)){
                    cardsInDeck.add(newCard);
                    break;
                }
            }
        }
        newCard = gameScreen.getRandomManagerCard();
        cardsInDeck.add(newCard);
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
