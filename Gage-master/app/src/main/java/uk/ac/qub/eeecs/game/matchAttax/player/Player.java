package uk.ac.qub.eeecs.game.matchAttax.player;

import uk.ac.qub.eeecs.game.CardDemo.Card;

//By Adam Kennedy
public class Player {

    private Deck deck;
    private int score;
    private boolean endTurn;

    public Player(Deck deck){
        this.deck = deck;
        score = 0;
        endTurn = true;
    }

    public Deck getDeck() {return deck;}
    public int getScore() {return score;}
    public boolean getEndTurn(){return endTurn;}

    public void setDeck(Deck newDeck){deck = newDeck;}
    public void setScore(int newScore){score = newScore;}
    public void setEndTurn(boolean val){endTurn = val;}

    public void playCard(Card card){
        if(deck.getDeck().contains(card)){
            deck.getDeck().remove(card);
            score += card.getOverall();
        }
    }

}
