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




public class Cards extends Sprite{
    private static final int DEFAULT_CARD_HEIGHT = 100;
    private static final int DEFAULT_CARD_WIDTH = 50;

    private final int amountOfCardsInDatabase = 40;


    private Bitmap cardPortrait;
    private int overallValue;
    private String league;
    private String club;
    private String firstName;
    private String surname;
    private Cards[] cardsInStore = new Cards[amountOfCardsInDatabase];



    public Cards(float x, float y, GameScreen gameScreen, int overallValue, String league, String club, String firstName, String surname, Bitmap cardPortrait){
        super(x, y, DEFAULT_CARD_WIDTH, DEFAULT_CARD_HEIGHT, null, gameScreen);


            AssetManager assetManager = gameScreen.getGame().getAssetManager();
            assetManager.loadAssets("txt/assets/Players.JSON");

            for (int i = 0; i < amountOfCardsInDatabase; i++) {
                //Below is comments of the code im trying to achieve
                /*overallValue = assetManager.load___("overall");
                league = assetManager.load___("league");
                club = assetManager.load___("team")
                firstName = assetManager.load____("fname");
                surname = assetManager.load_____("sname");
                cardPortrait = assetManager.loadBitmap("portrait");
                cardsInStore[i] = {overallValue, league, club, firstName, surname, cardPortrait};

                 */
            }




    }
}


