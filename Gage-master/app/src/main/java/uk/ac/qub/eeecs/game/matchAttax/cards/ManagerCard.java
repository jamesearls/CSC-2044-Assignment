package uk.ac.qub.eeecs.game.matchAttax.cards;

import android.graphics.Bitmap;

import org.json.JSONObject;

import java.io.IOException;

import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.gage.world.GameScreen;

public class ManagerCard extends Card {

    private final int amountOfCardsInDatabase = 7;
    private Bitmap cardPortrait;
    private int overallValue;
    private String firstName;
    private String surname;
    private int value;
    private boolean type; //Addition card or a multiplier (true = add // false = multiply)
    private Cards[] cardsInStore = new Cards[amountOfCardsInDatabase];

    public ManagerCard(GameScreen gameScreen, int overallValue, String firstName, String surname, Bitmap cardPortrait) {
        super(gameScreen, overallValue, firstName, surname, cardPortrait);

        AssetManager assetManager = gameScreen.getGame().getAssetManager();
        String jsonToBeAdded = "";

        try {
            jsonToBeAdded = assetManager.getFileIO().loadJSON("txt/assets/Managers.json");

        } catch (IOException ioEx) {
            System.out.println(ioEx.getMessage());
        }

        try {
            JSONObject manager = new JSONObject(jsonToBeAdded);

        //    if (value == FALSE)

        }catch(Exception ioEx){
            System.out.println(ioEx.getMessage());

        }

    }
}
