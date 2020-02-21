package uk.ac.qub.eeecs.gage.playerTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;
import org.mockito.runners.MockitoJUnitRunner;

import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.game.CardDemo.Card;
import uk.ac.qub.eeecs.game.matchAttax.player.Deck;
import uk.ac.qub.eeecs.game.matchAttax.player.PlayerAI;
import uk.ac.qub.eeecs.game.matchAttax.screens.MatchGameScreen;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlayerAITest {
    @Mock
    private MatchGameScreen gameScreen = mock(MatchGameScreen.class);
    @Mock
    private Game game = mock(Game.class);
    @Mock
    private AssetManager assetManager = mock(AssetManager.class);
    @Mock
    private Deck deck = mock(Deck.class);

    @Before
    public void setup()
    {

    }

    @Test
    public void constructPlayerAIObject()
    {
        PlayerAI player = new PlayerAI("Adam", deck, gameScreen);

        assertTrue(player.getName().equals("Adam"));
        assertTrue(player.getDeck() == deck);
    }
    @Test
    public void playerAINameGetAndSet()
    {
        PlayerAI playerAI= new PlayerAI("Adam", deck, gameScreen);
        playerAI.setName("Aaron");

        assertTrue(playerAI.getName().equals("Aaron"));
    }
    @Test
    public void playerAIDeckGetAndSet()
    {
        PlayerAI playerAI = new PlayerAI("Adam", deck, gameScreen);
        Deck newDeck = mock(Deck.class);
        playerAI.setDeck(newDeck);

        assertTrue(playerAI.getDeck().equals(newDeck));
    }
    @Test
    public void playerAIScoreGetAndSet()
    {
        PlayerAI playerAI = new PlayerAI("Adam", deck, gameScreen);
        playerAI.setScore(1);

        assertTrue(playerAI.getScore() == 1);
    }
    @Test
    public void playerAIEndTurnGetAndSet()
    {
        PlayerAI playerAI = new PlayerAI("Adam", deck, gameScreen);
        playerAI.setEndTurn(false);

        assertTrue(playerAI.getEndTurn() == false);
    }
    public void playerAIselectCardToPlay_returnsCard(){
        PlayerAI playerAI = new PlayerAI("Adam", deck, gameScreen);
        int playerScore = 10, aiScore = 5, roundNum = 1;
        Card card = mock(Card.class);

        assertTrue(playerAI.selectCardToPlay(playerScore, aiScore, roundNum).equals(card));
    }
}
