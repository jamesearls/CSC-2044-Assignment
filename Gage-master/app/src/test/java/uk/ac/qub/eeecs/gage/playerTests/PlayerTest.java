package uk.ac.qub.eeecs.gage.playerTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.game.matchAttax.player.Deck;
import uk.ac.qub.eeecs.game.matchAttax.player.Player;
import uk.ac.qub.eeecs.game.matchAttax.screens.MatchGameScreen;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {
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
    public void constructPlayerObject()
    {
        Player player = new Player("Adam", deck);

        assertTrue(player.getName().equals("Adam"));
        assertTrue(player.getDeck() == deck);
    }
    @Test
    public void playerNameGetAndSet()
    {
        Player player = new Player("Adam", deck);
        player.setName("Aaron");

        assertTrue(player.getName().equals("Aaron"));
    }
    @Test
    public void playerDeckGetAndSet()
    {
        Player player = new Player("Adam", deck);
        Deck newDeck = mock(Deck.class);
        player.setDeck(newDeck);

        assertTrue(player.getDeck().equals(newDeck));
    }
    @Test
    public void playerScoreGetAndSet()
    {
        Player player = new Player("Adam", deck);
        player.setScore(1);

        assertTrue(player.getScore() == 1);
    }
    @Test
    public void playerEndTurnGetAndSet()
    {
        Player player = new Player("Adam", deck);
        player.setEndTurn(false);

        assertTrue(player.getEndTurn() == false);
    }

}
