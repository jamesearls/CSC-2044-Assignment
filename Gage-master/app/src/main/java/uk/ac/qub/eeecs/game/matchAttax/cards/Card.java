package uk.ac.qub.eeecs.game.matchAttax.cards;

import android.graphics.Bitmap;

import org.json.JSONObject;

import java.io.IOException;

import uk.ac.qub.eeecs.gage.engine.AssetManager;
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

    public Card(GameScreen gameScreen, int overallValue, String firstName, String surname, String cardPortraitPath){
        super(gameScreen);

        this.overallValue = overallValue;
        this.firstName = firstName;
        this.firstName = surname;
        this.cardPortraitPath = cardPortraitPath;
        this.isBeingDragged = false;
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

    public void setCardPortraitPath(String cardPortraitPath) {
        this.cardPortraitPath = cardPortraitPath;
    }

    public boolean getBeingDragged() {return  isBeingDragged;}
    public void setBeingDragged(boolean dragged){ this.isBeingDragged = dragged;}

    public boolean getPlaced() {return  isPlaced;}
    public void setPlaced(boolean placed){ this.isPlaced = placed;}
}
