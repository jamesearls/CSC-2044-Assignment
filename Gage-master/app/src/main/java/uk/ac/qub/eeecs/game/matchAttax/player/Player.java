package uk.ac.qub.eeecs.game.matchAttax.player;

import uk.ac.qub.eeecs.game.matchAttax.cards.Card;
import uk.ac.qub.eeecs.game.matchAttax.cards.ManagerCard;

//By Adam Kennedy
public class Player {

    private String name;
    private Deck deck;
    private int score;
    private boolean endTurn;
    private int managerScore;

    public Player(String name, Deck deck){
        this.name = name;
        this.deck = deck;
        score = 0;
        endTurn = true;
    }

    public String getName() {return name;}
    public Deck getDeck() {return deck;}
    public int getScore() {return score;}
    public boolean getEndTurn(){return endTurn;}

    public void setName(String newName) {name = newName;}
    public void setDeck(Deck newDeck){deck = newDeck;}
    public void setScore(int newScore){score = newScore;}
    public void setEndTurn(boolean val){endTurn = val;}

    //David Mackenzie
    public void playCard(Card card){
        if (card instanceof ManagerCard){
            if (((ManagerCard) card).isType() == true){
                score += card.getOverallValue();
            }else if(((ManagerCard) card).isType() == false){
                score *= card.getOverallValue();
            }
        }
    //Adam Kennedy
        if(deck.getCardsInDeck().contains(card)){
            deck.getCardsInDeck().remove(card);
            score += card.getOverallValue();
        }
    }



}
