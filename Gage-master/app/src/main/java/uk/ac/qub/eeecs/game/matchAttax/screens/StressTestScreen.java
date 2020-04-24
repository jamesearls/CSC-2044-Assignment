package uk.ac.qub.eeecs.game.matchAttax.screens;


import android.graphics.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.engine.input.Input;
import uk.ac.qub.eeecs.gage.engine.input.TouchEvent;
import uk.ac.qub.eeecs.gage.ui.PushButton;
import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.gage.world.LayerViewport;
import uk.ac.qub.eeecs.gage.world.ScreenViewport;
import uk.ac.qub.eeecs.game.matchAttax.font.Font;
import uk.ac.qub.eeecs.game.matchAttax.util.FpsCounter;

// James Earls
public class StressTestScreen extends GameScreen{

    //viewports
    private ScreenViewport mScreenViewport;
    private LayerViewport mLayerViewport;

    //object for stress testing
    private ArrayList<GameObject> mStressRectangles;
    //variable to hold number of objects
    private int numRectangles = 100;

    //buttons
    private PushButton plusButton, minusButton, backButton;

    // text
    private Font font;

    //Constructor
    public StressTestScreen(Game game) {
        super("StressTestScreen", game);
        font = new Font(this);

        // load Assets
        AssetManager assetManager = mGame.getAssetManager();

        assetManager.loadAndAddBitmap("stressRectangle", "img/buttons/stressRectangle.png");
        assetManager.loadAndAddBitmap("minusButton", "img/buttons/minusButton.png");
        assetManager.loadAndAddBitmap("plusButton", "img/buttons/plusButton.png");
        assetManager.loadAndAddBitmap("leftArrow", "img/buttons/leftArrow.png");
        assetManager.loadAndAddBitmap("leftArrowPressed", "img/buttons/leftArrowPressed.png");

        // Define the spacing that will be used to position the buttons
        int spacingX = (int)mDefaultLayerViewport.getWidth() / 5;
        int spacingY = (int)mDefaultLayerViewport.getHeight() / 3;

        mStressRectangles = new ArrayList<>();

        plusButton = new PushButton(spacingX * 6f, spacingY * 9.5f, spacingX, spacingY, "plusButton", this);
        minusButton = new PushButton(spacingX * 5f, spacingY * 9.5f, spacingX, spacingY, "minusButton",  this);
        backButton = new PushButton(
                spacingX * 2f, spacingY * 9.5f, spacingX, spacingY,
                "leftArrow", "leftArrowPressed", this);
        backButton.setPlaySounds(true, true);

        // Create the screen viewport
        mScreenViewport = new ScreenViewport(0, 0, mGame.getScreenWidth(),
                mGame.getScreenHeight());

        // Create the layer viewport, taking into account the orientation
        // and aspect ratio of the screen.
        if (mScreenViewport.width > mScreenViewport.height)
            mLayerViewport = new LayerViewport(240.0f, 240.0f
                    * mScreenViewport.height / mScreenViewport.width, 240,
                    240.0f * mScreenViewport.height / mScreenViewport.width);
        else
            mLayerViewport = new LayerViewport(240.0f * mScreenViewport.height
                    / mScreenViewport.width, 240.0f, 240.0f
                    * mScreenViewport.height / mScreenViewport.width, 240);

    }

    @Override
    public void update(ElapsedTime elapsedTime) {

        // Process touch events
        Input input = mGame.getInput();

        List<TouchEvent> touchEvents = input.getTouchEvents();
        if (touchEvents.size() > 0) {
            plusButton.update(elapsedTime, mLayerViewport, mScreenViewport);
            minusButton.update(elapsedTime, mLayerViewport, mScreenViewport);
            backButton.update(elapsedTime);

            //Known issue: These push buttons don't work

            if (backButton.isPushTriggered()) {
                mGame.getScreenManager().addScreen(new OptionsScreen(mGame));
            } else if (plusButton.isPushTriggered()) {
                numRectangles = numRectangles < 10000 ? numRectangles + 500 : 10000;

            } else if (minusButton.isPushTriggered()) {
                numRectangles = numRectangles <= 500 ? 15 : numRectangles - 500;

            }
        }

        // Get width and height
        float screenWidth = mGame.getScreenWidth();
        float screenHeight = mGame.getScreenHeight();

        //Create random positons for the rectangles
        Random random = new Random();

        //clear rectangles
        mStressRectangles.clear();

        for(int i = 0; i < numRectangles; i++){
            mStressRectangles.add(
                    new GameObject(random.nextFloat() * screenWidth, random.nextFloat() * screenHeight, screenWidth - random.nextFloat() * screenWidth * 2, screenHeight - random.nextFloat() * screenHeight * 2,
                            mGame.getAssetManager().getBitmap("stressRectangle"),
                            mGame.getScreenManager().getCurrentScreen()
                    )
            );
        }
    }

    @Override
    public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D) {

        //Reset the screen
        graphics2D.clear(Color.BLACK);

        //Draw Rectangles
        for(GameObject gameObject : mStressRectangles)
            gameObject.draw(elapsedTime, graphics2D);

        //Draw buttons
        plusButton.draw(elapsedTime, graphics2D);
        minusButton.draw(elapsedTime, graphics2D);
        backButton.draw(elapsedTime, graphics2D);

        //Draw fps Counter Regardless of if it is turned on or not
        FpsCounter fpsCounter = new FpsCounter(mGame, this);
        fpsCounter.drawFPS(elapsedTime, graphics2D);
        font.drawText("Number of Rectangles: " + numRectangles, "Hemi Head Bold Italic", 72, "Black", (getGame().getScreenWidth() / 2), getGame().getScreenHeight() / 2, elapsedTime, graphics2D);

    }
}
