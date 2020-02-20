package uk.ac.qub.eeecs.gage;

import android.content.Context;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.util.CollisionDetector;
import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.game.platformDemo.Platform;
import uk.ac.qub.eeecs.game.platformDemo.PlatformDemoScreen;
import uk.ac.qub.eeecs.game.platformDemo.Player;
import uk.ac.qub.eeecs.game.spaceDemo.Seeker;
import uk.ac.qub.eeecs.game.spaceDemo.SpaceshipDemoScreen;

@RunWith(MockitoJUnitRunner.class)
public class SeekerTest {
    @Mock
    Random random = new Random();
    SpaceshipDemoScreen gameScreen = Mockito.mock(SpaceshipDemoScreen.class);
    Seeker aSeeker = new Seeker(0,0, gameScreen,"img/Spaceship2.png");
}
