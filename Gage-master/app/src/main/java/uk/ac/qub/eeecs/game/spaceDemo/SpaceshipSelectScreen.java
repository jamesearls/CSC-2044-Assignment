package uk.ac.qub.eeecs.game.spaceDemo;

import android.graphics.Color;

import java.util.List;

import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.engine.input.Input;
import uk.ac.qub.eeecs.gage.engine.input.TouchEvent;
import uk.ac.qub.eeecs.gage.ui.PushButton;
import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.game.MatchAttax.CardDemoScreen;
import uk.ac.qub.eeecs.game.miscDemos.DemoMenuScreen;
import uk.ac.qub.eeecs.game.platformDemo.PlatformDemoScreen;
import uk.ac.qub.eeecs.game.spaceDemo.SpaceshipDemoScreen;

/**
 * A menu screen to select the seeker sprite
 *
 * @version 1.0
 */
public class SpaceshipSelectScreen extends GameScreen {

    // /////////////////////////////////////////////////////////////////////////
    // Properties
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Define the buttons for selecting the 'seeker'
     */
    private PushButton mSpaceship2;
    private PushButton mSpaceship3;
    private PushButton mSpaceship4;
    private PushButton mSpaceship5;

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Create a simple menu screen
     *
     * @param game Game to which this screen belongs
     */
    public SpaceshipSelectScreen(Game game) {
        super("MenuScreen", game);

        // Load in the bitmaps used on the spaceship select menu screen
        AssetManager assetManager = mGame.getAssetManager();
        assetManager.loadAndAddBitmap("Spaceship2", "img/Spaceship2.png");
        assetManager.loadAndAddBitmap("Spaceship3", "img/Spaceship3.png");
        assetManager.loadAndAddBitmap("Spaceship4", "img/Spaceship4.png");
        assetManager.loadAndAddBitmap("Spaceship5", "img/Spaceship5.png");

        // Define the spacing that will be used to position the buttons
        int spacingX = (int)mDefaultLayerViewport.getWidth() / 5;
        int spacingY = (int)mDefaultLayerViewport.getHeight() / 3;

        // Create the trigger buttons
        mSpaceship2 = new PushButton(
                spacingX * 0.50f, spacingY * 1.5f, spacingX, spacingY,
                "Spaceship2", "Spaceship2",this);
        mSpaceship2.setPlaySounds(true, true);
        mSpaceship3 = new PushButton(
                spacingX * 1.83f, spacingY * 1.5f, spacingX, spacingY,
                "Spaceship3", "Spaceship3", this);
        mSpaceship3.setPlaySounds(true, true);
        mSpaceship4 = new PushButton(
                spacingX * 3.17f, spacingY * 1.5f, spacingX, spacingY,
                "Spaceship4", "Spaceship4", this);
        mSpaceship4.setPlaySounds(true, true);
        mSpaceship5 = new PushButton(
                spacingX * 4.50f, spacingY * 1.5f, spacingX, spacingY,
                "Spaceship5", "Spaceship5", this);
        mSpaceship5.setPlaySounds(true, true);
    }

    // /////////////////////////////////////////////////////////////////////////
    // Methods
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Update the menu screen
     *
     * @param elapsedTime Elapsed time information
     */
    @Override
    public void update(ElapsedTime elapsedTime) {

        // Process any touch events occurring since the update
        Input input = mGame.getInput();

        List<TouchEvent> touchEvents = input.getTouchEvents();
        if (touchEvents.size() > 0) {

            // Update each button and transition if needed
            mSpaceship2.update(elapsedTime);
            mSpaceship3.update(elapsedTime);
            mSpaceship4.update(elapsedTime);
            mSpaceship5.update(elapsedTime);

            if (mSpaceship2.isPushTriggered())
                mGame.getScreenManager().addScreen(new TurretSelectScreen(mGame, "Spaceship2"));
            else if (mSpaceship3.isPushTriggered())
                mGame.getScreenManager().addScreen(new TurretSelectScreen(mGame, "Spaceship3"));
            else if (mSpaceship4.isPushTriggered())
                mGame.getScreenManager().addScreen(new TurretSelectScreen(mGame, "Spaceship4"));
            else if (mSpaceship5.isPushTriggered())
                mGame.getScreenManager().addScreen(new TurretSelectScreen(mGame, "Spaceship5"));
        }
    }

    /**
     * Draw the menu screen
     *
     * @param elapsedTime Elapsed time information
     * @param graphics2D  Graphics instance
     */
    @Override
    public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D) {

        // Clear the screen and draw the buttons
        graphics2D.clear(Color.WHITE);

        mSpaceship2.draw(elapsedTime, graphics2D, mDefaultLayerViewport, mDefaultScreenViewport);
        mSpaceship3.draw(elapsedTime, graphics2D, mDefaultLayerViewport, mDefaultScreenViewport);
        mSpaceship4.draw(elapsedTime, graphics2D, mDefaultLayerViewport, mDefaultScreenViewport);
        mSpaceship5.draw(elapsedTime, graphics2D, mDefaultLayerViewport, mDefaultScreenViewport);
    }
}
