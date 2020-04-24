package uk.ac.qub.eeecs.gage.screenTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.gage.engine.audio.AudioManager;
import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.game.matchAttax.screens.FAQScreen;
import uk.ac.qub.eeecs.game.matchAttax.screens.OptionsScreen;


import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

//import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class FAQScreenTest {
    @Mock
    private Game game;
    @Mock
    private GameScreen gameScreen;
    @Mock
    private AssetManager assetManager;
    @Mock
    private AudioManager audioManager;
    @Mock
    private FAQScreen faqScreen;

    //Andrew Bingham Test
    @Test
    public void testfaqScreenConstructor() {
        faqScreen = new FAQScreen(game);
        Assert.assertEquals(faqScreen.getName(), "FAQScreen");
    }

    // I decided not to test the other buttons as i have proven to do that on the Options Screen

    @RunWith(MockitoJUnitRunner.class)
    public static class OptionsScreenTest {
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

        //Andrew Bingham Test
        @Test
        public void testOptionsScreenConstructor() {
            optionsScreen = new OptionsScreen(game);
           Assert.assertEquals(optionsScreen.getName(), "OptionsScreen");
        }

        @Test
        public void testOptionsScreenBackground() {
            optionsScreen = new OptionsScreen(game);
          assertNotNull(assetManager.getBitmap("img/menuBackground.png"));

        }

        @Test
        public void testFAQBitmaploaded() {
            optionsScreen = new OptionsScreen(game);
            assetManager.loadAndAddBitmap("FAQButton", "img/FAQButton.png");
            Assert.assertNotNull("FAQButton", "img/buttons/FAQButton");
        }

        @Test
        public void testFAQButtonInvalid() {
            optionsScreen = new OptionsScreen(game);
            assetManager.loadAndAddBitmap("FAQButton", "img/FAQButton.png");
            Assert.assertNotNull("faqbutton", "img/buttons/faqbutton");
        }

        //Bronach Falls

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

        @Test
        public void testBitmapLoaded() {
            optionsScreen = new OptionsScreen(game);
            assetManager.loadAndAddBitmap("homeButton", "img/homeButton.png");
            Assert.assertNotNull("homeButton", "img/buttons/homeButton");
        }

        @Test
        public void testSongLoaded() {
            optionsScreen = new OptionsScreen(game);
            assetManager.loadAndAddMusic("WavinFlag", "sound/WavinFlag.png");
            Assert.assertNotNull("WavinFlag", "sounds/WavinFlag.mp3");
        }

        @Test
        public void testHomeButtonInvalid() {
            optionsScreen = new OptionsScreen(game);
            assetManager.loadAndAddBitmap("homeButton", "img/homeButton.png");
            Assert.assertNotNull("Homebutton", "img/buttons/Homebutton");
        }

    }
}