package uk.ac.qub.eeecs.game.MatchAttax;

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
import uk.ac.qub.eeecs.game.MenuScreen;

/**
 * Starter class for Card game stories
 *
 * @version 1.0
 */
public class CardDemoScreen extends GameScreen {

    // /////////////////////////////////////////////////////////////////////////
    // Properties
    // /////////////////////////////////////////////////////////////////////////

    // Define a card to be displayed
    private Card card;

    // Define the button
    private PushButton endTurn;

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Create the Card game screen
     *
     * @param game Game to which this screen belongs
     */
    public CardDemoScreen(Game game) {
        super("CardScreen", game);

        // Load in the bitmaps for the button
        AssetManager assetManager = mGame.getAssetManager();
        mGame.getAssetManager().loadAssets("txt/assets/CardDemoScreenAssets.JSON");
        assetManager.loadAndAddBitmap("BlueButton", "img/BlueButton.png");
        assetManager.loadAndAddBitmap("BlueButtonActive", "img/BlueButtonActive.png");

        // Define the spacing that will be used to position the button
        int spacingX = (int)mDefaultLayerViewport.getWidth() / 5;
        int spacingY = (int)mDefaultLayerViewport.getHeight() / 3;

        // Create the trigger buttons
        endTurn = new PushButton(
                spacingX * 8f, spacingY * 6f, spacingX, spacingY,
                "BlueButton", "BlueButtonActive",this);
        endTurn.setPlaySounds(true, true);

        // Load the various images used by the cards

        // Create a new, centered card
        card = new Card(mDefaultLayerViewport.x, mDefaultLayerViewport.y, this);
    }

    // /////////////////////////////////////////////////////////////////////////
    // Methods
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Update the card demo screen
     *
     * @param elapsedTime Elapsed time information
     */
    @Override
    public void update(ElapsedTime elapsedTime) {
        // Process any touch events occurring since the last update
        Input input = mGame.getInput();

        // Update the card
        card.angularVelocity = 0f;

        card.update(elapsedTime);

        // Update button

        List<TouchEvent> touchEvents = input.getTouchEvents();
        if (touchEvents.size() > 0) {

            // Update each button and transition if needed
            endTurn.update(elapsedTime);

            if (endTurn.isPushTriggered())
                mGame.getScreenManager().addScreen(new MenuScreen(mGame));
        }
    }

    /**
     * Draw the card demo screen
     *
     * @param elapsedTime Elapsed time information
     * @param graphics2D  Graphics instance
     */
    @Override
    public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D) {
        graphics2D.clear(Color.WHITE);

        // Draw the card
        card.draw(elapsedTime, graphics2D,
                mDefaultLayerViewport, mDefaultScreenViewport);

        // Draw the button
        endTurn.draw(elapsedTime, graphics2D,
                mDefaultLayerViewport, mDefaultScreenViewport);

    }


}
