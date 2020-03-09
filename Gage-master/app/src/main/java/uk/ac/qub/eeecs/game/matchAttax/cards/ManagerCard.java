package uk.ac.qub.eeecs.game.matchAttax.cards;

import android.graphics.Bitmap;

import org.json.JSONObject;

import java.io.IOException;

import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;

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
        super.setPosition(DEFAULT_CARD_X, DEFAULT_CARD_Y);
        super.getBound().halfHeight = DEFAULT_CARD_HEIGHT * 0.5f;
        super.getBound().halfWidth = DEFAULT_CARD_WIDTH * 0.5f;

        bitmap = getBitmap(this.cardPortraitPath);
        this.firstName = firstName;
        this.surname = surname;
        this.value = overallValue;
        this.type = type;
        this.artwork = new GameObject(
            this.getBound().getLeft() + this.getBound().getWidth(),
                this.getBound().getBottom() + this.getBound().getHeight(),
                this.getBound().getWidth(),
                this.getBound().getHeight(),
                bitmap, gameScreen);
    }

    public int getAmountOfCardsInDatabase() {
        return amountOfCardsInDatabase;
    }

    @Override
    public String getCardPortraitPath() {
        return cardPortraitPath;
    }

    @Override
    public void setCardPortraitPath(String cardPortraitPath) {
        this.cardPortraitPath = cardPortraitPath;
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public GameObject getArtwork() {
        return artwork;
    }

    public void setArtwork(GameObject artwork) {
        this.artwork = artwork;
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
