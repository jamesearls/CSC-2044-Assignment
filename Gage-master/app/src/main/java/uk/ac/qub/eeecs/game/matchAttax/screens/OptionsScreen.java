
package uk.ac.qub.eeecs.game.matchAttax.screens;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;

import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.ScreenManager;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.ui.PushButton;
import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;

public class OptionsScreen extends  GameScreen {
    private float screenWidth = getGame().getScreenWidth(),
            screenHeight = getGame().getScreenHeight(),
            symbolXOffset = screenWidth * 0.125f,
            symbolYOffset = screenHeight * 0.125f;
    private double timeAccumulator = 0;
    private AssetManager assetStore = getGame().getAssetManager();
    private ScreenManager screenManager = getGame().getScreenManager();
    private Bitmap background, symbol, muteBitmap, unmuteBitmap, highBitmap, normalBitmap, lowBitmap, returnBitmap;
    private Typeface font;
    private GameObject backgroundObject;

    private TextPaint titlePaint, buttonPaint;
    private Rect muteRect, unmuteRect, highRect, normalRect, lowRect, returnRect;
    private PushButton muteButton, unmuteButton, highButton, normalButton, lowButton, returnButton;
    private Canvas buttonCanvas;

    public OptionsScreen(Game game) {
        super("OptionsScreen", game);

        /* Load font */
        assetStore.loadAndAddTypeface("font", "fonts/audiowide.ttf");
        font = assetStore.getTypeface("font");
        /* Load music */
        assetStore.loadAndAddMusic("ChelseaDagger", "music/ChelseaDagger.mp3");
        playMusic();

        /* Set title text size and font */
        titlePaint = new TextPaint();
        titlePaint.setTextSize(screenWidth * 0.08f);
        titlePaint.setTypeface(font);
        /* Set button text size and font */
        buttonPaint = new TextPaint();
        buttonPaint.setTextSize(screenWidth * 0.04f);
        buttonPaint.setTypeface(font);

        makeHighButton();
        makeNormalButton();
        makeLowButton();
    }

    /* Play music */
    public void playMusic() {
        if (mGame.getMusicActive() == true) {
            assetStore.getMusic("ChelseaDagger").play();
        }
    }


    /* Button for raising music */
    public void makeHighButton() {
        buttonPaint.getTextBounds("HIGH", 0, "HIGH".length(), highRect = new Rect());
        highBitmap = Bitmap.createBitmap(highRect.width(), highRect.height(), Bitmap.Config.ARGB_8888);
        buttonCanvas = new Canvas(highBitmap);
        buttonCanvas.drawText("HIGH", 0, highRect.height(), buttonPaint);
        assetStore.add("high", highBitmap);
        highButton = new PushButton(
                screenWidth * 0.50f, titlePaint.getTextSize() + buttonPaint.getTextSize() * 3 + screenHeight * 0.34f,
                highRect.width(), highRect.height(), "high", this);
    }

    /* Button for resetting music */
    public void makeNormalButton() {
        buttonPaint.getTextBounds("NORMAL", 0, "NORMAL".length(), normalRect = new Rect());
        normalBitmap = Bitmap.createBitmap(normalRect.width(), normalRect.height(), Bitmap.Config.ARGB_8888);
        buttonCanvas = new Canvas(normalBitmap);
        buttonCanvas.drawText("NORMAL", 0, normalRect.height(), buttonPaint);
        assetStore.add("normal", normalBitmap);
        normalButton = new PushButton(
                screenWidth * 0.50f, titlePaint.getTextSize() + buttonPaint.getTextSize() * 3 + screenHeight * 0.42f,
                normalRect.width(), normalRect.height(), "normal", this);
    }

    /* Button for lowering music */
    public void makeLowButton() {
        buttonPaint.getTextBounds("LOW", 0, "LOW".length(), lowRect = new Rect());
        lowBitmap = Bitmap.createBitmap(lowRect.width(), lowRect.height(), Bitmap.Config.ARGB_8888);
        buttonCanvas = new Canvas(lowBitmap);
        buttonCanvas.drawText("LOW", 0, lowRect.height(), buttonPaint);
        assetStore.add("low", lowBitmap);
        lowButton = new PushButton(
                screenWidth * 0.50f, titlePaint.getTextSize() + buttonPaint.getTextSize() * 3 + screenHeight * 0.50f,
                lowRect.width(), lowRect.height(), "low", this);
    }

    public void onButtonPressed() {

        if(highButton.isPushTriggered()) {
            mGame.setMusicActive(true);
            mGame.setMusicVolume(mGame.getMusicVolume() + 0.1f);
            assetStore.getMusic("ChelseaDagger").setVolume(mGame.getMusicVolume());
        }
        if(normalButton.isPushTriggered()) {
            mGame.setMusicActive(true);
            mGame.setMusicVolume(0.5f);
            assetStore.getMusic("ChelseaDagger").setVolume(mGame.getMusicVolume());
        }
        if(lowButton.isPushTriggered()) {
            mGame.setMusicActive(true);
            mGame.setMusicVolume(mGame.getMusicVolume() - 0.1f);
            assetStore.getMusic("ChelseaDagger").setVolume(mGame.getMusicVolume());
        }
    }





    public Paint getTitlePaint() {
        return titlePaint;
    }
    public void setTitlePaint(TextPaint titlePaint) {
        this.titlePaint = titlePaint;
    }
    public Paint getButtonPaint() {
        return buttonPaint;
    }
    public void setButtonPaint(TextPaint buttonPaint) {
        this.buttonPaint = buttonPaint;
    }

    public PushButton getHighButton() { return highButton; }
    public void setHighButton(PushButton button) { this.highButton = button; }
    public PushButton getNormalButton() { return normalButton; }
    public void setNormalButton(PushButton button) { this.normalButton = button; }
    public PushButton getLowButton() { return lowButton; }
    public void setLowButton(PushButton button) { this.lowButton = button; }

    @Override
    public void update(ElapsedTime elapsedTime) {
        timeAccumulator += elapsedTime.stepTime;
        while(timeAccumulator > 0.1) {
            timeAccumulator -= 0.1;
        }


        highButton.update(elapsedTime);
        normalButton.update(elapsedTime);
        lowButton.update(elapsedTime);
        onButtonPressed();
    }



    @Override
    public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D) {
        graphics2D.drawBitmap(
                background,
                null,
                new Rect(0, 0, (int) screenWidth, (int) screenHeight),
                null
        );


        highButton.draw(elapsedTime, graphics2D);
        normalButton.draw(elapsedTime, graphics2D);
        lowButton.draw(elapsedTime, graphics2D);



    }

}
