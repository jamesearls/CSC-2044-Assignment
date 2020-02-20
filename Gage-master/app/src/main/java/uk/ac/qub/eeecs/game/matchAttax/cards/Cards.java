package uk.ac.qub.eeecs.game.matchAttax.cards;

import android.graphics.Bitmap;

import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.util.BoundingBox;
import uk.ac.qub.eeecs.gage.util.GraphicsHelper;
import uk.ac.qub.eeecs.gage.util.Vector2;
import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.gage.world.LayerViewport;
import uk.ac.qub.eeecs.gage.world.ScreenViewport;
import uk.ac.qub.eeecs.gage.world.Sprite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class Cards extends Sprite{
    private static final int DEFAULT_CARD_HEIGHT = 100;
    private static final int DEFAULT_CARD_WIDTH = 50;

    public static final float DEFAULT_CARD_X = 0.0f;
    public static final float DEFAULT_CARD_Y = 0.0f;

    private final int amountOfCardsInDatabase = 40;


    private Bitmap cardPortrait;
    private int overallValue;
    private String league;
    private String club;
    private String firstName;
    private String surname;
    private Cards[] cardsInStore = new Cards[amountOfCardsInDatabase];
    private int totalScore;



    public Cards(float x, float y, GameScreen gameScreen, int overallValue, String league, String club, String firstName, String surname, Bitmap cardPortrait){
        super(x, y, DEFAULT_CARD_WIDTH, DEFAULT_CARD_HEIGHT, null, gameScreen);

        AssetManager assetManager = gameScreen.getGame().getAssetManager();
        String jsonToBeLoaded = "";
        try
        {
            jsonToBeLoaded = assetManager.getFileIO().loadJSON("txt/assets/Players.json");
        }
        catch(IOException ioEx)
        {
            System.out.println(ioEx.getMessage());
        }

        try
        {
            JSONObject players = new JSONObject(jsonToBeLoaded);

            for (int id = 0; id < amountOfCardsInDatabase; id++) {
                if(id == players.getInt("id"))
                {
                    overallValue = players.getInt("overall");
                    league = players.getString("league");
                    club = players.getString("team");
                    firstName = players.getString("fname");
                    surname = players.getString("sname");
                    //cardPortrait =
                    ////cardsInStore[id] = {x, y, DEFAULT_CARD_WIDTH, DEFAULT_CARD_HEIGHT, null, gameScreen, overallValue, league, club, firstName, surname, cardPortrait};
                }

            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }

        //calculating the total score and who wins the round
        totalScore += overallValue;
        //if (players score > AI score)
        // { player wins }
        //else
        // { AI wins }



    }


}


