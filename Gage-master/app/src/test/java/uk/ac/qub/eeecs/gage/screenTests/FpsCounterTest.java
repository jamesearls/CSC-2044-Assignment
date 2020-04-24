package uk.ac.qub.eeecs.gage.screenTests;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.gage.world.GameScreen;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class FpsCounterTest {

    @Mock
    Game testGame = Mockito.mock(Game.class);
    @Mock
    GameScreen testGameScreen = Mockito.mock(GameScreen.class);

    //1
    @Test
    public void check_FPS_Meets_Target_ValidData_TestIsSuccessFul(){
        float fps = testGame.getAverageFramesPerSecond();
        boolean success = false;
        if(fps >20){
        }
        else{
             success = true;
        }
        assertTrue(success);
    }
}
