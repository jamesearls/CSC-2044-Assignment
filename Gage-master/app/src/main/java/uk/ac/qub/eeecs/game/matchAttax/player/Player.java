package uk.ac.qub.eeecs.game.matchAttax.player;

public class Player {

    private Deck deck;
    private int score;
    private boolean endTurn;

    public Player(Deck deck){
        this.deck = deck;
        score = 0;
        endTurn = false;
    }

    public Deck getDeck() {return deck;}
    public int getScore() {return score;}
    public boolean getEndTurn(){return endTurn;}

    public void setDeck(Deck newDeck){deck = newDeck;}
    public void setScore(int newScore){score = newScore;}
    public void setEndTurn(boolean val){endTurn = val;}


}
