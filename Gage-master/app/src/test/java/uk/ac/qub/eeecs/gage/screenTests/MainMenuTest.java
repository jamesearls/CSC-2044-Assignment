package uk.ac.qub.eeecs.gage.screenTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.game.matchAttax.screens.MainMenu;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainMenuTest {

    @Mock
    Game aGame = Mockito.mock(Game.class);
    @Mock
    AssetManager assetManager = Mockito.mock(AssetManager.class);


    @Test
    public void mainMenu_CreateInstance(){
        when(aGame.getAssetManager()).thenReturn(assetManager);
        MainMenu mainMenu = new MainMenu((aGame));
        assertEquals(aGame, mainMenu.getGame());
    }

    // tests to check if buttons will go to correct screens
}
