package uk.ac.qub.eeecs.gage;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.gage.engine.ScreenManager;
import uk.ac.qub.eeecs.gage.engine.io.FileIO;
import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.game.DemoGame;
import uk.ac.qub.eeecs.game.matchAttax.MatchAttaxGame;
import uk.ac.qub.eeecs.game.matchAttax.screens.MainMenu;
import uk.ac.qub.eeecs.game.matchAttax.screens.OptionsScreen;
import uk.ac.qub.eeecs.game.matchAttax.screens.StressTestScreen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

//James Earls
@RunWith(AndroidJUnit4.class)
public class StressTestScreenTest {

    private Context context;
    private MatchAttaxGame testGame;


    @Before
    public void setUp() {

        context = InstrumentationRegistry.getTargetContext();
        FileIO fileIO = new FileIO(context);
        testGame = new MatchAttaxGame();
        testGame.mFileIO = new FileIO(context);
        testGame.mAssetManager = new AssetManager(testGame);
        testGame.mScreenManager = new ScreenManager(testGame);
        StressTestScreen stressTestScreen = new StressTestScreen(testGame);
        OptionsScreen optionsScreen = new OptionsScreen(testGame);

    }
    @Test
    public void loadAndAddBitmap_ValidData_For_StressTestScreen_TestIsSuccessful() {
        AssetManager testAssetManager = new AssetManager(new MatchAttaxGame());
        assertNotNull(testAssetManager.loadAndAddBitmap("stressRectangle","img/buttons/stressRectangle.png"));
        assertNotNull(testAssetManager.loadAndAddBitmap("plusButton", "img/buttons/plusButton.png" ));
        assertNotNull(testAssetManager.loadAndAddBitmap("minusButton", "img/buttons/minusButton.png"));
        assertNotNull(testAssetManager.loadAndAddBitmap("minusButton", "img/buttons/leftArrow.png"));
        assertNotNull(testAssetManager.loadAndAddBitmap("leftArrow", "img/buttons/leftArrow.png"));
        assertNotNull(testAssetManager.loadAndAddBitmap("leftArrowPressed", "img/buttons/leftArrowPressed.png"));
    }

}
