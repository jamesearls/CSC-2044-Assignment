package uk.ac.qub.eeecs.game.matchAttax.cards;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.IOException;

import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.engine.input.Input;
import uk.ac.qub.eeecs.gage.engine.input.TouchEvent;
import uk.ac.qub.eeecs.gage.util.BoundingBox;
import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.gage.world.LayerViewport;
import uk.ac.qub.eeecs.gage.world.ScreenViewport;

public abstract class Card extends GameObject {

    public static final int DEFAULT_CARD_HEIGHT = 210;
    public static final int DEFAULT_CARD_WIDTH = 140;

    public static final float DEFAULT_CARD_X = 0.0f;
    public static final float DEFAULT_CARD_Y = 0.0f;

    private String firstName;
    private String surname;
    private double overallValue;

    private String cardPortraitPath;
    private boolean isBeingDragged;
    private boolean isPlaced;
    private Bitmap bitmap;
    private GameObject artwork;

    public Card(GameScreen gameScreen, double overallValue, String firstName, String surname, String cardPortraitPath){
        super(gameScreen);
        super.setPosition(DEFAULT_CARD_X, DEFAULT_CARD_Y);
        super.getBound().halfHeight = DEFAULT_CARD_HEIGHT * 0.5f;
        super.getBound().halfWidth = DEFAULT_CARD_WIDTH * 0.5f;

        this.overallValue = overallValue;
        this.firstName = firstName;
        this.surname = surname;
        this.cardPortraitPath = cardPortraitPath;
        this.isBeingDragged = false;

        this.setPosition(DEFAULT_CARD_X, DEFAULT_CARD_Y);
        this.setHeight(DEFAULT_CARD_HEIGHT);
        this.setWidth(DEFAULT_CARD_WIDTH);

        bitmap = getBitmap(this.cardPortraitPath);

        this.artwork = new GameObject(
                this.getBound().getLeft() + this.getBound().getWidth(),
                this.getBound().getBottom() + this.getBound().getHeight(),
                this.getBound().getWidth(),
                this.getBound().getHeight(),
                bitmap,
                gameScreen);
    }

    public Bitmap getBitmap(String filePath){
        try{
            return getGameScreen().getGame().getFileIO().loadBitmap(filePath, null);
        }
        catch(IOException ex){
            Log.e("CardStore", this.cardPortraitPath + " failed to load.");
        }
        return null;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getOverallValue() {
        return overallValue;
    }

    public void setOverallValue(int overallValue) {
        this.overallValue = overallValue;
    }

    public String getCardPortraitPath() {
        return cardPortraitPath;
    }

    public void setCardPortraitPath(String cardPortraitPath) { this.cardPortraitPath = cardPortraitPath; }

    public boolean getBeingDragged() {return  isBeingDragged;}

    public void setBeingDragged(boolean dragged){ this.isBeingDragged = dragged;}

    public boolean getPlaced() {return  isPlaced;}

    public void setPlaced(boolean placed){ this.isPlaced = placed;}

    private void dragCard()
    {
        Input input = mGameScreen.getGame().getInput();

        if (input.getTouchEvents().size() > 0)
        {
            TouchEvent touchEvent = input.getTouchEvents().get(0);
            float xCardLastTouch = position.x;
            float yCardLastTouch = position.y;

            if (getBound().contains(touchEvent.x, touchEvent.y))
            {
                Log.d("Touch: ", Integer.toString(touchEvent.type));
                if (touchEvent.type == 6)
                {
                    isBeingDragged = true;
                    touchEvent.dx = touchEvent.x - xCardLastTouch;
                    touchEvent.dy = touchEvent.y - yCardLastTouch;

                    position.x += touchEvent.dx;
                    position.y += touchEvent.dy;
                }
            }
        }
    }

    @Override
    public void update(ElapsedTime elapsedTime){
        super.update(elapsedTime);
        dragCard();

        this.artwork.setPosition(
                this.getBound().getLeft() + this.getBound().getWidth(),
                this.getBound().getBottom() + this.getBound().getHeight()
                );
        this.artwork.update(elapsedTime);
    }

    @Override
    public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D){
        //super.draw(elapsedTime, graphics2D);

        this.artwork.draw(elapsedTime, graphics2D);
    }
}
