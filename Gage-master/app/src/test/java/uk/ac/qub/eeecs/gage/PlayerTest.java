package uk.ac.qub.eeecs.gage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

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
        when(gameScreen.getGame()).thenReturn(game);
        when(game.getAssetManager()).thenReturn(assetManager);
    }

    @Test
    public void player_ConstructObject()
    {
        Player player = new Player("Adam", deck);

        assertTrue(player.getName().equals("Adam"));
        assertTrue(player.getDeck() == deck);
    }
}
