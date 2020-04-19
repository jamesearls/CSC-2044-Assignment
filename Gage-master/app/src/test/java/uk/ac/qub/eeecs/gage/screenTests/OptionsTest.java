package uk.ac.qub.eeecs.gage.screenTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.game.matchAttax.screens.OptionsScreen;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OptionsTest {

    @Mock
    Game aGame = Mockito.mock(Game.class);
    @Mock
    AssetManager assetManager = Mockito.mock(AssetManager.class);


    @Test
    public void Options_CreateInstance(){
        when(aGame.getAssetManager()).thenReturn(assetManager);
        OptionsScreen optionsScreen = new OptionsScreen((aGame));
        assertEquals(aGame, optionsScreen.getGame());
    }


}
