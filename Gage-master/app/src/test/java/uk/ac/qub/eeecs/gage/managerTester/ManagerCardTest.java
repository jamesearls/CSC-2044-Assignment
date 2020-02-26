//Add into next sprint
/*
import android.content.Context;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import uk.ac.qub.eeecs.gage.engine.io.FileIO;
import uk.ac.qub.eeecs.game.DemoGame;
import uk.ac.qub.eeecs.game.matchAttax.screens.MatchGameScreen;

import static junit.framework.Assert.assertTrue;


//40238376
@RunWith(AndroidUnit4.class)
public class ManagerCardTest {

    private Context context;
    private DemoGame demo;
    MatchGameScreen matchScreen;

    @Before
    public void setUp() throws Exception{
        context = InstrumentationRegistry.getTargetContext();
        game = new DemoGame();
        FileIO fileIO = new File(context);
        matchScreen = new matchGameScreen(game);
        game.getScreenManager().addScreen(matchScreen);
    }

    @Test
    public void listIsntEmpty() throws Exception{
        matchScreen.addCards("txt/assets/Managers.json`");

        assertTrue(matchScreen.getManagerCardsList() != null
                && matchScreen.getManagerCardsList().size() > 0);
    }

}
*/