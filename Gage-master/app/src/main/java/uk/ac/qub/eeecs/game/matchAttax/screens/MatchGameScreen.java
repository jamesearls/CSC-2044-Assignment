package uk.ac.qub.eeecs.game.matchAttax.screens;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uk.ac.qub.eeecs.gage.engine.AssetManager;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.engine.input.Input;
import uk.ac.qub.eeecs.gage.engine.input.TouchEvent;
import uk.ac.qub.eeecs.gage.engine.io.FileIO;
import uk.ac.qub.eeecs.gage.ui.PushButton;
import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.gage.world.LayerViewport;
import uk.ac.qub.eeecs.gage.world.ScreenViewport;
import uk.ac.qub.eeecs.game.matchAttax.cards.Card;
import uk.ac.qub.eeecs.game.matchAttax.cards.ManagerCard;
import uk.ac.qub.eeecs.game.matchAttax.cards.PlayerCard;
import uk.ac.qub.eeecs.game.matchAttax.player.Deck;
import uk.ac.qub.eeecs.game.matchAttax.player.Player;
import uk.ac.qub.eeecs.game.matchAttax.player.PlayerAI;

public class MatchGameScreen extends GameScreen {

    private String gameBackground;
    private GameObject mGameBoard;

    private GameObject cardBack;

    private ScreenViewport mScreenViewport;
    private LayerViewport mLayerViewport;

    private AssetManager assetStore = getGame().getAssetManager();

    private String playerName;
    private String aiName;

    private Player humanPlayer;
    private PlayerAI aiPlayer;

    private Deck playerDeck;
    private Deck aiDeck;

    private int playerScore;
    private int aiScore;
    private int roundNum;

    public ArrayList<PlayerCard> mPlayerCards;
    public ArrayList<ManagerCard> mManagerCards;
    private AssetManager assetManager;

    private PushButton homeButton, homeButtonPressed, settingsMenuButton, settingsMenuButtonPressed;

    public static final int AMOUNT_OF_PLAYER_CARDS = 40;
    public static final int AMOUNT_OF_MANAGER_CARDS = 7;

    public int getPlayerScore(){ return playerScore;}
    public int AiScore(){return aiScore;}

    public MatchGameScreen(Game game){
        super("MatchGameScreen", game);

        loadAssets();

        int spacingX = (int)mDefaultLayerViewport.getWidth() / 5;
        int spacingY = (int)mDefaultLayerViewport.getHeight() / 3;

        mScreenViewport = new ScreenViewport( 0,0, game.getScreenWidth(), game.getScreenHeight());
        mLayerViewport = new LayerViewport(mScreenViewport.centerX(),
                mScreenViewport.centerY(),
                mScreenViewport.width / 2,
                mScreenViewport.height / 2);

        mGameBoard = new GameObject(game.getScreenWidth() / 2.0f,
                game.getScreenHeight() / 2.0f,
                game.getScreenWidth(),
                game.getScreenHeight(),
                getGame().getAssetManager().getBitmap("gameBoard"),
                this);

        mPlayerCards = new ArrayList<PlayerCard>();
        mManagerCards = new ArrayList<ManagerCard>();
        addCards("txt/assets/Players.json");
        addCards2("txt/assets/Managers.json");

        assetStore.loadAndAddBitmap("homeButton", "img/buttons/homeButton.png");
        assetStore.loadAndAddBitmap("homeButtonPressed", "img/buttons/homeButtonPressed.png");
        assetStore.loadAndAddBitmap("settingsMenuButton", "img/buttons/settingsMenuButton.png");
        assetStore.loadAndAddBitmap("settingsMenuButtonPressed", "img/buttons/settingsMenuButtonPressed.png");

        homeButton = new PushButton(
                spacingX * 0.23f, spacingY * 2.6f, spacingX*0.5f, spacingY*0.5f,
                "homeButton", "homeButtonPressed",this);
        homeButton.setPlaySounds(false, false);

       settingsMenuButton = new PushButton(
                spacingX * 0.75f, spacingY * 2.6f, spacingX*0.5f, spacingY*0.5f,
                "settingsMenuButton", "settingsMenuButtonPressed",this);
        settingsMenuButton.setPlaySounds(false, false);


        playerName = "Player1";
        aiName = "Player2";

        playerDeck = new Deck(this);
        aiDeck = new Deck(this);

        humanPlayer = new Player(playerName, playerDeck);
        aiPlayer = new PlayerAI(aiName, aiDeck, this);

        int spacing = 385;
        for (int i=0; i<humanPlayer.getDeck().getCardsInDeck().size(); i++){
            humanPlayer.getDeck().getCardsInDeck().get(i).setPosition(humanPlayer.getDeck().getCardsInDeck().get(i).getBound().x+=spacing,game.getScreenHeight()-255);
            spacing += 200;
        }

        for (int i=0; i<aiPlayer.getDeck().getCardsInDeck().size(); i++){
            aiPlayer.getDeck().getCardsInDeck().get(i).setPosition(game.getScreenWidth()-100,155);
        }
        cardBack = new GameObject(
                game.getScreenWidth()-100,
                155,
                Card.DEFAULT_CARD_WIDTH,
                Card.DEFAULT_CARD_HEIGHT,
                getGame().getAssetManager().getBitmap("cardBack"),
                this
                );

        playerScore = 0;
        aiScore = 0;
        roundNum = 1;

        humanPlayer.setEndTurn(false);

    }

    public void onButtonPressed(){

    }
    //David Mackenzie
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
    //David Mackenzie
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

    public ArrayList<ManagerCard> getManagerCardList()
    {
        return mManagerCards;
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

    public ArrayList<PlayerCard> getPlayerCardList()
    {
        return mPlayerCards;
    }

    public Player getHumanPlayer(){ return humanPlayer; }
    public Player getAiPlayer(){ return aiPlayer; }


    public PlayerCard getRandomPlayerCard(){
        Random random = new Random();
        int num = random.nextInt(AMOUNT_OF_PLAYER_CARDS);
        PlayerCard card = mPlayerCards.get(num);

        return card;
    }

    public Card getRandomManagerCard(){
        Random random = new Random();
        int num = random.nextInt(AMOUNT_OF_MANAGER_CARDS);
        ManagerCard card = mManagerCards.get(num);

        return card;
    }

    //Bronach Falls
    private void loadAssets(){
        loadBitmaps("gameBoard", "img/gameBoard.png");
        loadBitmaps("cardBack", "img/cardBack.png");

        loadMusic("ChelseaDagger", "sound/ChelseaDagger.mp3");
        loadMusic("FluorescentAdolescent", "sound/FluorescentAdolescent.mp3");
        loadMusic("SevenNationArmy", "sound/SevenNationArmy.mp3");
        loadMusic("WavinFlag", "sound/WavinFlag.mp3");
        loadMusic("WhatYouKnow", "sound/WhatYouKnow.mp3");
        //loadMusic("GuitarMusic", "sound/GuitarMusic.mp3");
    }

    private void loadMusic(String assetName, String fileName){
        getGame().getAssetManager().loadAndAddMusic(assetName, fileName);
    }

    private void loadBitmaps(String assetName, String fileName){
        getGame().getAssetManager().loadAndAddBitmap(assetName, fileName);
    }

    public void update(ElapsedTime elapsedTime){

        Input input = mGame.getInput();
        //Prevents more than 1 card from being dragged (Reference - CardDefence)
        boolean dragged =false;
        for (Card card: getHumanPlayer().getDeck().getCardsInDeck()) {
            if (card.getBeingDragged()) {
                dragged=true;
            }
        }

        // drag card (Reference - Card Defence)
        for (int i = 0; i<getHumanPlayer().getDeck().getCardsInDeck().size(); i++ ){
            Card card = getHumanPlayer().getDeck().getCardsInDeck().get(i);
            if(!card.getPlaced()) {
                if(!dragged || card.getBeingDragged()) {
                    card.update(elapsedTime);
                    if (card.getBeingDragged())
                    {
                        dragged = true;
                    }
                }
            }
            else{
                card.setBeingDragged(false);
            }
        }
        if (homeButton.isPushTriggered()){
            mGame.getScreenManager().addScreen(new MainMenu(mGame));
        }

        //settings menu button wil act as a pause button
       if(settingsMenuButton.isPushTriggered()) {
            mGame.getScreenManager().addScreen(new OptionsScreen(mGame));
            mGame.onPause();
        }

        // Get touch events
        List<TouchEvent> touchEvents = input.getTouchEvents();
        homeButton.update(elapsedTime);
        settingsMenuButton.update(elapsedTime);
        onButtonPressed();
    }

    public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D){

        graphics2D.clear(Color.WHITE);

        mGameBoard.draw(elapsedTime, graphics2D);

        Input input = this.getGame().getInput();
        int n = -1;
        // draw cards not being dragged first
        for(int i = 0; i< getHumanPlayer().getDeck().getCardsInDeck().size(); i++) {
            if (!getHumanPlayer().getDeck().getCardsInDeck().get(i).getPlaced()) {
                if (!getHumanPlayer().getDeck().getCardsInDeck().get(i).getBeingDragged()) {
                    getHumanPlayer().getDeck().getCardsInDeck().get(i).draw(elapsedTime, graphics2D);
                }
            }
        }
        homeButton.draw(elapsedTime, graphics2D, mDefaultLayerViewport, mDefaultScreenViewport);
        settingsMenuButton.draw(elapsedTime, graphics2D, mDefaultLayerViewport, mDefaultScreenViewport);
        cardBack.draw(elapsedTime, graphics2D);

    }



}
