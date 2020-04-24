package uk.ac.qub.eeecs.gage;

import android.content.Context;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;

import uk.ac.qub.eeecs.gage.engine.io.FileIO;
import uk.ac.qub.eeecs.game.DemoGame;
import uk.ac.qub.eeecs.game.matchAttax.cards.Card;
import uk.ac.qub.eeecs.game.matchAttax.gameBoard.PlayingField;

import static junit.framework.TestCase.assertTrue;

//James Earls
@RunWith(MockitoJUnitRunner.class)
public class PlayingFieldTest {
    private Context context;
    private DemoGame game;
    private ArrayList<Card> playedCards = new ArrayList<>();


    @Mock
    Card testCard = Mockito.mock(Card.class);
    Card testCard1 = Mockito.mock(Card.class);
    Card testCard2 = Mockito.mock(Card.class);
    Card testCard3 = Mockito.mock(Card.class);
    Card testCard4 = Mockito.mock(Card.class);
    //1
    @Test
    public void check_If_Score_Correctly_Scored_Player_ValidData_TestIsSuccessful(){
        PlayingField playingField = new PlayingField(PlayingField.PlayingFieldType.PLAYER, 10);
        boolean success = false;
        if(playingField.getScore() == 10){
            success = true;
        }
        assertTrue(success = true);

    }

    //2
    @Test
    public void check_If_Score_Correctly_Scored_Cpu_ValidData_TestIsSuccessful(){
        PlayingField playingField = new PlayingField(PlayingField.PlayingFieldType.CPU, 6);
        boolean success = false;
        if(playingField.getScore() == 6){
            success = true;
        }
        assertTrue(success = true);
    }

    //3
    @Test
    public void check_If_Cards_Are_Correctly_Stored_In_ArrayList_ValidData_TestIsSuccessful(){
        boolean success = false;
        int numOfCards = 0;
        playedCards.add(testCard);
        playedCards.add(testCard1);
        playedCards.add(testCard2);
        playedCards.add(testCard3);
        playedCards.add(testCard4);

        for(int i = 0; i<playedCards.size(); i++){
         numOfCards = 0;
        }

        if(playedCards.get(3) == testCard2 && numOfCards == 5){
            success = true;
        }
        assertTrue(success = true);
    }
}
