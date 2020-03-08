package uk.ac.qub.eeecs.game.matchAttax.cards;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.IOException;

import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;

public abstract class Card extends GameObject {

    public static final int DEFAULT_CARD_HEIGHT = 100;
    public static final int DEFAULT_CARD_WIDTH = 50;

    public static final float DEFAULT_CARD_X = 0.0f;
    public static final float DEFAULT_CARD_Y = 0.0f;

    private String firstName;
    private String surname;
    private int overallValue;

    private String cardPortraitPath;
    private boolean isBeingDragged;
    private boolean isPlaced;
    private Bitmap bitmap;
    private GameObject artwork;

    public Card(GameScreen gameScreen, int overallValue, String firstName, String surname, String cardPortraitPath){
        super(gameScreen);
        super.setPosition(DEFAULT_CARD_X, DEFAULT_CARD_Y);
        super.getBound().halfHeight = DEFAULT_CARD_HEIGHT * 0.5f;
        super.getBound().halfWidth = DEFAULT_CARD_WIDTH * 0.5f;

        this.overallValue = overallValue;
        this.firstName = firstName;
        this.surname = surname;
        this.cardPortraitPath = cardPortraitPath;
        this.isBeingDragged = false;

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

    public int getOverallValue() {
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

    @Override
    public void update(ElapsedTime elapsedTime){
        super.update(elapsedTime);
    }

    @Override
    public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D){
        super.draw(elapsedTime, graphics2D);

        this.artwork.draw(elapsedTime, graphics2D);
    }
}
