package uk.ac.qub.eeecs.game.matchAttax.cards;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.IOException;

import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.engine.input.Input;
import uk.ac.qub.eeecs.gage.engine.input.TouchEvent;
import uk.ac.qub.eeecs.gage.util.BoundingBox;
import uk.ac.qub.eeecs.gage.util.Vector2;
import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.gage.world.LayerViewport;
import uk.ac.qub.eeecs.gage.world.ScreenViewport;
import uk.ac.qub.eeecs.gage.world.Sprite;

public abstract class Card extends Sprite {

    public static final int DEFAULT_CARD_HEIGHT = 180;
    public static final int DEFAULT_CARD_WIDTH = 120;

    public static final float DEFAULT_CARD_X = 0.0f;
    public static final float DEFAULT_CARD_Y = 0.0f;

    public static final float DEFAULT_BOARD_X = 620f;
    public static final float DEFAULT_BOARD_Y = 700f;

    private String firstName;
    private String surname;
    private double overallValue;

    private String cardPortraitPath;
    private boolean isBeingDragged;
    private boolean isPlaced;
    private Bitmap bitmap;
    private GameObject artwork;

    private Vector2 mTargetLocation = new Vector2();
    private Vector2 centre = new Vector2();

    //Adam Kennedy
    public Card(GameScreen gameScreen, double overallValue, String firstName, String surname, String cardPortraitPath){
        super(gameScreen);
        super.setPosition(DEFAULT_CARD_X, DEFAULT_CARD_Y);
        super.getBound().halfHeight = DEFAULT_CARD_HEIGHT * 0.5f;
        super.getBound().halfWidth = DEFAULT_CARD_WIDTH * 0.5f;

        this.overallValue = overallValue;
        this.firstName = firstName;
        this.surname = surname;
        this.cardPortraitPath = cardPortraitPath;
        this.isBeingDragged = false;

        this.setPosition(DEFAULT_CARD_X, DEFAULT_CARD_Y);
        this.setHeight(DEFAULT_CARD_HEIGHT);
        this.setWidth(DEFAULT_CARD_WIDTH);

        setTargetLocation(position.x, position.y);

        bitmap = getBitmap(this.cardPortraitPath);
        super.setBitmap(bitmap);

        this.artwork = new GameObject(
                this.getBound().getLeft() + this.getBound().getWidth(),
                this.getBound().getBottom() + this.getBound().getHeight(),
                this.getBound().getWidth(),
                this.getBound().getHeight(),
                bitmap,
                gameScreen);
    }

    //copy constructor
    public Card(Card card){
        super(card.getGameScreen());
        super.setPosition(card.position.x, card.position.y);
        super.getBound().halfHeight = card.getBound().halfHeight;
        super.getBound().halfWidth = card.getBound().halfWidth;

        this.setPosition(card.position.x, card.position.y);
        this.setHeight(card.getHeight());
        this.setWidth(card.getWidth());

        this.overallValue = card.getOverallValue();
        this.firstName = card.getFirstName();
        this.surname = card.getSurname();
        this.cardPortraitPath = card.getCardPortraitPath();
        this.isBeingDragged = card.getBeingDragged();

        setTargetLocation(position.x, position.y);

        bitmap = getBitmap(this.cardPortraitPath);
        super.setBitmap(bitmap);

        this.artwork = new GameObject(
                this.getBound().getLeft() + this.getBound().getWidth(),
                this.getBound().getBottom() + this.getBound().getHeight(),
                this.getBound().getWidth(),
                this.getBound().getHeight(),
                bitmap,
                card.getGameScreen());
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

    public double getOverallValue() {
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

    public Vector2 getTargetLocation(){ return mTargetLocation; }

    public void setTargetLocation(float x, float y) { mTargetLocation = new Vector2(x, y);}

    private void moveCard()
    {
        Input input = mGameScreen.getGame().getInput();

        if (input.getTouchEvents().size() > 0)
        {
            TouchEvent touchEvent = input.getTouchEvents().get(0);
            float xCardLastTouch = position.x;
            float yCardLastTouch = position.y;

            if (getBound().contains(touchEvent.x, touchEvent.y)){
                setBeingDragged(true);
                setTargetLocation(DEFAULT_BOARD_X, DEFAULT_BOARD_Y);
            }

            if (Math.abs(position.x - getTargetLocation().x) < 10 && Math.abs(position.y - getTargetLocation().y) < 10 && getBeingDragged()){
                setPlaced(true);
                setBeingDragged(false);
            }

            keepInBounds();
        }
    }

    private void aiMoveCard(){
        setBeingDragged(true);
        setTargetLocation(Card.DEFAULT_BOARD_X, Card.DEFAULT_BOARD_Y-350);

        if (Math.abs(position.x - getTargetLocation().x) < 10 && Math.abs(position.y - getTargetLocation().y) < 10 && getBeingDragged()){
            setPlaced(true);
            setBeingDragged(false);
        }

        keepInBounds();
    }

    private void keepInBounds()
    {
        if (getBound().getBottom() < 0)
        {
            position.y = position.y - getBound().getBottom();
        }
        else if (getBound().getTop() > mGameScreen.getGame().getScreenHeight())
        {
            position.y = position.y - (getBound().getTop() - mGameScreen.getGame().getScreenHeight());
        }
        if (getBound().getLeft() < 0)
        {
            position.x = position.x - getBound().getLeft();
        }
        else if (getBound().getRight() > mGameScreen.getGame().getScreenWidth()) {
            position.x = position.x - (getBound().getRight() - mGameScreen.getGame().getScreenWidth());
        }
    }

    public void update(ElapsedTime elapsedTime, boolean ai){
        maxAcceleration = 300.0f;
        maxVelocity = 300.0f;

        super.update(elapsedTime);
        if (!ai){ moveCard();}
        else { aiMoveCard();}

        this.artwork.setPosition(
                this.getBound().getLeft() + this.getBound().getWidth()/2,
                this.getBound().getBottom() + this.getBound().getHeight()/2
        );
        this.artwork.update(elapsedTime);
    }

    @Override
    public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D){
        super.draw(elapsedTime, graphics2D);

        this.artwork.draw(elapsedTime, graphics2D);
    }
}