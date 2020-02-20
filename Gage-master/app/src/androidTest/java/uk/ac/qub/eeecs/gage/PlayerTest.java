package uk.ac.qub.eeecs.gage;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import uk.ac.qub.eeecs.game.matchAttax.MatchAttaxGame;

@RunWith(AndroidJUnit4.class)
public class PlayerTest {

    private Context context;
    private MatchAttaxGame game;

    @Before
    public void setup(){
        context = InstrumentationRegistry.getTargetContext();

        game = new MatchAttaxGame();
    }
}
