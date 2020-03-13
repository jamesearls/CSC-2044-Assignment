package uk.ac.qub.eeecs.gage;

import android.graphics.Paint;
import android.text.TextPaint;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.gage.engine.audio.AudioManager;
import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.game.matchAttax.screens.OptionsScreen;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OptionsScreenTest {
    @Mock
    private Game game;
    @Mock
    private GameScreen gameScreen;
    @Mock
    private AssetManager assetManager;
    @Mock
    private AudioManager audioManager;
    @Mock
    private OptionsScreen optionsScreen;

    @Before
    public void setUp(){
        when(game.getAudioManager()).thenReturn(audioManager);
        when(game.getAssetManager()).thenReturn(assetManager);
        when(gameScreen.getGame()).thenReturn(game);
    }

    @Test
    public void setMusicTestValid(){
        optionsScreen = new OptionsScreen(game);
        assertEquals(optionsScreen.setCurrentSong(3), true);
        assertEquals(optionsScreen.getCurrentSong(), 3);
    }

    @Test
    public void setMusicTestInvalid(){
        optionsScreen = new OptionsScreen(game);
        assertEquals(optionsScreen.setCurrentSong(10), false);
        assertEquals(optionsScreen.getCurrentSong(), 1);
    }
}
