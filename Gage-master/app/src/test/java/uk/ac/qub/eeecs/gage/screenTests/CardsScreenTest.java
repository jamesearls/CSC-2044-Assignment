package uk.ac.qub.eeecs.gage.screenTests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.game.matchAttax.screens.CardsScreen;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

//David Mackenzie
@RunWith(MockitoJUnitRunner.class)
public class CardsScreenTest {

    @Mock
    Game aGame = Mockito.mock(Game.class);
    @Mock
    AssetManager assetManager = Mockito.mock(AssetManager.class);

    @Test
    public void CardsScreen_CreateInstance(){
        when(aGame.getAssetManager()).thenReturn(assetManager);
        CardsScreen cardsScreen = new CardsScreen((aGame));
        assertEquals(aGame, cardsScreen.getGame());
    }
}
