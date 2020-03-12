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
import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;


/**
 * The Main Menu Screen to Navigate the game from it's launch
 *
 * @version 1.0
 */
public class MainMenu extends GameScreen {

    // /////////////////////////////////////////////////////////////////////////
    // Properties
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Define the background
     */
    private GameObject mBackground;


    /**
     * Define the buttons for playing the 'games'
     */
    private PushButton mPlayButton;
    private PushButton mSettingsButton;
    private PushButton mCardsButton;

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Constructor for the menu screen
     *
     * @param game Game to which this screen belongs
     */
    public MainMenu(Game game) {
        super("MainMenu", game);

        // Load in the assets used on the main menu screen
        AssetManager assetManager = mGame.getAssetManager();

        assetManager.loadAndAddBitmap("menuBackground", "img/menuBackground.png");

        assetManager.loadAndAddBitmap("playButton", "img/buttons/playButton.png");
        assetManager.loadAndAddBitmap("playButtonPressed", "img/buttons/playButtonPressed.png");

        assetManager.loadAndAddBitmap("cardsButton", "img/buttons/cardsButton.png");
        assetManager.loadAndAddBitmap("cardsButtonPressed", "img/buttons/cardsButtonPressed.png");

        assetManager.loadAndAddBitmap("settingsButton", "img/buttons/settingsButton.png");
        assetManager.loadAndAddBitmap("settingsButtonPressed", "img/buttons/settingsButtonPressed.png");

        //loading in background music
        assetManager.loadAndAddMusic("GuitarMusic", "sound/gage/GuitarMusic.mp3");


        // Define the spacing that will be used to position the buttons
        int spacingX = (int)mDefaultLayerViewport.getWidth() / 5;
        int spacingY = (int)mDefaultLayerViewport.getHeight() / 3;

        //Create background
        mBackground = new GameObject(game.getScreenWidth()/2, game.getScreenHeight()/2, game.getScreenWidth(),
                game.getScreenHeight(), getGame().getAssetManager().getBitmap("menuBackground"), this);

        // Create the trigger buttons
        mPlayButton = new PushButton(
                spacingX * 2.5f, spacingY * 1.8f, spacingX*1.5f, spacingY*1.5f,
                "playButton", "playButtonPressed",this);
        mPlayButton.setPlaySounds(true, true);

        mCardsButton = new PushButton(
                spacingX * 1.1f, spacingY * 1.2f, spacingX, spacingY,
                "cardsButton", "cardsButtonPressed",this);
        mCardsButton.setPlaySounds(true, true);

        mSettingsButton = new PushButton(
                spacingX * 3.9f, spacingY * 1.2f, spacingX, spacingY,
                "settingsButton", "settingsButtonPressed",this);
        mSettingsButton.setPlaySounds(true, true);
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

        //playing background music
        this.getGame().getAssetManager().getMusic("GuitarMusic").play();
        // Process any touch events occurring since the update
        Input input = mGame.getInput();

        List<TouchEvent> touchEvents = input.getTouchEvents();
        if (touchEvents.size() > 0) {

            // Update each button
            mPlayButton.update(elapsedTime);
            mCardsButton.update(elapsedTime);
            mSettingsButton.update(elapsedTime);

            // Events that take place once button is activated
            if (mPlayButton.isPushTriggered()) {
                mGame.getScreenManager().removeScreen(this);
                mGame.getScreenManager().addScreen(new MatchGameScreen(mGame));
            }
            else if (mSettingsButton.isPushTriggered()){
                mGame.getScreenManager().addScreen(new OptionsScreen(mGame));
            }
//            else if (mCardsButton.isPushTriggered()){
//                mGame.getScreenManager().addScreen(new CardsScreen(mGame));
//            }
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

        // Clear the screen and draw background and the buttons
        graphics2D.clear(Color.WHITE);

        mBackground.draw(elapsedTime, graphics2D);

        mPlayButton.draw(elapsedTime, graphics2D, mDefaultLayerViewport, mDefaultScreenViewport);
        mCardsButton.draw(elapsedTime, graphics2D, mDefaultLayerViewport, mDefaultScreenViewport);
        mSettingsButton.draw(elapsedTime, graphics2D, mDefaultLayerViewport, mDefaultScreenViewport);

    }
}

