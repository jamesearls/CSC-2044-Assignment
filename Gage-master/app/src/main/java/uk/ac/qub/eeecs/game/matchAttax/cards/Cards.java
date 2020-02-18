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
import java.util.ArrayList;


public class Cards extends Sprite{
    private static final int DEFAULT_CARD_HEIGHT = 100;
    private static final int DEFAULT_CARD_WIDTH = 50;

    private final int amountOfCardsInDatabase = 40;

    private Cards card;
    private String cardPortraitFilepath;
    private int overallValue;
    private String league;
    private String club;
    private String firstName;
    private String surname;
    private ArrayList<Cards> cardBank;



    public Cards(float x, float y, GameScreen gameScreen, int overallValue, String league, String club, String firstName, String surname, String cardPortraitFilepath){
        super(x, y, DEFAULT_CARD_WIDTH, DEFAULT_CARD_HEIGHT, null, gameScreen);

        AssetManager assetManager = gameScreen.getGame().getAssetManager();
        String jsonToBeLoaded = "";
        cardBank = new ArrayList<Cards>();
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

            for(int id = 0; id < amountOfCardsInDatabase; id++)
            {
                if(id == players.getInt("id"))
                {
                    overallValue = players.getInt("overall");
                    league = players.getString("league");
                    club = players.getString("club");
                    firstName = players.getString("fname");
                    surname = players.getString("sname");
                    cardPortraitFilepath = players.getString("portrait");
                    card = new Cards(x, y, gameScreen, overallValue, league, club, firstName, surname, cardPortraitFilepath);
                    cardBank.add(card);
                }
            }


        }
        catch(JSONException jEx)
        {
            System.out.println(jEx.getMessage());
        }
    }


}


