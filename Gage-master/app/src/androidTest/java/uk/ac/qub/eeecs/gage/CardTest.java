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
import uk.ac.qub.eeecs.game.CardDemo.CardDemoScreen;
import uk.ac.qub.eeecs.game.DemoGame;
import uk.ac.qub.eeecs.game.MenuScreen;
import uk.ac.qub.eeecs.game.matchAttax.MatchAttaxGame;
import uk.ac.qub.eeecs.game.matchAttax.screens.MatchGameScreen;
import uk.ac.qub.eeecs.game.spaceDemo.Turret;
import uk.ac.qub.eeecs.game.spaceDemo.SpaceshipDemoScreen;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;
import uk.ac.qub.eeecs.game.matchAttax.cards.*;

@RunWith(AndroidJUnit4.class)
public class CardTest{
    private Context context;
    private DemoGame game;
    MatchGameScreen matchScreen;

    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getTargetContext();
        game = new DemoGame();
        FileIO fileIO = new FileIO(context);
        matchScreen = new MatchGameScreen(game);
        game.getScreenManager().addScreen(matchScreen);
    }


    @Test
    public void listIsntEmpty() throws Exception
    {
        matchScreen.addCards("txt/assets/Players.json");
        assertTrue(matchScreen.getPlayerCardList() != null && matchScreen.getPlayerCardList().size() > 0);
    }

}
