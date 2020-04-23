package uk.ac.qub.eeecs.game.matchAttax.gameBoard;

import java.util.ArrayList;
import uk.ac.qub.eeecs.game.matchAttax.cards.Card;


//James Earls
public class PlayingField{
    // Defines the types of Playing Fields's that will be required
    public enum PlayingFieldType{
        PLAYER,CPU
    }

    // Arraylist to store cards needed to count score
    private ArrayList<Card> playedCards;

    //integer to hold score
    private int score = 0;

    public PlayingField(PlayingFieldType playingFieldType, int score) {
        this.score = score;
        this.playedCards = new ArrayList<>();
    }

    public int getScore() {
        return score;
    }

    public void playCard(Card card){
        this.playedCards.add(card);
        this.score += card.getOverallValue();
    }

}
