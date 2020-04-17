package uk.ac.qub.eeecs.game.matchAttax.screens;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import uk.ac.qub.eeecs.gage.Game;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.ScreenManager;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.engine.input.Input;
import uk.ac.qub.eeecs.gage.engine.input.TouchEvent;
import uk.ac.qub.eeecs.gage.engine.io.FileIO;
import uk.ac.qub.eeecs.gage.ui.PushButton;
import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.game.matchAttax.cards.ManagerCard;
import uk.ac.qub.eeecs.game.matchAttax.cards.PlayerCard;
import uk.ac.qub.eeecs.game.matchAttax.player.Deck;
import uk.ac.qub.eeecs.game.matchAttax.player.Player;
import uk.ac.qub.eeecs.gage.Game;

public class CardsScreen extends GameScreen  {

    public ArrayList<PlayerCard> mPlayerCards;
    public ArrayList<ManagerCard> mManagerCards;

    private float screenWidth = getGame().getScreenWidth(),
            screenHeight = getGame().getScreenHeight(),
            symbolXOffset = screenWidth * 0.125f,
            symbolYOffset = screenHeight * 0.125f;
    private AssetManager assetStore = getGame().getAssetManager();
    private Game gameObj1;
    private Bitmap background;
    private Deck playerDeck;
    private Player humanPlayer;
    private GameObject mBackground;
    private ScreenManager screenManager = getGame().getScreenManager();
    private PushButton homeButton, homeButtonPressed, settingsMenuButton, settingsMenuButtonPressed, leftArrow, rightArrow;

    int spacingX = (int)mDefaultLayerViewport.getWidth() / 5;
    int spacingY = (int)mDefaultLayerViewport.getHeight() / 3;

    public CardsScreen(Game game)
    {
        super("CardsScreen", game);

        loadAssets();

        assetStore.loadAndAddBitmap("menuBackground", "img/menuBackground.png");
        background = assetStore.getBitmap("menuBackground");

        mBackground = new GameObject(game.getScreenWidth()/2, game.getScreenHeight()/2, game.getScreenWidth(),
                game.getScreenHeight(), getGame().getAssetManager().getBitmap("menuBackground"), this);

        homeButton = new PushButton(
                spacingX * 0.23f, spacingY * 2.6f, spacingX*0.5f, spacingY*0.5f,
                "homeButton", "homeButtonPressed",this);
        homeButton.setPlaySounds(false, false);

        settingsMenuButton = new PushButton(
                spacingX * 0.75f, spacingY * 2.6f, spacingX*0.5f, spacingY*0.5f,
                "settingsMenuButton", "settingsMenuButtonPressed",this);
        settingsMenuButton.setPlaySounds(false, false);

         rightArrow = new PushButton(
                spacingX * 0.3f, spacingY * 1.6f, spacingX*0.5f, spacingY*0.5f,
                "leftArrow", "leftArrowPressed",this);
        rightArrow.setPlaySounds(false, false);

        leftArrow = new PushButton(
                spacingX * 4.7f, spacingY * 1.6f, spacingX*0.5f, spacingY*0.5f,
                "rightArrow", "rightArrowPressed",this);
        leftArrow.setPlaySounds(false, false);

    }

    private void loadAssets(){
        assetStore.loadAndAddBitmap("homeButton", "img/buttons/homeButton.png");
        assetStore.loadAndAddBitmap("homeButtonPressed", "img/buttons/homeButtonPressed.png");
        assetStore.loadAndAddBitmap("settingsMenuButton", "img/buttons/settingsMenuButton.png");
        assetStore.loadAndAddBitmap("settingsMenuButtonPressed", "img/buttons/settingsMenuButtonPressed.png");

        assetStore.loadAndAddBitmap("leftArrow", "img/buttons/leftArrow.png");
        assetStore.loadAndAddBitmap("rightArrow", "img/buttons/rightArrow.png");
        assetStore.loadAndAddBitmap("leftArrowPressed", "img/buttons/leftArrowPressed.png");
        assetStore.loadAndAddBitmap("rightArrowPressed", "img/buttons/rightArrowPressed.png");
    }




    public void onButtonPressed(){ }
    @Override
    public void update(ElapsedTime elapsedTime) {
        Input input = mGame.getInput();

        rightArrow.update(elapsedTime);
        leftArrow.update(elapsedTime);

        if (homeButton.isPushTriggered()){
            mGame.getScreenManager().addScreen(new MainMenu(mGame));
        }

        if(settingsMenuButton.isPushTriggered()) {
            mGame.getScreenManager().addScreen(new OptionsScreen(mGame));
        }

        if(leftArrow.isPushTriggered()){

        }

        if(rightArrow.isPushTriggered()){

        }

        // Get touch events
        List<TouchEvent> touchEvents = input.getTouchEvents();
        homeButton.update(elapsedTime);
        settingsMenuButton.update(elapsedTime);
        leftArrow.update(elapsedTime);
        rightArrow.update(elapsedTime);
        onButtonPressed();
    }

    @Override
    public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D)
    {
        graphics2D.drawBitmap(
                background,
                null,
                new Rect(0, 0, (int) screenWidth, (int) screenHeight),
                new Paint());

        homeButton.draw(elapsedTime, graphics2D, mDefaultLayerViewport, mDefaultScreenViewport);
        settingsMenuButton.draw(elapsedTime, graphics2D, mDefaultLayerViewport, mDefaultScreenViewport);
        leftArrow.draw(elapsedTime, graphics2D, mDefaultLayerViewport, mDefaultScreenViewport);
        rightArrow.draw(elapsedTime, graphics2D, mDefaultLayerViewport, mDefaultScreenViewport);
    }

    //David Mackenzie - read in manager details
    public void addCards2(String jsonFilePath)
    {
        FileIO mFileIO = mGame.getFileIO();
        String loadedJSON = "";
        try
        {
            loadedJSON = mFileIO.loadJSON(jsonFilePath);
        }
        catch(IOException ioEx)
        {
            System.out.println(ioEx.getMessage());
        }

        try
        {
            JSONArray cards = new JSONArray(loadedJSON);
            addManagerCards(cards, this);
        }
        catch(JSONException jEx)
        {
            System.out.println(jEx.getMessage());
        }
    }

    //David Mackenzie - parser for manager
    public void addManagerCards(JSONArray managers, GameScreen gameScreen)
    {
        try {
            for (int i = 0; i < managers.length(); i++) {
                JSONObject manager = managers.getJSONObject(i);
                ManagerCard managerCard = new ManagerCard(
                        gameScreen,
                        manager.getInt("value"),
                        manager.getString("firstName"),
                        manager.getString("surname"),
                        manager.getBoolean("type"),
                        manager.getString("portrait"));
                mManagerCards.add(managerCard);
            }
        }
        catch (JSONException jEx)
        {
            System.out.println(jEx.getMessage());
        }
    }

    //Pauric Donnelly
    public void addCards(String jsonFilePath)
    {
        FileIO mFileIO = mGame.getFileIO();
        String loadedJSON = "";
        try
        {
            loadedJSON = mFileIO.loadJSON(jsonFilePath);
        }
        catch(IOException ioEx)
        {
            System.out.println(ioEx.getMessage());
        }

        try
        {
            JSONArray cards = new JSONArray(loadedJSON);
            addPlayerCards(cards, this);
        }
        catch(JSONException jEx)
        {
            System.out.println(jEx.getMessage());
        }
    }
    //Pauric Donnelly
    public void addPlayerCards(JSONArray players, GameScreen gameScreen)
    {
        try {
            //JSONArray players = cards.getJSONArray("players");
            for (int i = 0; i < players.length(); i++) {
                JSONObject player = players.getJSONObject(i);
                PlayerCard playerCard = new PlayerCard(
                        gameScreen,
                        player.getInt("overall"),
                        player.getString("league"),
                        player.getString("team"),
                        player.getString("fname"),
                        player.getString("sname"),
                        player.getString("portrait"));
                mPlayerCards.add(playerCard);
            }

        }
        catch (JSONException jEx)
        {
            System.out.println(jEx.getMessage());
        }
    }

}
