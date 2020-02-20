package uk.ac.qub.eeecs.game.matchAttax.cards;

import android.graphics.Bitmap;

import org.json.JSONObject;

import java.io.IOException;

import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.gage.world.GameScreen;

public class ManagerCard extends Card {

    private final int amountOfCardsInDatabase = 7;
    private int value;
    private String firstName;
    private String surname;
    private boolean type; //Addition card or a multiplier (true = add // false = multiply)
    private Cards[] cardsInStore = new Cards[amountOfCardsInDatabase];

    public ManagerCard(GameScreen gameScreen, int overallValue, String firstName, String surname, String cardPortraitPath) {
        super(gameScreen, overallValue, firstName, surname, cardPortraitPath);

        AssetManager assetManager = gameScreen.getGame().getAssetManager();
        String jsonToBeAdded = "txt/assets/Managers.json";
        String ManagerJSON = "";

        try {
            ManagerJSON = assetManager.getFileIO().loadJSON(jsonToBeAdded);

        } catch (Exception ioEx) {
            System.out.println(ioEx.getMessage());
        }

        try {
            JSONObject manager = new JSONObject(ManagerJSON);

            //Check through Database
            for (int i = 0; i < amountOfCardsInDatabase; i++) {
                if (i == manager.getInt("i")){
                    value = manager.getInt("value");
                    firstName = manager.getString("firstName");
                    surname = manager.getString("surname");
                    type = manager.getBoolean("type");
                }
            }

            //if (value == FALSE){
            //value * card total 
            //else if (Value == TRUE
            //value + card total

        }catch(Exception ioEx){
            System.out.println(ioEx.getMessage());

        }

    }
}
