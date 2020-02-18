package uk.ac.qub.eeecs.game.matchAttax.cards;

import android.graphics.Bitmap;

import org.json.JSONObject;

import java.io.IOException;

import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;

public class Card extends GameObject {

    protected static final int DEFAULT_CARD_HEIGHT = 100;
    protected static final int DEFAULT_CARD_WIDTH = 50;

    public static final float DEFAULT_CARD_X = 0.0f;
    public static final float DEFAULT_CARD_Y = 0.0f;

    private Bitmap cardPortrait;
    private String firstName;
    private String surname;
    private int overallValue;

    public Card(GameScreen gameScreen, int overallValue, String firstName, String surname, Bitmap cardPortrait){
        super(gameScreen);


    }

    public int getOverallValue(){
        return overallValue;
    }
}
