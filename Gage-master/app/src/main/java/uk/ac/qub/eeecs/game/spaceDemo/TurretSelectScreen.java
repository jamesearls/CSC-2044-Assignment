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
 * A menu screen to select the turret sprite
 *
 * @version 1.0
 */
public class TurretSelectScreen extends GameScreen {

    // /////////////////////////////////////////////////////////////////////////
    // Properties
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Define the buttons for selecting the 'turret'
     */
    private PushButton mTurret1;
    private PushButton mTurret2;
    private PushButton mTurret3;
    private PushButton mTurret4;

    /**
     * Define string to pass spaceship choice over
     */
    private String spaceshipChoice;

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////

    /**
     * create a simple menu screen
     *
     * @param game Game to which this screen belongs
     */
    public TurretSelectScreen(Game game, String AiSpaceship){
        super("TurretSelectScreen", game);

            spaceshipChoice = AiSpaceship;

        // Load in the bitmaps used on the main menu screen
        AssetManager assetManager = mGame.getAssetManager();
        assetManager.loadAndAddBitmap("Turret1", "img/Turret1.png");
        assetManager.loadAndAddBitmap("Turret2", "img/Turret2.png");
        assetManager.loadAndAddBitmap("Turret3", "img/Turret3.png");
        assetManager.loadAndAddBitmap("Turret4", "img/Turret4.png");

        // Define the spacing that will be used to position the buttons
        int spacingX = (int)mDefaultLayerViewport.getWidth() / 5;
        int spacingY = (int)mDefaultLayerViewport.getHeight() / 3;

        // Create the trigger buttons
        mTurret1 = new PushButton(
                spacingX * 0.50f, spacingY * 1.5f, spacingX, spacingY,
                "Turret1", "Turret1",this);
        mTurret1.setPlaySounds(true, true);

        mTurret2 = new PushButton(
                spacingX * 1.83f, spacingY * 1.5f, spacingX, spacingY,
                "Turret2", "Turret2",this);
        mTurret2.setPlaySounds(true, true);

        mTurret3 = new PushButton(
                spacingX * 3.17f, spacingY * 1.5f, spacingX, spacingY,
                "Turret3", "Turret3",this);
        mTurret3.setPlaySounds(true,true);

        mTurret4 = new PushButton(
                spacingX * 4.50f, spacingY * 1.5f, spacingX, spacingY,
                "Turret4", "Turret4",this);
        mTurret4.setPlaySounds(true, true);
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
    public void update (ElapsedTime elapsedTime){

        // Process any touch events occuring since the update
        Input input = mGame.getInput();

        List<TouchEvent> touchEvents = input.getTouchEvents();
        if (touchEvents.size() > 0) {

            //Update each button and transition if needed
            mTurret1.update(elapsedTime);
            mTurret2.update(elapsedTime);
            mTurret3.update(elapsedTime);
            mTurret4.update(elapsedTime);


            if (mTurret1.isPushTriggered())
                mGame.getScreenManager().addScreen(new SpaceshipDemoScreen(mGame, spaceshipChoice, "Turret1"));
            else if (mTurret2.isPushTriggered())
                mGame.getScreenManager().addScreen(new SpaceshipDemoScreen(mGame, spaceshipChoice, "Turret2"));
            else if (mTurret3.isPushTriggered())
                mGame.getScreenManager().addScreen(new SpaceshipDemoScreen(mGame, spaceshipChoice, "Turret3"));
            else if (mTurret4.isPushTriggered())
                mGame.getScreenManager().addScreen(new SpaceshipDemoScreen(mGame, spaceshipChoice, "Turret4"));
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

        mTurret1.draw(elapsedTime, graphics2D, mDefaultLayerViewport, mDefaultScreenViewport);
        mTurret2.draw(elapsedTime, graphics2D, mDefaultLayerViewport, mDefaultScreenViewport);
        mTurret3.draw(elapsedTime, graphics2D, mDefaultLayerViewport, mDefaultScreenViewport);
        mTurret4.draw(elapsedTime, graphics2D, mDefaultLayerViewport, mDefaultScreenViewport);
    }

}
