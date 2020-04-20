package uk.ac.qub.eeecs.game.matchAttax.screens;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;

import java.util.List;

import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.ScreenManager;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.engine.input.Input;
import uk.ac.qub.eeecs.gage.engine.input.TouchEvent;
import uk.ac.qub.eeecs.gage.ui.PushButton;
import uk.ac.qub.eeecs.gage.world.GameScreen;

public class FAQScreen extends GameScreen {


    private Typeface font;
    private float screenWidth = getGame().getScreenWidth(),
            screenHeight = getGame().getScreenHeight(),
    symbolXOffset = screenWidth * 0.125f,
    symbolYOffset = screenHeight * 0.125f;
    private PushButton homeButton2, optionsButton2;
    private double timeAccumulator = 0;
    private AssetManager assetStore = getGame().getAssetManager();
    private ScreenManager screenManager = getGame().getScreenManager();
    private TextPaint titlePaint, buttonPaint;
    private Canvas buttonCanvas;
    private Bitmap background;
    private Object IGraphics2D;
    private Canvas canvas;


    /**
     * Create a new game screen associated with the specified game instance
     *

     * @param game Game instance to which the game screen belongs
     */
    public FAQScreen ( Game game) {
        super( "FAQScreen", game);


        //load background
        assetStore.loadAndAddBitmap("menuBackground", "img/menuBackground.png");
        background = assetStore.getBitmap("menuBackground");

        //load buttons
        assetStore.loadAndAddBitmap("homeButton2", "img/buttons/homeButton.png");
        assetStore.loadAndAddBitmap("homeButton2Pressed", "img/buttons/homeButton2Pressed.png");
        assetStore.loadAndAddBitmap("optionsButton", "img/buttons/optionsButton.png");
        assetStore.loadAndAddBitmap("optionsButtonPressed", "img/buttons/optionsButtonPressed.png");

        int spacingX = (int)mDefaultLayerViewport.getWidth() / 5;
        int spacingY = (int)mDefaultLayerViewport.getHeight() / 3;

        homeButton2 = new PushButton(
                spacingX * 0.23f, spacingY * 2.6f, spacingX*0.5f, spacingY*0.5f,
                "homeButton2", "homeButton2Pressed",this);
        homeButton2.setPlaySounds(false, false);

        optionsButton2 = new PushButton(
                spacingX * 0.23f, spacingY * 2.2f, spacingX*0.5f, spacingY*0.5f,
                "optionsButton2", "optionsButton2Pressed",this);
        optionsButton2.setPlaySounds(false, false);

        /* Set title text size and font */
        titlePaint = new TextPaint();
        titlePaint.setTextSize(screenWidth * 0.08f);
        titlePaint.setTypeface(font);
        /* Set button text size and font */
        buttonPaint = new TextPaint();
        buttonPaint.setTextSize(screenWidth * 0.04f);
        buttonPaint.setTypeface(font);

    }




    @Override
    public void update(ElapsedTime elapsedTime) {

        Input input = mGame.getInput();

        List<TouchEvent> touchEvents = input.getTouchEvents();
        if (touchEvents.size() > 0) {


            timeAccumulator += elapsedTime.stepTime;
            while (timeAccumulator > 0.1) {
                timeAccumulator -= 0.1;
            }

            homeButton2.update(elapsedTime);
            optionsButton2.update(elapsedTime);
        }
        if (homeButton2.isPushTriggered()) {
            mGame.getScreenManager().addScreen(new MainMenu(mGame));
        }
        if (optionsButton2.isPushTriggered()) {
            mGame.getScreenManager().addScreen(new OptionsScreen(mGame));
        }
    }

    @Override
    public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D) {
        graphics2D.drawBitmap(
                background,
                null,
                new Rect(0, 0, (int) screenWidth, (int) screenHeight),
                new Paint()
        );
        homeButton2.draw(elapsedTime, graphics2D, mDefaultLayerViewport, mDefaultScreenViewport);
        optionsButton2.draw(elapsedTime, graphics2D, mDefaultLayerViewport, mDefaultScreenViewport);

//@stack overflow website reference
       Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
       canvas.drawPaint(paint);

       paint.setColor(Color.BLACK);
       paint.setTextSize(20);
       canvas.drawText("How to play the game", 5, 3, paint);
       canvas.drawText("Once you hit play the user will be brought to the gameboard where they face an AI  ", 4,3 , paint);
       canvas.drawText("Once you play your turn the AI will play theirs", 3,5 , paint);

       canvas.drawText("Did the game take long to create", 2, 3, paint);
       canvas.drawText("we started the game in january and finished in April so yes it took a while", 1, 3, paint);



    }
}
