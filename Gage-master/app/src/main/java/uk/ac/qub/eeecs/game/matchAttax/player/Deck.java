package uk.ac.qub.eeecs.game.matchAttax.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uk.ac.qub.eeecs.game.matchAttax.cards.Card;
import uk.ac.qub.eeecs.game.matchAttax.screens.MatchGameScreen;

public class Deck {

    public static final int MAX_SIZE = 5;

    private List<Card> cardsInDeck;
    private MatchGameScreen gameScreen;

    Random random = new Random();

    public Deck(MatchGameScreen gameScreen){
        this.gameScreen = gameScreen;

        cardsInDeck = new ArrayList<>();
        for (int idx=0; idx<MAX_SIZE; idx++){
            while(true) {
                Card newCard = gameScreen.getRandomCard();
                if(cardAlreadyInDeck(newCard)){
                    cardsInDeck.add(newCard);
                    break;
                }
            }
        }
    }

    public boolean cardAlreadyInDeck(Card card){
        if (cardsInDeck.contains(card)) return true;
        return false;
    }


}
