package uk.ac.qub.eeecs.game.matchAttax.player;

import java.util.Random;

import uk.ac.qub.eeecs.game.matchAttax.cards.Card;
import uk.ac.qub.eeecs.game.matchAttax.cards.ManagerCard;
import uk.ac.qub.eeecs.game.matchAttax.screens.MatchGameScreen;

import static java.lang.StrictMath.abs;

//Adam Kennedy
public class PlayerAI extends Player {

    public PlayerAI(String name, Deck deck, MatchGameScreen gameScreen){
        super(name, deck);
    }

    public PlayerAI(PlayerAI ai){
        super(ai.getName(), ai.getDeck());

        this.setScore(ai.getScore());
        this.setEndTurn(ai.getEndTurn());
    }

    public Card selectCardToPlay(int playerScore, int aiScore, int roundNum){
        int scoreDiff, closestToDiff=-1;
        Random random = new Random();
        Card cardToPlay = getDeck().getCardsInDeck().get(0);
        if (playerScore > aiScore){
            scoreDiff = playerScore - aiScore;
            for (Card card : getDeck().getCardsInDeck()){
                if (card.getOverallValue() == scoreDiff){
                    cardToPlay = card;
                    break;
                }
                else{
                    if (abs(scoreDiff - card.getOverallValue()) < closestToDiff || closestToDiff == -1){
                        closestToDiff = abs(scoreDiff - (int)card.getOverallValue());
                        cardToPlay = card;
                    }
                }
            }
        }
        else {
            cardToPlay = getDeck().getCardsInDeck().get(random.nextInt(getDeck().getCardsInDeck().size()));
        }

        return cardToPlay;
    }
}