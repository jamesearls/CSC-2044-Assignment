package uk.ac.qub.eeecs.gage;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.content.Context;

import java.util.List;

import uk.ac.qub.eeecs.gage.engine.io.FileIO;
import uk.ac.qub.eeecs.game.DemoGame;
import uk.ac.qub.eeecs.game.matchAttax.cards.ManagerCard;
import uk.ac.qub.eeecs.game.matchAttax.screens.MatchGameScreen;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)

public class ManagerCardTest
{
    private Context context;
    MatchGameScreen matchScreen;
    private DemoGame game;

    @Before
    public void setUp() throws Exception
    {
        context = InstrumentationRegistry.getTargetContext();
        game = new DemoGame();
        game.getScreenManager().addScreen(matchScreen);
        FileIO fileIo = new FileIO(context);
        matchScreen = new MatchGameScreen(game);
    }

    //1st Test
    //Check manager list exists & check it isn't empty
    @Test
    public void CheckManagerCardPresent() throws Exception
    {
        matchScreen.addCards("txt/assets/Managers.json");
        assertTrue(matchScreen.getManagerCardList().size() > 0 && matchScreen.getManagerCardList() != null);

    }

    //2nd Test
    //Checking the manager surname is present
    public void CheckManagerSurname() throws Exception
    {
        matchScreen.addCards("txt/assets/Managers.json");
        List<ManagerCard> testerList = matchScreen.getManagerCardList();
        for (int i = 0; i < testerList.size(); i++)
        {
          ManagerCard testerCard = testerList.get(i);
          assertTrue(testerCard.getSurname() != null);
          assertNotNull(testerCard.getSurname());
          }
        }

    }



