package uk.ac.qub.eeecs.gage;

import android.graphics.Bitmap;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import org.junit.Test;
//import org.hamcrest.collection.IsEmptyCollection;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.ScreenManager;
import uk.ac.qub.eeecs.gage.engine.input.Input;
import uk.ac.qub.eeecs.gage.engine.input.TouchEvent;
import uk.ac.qub.eeecs.gage.engine.io.FileIO;
import uk.ac.qub.eeecs.gage.util.BoundingBox;
import uk.ac.qub.eeecs.gage.util.CollisionDetector;
import uk.ac.qub.eeecs.gage.util.Vector2;
import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.game.DemoGame;
import uk.ac.qub.eeecs.game.MenuScreen;
import uk.ac.qub.eeecs.game.spaceDemo.Turret;
import uk.ac.qub.eeecs.game.spaceDemo.SpaceshipDemoScreen;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;
import uk.ac.qub.eeecs.game.matchAttax.cards.*;


public class CardTest{
    private Context context;
    private DemoGame game;

    @Before
    public void setUp() {

        context = InstrumentationRegistry.getTargetContext();
        game = new DemoGame();
        game.mFileIO = new FileIO(context);
    }


    @Test
    public void listIsntEmpty() throws Exception
    {
        AssetManager assetManager = new AssetManager(game);
        assertTrue(assetManager.getPlayerCards() != null && assetManager.getPlayerCards().size() > 0);
    }

}
