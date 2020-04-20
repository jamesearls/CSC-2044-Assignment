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

}