package uk.ac.qub.eeecs.game.matchAttax.cards;

import android.graphics.Bitmap;

import org.json.JSONObject;

import java.io.IOException;

import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;
//David Mackenzie 40238376
public class ManagerCard extends Card {

    private final int amountOfCardsInDatabase = 7;
    private String cardPortraitPath;
    private Bitmap bitmap;
    private int value;

    private String firstName;
    private String surname;
    private boolean type; //Addition card or a multiplier (true = add // false = multiply)
//    private Cards[] cardsInStore = new Cards[amountOfCardsInDatabase];
    private GameObject artwork;
  //  private int managerScore;

    public ManagerCard(GameScreen gameScreen, int overallValue, String firstName, String surname, boolean type, String cardPortraitPath) {
        super(gameScreen, overallValue, firstName, surname, cardPortraitPath);
        this.type = type;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    @Override
    public void update(ElapsedTime elapsedTime){
        super.update(elapsedTime);
    }

    @Override
    public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D){
        super.draw(elapsedTime, graphics2D);
    }
}
