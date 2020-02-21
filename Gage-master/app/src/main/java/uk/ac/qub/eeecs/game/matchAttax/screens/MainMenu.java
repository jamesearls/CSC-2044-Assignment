package uk.ac.qub.eeecs.game.matchAttax.screens;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.engine.input.Input;
import uk.ac.qub.eeecs.gage.engine.input.TouchEvent;
import uk.ac.qub.eeecs.gage.ui.PushButton;
import uk.ac.qub.eeecs.gage.util.ViewportHelper;
import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;

/**
 * An exceedingly basic menu screen with a couple of touch buttons
 *
 * @version 1.0
 */
public class MainMenu extends GameScreen {

    // /////////////////////////////////////////////////////////////////////////
    // Properties
    // /////////////////////////////////////////////////////////////////////////


    /**
     * Define the buttons for playing the 'games'
     */
    private GameObject mBackground;

    private PushButton mPlayButton;
    private PushButton mSettingsButton;
    private PushButton mCardsButton;


    // Variables to hold height and width of screen
    int screenHeight = mGame.getScreenHeight();
    int screenWidth = mGame.getScreenWidth();

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Create a simple menu screen
     *
     * @param game Game to which this screen belongs
     */
    public MainMenu(Game game) {
        super("MainMenu", game);

        // Load in the bitmaps used on the main menu screen
        AssetManager assetManager = mGame.getAssetManager();
        //assetManager.loadAssets("txt/assets/MatchAttaxAssets.JSON");

        //assetManager.loadAndAddBitmap("menuBackground", "img/menuBackground.png");

       assetManager.loadAndAddBitmap("playButton", "img/buttons/playButton.png");
       //assetManager.loadAndAddBitmap("playButtonPressed", "img/buttons/playButtonPressed.png");



        // assetManager.loadAndAddBitmap("CardsButton", "img/buttons/CardsButton.bmp");
       // assetManager.loadAndAddBitmap("SettingsButton", "img/buttons/SettingsButton.bmp");


        // Define the spacing that will be used to position the buttons
        int spacingX = (int)mDefaultLayerViewport.getWidth() / 5;
        int spacingY = (int)mDefaultLayerViewport.getHeight() / 3;

        //Create background
       // mBackground = new GameObject(screenWidth / 2, screenHeight / 2, screenWidth,
        //        screenHeight, getGame().getAssetManager().getBitmap("menuBackground"), this);

        // Create the trigger buttons
       // mPlayButton = new PushButton(
         //       spacingX * 2f, spacingY * 1.4f, spacingX, spacingY,
           //     "PlayButton", "PlayButtonPressed",this);
        //mPlayButton.setPlaySounds(true, true);
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
         //  mPlayButton.update(elapsedTime);


//            if (playButton.isPushTriggered())
//               mGame.getScreenManager().addScreen(new SpaceshipSelectScreen(mGame));
//            else if (mPlatformDemoButton.isPushTriggered())
//                mGame.getScreenManager().addScreen(new PlatformDemoScreen(mGame));
        }
    }

    /**
     * Define a internal paint instance - declared externally to avoid object
     * creation costs.
     */
    private Paint textPaint = new Paint();

    /**
     * Draw the menu screen
     *
     * @param elapsedTime Elapsed time information
     * @param graphics2D  Graphics instance
     */
    @Override
    public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D) {

        // Clear the screen and draw background and the buttons
        graphics2D.clear(Color.WHITE);
       //mBackground.draw(elapsedTime, graphics2D);


       // mPlayButton.draw(elapsedTime, graphics2D, mDefaultLayerViewport, mDefaultScreenViewport);


        // Determine font properties - created so a total of twenty
        // lines of text (0.05) could fit into the screen, aligned
        // along the x axis and drawn in black.

        float textSize =
                ViewportHelper.convertXDistanceFromLayerToScreen(
                        mDefaultLayerViewport.getHeight() * 0.05f,
                        mDefaultLayerViewport, mDefaultScreenViewport);
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.BLACK);

        // Draw text displaying the name of this screen and some instructions

        graphics2D.drawText("MATCH ATTAX",
                mDefaultScreenViewport.centerX(),
                mDefaultScreenViewport.top + 2.5f * textSize, textPaint);

    }
}

